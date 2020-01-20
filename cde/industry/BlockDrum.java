/**
 *
 * @author StormTiberius
 */

package cde.industry;

import buildcraft.api.tools.IToolWrench;
import cde.CDECore;
import cde.IndustryCore;
import cde.core.Defaults;
import ic2.api.IPaintableBlock;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.src.ModLoader;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.oredict.OreDictionary;

public class BlockDrum extends BlockContainer implements IPaintableBlock
{
    public BlockDrum(int id)
    {
        super(id, Material.iron);
        
        setBlockBounds(0.075F, 0.0F, 0.075F, 0.925F, 1.0F, 0.925F);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if(world.isRemote)
        {
            boolean flag = true;
            
            if(player != null && player.getHeldItem() != null && player.getHeldItem().getItem() != null)
            {
                flag = !player.getHeldItem().getItem().getClass().getCanonicalName().equalsIgnoreCase("ic2.core.item.tool.ItemToolPainter");
            }
            
            return flag;
        }
        else
        {
            if(player != null)
            {
                TileEntity te = world.getBlockTileEntity(x, y, z);
                ItemStack held = player.getHeldItem();

                if(te instanceof TileEntityDrum && held != null)
                {
                    TileEntityDrum ted = (TileEntityDrum)te;

                    if(held.getItem() instanceof IToolWrench)
                    {
                        return taskWrench(world, x, y, z, player, held, ted);
                    }
                    else if(held.itemID == Item.stick.itemID)
                    {
                        return taskStick(player, ted);
                    }
                    else if(LiquidContainerRegistry.isEmptyContainer(held))
                    {
                        return taskEmptyContainer(player, held, ted);
                    }
                    else if(LiquidContainerRegistry.isFilledContainer(held))
                    {
                        return taskFilledContainer(player, held, ted);
                    }
                    else if(held.itemID == Item.paper.itemID)
                    {
                        return taskLabel(player, held, ted);
                    }
                    else if(held.itemID == IndustryCore.getSealant().itemID)
                    {
                        return taskSealant(player, held, ted);
                    }
                    else if(isDye(held))
                    {
                        return taskDye(player, held, ted);
                    }
                }
            }
        }
        
        return false;
    }
    
    private boolean isDye(ItemStack held)
    {
        String name = OreDictionary.getOreName(OreDictionary.getOreID(held));
        
        for(String s : Defaults.DYE_ORE_DICTIONARY_NAMES)
        {
            if(name.contentEquals(s))
            {
                return true;
            }
        }
        
        return false;
    }
    
    private boolean taskWrench(World world, int x, int y, int z, EntityPlayer player, ItemStack held, TileEntityDrum ted)
    {
        IToolWrench tool = (IToolWrench)held.getItem();

        if(tool.canWrench(player, x, y, z))
        {
            tool.wrenchUsed(player, x, y, z);
            
            if(!player.isSneaking())
            {
                dropBlockAsItem(world, x, y, z, 0, 0);
                world.setBlockWithNotify(x, y, z, 0);
            }
            else
            {
                player.sendChatToPlayer(ted.useWrench(false));
            }

            return true;
        }
        
        return false;
    }

    private boolean taskStick(EntityPlayer player, TileEntityDrum ted)
    {
        StringBuilder sb = new StringBuilder("This ");
        
        ILiquidTank tank = ted.getTank(ForgeDirection.UP, null);
        
        if(tank != null)
        {
            int capacity = tank.getCapacity();
            
            switch(capacity)
            {
                case Defaults.DRUM_CAPACITY_IRON: sb.append("iron"); break;
                case Defaults.DRUM_CAPACITY_STEEL: sb.append("steel"); break;
                default: sb.append("UNKNOWN"); break;
            }
            
            sb.append(" drum is ");
            
            LiquidStack liquid = tank.getLiquid();
            
            if(liquid != null)
            {
                int amount = liquid.amount;
                int percent = amount * 100 / capacity;
            
                sb.append(percent);
                sb.append("% full and has ");
                
                if(amount < LiquidContainerRegistry.BUCKET_VOLUME)
                {
                    sb.append(amount);
                    sb.append(" drop");
                    
                    if(amount > 1)
                    {
                        sb.append("s");
                    }
                }
                else
                {
                    int buckets = amount / LiquidContainerRegistry.BUCKET_VOLUME;
    
                    sb.append(buckets);
                    sb.append(" bucket");
                    
                    if(buckets > 1)
                    {
                        sb.append("s");
                    }
                }
                
                sb.append(" of ");
                
                ItemStack is = liquid.asItemStack();
                
                if(is != null)
                {    
                    sb.append(is.getDisplayName().toLowerCase());
                }
                
                sb.append(".");
            }
            else
            {
                sb.append("empty.");
            }
        }
        else
        {
            sb = new StringBuilder("ERROR: TANK IS NULL!");
        }
        
        int i = ted.getFillPercentage();
        
        if(i > 0)
        {
            ted.makeParticles("splash", 3);
        }
        
        player.sendChatToPlayer(sb.toString());
    
        return true;
    }

    private boolean taskEmptyContainer(EntityPlayer player, ItemStack held, TileEntityDrum ted)
    {
        ItemStack filled = LiquidContainerRegistry.fillLiquidContainer(ted.getTank(ForgeDirection.UP, null).getLiquid(), held);
        
        if(filled != null)
        {
            int maxDrain = LiquidContainerRegistry.getLiquidForFilledItem(filled).amount;
            
            if(player.capabilities.isCreativeMode)
            {
                ted.drain(ForgeDirection.UP, maxDrain, true);
                
                return true;
            }
            else if(held.stackSize == 1)
            {
                player.setCurrentItemOrArmor(0, filled);
                ted.drain(ForgeDirection.UP, maxDrain, true);
                
                return true;
            }
            else if(player.inventory.addItemStackToInventory(filled))
            {
                held.stackSize--;
                ted.drain(ForgeDirection.UP, maxDrain, true);
                
                return true;
            }
        }
        
        return false;
    }

    private boolean taskFilledContainer(EntityPlayer player, ItemStack held, TileEntityDrum ted)
    {
        LiquidStack liquid = LiquidContainerRegistry.getLiquidForFilledItem(held);
        
        if(ted.fill(ForgeDirection.UP, liquid, false) == liquid.amount)
        {
            if(!player.capabilities.isCreativeMode)
            {
                ItemStack c = null;
                
                if(held.getItem().hasContainerItem())
                {
                    c = held.getItem().getContainerItemStack(held);
                }
                
                if(c == null || held.stackSize == 1 || player.inventory.addItemStackToInventory(c))
                {
                    if(held.stackSize == 1)
                    {
                        player.setCurrentItemOrArmor(0, c);
                    }
                    else if(held.stackSize > 1)
                    {
                        held.stackSize--;
                    }
                }
                else
                {
                    return false;
                }
            }
            
            ted.fill(ForgeDirection.UP, liquid, true);
            
            return true;
        }
        
        return false;
    }
    
    private boolean taskLabel(EntityPlayer player, ItemStack held, TileEntityDrum ted)
    {
        LiquidStack liquid = ted.getTank(ForgeDirection.UP, null).getLiquid();
        
        if(liquid != null && ted.applyPaintCoating(IndustryCore.getLiquidColor(liquid.itemID)))
        {
            if(!player.capabilities.isCreativeMode)
            {
                if(held.stackSize == 1)
                {
                    player.setCurrentItemOrArmor(0, null);
                }
                else if(held.stackSize > 1)
                {
                    held.stackSize--;
                }
            }
            
            return true;
        }
        
        return false;
    }
    
    private boolean taskSealant(EntityPlayer player, ItemStack held, TileEntityDrum ted)
    {
        if(held.stackSize >= 4 || player.capabilities.isCreativeMode)
        {
            if(ted.applySealant())
            {
                if(!player.capabilities.isCreativeMode)
                {
                    if(held.stackSize == 4)
                    {
                        player.setCurrentItemOrArmor(0, null);
                    }
                    else if(held.stackSize > 4)
                    {
                        held.stackSize -= 4;
                    }
                }

                return true;
            }
        }
        
        return false;
    }
    
    private boolean taskDye(EntityPlayer player, ItemStack held, TileEntityDrum ted)
    {
        if(ModLoader.isModLoaded("IC2") || ModLoader.isModLoaded("RedPowerCore"))
        {
            return false;
        }
        
        String name = OreDictionary.getOreName(OreDictionary.getOreID(held));
        
        int index = 0;
        
        for(String s : Defaults.DYE_ORE_DICTIONARY_NAMES)
        {
            if(name.contentEquals(s))
            {
                break;
            }
            else
            {
                index++;
            }
        }
        
        if(index > -1 && index < 16)
        {
            if(ted.setDrumColor(index))
            {
                if(!player.capabilities.isCreativeMode)
                {
                    if(held.stackSize == 1)
                    {
                        player.setCurrentItemOrArmor(0, null);
                    }
                    else if(held.stackSize > 1)
                    {
                        held.stackSize--;
                    }
                }
                
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    @Override
    public int getRenderType()
    {
        return IndustryCore.getDrumRenderId();
    }
    
    @Override
    public int colorMultiplier(IBlockAccess iba, int x, int y, int z)
    {
        TileEntity te = iba.getBlockTileEntity(x, y, z);
        
        if(te instanceof TileEntityDrum)
        {   
            return ((TileEntityDrum)te).getDrumColor().getRGB();
        }
        
        return IndustryCore.getPaintColor().getRGB();
    }
    
    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileEntityDrum();
    }
    
    @Override
    public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z)
    {
        if(!player.capabilities.isCreativeMode && canHarvestBlock(player, world.getBlockMetadata(x, y, z)))
        {
            ArrayList<ItemStack> items = getBlockDropped(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            
            if(world.setBlockWithNotify(x, y, z, 0))
            {
                if(!world.isRemote)
                {
                    for(ItemStack is : items)
                    {
                        if(is.hasTagCompound())
                        {
                            NBTTagCompound tag = is.getTagCompound();
                            
                            if(tag.hasKey("liquid"))
                            {
                                tag.removeTag("liquid");
                            }
                            
                            tag.setInteger("color", IndustryCore.getPaintColor().getRGB());
                            tag.setInteger("paint", -1);
                            tag.setBoolean("sealant", false);
                            
                            if(tag.hasKey("capacity"))
                            {
                                is.setItemDamage(getDamageValue(0, tag.getInteger("capacity"), is.getMaxDamage()));
                            }
                        }
                        
                        dropBlockAsItem_do(world, x, y, z, is);
                    }
                }
                
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return super.removeBlockByPlayer(world, player, x, y, z);
        }
    }
    
    @Override
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int metadata)
    {
        player.addStat(StatList.mineBlockStatArray[blockID], 1);
        player.addExhaustion(0.025F);
    }
    
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity)
    {
        TileEntity te = world.getBlockTileEntity(x, y, z);
        
        if(te instanceof TileEntityDrum && entity != null)
        {
            ItemStack is = entity.getHeldItem();
            
            if(is != null && is.hasTagCompound())
            {
                NBTTagCompound note = is.getTagCompound();
                NBTTagCompound tag = new NBTTagCompound();
                
                te.writeToNBT(tag);
                
                if(note.hasKey("capacity"))
                {
                    tag.setInteger("capacity", note.getInteger("capacity"));
                    
                    if(note.hasKey("liquid"))
                    {
                        tag.setCompoundTag("liquid", note.getCompoundTag("liquid"));
                    }
                }
                
                if(note.hasKey("color"))
                {
                    tag.setInteger("color", note.getInteger("color"));
                }
                
                if(note.hasKey("paint"))
                {
                    tag.setInteger("paint", note.getInteger("paint"));
                }
                
                if(note.hasKey("sealant"))
                {
                    tag.setBoolean("sealant", note.getBoolean("sealant"));
                }
                
                te.readFromNBT(tag);
            }
        }
    }
 
    @Override
    public void getSubBlocks(int id, CreativeTabs tab, List list)
    {
        ItemStack drum;
        
        for(int i = 0; i < 2; i++)
        {
            int capacity;
            
            switch(i)
            {
                case 0: capacity = Defaults.DRUM_CAPACITY_IRON; break;
                case 1: capacity = Defaults.DRUM_CAPACITY_STEEL; break;
                default: capacity = Defaults.DRUM_CAPACITY_IRON; break;
            }
            
            for(int j = -1; j < 16; j++)
            {
                drum = new ItemStack(id, 1, 0);

                if(!drum.hasTagCompound())
                {
                    drum.setTagCompound(new NBTTagCompound());
                }

                if(drum.getTagCompound().getName().isEmpty())
                {
                    drum.getTagCompound().setName("tag");
                }
                
                drum.getTagCompound().setInteger("capacity", capacity);
                drum.getTagCompound().setInteger("color", IndustryCore.getPaintColor(j).getRGB());
                drum.getTagCompound().setInteger("paint", j);
                drum.getTagCompound().setBoolean("sealant", true);

                drum.setItemDamage(getDamageValue(0, capacity, drum.getMaxDamage()));

                list.add(drum);
            }

            for(Map.Entry<String, LiquidStack> entry : LiquidDictionary.getLiquids().entrySet())
            {
                LiquidStack liquid = entry.getValue();

                if(liquid != null)
                {
                    liquid.amount = capacity;

                    NBTTagCompound tag = liquid.writeToNBT(new NBTTagCompound());

                    drum = new ItemStack(id, 1, 0);

                    if(!drum.hasTagCompound())
                    {
                        drum.setTagCompound(new NBTTagCompound());
                    }
                    
                    if(drum.getTagCompound().getName().isEmpty())
                    {
                        drum.getTagCompound().setName("tag");
                    }
  
                    drum.getTagCompound().setInteger("capacity", capacity);
                    drum.getTagCompound().setTag("liquid", tag);
                    drum.getTagCompound().setInteger("color", IndustryCore.getLiquidColor(liquid.itemID).getRGB());
                    drum.getTagCompound().setInteger("paint", -2);
                    drum.getTagCompound().setBoolean("sealant", true);

                    drum.setItemDamage(getDamageValue(capacity, capacity, drum.getMaxDamage()));

                    list.add(drum);
                }
            }
        }
    }
    
    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
        return getBlockDropped(world, x, y, z, world.getBlockMetadata(x, y, z), 0).get(0);
    }
    
    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        
        ItemStack drum = new ItemStack(blockID, 1, 0);
                    
        if(!drum.hasTagCompound())
        {
           drum.setTagCompound(new NBTTagCompound());
        }
        
        if(drum.getTagCompound().getName().isEmpty())
        {
            drum.getTagCompound().setName("tag");
        }
  
        TileEntity te = world.getBlockTileEntity(x, y, z);
        
        if(te instanceof TileEntityDrum)
        {
            NBTTagCompound tag = new NBTTagCompound();
            
            te.writeToNBT(tag);    
            
            if(tag.hasKey("capacity"))
            {
                int capacity = tag.getInteger("capacity");
                
                drum.getTagCompound().setInteger("capacity", capacity);
                
                if(tag.hasKey("liquid"))
                {
                    NBTTagCompound liquid = tag.getCompoundTag("liquid");
                    
                    drum.getTagCompound().setCompoundTag("liquid", liquid);
                    
                    if(liquid.hasKey("Amount"))
                    {
                        drum.setItemDamage(getDamageValue(liquid.getInteger("Amount"), capacity, drum.getMaxDamage()));
                    }
                }
                else
                {
                    drum.setItemDamage(getDamageValue(0, capacity, drum.getMaxDamage()));
                }
            }
            
            if(tag.hasKey("color"))
            {
                drum.getTagCompound().setInteger("color", tag.getInteger("color"));
            }

            if(tag.hasKey("paint"))
            {
                drum.getTagCompound().setInteger("paint", tag.getInteger("paint"));
            }
            
            if(tag.hasKey("sealant"))
            {
                drum.getTagCompound().setBoolean("sealant", tag.getBoolean("sealant"));
            }
            
            list.add(drum);
        }
        
        return list;
    }
    
    @Override
    public int getBlockTextureFromSideAndMetadata(int side, int metadata)
    {
        if(side == 0 || side == 1)
        {
            switch(metadata)
            {
                case 0: return Defaults.TEXTURE_DRUM_IRON_TOP;
                case 1: return Defaults.TEXTURE_DRUM_STEEL_TOP;
                default: return 0;
            }
        }
        
        switch(metadata)
        {
            case 0: return Defaults.TEXTURE_DRUM_IRON_SIDE;
            case 1: return Defaults.TEXTURE_DRUM_STEEL_SIDE;
            default: return 0;
        }
    }
    
    @Override
    public String getTextureFile()
    {
        return CDECore.CDE_BLOCKS;
    }
    
    @Override
    public boolean colorBlock(World world, int x, int y, int z, int index)
    {
        TileEntity te = world.getBlockTileEntity(x, y, z);
        
        if(te instanceof TileEntityDrum)
        {
            return ((TileEntityDrum)te).setDrumColor(index);
        }
        
        return false;
    }
    
    private int getDamageValue(int amount, int capacity, int max)
    {
        int i = amount * max / capacity;
       
        i = max - i;
        
        if(i == 0)
        {
            i++;
        }
        
        return i;
    }
}
