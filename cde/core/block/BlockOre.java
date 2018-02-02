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
    public int idDropped(int meta, Random random, int fortune)
    {
        if(meta > 3)
        {
            return CDECore.partsItem.itemID;
        }
        
        return blockID;
    }
    
    @Override
    public int damageDropped(int meta)
    {
        if(meta > 3)
        {
            switch(meta)
            {
                case 4: return 53;
                case 5: return 20;
                case 6: return 21;
                case 7: return 42;
                case 8: return 39;
                case 9: return 40;
                case 10: return 41;
                case 11: return 43;
            }
        }
                
        return meta;
    }
    
    @Override
    public int getDamageValue(World par1World, int par2, int par3, int par4)
    {
        return par1World.getBlockMetadata(par2, par3, par4);
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
    public int getBlockTextureFromSideAndMetadata(int side, int meta)
    {
        return 32 + meta;
    }
    
    @Override
    public String getTextureFile()
    {
        return CDECore.CDE_BLOCKS;
    }
}
