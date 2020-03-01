/**
 *
 * @author StormTiberius
 */

package cde.industry.machines;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMachineBeta extends BlockMachine
{
    public BlockMachineBeta(int id)
    {
        super(id);
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
            default: return null;
        }
    }
}
