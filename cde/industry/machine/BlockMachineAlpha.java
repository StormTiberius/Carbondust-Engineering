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
            case 0: return new TileEntityFurnace();
            case 1: return new TileEntityFurnace();
            case 2: return new TileEntityFurnace();
            case 3: return new TileEntityMacerator();
            case 4: return new TileEntityCompressor();
            case 5: return new TileEntityExtractor();
            case 6: return new TileEntityCanningMachine();
            case 7: return new TileEntityRollingMachine();
            case 8: return new TileEntityAssemblingMachine();
            case 9: return new TileEntityRecycler();
            case 10: return new TileEntitySawmill();
            case 11: return new TileEntityHeater();
            case 12: return new TileEntityPump();
            default: return null;
        }
    }
    
    @Override
    public String getTextureFile()
    {
        return MachineModule.INDUSTRY_ALPHA;
    }
}
