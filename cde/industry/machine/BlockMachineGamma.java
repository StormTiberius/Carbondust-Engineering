/**
 *
 * @author StormTiberius
 */

package cde.industry.machine;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMachineGamma extends BlockMachine
{
    public BlockMachineGamma(int id)
    {
        super(id);
    }
    
    @Override
    public TileEntity createNewTileEntity(World world, int md)
    {
        switch(md)
        {
            case 0: return new TileEntityEnergyStorageUnit(21,1);
            case 1: return new TileEntityEnergyStorageUnit(22,2);
            case 2: return new TileEntityEnergyStorageUnit(23,3);
            case 3: return new TileEntityTransformer(24,1);
            case 4: return new TileEntityTransformer(25,2);
            case 5: return new TileEntityTransformer(26,3);
            case 6: return new TileEntityChargingBench(27,1);
            case 7: return new TileEntityChargingBench(28,2);
            case 8: return new TileEntityChargingBench(29,3);
            case 9: return new TileEntityBatteryStation(30,1);
            case 10: return new TileEntityBatteryStation(31,2);
            case 11: return new TileEntityBatteryStation(32,3);
            case 12: return new TileEntityInductionCharger(33);
            case 13: return new TileEntityElectrolyzer(34);
            default: return null;
        }
    }
    
    @Override
    public String getTextureFile()
    {
        return MachineModule.INDUSTRY_GAMMA;
    }
}
