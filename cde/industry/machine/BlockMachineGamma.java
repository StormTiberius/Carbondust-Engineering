/**
 *
 * @author StormTiberius
 */

package cde.industry.machine;

import cde.core.Defaults;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

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
            case 0: return new TileEntityEnergyStorageUnit();
            case 1: return new TileEntityEnergyStorageUnit();
            case 2: return new TileEntityEnergyStorageUnit();
            case 3: return new TileEntityTransformer();
            case 4: return new TileEntityTransformer();
            case 5: return new TileEntityTransformer();
            case 6: return new TileEntityChargingBench();
            case 7: return new TileEntityChargingBench();
            case 8: return new TileEntityChargingBench();
            case 9: return new TileEntityBatteryStation();
            case 10: return new TileEntityBatteryStation();
            case 11: return new TileEntityBatteryStation();
            case 12: return new TileEntityInductionCharger();
            case 13: return new TileEntityElectrolyzer();
            case 14: return null;
            case 15: return null;
            default: return null;
        }
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getBlockTexture(IBlockAccess iba, int x, int y, int z, int side)
    {
        int md = iba.getBlockMetadata(x, y, z);
        TileEntity te = iba.getBlockTileEntity(x, y, z);
        
        if(te instanceof TileEntityEnergyBase)
        {
            TileEntityEnergyBase teeb = (TileEntityEnergyBase)te;
            
            if(teeb.isConnected(ForgeDirection.getOrientation(side)))
            {
                return Defaults.TEXTURE_MACHINE_CONNECTED;
            }
            
            if(teeb.isPowered())
            {
                switch(md)
                {
                    case 0:
                        switch(side)
                        {
                            case 0: return 0;
                            case 1: return 0;
                            case 2: return 0;
                            case 3: return 0;
                            case 4: return 0;
                            case 5: return 0;
                            default: return 0;
                        }
                    
                    case 1:
                        switch(side)
                        {
                            case 0: return 0;
                            case 1: return 0;
                            case 2: return 0;
                            case 3: return 0;
                            case 4: return 0;
                            case 5: return 0;
                            default: return 0;
                        }
                    
                    case 2:
                        switch(side)
                        {
                            case 0: return 0;
                            case 1: return 0;
                            case 2: return 0;
                            case 3: return 0;
                            case 4: return 0;
                            case 5: return 0;
                            default: return 0;
                        }
                    
                    case 3:
                        switch(side)
                        {
                            case 0: return 0;
                            case 1: return 0;
                            case 2: return 0;
                            case 3: return 0;
                            case 4: return 0;
                            case 5: return 0;
                            default: return 0;
                        }
                    
                    case 4:
                        switch(side)
                        {
                            case 0: return 0;
                            case 1: return 0;
                            case 2: return 0;
                            case 3: return 0;
                            case 4: return 0;
                            case 5: return 0;
                            default: return 0;
                        }
                    
                    case 5:
                        switch(side)
                        {
                            case 0: return 0;
                            case 1: return 0;
                            case 2: return 0;
                            case 3: return 0;
                            case 4: return 0;
                            case 5: return 0;
                            default: return 0;
                        }
                    
                    case 6:
                        switch(side)
                        {
                            case 0: return 0;
                            case 1: return 0;
                            case 2: return 0;
                            case 3: return 0;
                            case 4: return 0;
                            case 5: return 0;
                            default: return 0;
                        }
                    
                    case 7:
                        switch(side)
                        {
                            case 0: return 0;
                            case 1: return 0;
                            case 2: return 0;
                            case 3: return 0;
                            case 4: return 0;
                            case 5: return 0;
                            default: return 0;
                        }
                    
                    case 8:
                        switch(side)
                        {
                            case 0: return 0;
                            case 1: return 0;
                            case 2: return 0;
                            case 3: return 0;
                            case 4: return 0;
                            case 5: return 0;
                            default: return 0;
                        }
                    
                    case 9:
                        switch(side)
                        {
                            case 0: return 0;
                            case 1: return 0;
                            case 2: return 0;
                            case 3: return 0;
                            case 4: return 0;
                            case 5: return 0;
                            default: return 0;
                        }
                    
                    case 10:
                        switch(side)
                        {
                            case 0: return 0;
                            case 1: return 0;
                            case 2: return 0;
                            case 3: return 0;
                            case 4: return 0;
                            case 5: return 0;
                            default: return 0;
                        }
                    
                    case 11:
                        switch(side)
                        {
                            case 0: return 0;
                            case 1: return 0;
                            case 2: return 0;
                            case 3: return 0;
                            case 4: return 0;
                            case 5: return 0;
                            default: return 0;
                        }
                    
                    case 12:
                        switch(side)
                        {
                            case 0: return 0;
                            case 1: return 0;
                            case 2: return 0;
                            case 3: return 0;
                            case 4: return 0;
                            case 5: return 0;
                            default: return 0;
                        }
                    
                    case 13:
                        switch(side)
                        {
                            case 0: return 0;
                            case 1: return 0;
                            case 2: return 0;
                            case 3: return 0;
                            case 4: return 0;
                            case 5: return 0;
                            default: return 0;
                        }
                    
                    case 14:
                        switch(side)
                        {
                            case 0: return 0;
                            case 1: return 0;
                            case 2: return 0;
                            case 3: return 0;
                            case 4: return 0;
                            case 5: return 0;
                            default: return 0;
                        }
                    
                    case 15:
                        switch(side)
                        {
                            case 0: return 0;
                            case 1: return 0;
                            case 2: return 0;
                            case 3: return 0;
                            case 4: return 0;
                            case 5: return 0;
                            default: return 0;
                        }
                    
                    default: return 0;
                }
            }
        }
        
        return getBlockTextureFromSideAndMetadata(side, md);
    }
    
    @Override
    public int getBlockTextureFromSideAndMetadata(int side, int md)
    {
        switch(md)
        {
            case 0:
                switch(side)
                {
                    case 0: return 0;
                    case 1: return 0;
                    case 2: return 0;
                    case 3: return 0;
                    case 4: return 0;
                    case 5: return 0;
                    default: return 0;
                }
            
            case 1:
                switch(side)
                {
                    case 0: return 0;
                    case 1: return 0;
                    case 2: return 0;
                    case 3: return 0;
                    case 4: return 0;
                    case 5: return 0;
                    default: return 0;
                }
            
            case 2:
                switch(side)
                {
                    case 0: return 0;
                    case 1: return 0;
                    case 2: return 0;
                    case 3: return 0;
                    case 4: return 0;
                    case 5: return 0;
                    default: return 0;
                }
            
            case 3:
                switch(side)
                {
                    case 0: return 0;
                    case 1: return 0;
                    case 2: return 0;
                    case 3: return 0;
                    case 4: return 0;
                    case 5: return 0;
                    default: return 0;
                }
            
            case 4:
                switch(side)
                {
                    case 0: return 0;
                    case 1: return 0;
                    case 2: return 0;
                    case 3: return 0;
                    case 4: return 0;
                    case 5: return 0;
                    default: return 0;
                }
            
            case 5:
                switch(side)
                {
                    case 0: return 0;
                    case 1: return 0;
                    case 2: return 0;
                    case 3: return 0;
                    case 4: return 0;
                    case 5: return 0;
                    default: return 0;
                }
            
            case 6:
                switch(side)
                {
                    case 0: return 0;
                    case 1: return 0;
                    case 2: return 0;
                    case 3: return 0;
                    case 4: return 0;
                    case 5: return 0;
                    default: return 0;
                }
            
            case 7:
                switch(side)
                {
                    case 0: return 0;
                    case 1: return 0;
                    case 2: return 0;
                    case 3: return 0;
                    case 4: return 0;
                    case 5: return 0;
                    default: return 0;
                }
            
            case 8:
                switch(side)
                {
                    case 0: return 0;
                    case 1: return 0;
                    case 2: return 0;
                    case 3: return 0;
                    case 4: return 0;
                    case 5: return 0;
                    default: return 0;
                }
            
            case 9:
                switch(side)
                {
                    case 0: return 0;
                    case 1: return 0;
                    case 2: return 0;
                    case 3: return 0;
                    case 4: return 0;
                    case 5: return 0;
                    default: return 0;
                }
            
            case 10:
                switch(side)
                {
                    case 0: return 0;
                    case 1: return 0;
                    case 2: return 0;
                    case 3: return 0;
                    case 4: return 0;
                    case 5: return 0;
                    default: return 0;
                }
            
            case 11:
                switch(side)
                {
                    case 0: return 0;
                    case 1: return 0;
                    case 2: return 0;
                    case 3: return 0;
                    case 4: return 0;
                    case 5: return 0;
                    default: return 0;
                }
            
            case 12:
                switch(side)
                {
                    case 0: return 0;
                    case 1: return 0;
                    case 2: return 0;
                    case 3: return 0;
                    case 4: return 0;
                    case 5: return 0;
                    default: return 0;
                }
            
            case 13:
                switch(side)
                {
                    case 0: return 0;
                    case 1: return 0;
                    case 2: return 0;
                    case 3: return 0;
                    case 4: return 0;
                    case 5: return 0;
                    default: return 0;
                }
            
            case 14:
                switch(side)
                {
                    case 0: return 0;
                    case 1: return 0;
                    case 2: return 0;
                    case 3: return 0;
                    case 4: return 0;
                    case 5: return 0;
                    default: return 0;
                }
            
            case 15:
                switch(side)
                {
                    case 0: return 0;
                    case 1: return 0;
                    case 2: return 0;
                    case 3: return 0;
                    case 4: return 0;
                    case 5: return 0;
                    default: return 0;
                }
            
            default: return 0;
        }
    }
}
