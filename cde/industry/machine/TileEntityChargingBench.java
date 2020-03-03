/**
 *
 * @author StormTiberius
 */

package cde.industry.machine;

import net.minecraft.nbt.NBTTagCompound;

public class TileEntityChargingBench extends TileEntityMachine
{
    public TileEntityChargingBench(int machineId, int machineTier)
    {
        super(machineId,machineTier);
    }
    
    @Override
    protected void doWorkCycle()
    {
        
    }
    
    @Override
    public String useWrench(boolean flag)
    {    
        return "Charging Bench by CDE";
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
}