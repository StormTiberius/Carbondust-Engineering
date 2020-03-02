/**
 *
 * @author StormTiberius
 */

package cde.industry.machine;

import cde.CDECore;
import cde.core.particles.ParticleSteam;
import ic2.api.Direction;
import ic2.api.energy.event.EnergyTileSourceEvent;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.energy.tile.IEnergyTile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

public class TileEntitySteamTurbine extends TileEntityMachine implements IEnergySource 
{
    private int euOutput;
    private int steamBuffer;
    private final int STEAM_MULTIPLIER = 2;
    private TileEntity te;
    private ITankContainer itc;
    private ILiquidTank ilt;
    private LiquidStack ls;
    private final LiquidStack STEAM;
    private final String BOILER_LP = "railcraft.common.blocks.machine.beta.TileBoilerTankLow";
    private final String BOILER_HP = "railcraft.common.blocks.machine.beta.TileBoilerTankHigh";
    
    public TileEntitySteamTurbine(int machineId)
    {
        super(machineId);
        euOutput = 32;
        STEAM = LiquidDictionary.getLiquid("Steam", LiquidContainerRegistry.BUCKET_VOLUME);
    }
    
    private boolean isValidBoiler()
    {
        te = worldObj.getBlockTileEntity(xCoord, yCoord - 1, zCoord);
        
        if(te instanceof ITankContainer)
        {
            String s = te.getClass().getCanonicalName();

            return s.equalsIgnoreCase(BOILER_LP) || s.equalsIgnoreCase(BOILER_HP);
        }
        
        return false;
    }
    
    private void makeSteamParticle()
    {
        double xOffset = worldObj.rand.nextDouble() * 0.625D + 0.1875D;
        double zOffset = worldObj.rand.nextDouble() * 0.625D + 0.1875D;
        
        double vx = worldObj.rand.nextGaussian() * 0.02D;
        double vy = worldObj.rand.nextGaussian() * 0.02D;
        double vz = worldObj.rand.nextGaussian() * 0.02D;
        
        ParticleSteam steam = new ParticleSteam(worldObj, xCoord + xOffset, yCoord + 1.1D, zCoord + zOffset, vx, vy + 0.15D, vz, 1.0F, false);
        
        CDECore.proxy.spawnParticle(steam);
    }
    
    @Override
    public void updateEntity()
    {
        super.updateEntity();
        
        if(worldObj.isRemote && CDECore.particlesEnabled() && isActive())
        {
            makeSteamParticle();
        }
    }
    
    @Override
    protected void doWorkCycle()
    {   
        if(isValidBoiler())
        {
            int amountToDrain = STEAM_MULTIPLIER * euOutput;
            STEAM.amount = amountToDrain;
            itc = (ITankContainer)te;
            ilt = itc.getTank(ForgeDirection.UP, STEAM);

            if(ilt != null && ilt.getLiquid() != null)
            {
                ls = ilt.getLiquid();
                
                STEAM.amount = ilt.getCapacity() / 2 + amountToDrain;
                
                if(steamBuffer < amountToDrain && ls.containsLiquid(STEAM))
                {
                    ls = ilt.drain(amountToDrain, true);
                    
                    if(ls != null)
                    {
                        steamBuffer += ls.amount;
                    }
                }
                
                if(steamBuffer >= amountToDrain)
                {
                    EnergyTileSourceEvent event = new EnergyTileSourceEvent(this, euOutput);
                    MinecraftForge.EVENT_BUS.post(event);

                    int excess = STEAM_MULTIPLIER * event.amount;

                    steamBuffer -= amountToDrain - excess;
                }
            }
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
            
            return "Turbine output set to: " + euOutput + " EU/t";
        }
            
        return "Turbine output: " + euOutput + " EU/t";
    }
    
    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        euOutput = par1NBTTagCompound.getInteger("euOutput");
        steamBuffer = par1NBTTagCompound.getInteger("steamBuffer");
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("euOutput", euOutput);
        par1NBTTagCompound.setInteger("steamBuffer", steamBuffer);
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
        
    // CDE Sound
    @Override
    public String getResourceName()
    {
        return "breather.wav";
    }
}
