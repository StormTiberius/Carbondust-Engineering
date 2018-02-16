/**
 *
 * @author StormTiberius
 */

package cde.machinery;

import cde.CDECore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockGrate extends Block
{
    public BlockGrate(int id)
    {
        super(id, Material.iron);
    }
    
    @Override
    public int getBlockTextureFromSide(int side)
    {
        switch(side)
        {
            case 0: return 19;
            case 1: return 19;
            case 2: return 18;
            case 3: return 18;
            case 4: return 18;
            case 5: return 18;
            default: return 4;
}
    }
    
    @Override
    public String getTextureFile()
    {             
        return CDECore.CDE_BLOCKS;
    }
}
