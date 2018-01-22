/**
 *
 * @author StormTiberius
 */

package cde.energy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockParticleFountain extends BlockMachine
{   
    public BlockParticleFountain(int par1)
    {
        super(par1);
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1)
    {
        return new TileEntityParticleFountain();
    }
    
    @Override
    public int getBlockTextureFromSide(int side)
    {
        switch(side)
        {
            case 0: return 22;
            case 1: return 4;
            case 2: return 24;
            case 3: return 24;
            case 4: return 24;
            case 5: return 24;
            default: return 4;
        }
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getBlockTexture(IBlockAccess par1IBlockAccess, int x, int y, int z, int side)
    {
        TileEntityMachine tem = (TileEntityMachine)par1IBlockAccess.getBlockTileEntity(x, y, z);
        
        if(tem != null)
        {
            if(tem.isConnected(ForgeDirection.getOrientation(side)))
            {
                return 5;
            }
            
            if(tem.isPowered())
            {
                switch(side)
                {
                    case 0: return 23;
                    case 1: return 4;
                    case 2: return 24;
                    case 3: return 24;
                    case 4: return 24;
                    case 5: return 24;
                    default: return 4;
                }
            }
        }
        
        return getBlockTextureFromSide(side);
    }
}
