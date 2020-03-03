/**
 *
 * @author StormTiberius
 */

package cde.industry.machine;

import net.minecraft.nbt.NBTTagCompound;

public class TileEntityCanningMachine extends TileEntityMachine
{
    public TileEntityCanningMachine(){}
    
    public TileEntityCanningMachine(int machineId)
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
        return "Canning Machine by CDE";
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
