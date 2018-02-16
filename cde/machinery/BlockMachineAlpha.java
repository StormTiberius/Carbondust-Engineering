/**
 *
 * @author StormTiberius
 */

package cde.machinery;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockMachineAlpha extends BlockMachine
{   
    public BlockMachineAlpha(int par1)
    {
        super(par1);
    }
    
    @Override
    public int damageDropped(int i)
    {
        return i;
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
            case 0: return new TileEntityGenerator();
            case 1: return new TileEntityTurbine();
            case 2: return new TileEntityHeater();
            case 3: return new TileEntityPump();
            case 4: return new TileEntityMixer();
            case 5: return new TileEntitySolarPanel();
            case 6: return new TileEntityTransformer();
            default: return null;
        }
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getBlockTexture(IBlockAccess par1IBlockAccess, int x, int y, int z, int side)
    {
        int meta = par1IBlockAccess.getBlockMetadata(x, y, z);
        
        switch(meta)
        {
            case 0: return getGeneratorTexture(par1IBlockAccess, x, y, z, side);
            case 1: return getTurbineTexture(par1IBlockAccess, x, y, z, side);
            case 2: return getHeaterTexture(par1IBlockAccess, x, y, z, side);
            case 3: return getPumpTexture(par1IBlockAccess, x, y, z, side);
            case 4: return getMixerTexture(par1IBlockAccess, x, y, z, side);
            case 5: return getSolarPanelTexture(par1IBlockAccess, x, y, z, side);
            case 6: return getTransformerTexture(par1IBlockAccess, x, y, z, side);
            default: return 4;
        }
    }
    
    @Override
    public int getBlockTextureFromSideAndMetadata(int side, int meta)
    {
        switch(meta)
        {
            case 0: return getGeneratorTextureFromSide(side);
            case 1: return getTurbineTextureFromSide(side);
            case 2: return getHeaterTextureFromSide(side);
            case 3: return getPumpTextureFromSide(side);
            case 4: return getMixerTextureFromSide(side);
            case 5: return getSolarPanelTextureFromSide(side);
            case 6: return getTransformerTextureFromSide(side);
            default: return 4;
        }
    }
    
    private int getGeneratorTextureFromSide(int side)
    {
        switch(side)
        {
            case 0: return 8;
            case 1: return 4;
            case 2: return 2;
            case 3: return 2;
            case 4: return 2;
            case 5: return 2;
            default: return 4;
        }
    }
    
    private int getTurbineTextureFromSide(int side)
    {
        switch(side)
        {
            case 0: return 8;
            case 1: return 11;
            case 2: return 9;
            case 3: return 9;
            case 4: return 9;
            case 5: return 9;
            default: return 4;
        }
    }
    
    private int getHeaterTextureFromSide(int side)
    {
        switch(side)
        {
            case 0: return 8;
            case 1: return 10;
            case 2: return 4;
            case 3: return 4;
            case 4: return 4;
            case 5: return 4;
            default: return 4;
        }
    }
    
    private int getPumpTextureFromSide(int side)
    {
        switch(side)
        {
            case 0: return 8;
            case 1: return 4;
            case 2: return 6;
            case 3: return 6;
            case 4: return 6;
            case 5: return 6;
            default: return 4;
        }
    }
    
    private int getMixerTextureFromSide(int side)
    {
        switch(side)
        {
            case 0: return 8;
            case 1: return 4;
            case 2: return 13;
            case 3: return 13;
            case 4: return 13;
            case 5: return 13;
            default: return 4;
        }
    }
    
    private int getSolarPanelTextureFromSide(int side)
    {
        switch(side)
        {
            case 0: return 8;
            case 1: return 15;
            case 2: return 4;
            case 3: return 4;
            case 4: return 4;
            case 5: return 4;
            default: return 4;
        }
    }
    
    private int getTransformerTextureFromSide(int side)
    {
        switch(side)
        {
            case 0: return 8;
            case 1: return 0;
            case 2: return 4;
            case 3: return 4;
            case 4: return 4;
            case 5: return 4;
            default: return 4;
        }
    }
    
    private int getGeneratorTexture(IBlockAccess par1IBlockAccess, int x, int y, int z, int side)
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
                    case 0: return 8;
                    case 1: return 4;
                    case 2: return 3;
                    case 3: return 3;
                    case 4: return 3;
                    case 5: return 3;
                    default: return 4;
                }
            }
        }
        
        return getGeneratorTextureFromSide(side);
    }
    
    private int getTurbineTexture(IBlockAccess par1IBlockAccess, int x, int y, int z, int side)
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
                    case 0: return 8;
                    case 1: return 12;
                    case 2: return 9;
                    case 3: return 9;
                    case 4: return 9;
                    case 5: return 9;
                    default: return 4;
                }
            }
        }
        
        return getTurbineTextureFromSide(side);
    }
    
    private int getHeaterTexture(IBlockAccess par1IBlockAccess, int x, int y, int z, int side)
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
                    case 0: return 8;
                    case 1: return 10;
                    case 2: return 4;
                    case 3: return 4;
                    case 4: return 4;
                    case 5: return 4;
                    default: return 4;
                }
            }
        }
        
        return getHeaterTextureFromSide(side);
    }
    
    private int getPumpTexture(IBlockAccess par1IBlockAccess, int x, int y, int z, int side)
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
                    case 0: return 8;
                    case 1: return 4;
                    case 2: return 7;
                    case 3: return 7;
                    case 4: return 7;
                    case 5: return 7;
                    default: return 4;
                }
            }
        }
        
        return getPumpTextureFromSide(side);
    }
    
    private int getMixerTexture(IBlockAccess par1IBlockAccess, int x, int y, int z, int side)
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
                    case 0: return 8;
                    case 1: return 4;
                    case 2: return 14;
                    case 3: return 14;
                    case 4: return 14;
                    case 5: return 14;
                    default: return 4;
                }
            }
        }
        
        return getMixerTextureFromSide(side);
    }
    
    private int getSolarPanelTexture(IBlockAccess par1IBlockAccess, int x, int y, int z, int side)
    {
        TileEntityMachine tem = (TileEntityMachine)par1IBlockAccess.getBlockTileEntity(x, y, z);
        
        if(tem != null)
        {
            if(!ForgeDirection.getOrientation(side).equals(ForgeDirection.UP) && tem.isConnected(ForgeDirection.getOrientation(side)))
            {
                return 5;
            }
            
            if(tem.isPowered())
            {
                switch(side)
                {
                    case 0: return 8;
                    case 1: return 15;
                    case 2: return 4;
                    case 3: return 4;
                    case 4: return 4;
                    case 5: return 4;
                    default: return 4;
                }
            }
        }
        
        return getSolarPanelTextureFromSide(side);
    }
    
    private int getTransformerTexture(IBlockAccess par1IBlockAccess, int x, int y, int z, int side)
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
                    case 0: return 8;
                    case 1: return 1;
                    case 2: return 4;
                    case 3: return 4;
                    case 4: return 4;
                    case 5: return 4;
                    default: return 4;
                }
            }
        }
        
        return getTransformerTextureFromSide(side);
    }
}
