/**
 *
 * @author StormTiberius
 */

package cde.industry;

import buildcraft.api.tools.IToolWrench;
import cde.CDECore;
import cde.IndustryCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockIndustry extends BlockContainer
{   
    public BlockIndustry(int id)
    {
        super(id, Material.iron);

        setBlockBounds(0.075F, 0.0F, 0.075F, 0.925F, 1.0F, 0.925F);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int par6, float par7, float par8, float par9)
    {
        TileEntityIndustry tei = (TileEntityIndustry)world.getBlockTileEntity(x, y, z);

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
        return IndustryCore.drumRenderId;
    }
    
    @Override
    public int colorMultiplier(IBlockAccess iba, int x, int y, int z)
    {
        TileEntity te = iba.getBlockTileEntity(x, y, z);
        
        if(te instanceof TileEntityDrum)
        {
            TileEntityDrum ted = (TileEntityDrum)te;
            
            return ted.getLiquidColor();
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
        return null;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        switch(metadata)
        {
            case 0: return new TileEntityDrum();
            default: return null;
        }
    }    
    
    @Override
    public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z)
    {
        return false;
    }
    
    @Override
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int metadata)
    {
        
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
        return null;
    }
    
    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        return null;
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
    public int damageDropped(int i)
    {
        return i;
    }
    
    @Override
    public String getTextureFile()
    {             
        return CDECore.CDE_BLOCKS;
    }
    
    @Override
    public int getBlockTextureFromSideAndMetadata(int side, int meta)
    {
        switch(meta)
        {
            default: return 0;
        }
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getBlockTexture(IBlockAccess par1IBlockAccess, int x, int y, int z, int side)
    {
        int meta = par1IBlockAccess.getBlockMetadata(x, y, z);
        
        switch(meta)
        {
            default: return 0;
        }
    }
}
