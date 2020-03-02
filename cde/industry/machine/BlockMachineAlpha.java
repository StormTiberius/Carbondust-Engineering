/**
 *
 * @author StormTiberius
 */

package cde.industry.machine;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMachineAlpha extends BlockMachine
{
    public BlockMachineAlpha(int id)
    {
        super(id);
    }
    
    @Override
    public TileEntity createNewTileEntity(World world, int md)
    {
        switch(md)
        {
            case 0: return new TileEntityFurnace(0,1);
            case 1: return new TileEntityFurnace(1,2);
            case 2: return new TileEntityFurnace(2,3);
            case 3: return new TileEntityMacerator(3);
            case 4: return new TileEntityCompressor(4);
            case 5: return new TileEntityExtractor(5);
            case 6: return new TileEntityCanningMachine(6);
            case 7: return new TileEntityRollingMachine(7);
            case 8: return new TileEntityAssemblingMachine(8);
            case 9: return new TileEntityRecycler(9);
            case 10: return new TileEntitySawmill(10);
            case 11: return new TileEntityHeater(11);
            case 12: return new TileEntityPump(12);
            default: return null;
        }
    }
    
    @Override
    public String getTextureFile()
    {
        return MachineModule.INDUSTRY_ALPHA;
    }
}
