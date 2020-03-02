/**
 *
 * @author StormTiberius
 */

package cde.industry.machine;

import net.minecraft.nbt.NBTTagCompound;

public class TileEntityChargingBench extends TileEntityMachine
{
    private int cde;
    private int tier;
    
    public TileEntityChargingBench(int cde, int tier)
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
        switch(tier)
        {
            case 1: return 0.01F * tier;
            case 2: break;
            case 3: break;
        }
        
        return 1.0F / 100 * 1;
    }
    
    @Override
    public float getPitch()
    {
        return 1.0F / 100 * 1;
    }
}
