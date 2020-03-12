package cde.core.block;

import cde.CDECore;
import cde.core.Defaults;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockStorage extends Block
{
    public BlockStorage(int id)
    {
        super(id,Material.iron);
    }
    
    @Override
    public int damageDropped(int md)
    {
        return md;
    }
    
    @Override
    public int getBlockTextureFromSideAndMetadata(int side, int md)
    {
        switch(md)
        {
            case 0: return Defaults.TEXTURE_BLOCK_STORAGE_COPPER;
            case 1: return Defaults.TEXTURE_BLOCK_STORAGE_TIN;
            case 2: return Defaults.TEXTURE_BLOCK_STORAGE_SILVER;
            case 3: return Defaults.TEXTURE_BLOCK_STORAGE_LEAD;
            case 4: return Defaults.TEXTURE_BLOCK_STORAGE_ZINC;
            case 5: return Defaults.TEXTURE_BLOCK_STORAGE_URANIUM;
            case 6: return Defaults.TEXTURE_BLOCK_STORAGE_BRONZE;
            case 7: return Defaults.TEXTURE_BLOCK_STORAGE_BRASS;
            case 8: return Defaults.TEXTURE_BLOCK_STORAGE_STEEL;
            case 9: return Defaults.TEXTURE_BLOCK_STORAGE_RUBY;
            case 10: return Defaults.TEXTURE_BLOCK_STORAGE_JADE;
            case 11: return Defaults.TEXTURE_BLOCK_STORAGE_SAPPHIRE;
            default: return 0;
        }
    }
    
    @Override
    public String getTextureFile()
    {
        return CDECore.CDE_BLOCKS;
    }
}
