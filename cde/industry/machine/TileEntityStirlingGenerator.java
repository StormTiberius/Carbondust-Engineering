/**
 *
 * @author StormTiberius
 */

package cde.industry.machine;

import net.minecraft.nbt.NBTTagCompound;

public class TileEntityStirlingGenerator extends TileEntityMachine
{
    public TileEntityStirlingGenerator(int machineId)
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
        return "Stirling Generator by CDE";
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
        return "";
    }
}
