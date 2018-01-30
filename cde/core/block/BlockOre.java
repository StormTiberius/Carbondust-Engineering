package cde.core.block;

import cde.CDECore;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockOre extends Block
{
    public BlockOre(int par1)
    {
        super(par1,Material.rock);
    }

    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return blockID;
    }

    @Override
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }

    @Override
    public int quantityDroppedWithBonus(int par1, Random par2Random)
    {
        if (par1 > 0 && this.blockID != this.idDropped(0, par2Random, par1))
        {
            int var3 = par2Random.nextInt(par1 + 2) - 1;

            if (var3 < 0)
            {
                var3 = 0;
            }

            return this.quantityDropped(par2Random) * (var3 + 1);
        }
        else
        {
            return this.quantityDropped(par2Random);
        }
    }

    @Override
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, par7);

        if (this.idDropped(par5, par1World.rand, par7) != this.blockID)
        {
            int var8 = 0;

            this.dropXpOnBlockBreak(par1World, par2, par3, par4, var8);
        }
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
        return 32 + meta;
    }
}
