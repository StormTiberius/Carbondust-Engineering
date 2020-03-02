/**
 *
 * @author StormTiberius
 */

package cde.industry.machine;

import net.minecraft.nbt.NBTTagCompound;

public class TileEntityBatteryStation extends TileEntityMachine
{
    private int cde,tier;
    
    public TileEntityBatteryStation(int cde, int tier)
    {
        this.cde = cde;
        this.tier = tier;
    }
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
        cde = tag.getInteger("cde");
        tier = tag.getInteger("tier");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        tag.setInteger("cde", cde);
        tag.setInteger("tier", tier);
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
