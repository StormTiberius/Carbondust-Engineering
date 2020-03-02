/**
 *
 * @author StormTiberius
 */

package cde.industry.machine;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMachineDelta extends BlockMachine
{
    public BlockMachineDelta(int id)
    {
        super(id);
    }
    
    @Override
    public TileEntity createNewTileEntity(World world, int md)
    {
        switch(md)
        {
            default: return null;
        }
    }
    
    @Override
    public String getTextureFile()
    {
        return MachineModule.INDUSTRY_DELTA;
    }
}
