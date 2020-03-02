/**
 *
 * @author StormTiberius
 */

package cde.industry.machine;

import net.minecraft.nbt.NBTTagCompound;

public class TileEntityTidalGenerator extends TileEntityMachine
{
    public TileEntityTidalGenerator(int machineType)
    {
        super(machineType);
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
    
    // CDE Sound
    @Override
    public String getResourceName()
    {
        return "";
    }
}
