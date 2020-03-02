/**
 *
 * @author StormTiberius
 */

package cde.industry.machine;

import ic2.api.Direction;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityAssemblingMachine extends TileEntityMachine
{
    @Override
    protected boolean isPowered()
    {
        return false;
    }
    
    @Override
    protected void doWorkCycle()
    {
        
    }
    
    @Override
    public String useWrench(boolean flag)
    {    
        return "Assembling Machine by CDE";
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
    }
    
    // Ambient Sounds
    @Override
    public boolean isWorking()
    {
        return isPowered();
    }
    
    @Override
    public String getResourceName()
    {
        return "fluorescent.wav";
    }
    
    @Override
    public float getVolume()
    {
        return 1.0F / 100 * 1;
    }
    
    @Override
    public float getPitch()
    {
        return 1.0F / 100 * 1;
    }
    
    // IC2
    @Override
    public int demandsEnergy()
    {
        return 0;
    }

    @Override
    public int injectEnergy(Direction directionFrom, int amount)
    {
        return 0;
    }

    @Override
    public int getMaxSafeInput()
    {
        return 128;
    }

    @Override
    public boolean acceptsEnergyFrom(TileEntity emitter, Direction direction)
    {
        return true;
    }
}
