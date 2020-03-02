/**
 *
 * @author StormTiberius
 */

package cde.industry.machine;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMachineBeta extends BlockMachine
{
    public BlockMachineBeta(int id)
    {
        super(id);
    }
    
    @Override
    public TileEntity createNewTileEntity(World world, int md)
    {
        switch(md)
        {
            case 0: return new TileEntityStirlingGenerator();
            case 1: return new TileEntityGeothermalGenerator();
            case 2: return new TileEntityTidalGenerator();
            case 3: return new TileEntityWindGenerator();
            case 4: return new TileEntitySolarPanel();
            case 5: return new TileEntityNuclearReactor();
            case 6: return new TileEntityElectricDynamo();
            case 7: return new TileEntitySteamTurbine();
            default: return null;
        }
    }
    
    @Override
    public String getTextureFile()
    {
        return MachineModule.INDUSTRY_BETA;
    }
}
