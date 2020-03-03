/**
 *
 * @author StormTiberius
 */

package cde.industry.machine;

import net.minecraft.nbt.NBTTagCompound;

public class TileEntityBatteryStation extends TileEntityMachine
{
    public TileEntityBatteryStation(){}
    
    public TileEntityBatteryStation(int machineId, int machineTier)
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
        return "Industry";
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
    public boolean isActive()
    {
        return false;
    }
    
    @Override
    public String getResourceName()
    {
        return "fluorescent.wav";
    }
}
