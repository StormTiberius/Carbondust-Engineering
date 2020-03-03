/**
 *
 * @author StormTiberius
 */

package cde.industry.machine;

import ic2.api.Direction;
import ic2.api.energy.event.EnergyTileSourceEvent;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.energy.tile.IEnergyTile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;

public class TileEntitySolarPanel extends TileEntityMachine implements IEnergySource 
{
    private int euOutput,counter;
    private boolean isSolarDay;
    
    public TileEntitySolarPanel(){}
    
    public TileEntitySolarPanel(int machineId)
    {
        super(machineId);
        euOutput = 1;
    }
    
    @Override
    protected void doWorkCycle()
    {
        if(counter > 40)
        {
            isSolarDay = worldObj.isDaytime() && !worldObj.isRaining() && worldObj.canBlockSeeTheSky(xCoord, yCoord + 1, zCoord);
            counter = 0;
        }
        else
        {
            counter++;
        }
        
        if(isSolarDay)
        {
            EnergyTileSourceEvent event = new EnergyTileSourceEvent(this, euOutput);
            MinecraftForge.EVENT_BUS.post(event);
        }
    }
        
    @Override
    public String useWrench(boolean flag)
    {    
        return "Solar Panel by CDE";
    }
    
    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        euOutput = par1NBTTagCompound.getInteger("euOutput");
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("euOutput", euOutput);
    }

    // IC2 Methods
    @Override
    public boolean emitsEnergyTo(TileEntity receiver, Direction direction)
    {
        return !direction.equals(Direction.YP) && receiver instanceof IEnergyTile;
    }
    
    @Override
    public int getMaxEnergyOutput()
    {
        return euOutput;
    }
        
    // CDE Sound
    @Override
    public boolean isActive()
    {
        return true;
    }
    
    @Override
    public String getResourceName()
    {
        return "";
    }
}
