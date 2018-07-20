package cde.resource;

import cde.CDECore;
import cde.core.Defaults;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockOilSand extends Block
{
    public BlockOilSand(int par1)
    {
        super(par1, Material.sand);
    }
    
    @Override
    public int getBlockTextureFromSideAndMetadata(int side, int meta)
    {
        return Defaults.TEXTURE_OIL_SAND;
    }
    
    @Override
    public String getTextureFile()
    {
        return CDECore.CDE_BLOCKS;
    }
}
