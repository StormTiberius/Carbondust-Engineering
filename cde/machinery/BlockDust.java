package cde.machinery;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDust extends Block
{
    protected BlockDust(int par1, int par2)
    {
        super(par1, par2, Material.snow);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        int var5 = par1World.getBlockMetadata(par2, par3, par4) & 7;
        return var5 >= 3 ? AxisAlignedBB.getAABBPool().addOrModifyAABBInPool((double)par2 + this.minX, (double)par3 + this.minY, (double)par4 + this.minZ, (double)par2 + this.maxX, (double)((float)par3 + 0.5F), (double)par4 + this.maxZ) : null;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 7;
        float var6 = (float)(2 * (1 + var5)) / 16.0F;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, var6, 1.0F);
    }

    @Override
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        int var5 = par1World.getBlockId(par2, par3 - 1, par4);
        Block block = Block.blocksList[var5];
        return block != null && (block.isLeaves(par1World, par2, par3 - 1, par4) || Block.blocksList[var5].isOpaqueCube()) ? par1World.getBlockMaterial(par2, par3 - 1, par4).blocksMovement() : false;
    }

    @Override
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        this.canSnowStay(par1World, par2, par3, par4);
    }

    private boolean canSnowStay(World par1World, int par2, int par3, int par4)
    {
        if (!this.canPlaceBlockAt(par1World, par2, par3, par4))
        {
            par1World.setBlockWithNotify(par2, par3, par4, 0);
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6)
    {
        super.harvestBlock(par1World, par2EntityPlayer, par3, par4, par5, par6);
        par1World.setBlockWithNotify(par3, par4, par5, 0);
    }

    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return Item.snowball.itemID;
    }

    @Override
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }

    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (par1World.getSavedLightValue(EnumSkyBlock.Block, par2, par3, par4) > 11)
        {
            par1World.setBlockWithNotify(par2, par3, par4, 0);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return par5 == 1 ? true : super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
    }
}
