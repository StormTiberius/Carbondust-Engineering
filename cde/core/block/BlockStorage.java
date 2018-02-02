package cde.core.block;

import cde.CDECore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockStorage extends Block
{
    public BlockStorage(int par1)
    {
        super(par1,Material.iron);
    }

    @Override
    public int damageDropped(int i)
    {
        return i;
    }
    
    @Override
    public int getBlockTextureFromSideAndMetadata(int side, int meta)
    {
        return 48 + meta;
    }
    
    @Override
    public String getTextureFile()
    {
        return CDECore.CDE_BLOCKS;
    }
}
