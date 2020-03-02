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
            case 0: return new TileEntityStirlingGenerator(13);
            case 1: return new TileEntityGeothermalGenerator(14);
            case 2: return new TileEntityTidalGenerator(15);
            case 3: return new TileEntityWindGenerator(16);
            case 4: return new TileEntitySolarPanel(17);
            case 5: return new TileEntityNuclearReactor(18);
            case 6: return new TileEntityElectricDynamo(19);
            case 7: return new TileEntitySteamTurbine(20);
            default: return null;
        }
    }
    
    @Override
    public String getTextureFile()
    {
        return MachineModule.INDUSTRY_BETA;
    }
}
