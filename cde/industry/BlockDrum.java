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
import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
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
import net.minecraftforge.liquids.LiquidStack;

public class BlockDrum extends BlockContainer
{   
    public BlockDrum(int id)
    {
        super(id, Material.iron);

        setBlockBounds(0.075F, 0.0F, 0.075F, 0.925F, 1.0F, 0.925F);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int par6, float par7, float par8, float par9)
    {
        TileEntityDrum tei = (TileEntityDrum)world.getBlockTileEntity(x, y, z);

        if (entityplayer.isSneaking())
        {
            return false;
        }

        Item equipped = entityplayer.getCurrentEquippedItem() != null ? entityplayer.getCurrentEquippedItem().getItem() : null;
		
        if (equipped instanceof IToolWrench && ((IToolWrench) equipped).canWrench(entityplayer, x, y, z))
        {
            if(!world.isRemote && tei != null)
            {
                if(tei.isPowered())
                {
                    entityplayer.sendChatToPlayer(tei.useWrench(false));
                }
                else
                {
                    entityplayer.sendChatToPlayer(tei.useWrench(true));
                }
            }
            
            ((IToolWrench) equipped).wrenchUsed(entityplayer, x, y, z);
            
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
    public boolean hasTileEntity(int metadata)
    {
        return true;
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
        
    }
 
    @Override
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
    }
    
    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
        TileEntity te = world.getBlockTileEntity(x, y, z);
        
        if(te instanceof ITankContainer)
        {
            ITankContainer itc = (ITankContainer)te;
            
            ILiquidTank ilt = itc.getTank(ForgeDirection.UP, null);
            
            if(ilt != null)
            {
                LiquidStack liquid = ilt.getLiquid();
                
                if(liquid != null)
                {
                    int id = liquid.itemID;
                    int amount = liquid.amount;
                    
                    ItemStack drum = new ItemStack(IndustryCore.blockDrum.blockID, 1, 0);
                    
                    NBTTagCompound tag;
                    
                    tag = drum.writeToNBT(new NBTTagCompound());
                    
                    tag.setInteger("liquidid", id);
                    tag.setInteger("amount", amount);
                    tag.setInteger("color", IndustryCore.getLiquidColor(id));
                    
                    drum.readFromNBT(tag);
                }
                else
                {
                    return new ItemStack(IndustryCore.blockDrum.blockID, 1, 0);
                }
            }
        }
        
        return new ItemStack(IndustryCore.blockDrum.blockID, 1, 0);
    }
    
    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> al = new ArrayList<ItemStack>();
        
        TileEntity te = world.getBlockTileEntity(x, y, z);
        
        if(te instanceof TileEntityDrum)
        {
            ITankContainer itc = (ITankContainer)te;
            
            ILiquidTank ilt = itc.getTank(ForgeDirection.UP, null);
            
            if(ilt != null)
            {
                LiquidStack liquid = ilt.getLiquid();
                
                if(liquid != null)
                {
                    int id = liquid.itemID;
                    int amount = liquid.amount;
                    
                    ItemStack drum = new ItemStack(IndustryCore.blockDrum.blockID, 1, 0);
                    
                    NBTTagCompound tag;
                    
                    tag = drum.writeToNBT(new NBTTagCompound());
                    
                    tag.setInteger("liquidid", id);
                    tag.setInteger("amount", amount);
                    tag.setInteger("color", IndustryCore.getLiquidColor(id));
                    
                    drum.readFromNBT(tag);
                    
                    al.add(drum);
                }
            }
        }
        
        return al;
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
}
