/**
 *
 * @author StormTiberius
 */

package cde.industry.machine;

import cde.api.IWrenchable;
import cde.core.sound.TileEntityWithSound;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyConductor;
import ic2.api.energy.tile.IEnergyTile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.MinecraftForge;

public abstract class TileEntityMachine extends TileEntityWithSound implements IEnergyTile, IWrenchable
{
    protected abstract void doWorkCycle();
    protected int machineId,machineTier;
    private boolean isActive,isAddedToEnergyNet;
    
    public TileEntityMachine(int machineId)
    {
        this.machineId = machineId;
        this.volume = MachineModule.VOLUME[machineId];
        this.pitch = MachineModule.PITCH[machineId];
    }
    
    public TileEntityMachine(int machineId, int machineTier)
    {
        this(machineId);
        this.machineTier = machineTier;
    }
    
    protected boolean isConnected(ForgeDirection side)
    {
        return worldObj.getBlockTileEntity(xCoord + side.offsetX, yCoord + side.offsetY, zCoord + side.offsetZ) instanceof IEnergyConductor;
    }
    
    @Override
    protected boolean isTileSoundEnabled()
    {
        return MachineModule.SOUND[machineId] && super.isTileSoundEnabled();
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        machineId = tag.getInteger("machineId");
        machineTier = tag.getInteger("machineTier");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        tag.setInteger("machineId", machineId);
        tag.setInteger("machineTier", machineTier);
    }
    
    @Override
    public void updateEntity()
    {
        super.updateEntity();
        
        boolean flag = worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
        
        if(isActive != flag)
        {
            isActive = flag;
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }
        
        if(!isAddedToEnergyNet)
        {
            MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
            isAddedToEnergyNet = true;
        }
        
        if(isActive())
        {
            if(!worldObj.isRemote)
            {
                doWorkCycle();
            }
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
    
    @Override
    public boolean isActive()
    {
        return isActive;
    }
    
    // IC2 Method
    @Override
    public boolean isAddedToEnergyNet()
    {
        return isAddedToEnergyNet;
    }
}
