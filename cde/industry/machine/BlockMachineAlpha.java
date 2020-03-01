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
            case 13: return null;
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
