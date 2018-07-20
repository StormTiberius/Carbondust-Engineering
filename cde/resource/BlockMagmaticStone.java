package cde.resource;

import cde.CDECore;
import cde.core.Defaults;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockMagmaticStone extends Block
{
    public BlockMagmaticStone(int par1)
    {
        super(par1, Material.rock);
    }
    
    @Override
    public int getBlockTextureFromSideAndMetadata(int side, int meta)
    {
        return Defaults.TEXTURE_MAGMATIC_STONE;
    }
    
    @Override
    public String getTextureFile()
    {
        return CDECore.CDE_BLOCKS;
    }
}
