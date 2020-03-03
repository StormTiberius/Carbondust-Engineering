/**
 *
 * @author StormTiberius
 */

package cde.industry.machine;

import ic2.api.Direction;
import ic2.api.energy.tile.IEnergySink;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityAssemblingMachine extends TileEntityMachine implements IEnergySink
{
    public TileEntityAssemblingMachine(){}
    
    public TileEntityAssemblingMachine(int machineId)
    {
        super(machineId);
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
    
    // CDE Sound
    @Override
    public boolean isActive()
    {
        return false;
    }
    
    @Override
    public String getResourceName()
    {
        return "";
    }
    
    // IC2 Energy
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
