/**
 *
 * @author StormTiberius
 */

package cde.industry;

import cde.IndustryCore;
import cde.core.sound.TileEntityWithSound;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;

public class TileEntityDrum extends TileEntityWithSound implements ITankContainer
{    
    private final LiquidTank TANK;
    private boolean isRedstonePowered,isWorking,flag;
    private int counter = 60; // 5 Seconds
    
    public TileEntityDrum()
    {
        TANK = new LiquidTank(BlockDrum.DRUM_CAPACITY_STEEL);
    }
    
    public TileEntityDrum(int capacity)
    {
        TANK = new LiquidTank(capacity);
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
        
        if(!worldObj.isRemote)
        {
            doWorkCycle();
        }
    }
    
    public void doWorkCycle()
    {
        if(counter == 0)
        {
            isWorking = true;
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }
        else if(counter == 59)
        {
            isWorking = false;
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }
        
        if(counter < 60)
        {
            counter++;
        }
    }
    
    protected boolean isPowered()
    {
        return isRedstonePowered;
    }
    
    public String useWrench(boolean flag)
    {
        String liquidName = TANK.getLiquid().asItemStack().getDisplayName();
        int i = TANK.getLiquid().amount;
        int ii = TANK.getCapacity();
        
        return liquidName + " Drum: " + i + " / " + ii;
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);

        TANK.setCapacity(tag.getInteger("capacity"));
        
        if(tag.hasKey("liquid"))
        {
            TANK.setLiquid(LiquidStack.loadLiquidStackFromNBT(tag.getCompoundTag("liquid")));
        }
        
        isWorking = tag.getBoolean("isworking");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        
        tag.setInteger("capacity", TANK.getCapacity());
        
        LiquidStack liquid = TANK.getLiquid();
        
        if(liquid != null)
        {
            tag.setCompoundTag("liquid", liquid.writeToNBT(new NBTTagCompound()));
        }
        else if(tag.hasKey("liquid"))
        {
            tag.removeTag("liquid");
        }
        
        tag.setBoolean("isworking", isWorking);
    }
    
    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound tag = new NBTTagCompound();
        
        writeToNBT(tag);
        
        return new Packet132TileEntityData(xCoord, yCoord, zCoord, 4, tag);
    }
    
    @Override
    public void onDataPacket(INetworkManager inm, Packet132TileEntityData pkt)
    {
        if(worldObj.isRemote)
        {
            readFromNBT(pkt.customParam1);
            worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
        }
    }
    
    // ITankContainer methods
    @Override
    public int fill(ForgeDirection from, LiquidStack resource, boolean doFill)
    {
        return fill(0, resource, doFill);
    }

    @Override
    public int fill(int tankIndex, LiquidStack resource, boolean doFill)
    {
        if(tankIndex == 0)
        {
            int i = TANK.fill(resource, doFill); 
            
            if(i > 0)
            {
                counter = 0;
            }
            
            return i;
        }
        
        return 0;
    }

    @Override
    public LiquidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
    {
        return drain(0, maxDrain, doDrain);
    }

    @Override
    public LiquidStack drain(int tankIndex, int maxDrain, boolean doDrain)
    {
        if(tankIndex == 0)
        {
            LiquidStack ls = TANK.drain(maxDrain, doDrain);
            
            if(ls != null)
            {
                counter = 0;
            }
            
            return ls;
        }
                    
        return null;
    }

    @Override
    public ILiquidTank[] getTanks(ForgeDirection direction) 
    {
        if(isValidDirection(direction))
        {
            return new ILiquidTank[] {TANK};
        }
        
        return new LiquidTank[0];
    }

    @Override
    public ILiquidTank getTank(ForgeDirection direction, LiquidStack type)
    {
        if(isValidDirection(direction))
        {
            return TANK;
        }
            
        return null;
    }
        
    // Ambient Sounds
    @Override
    public boolean isWorking()
    {
        return isWorking;
    }
    
    @Override
    public String getSoundFileName()
    {
        return "storage-tank.ogg";
    }
    
    @Override
    public float getVolume()
    {
        return 1.0F / 100 * IndustryCore.getDrumVolume();
    }
    
    @Override
    public float getPitch()
    {
        return 1.0F / 100 * IndustryCore.getDrumPitch();
    }
    
    @Override
    protected float getDistOrRoll()
    {
        return 8.0F;
    }
    
    private boolean isValidDirection(ForgeDirection fd)
    {
        return fd.equals(ForgeDirection.DOWN)  || fd.equals(ForgeDirection.UP);
    }
}
