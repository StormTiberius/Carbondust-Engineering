/**
 *
 * @author StormTiberius
 */

package cde.industry;

import buildcraft.api.tools.IToolWrench;
import cde.CDECore;
import cde.IndustryCore;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

public class BlockDrum extends BlockContainer
{   
    public static final int DRUM_CAPACITY_IRON = 100000;
    public static final int DRUM_CAPACITY_STEEL = 200000;
    
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
            return true;
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
                        return taskEmptyContainer(world, x, y, z, player, held, ted);
                    }
                    else if(LiquidContainerRegistry.isFilledContainer(held))
                    {
                        return taskFilledContainer(world, x, y, z, player, held, ted);
                    }
                }
            }
            
            return false;
        }
    }
    
    private boolean taskWrench(World world, int x, int y, int z, EntityPlayer player, ItemStack held, TileEntityDrum ted)
    {
        IToolWrench tool = (IToolWrench)held.getItem();

        if(tool.canWrench(player, x, y, z))
        {
            tool.wrenchUsed(player, x, y, z);
            
            if(player.isSneaking())
            {
                dropBlockAsItem(world, x, y, z, 0, 0);
                world.setBlock(x, y, z, 0);
                
                return true;
            }

            player.sendChatToPlayer(ted.useWrench(false));

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
                case DRUM_CAPACITY_IRON: sb.append("iron"); break;
                case DRUM_CAPACITY_STEEL: sb.append("steel"); break;
                default: sb.append("UNKNOWN TYPE"); break;
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
    
        player.sendChatToPlayer(sb.toString());
    
        return true;
    }

    private boolean taskEmptyContainer(World world, int x, int y, int z, EntityPlayer player, ItemStack held, ITankContainer drum)
    {
        ItemStack filled = LiquidContainerRegistry.fillLiquidContainer(drum.getTank(ForgeDirection.UP, null).getLiquid(), held);
        
        if(filled != null)
        {
            int maxDrain = LiquidContainerRegistry.getLiquidForFilledItem(filled).amount;
            
            if(player.capabilities.isCreativeMode)
            {
                drum.drain(ForgeDirection.UP, maxDrain, true);
            }
            else if(held.stackSize == 1)
            {
                player.setCurrentItemOrArmor(0, filled);
                drum.drain(ForgeDirection.UP, maxDrain, true);
            }
            else if(player.inventory.addItemStackToInventory(filled))
            {
                held.stackSize--;
                drum.drain(ForgeDirection.UP, maxDrain, true);
                
                if(player instanceof EntityPlayerMP)
                {
                    ((EntityPlayerMP)player).mcServer.getConfigurationManager().syncPlayerInventory((EntityPlayerMP)player);
                }
            }                
            
            if(drum.getTanks(ForgeDirection.UP)[0].getLiquid() == null)
            {
                world.markBlockForUpdate(x, y, z);
            }
                
            return true;
        }
        
        return false;
    }

    private boolean taskFilledContainer(World world, int x, int y, int z, EntityPlayer player, ItemStack held, ITankContainer drum)
    {
        LiquidStack fluid = LiquidContainerRegistry.getLiquidForFilledItem(held);
        
        if(drum.fill(ForgeDirection.UP, fluid, false) == fluid.amount)
        {
            if(player.capabilities.isCreativeMode)
            {
                drum.fill(ForgeDirection.UP, fluid, true);
            }
            else
            {
                ItemStack c = null;
                
                if(held.getItem().hasContainerItem())
                {
                    c = held.getItem().getContainerItemStack(held);
                }
                
                if(c == null || held.stackSize == 1 || player.inventory.addItemStackToInventory(c))
                {
                    drum.fill(ForgeDirection.UP, fluid, true);
                    
                    if(held.stackSize == 1)
                    {
                        player.setCurrentItemOrArmor(0, c);
                    }
                    else if(held.stackSize > 1)
                    {
                        held.stackSize--;
                    }
                }
            }
            
            return true;
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
        
        if(te instanceof ITankContainer)
        {
            ITankContainer itc = (ITankContainer)te;
            
            ILiquidTank ilt = itc.getTank(ForgeDirection.DOWN, null);
            
            if(ilt != null)
            {
                LiquidStack ls = ilt.getLiquid();
                
                if(ls != null)
                {
                    return IndustryCore.getLiquidColor(ls.itemID);
                }
            }
        }
        
        return 0xffffff;
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1)
    {
        return new TileEntityDrum();
    }
    
    @Override
    public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z)
    {
        if(!player.capabilities.isCreativeMode && canHarvestBlock(player, world.getBlockMetadata(x, y, z)))
        {
            ArrayList<ItemStack> items = getBlockDropped(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            
            if(world.setBlock(x, y, z, 0))
            {
                if(!world.isRemote)
                {
                    for(ItemStack is : items)
                    {
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
        
        if(te instanceof TileEntityDrum && entity instanceof EntityPlayer)
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
                
                te.readFromNBT(tag);
            }
        }
    }
 
    @Override
    public void getSubBlocks(int id, CreativeTabs tab, List list)
    {
        // IRON DRUMS
        ItemStack drum = new ItemStack(id, 1, 0);
        
        if(!drum.hasTagCompound())
        {
            drum.setTagCompound(new NBTTagCompound());
        }
        
        drum.getTagCompound().setInteger("capacity", DRUM_CAPACITY_IRON);
        
        drum.setItemDamage(getDamageValue(0, DRUM_CAPACITY_IRON, drum.getMaxDamage()));
        
        list.add(drum);
        
        for(Map.Entry<String, LiquidStack> entry : LiquidDictionary.getLiquids().entrySet())
        {
            LiquidStack liquid = entry.getValue();
            
            if(liquid != null)
            {
                liquid.amount = DRUM_CAPACITY_IRON;
                
                NBTTagCompound tag = liquid.writeToNBT(new NBTTagCompound());
                
                drum = new ItemStack(id, 1, 0);
                
                if(!drum.hasTagCompound())
                {
                    drum.setTagCompound(new NBTTagCompound());
                }
                
                drum.getTagCompound().setInteger("capacity", DRUM_CAPACITY_IRON);
                drum.getTagCompound().setTag("liquid", tag);

                drum.setItemDamage(getDamageValue(DRUM_CAPACITY_IRON, DRUM_CAPACITY_IRON, drum.getMaxDamage()));
                
                list.add(drum);
            }
        }
        
        // STEEL DRUMS
        drum = new ItemStack(id, 1, 0);
        
        if(!drum.hasTagCompound())
        {
            drum.setTagCompound(new NBTTagCompound());
        }
        
        drum.getTagCompound().setInteger("capacity", DRUM_CAPACITY_STEEL);
        
        drum.setItemDamage(getDamageValue(0, DRUM_CAPACITY_STEEL, drum.getMaxDamage()));
        
        list.add(drum);
        
        for(Map.Entry<String, LiquidStack> entry : LiquidDictionary.getLiquids().entrySet())
        {
            LiquidStack liquid = entry.getValue();
            
            if(liquid != null)
            {
                liquid.amount = DRUM_CAPACITY_STEEL;
                
                NBTTagCompound tag = liquid.writeToNBT(new NBTTagCompound());
                
                drum = new ItemStack(id, 1, 0);
                
                if(!drum.hasTagCompound())
                {
                    drum.setTagCompound(new NBTTagCompound());
                }
                
                drum.getTagCompound().setInteger("capacity", DRUM_CAPACITY_STEEL);
                drum.getTagCompound().setTag("liquid", tag);

                drum.setItemDamage(getDamageValue(DRUM_CAPACITY_STEEL, DRUM_CAPACITY_STEEL, drum.getMaxDamage()));
                
                list.add(drum);
            }
        }
    }
    
    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
        ItemStack drum = new ItemStack(blockID, 1, 0);
        
        TileEntity te = world.getBlockTileEntity(x, y, z);
        
        if(te instanceof TileEntityDrum)
        {
            NBTTagCompound tag = new NBTTagCompound();
            
            te.writeToNBT(tag);
            
            if(!drum.hasTagCompound())
            {
                drum.setTagCompound(new NBTTagCompound());
            }
            
            drum.getTagCompound().setInteger("capacity", tag.getInteger("capacity"));
            drum.getTagCompound().setCompoundTag("liquid", tag.getCompoundTag("liquid"));
        }
        
        return drum;
    }
    
    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        
        TileEntity te = world.getBlockTileEntity(x, y, z);
        
        if(te instanceof TileEntityDrum)
        {
            NBTTagCompound tag = new NBTTagCompound();
            
            te.writeToNBT(tag);
            
            ItemStack drum = new ItemStack(blockID, 1, 0);
                    
            if(!drum.hasTagCompound())
            {
               drum.setTagCompound(new NBTTagCompound());
            }      
            
            drum.getTagCompound().setInteger("capacity", tag.getInteger("capacity"));
            drum.getTagCompound().setCompoundTag("liquid", tag.getCompoundTag("liquid"));
                    
            list.add(drum);
        }
        
        return list;
    }
    
    // @Override
    public ItemStack dismantleBlock(EntityPlayer player, World world, int x, int y, int z, boolean returnBlock)
    {
        return null;
    }
    
    public boolean canDismantle(EntityPlayer player, World world, int x, int y, int z, boolean returnBlock)
    {
        return false;
    }
    
    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
    
    }
    
    @Override
    public String getTextureFile()
    {
        return CDECore.CDE_BLOCKS;
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
