/**
 *
 * @author StormTiberius
 */

package cde.industry.machine;

import net.minecraft.nbt.NBTTagCompound;

public class TileEntitySawmill extends TileEntityEnergyBase
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
}
