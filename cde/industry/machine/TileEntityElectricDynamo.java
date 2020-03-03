/**
 *
 * @author StormTiberius
 */

package cde.industry.machine;

import buildcraft.api.power.IPowerProvider;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerFramework;
import buildcraft.api.transport.IPipeConnection;
import ic2.api.Direction;
import ic2.api.energy.event.EnergyTileSourceEvent;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.energy.tile.IEnergyTile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.MinecraftForge;

public class TileEntityElectricDynamo extends TileEntityMachine implements IEnergySource, IPowerReceptor 
{
    public IPowerProvider powerProvider;
    private int euOutput;
    private float euBuffer;
    private final float MJ_MULTIPLIER = 0.4F;
    
    public TileEntityElectricDynamo()
    {
        powerProvider = PowerFramework.currentFramework.createPowerProvider();
        powerProvider.configure(20, 1, 820, 13, 16400);
        euOutput = 32;
    }
    
    public TileEntityElectricDynamo(int machineId)
    {
        super(machineId);
        powerProvider = PowerFramework.currentFramework.createPowerProvider();
        powerProvider.configure(20, 1, 820, 13, 16400);
        euOutput = 32;
    }
    
    @Override
    public void updateEntity()
    {
        powerProvider.update((IPowerReceptor)this);
        super.updateEntity();
    }
    
    @Override
    protected void doWorkCycle()
    {
        float energyToUse = MJ_MULTIPLIER * euOutput;
        
        if(euBuffer < energyToUse && powerProvider.getEnergyStored() >= energyToUse)
        {
            euBuffer += powerProvider.useEnergy(energyToUse, energyToUse, true);
        }
        
        if(euBuffer >= energyToUse)
        {
            EnergyTileSourceEvent event = new EnergyTileSourceEvent(this, euOutput);
            MinecraftForge.EVENT_BUS.post(event);
            
            float excess = MJ_MULTIPLIER * event.amount;
            
            euBuffer -= energyToUse - excess;
        }
    }
        
    @Override
    public String useWrench(boolean flag)
    {
        if(flag)
        {
            switch(euOutput)
            {
                case 32: euOutput = 128; break;
                case 128: euOutput = 512; break;
                case 512: euOutput = 2048; break;
                case 2048: euOutput = 32; break;
                default: euOutput = 32; break;
            }
            
            return "Generator output set to: " + euOutput + " EU/t";
        }
            
        return "Generator output: " + euOutput + " EU/t";
    }
    
    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        PowerFramework.currentFramework.loadPowerProvider(this, par1NBTTagCompound);
        powerProvider.configure(20, 1, 820, 13, 16400);
        euOutput = par1NBTTagCompound.getInteger("euOutput");
        euBuffer = par1NBTTagCompound.getFloat("euBuffer");
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        PowerFramework.currentFramework.savePowerProvider(this, par1NBTTagCompound);  
        par1NBTTagCompound.setInteger("euOutput", euOutput);
        par1NBTTagCompound.setFloat("euBuffer", euBuffer);
    }
    
    @Override
    protected boolean isConnected(ForgeDirection side)
    {
        TileEntity te = worldObj.getBlockTileEntity(xCoord + side.offsetX, yCoord + side.offsetY, zCoord + side.offsetZ);
        
        if(te instanceof IPipeConnection)
        {
            return ((IPipeConnection)te).isPipeConnected(side.getOpposite());
        }

        return super.isConnected(side);
    }

    // IC2 Methods
    @Override
    public boolean emitsEnergyTo(TileEntity receiver, Direction direction)
    {
        return receiver instanceof IEnergyTile;
    }
    
    @Override
    public int getMaxEnergyOutput()
    {
        return euOutput;
    }
    
    // BC Methods
    @Override	
    public void setPowerProvider(IPowerProvider provider)
    {
        powerProvider = provider;
    }

    @Override
    public IPowerProvider getPowerProvider()
    {
        return powerProvider;
    }

    @Override
    public void doWork()
    {
        // --
    }

    @Override
    public int powerRequest()
    {
        if (isActive())
        {		
            return (int) Math.ceil(Math.min(getPowerProvider().getMaxEnergyReceived(), getPowerProvider().getMaxEnergyStored() - getPowerProvider().getEnergyStored()));
        }
        
        return 0;
    }
    
    // CDE Sound
    @Override
    public String getResourceName()
    {
        return "rotormachine.wav";
    }
}
