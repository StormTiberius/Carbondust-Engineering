/**
 *
 * @author StormTiberius
 */

package cde.industry.machine;

import cde.core.sound.TileEntityWithSound;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyConductor;
import ic2.api.energy.tile.IEnergyTile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.MinecraftForge;

public abstract class TileEntityMachine extends TileEntityWithSound implements IEnergyTile
{
    protected int cde,tier,volume,pitch;
    
    protected abstract String useWrench(boolean flag);
    protected abstract void doWorkCycle();
    protected TileEntity tec;
    protected boolean isAddedToEnergyNet = false;
    protected boolean isRedstonePowered = false;
    private boolean flag = false;
    
    public TileEntityMachine(int cde, int tier)
    {
        this.cde = cde;
        this.tier = tier;
        this.volume = MachineModule.VOLUME[cde];
        this.pitch = MachineModule.PITCH[cde];
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        cde = tag.getInteger("cde");
        tier = tag.getInteger("tier");
        volume = tag.getInteger("volume");
        pitch = tag.getInteger("pitch");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        tag.setInteger("cde", cde);
        tag.setInteger("tier", tier);
    }
    
    @Override
    public void updateEntity()
    {
        super.updateEntity();
        
        flag = worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
        
        if(isRedstonePowered != flag)
        {
            isRedstonePowered = flag;
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }
        
        if(!isAddedToEnergyNet)
        {
            MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
            isAddedToEnergyNet = true;
        }
        
        if(!worldObj.isRemote && isPowered())
        {
            doWorkCycle();
        }
    }
    
    @Override
    public void invalidate()
    {
        if(isAddedToEnergyNet)
        {
            MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
            isAddedToEnergyNet = false;
        }
              
        super.invalidate();
    }
    
    @Override
    public void onChunkUnload()
    {
        if(isAddedToEnergyNet)
        {
            MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
            isAddedToEnergyNet = false;
        }
        
        super.onChunkUnload();
    }
    
    protected boolean isPowered()
    {
        return isRedstonePowered;
    }
    
    protected boolean isConnected(ForgeDirection side)
    {
        tec = worldObj.getBlockTileEntity(xCoord + side.offsetX, yCoord + side.offsetY, zCoord + side.offsetZ);
        
        return tec instanceof IEnergyConductor;
    }
    
    // IC2 Method
    @Override
    public boolean isAddedToEnergyNet()
    {
        return isAddedToEnergyNet;
    }
}
