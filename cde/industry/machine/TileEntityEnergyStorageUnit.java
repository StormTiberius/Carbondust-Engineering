/**
 *
 * @author StormTiberius
 */

package cde.industry.machine;

import net.minecraft.nbt.NBTTagCompound;

public class TileEntityEnergyStorageUnit extends TileEntityMachine
{
    public TileEntityEnergyStorageUnit(int machineId, int machineTier)
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
        return "Energy Storage Unit by CDE";
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
    public String getResourceName()
    {
        return "fluorescent.wav";
    }
}
