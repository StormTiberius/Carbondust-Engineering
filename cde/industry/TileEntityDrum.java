/**
 *
 * @author StormTiberius
 */

package cde.industry;

import cde.CDECore;
import cde.IndustryCore;
import cde.api.INetwork;
import cde.core.Defaults;
import cde.core.network.PacketTileParticle;
import cde.core.sound.PacketTileSound;
import cde.core.sound.TileEntityWithSound;
import com.eloraam.redpower.core.IPaintable;
import java.awt.Color;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;

public class TileEntityDrum extends TileEntityWithSound implements ITankContainer, IPaintable, INetwork
{    
    private final LiquidTank TANK;
    private final long NETWORK_UPDATE_INTERVAL;
    private long previousUpdateTime;
    private boolean isWorking,soundUpdateNeeded,recentlyUpdated;
    private int color,paint,counter;
    
    public TileEntityDrum()
    {
        TANK = new LiquidTank(LiquidContainerRegistry.BUCKET_VOLUME);
        NETWORK_UPDATE_INTERVAL = CDECore.getNetworkUpdateTime();
        color = IndustryCore.getLiquidColor().getRGB();
        paint = -1;
        counter = 70; // 3.5 Seconds
    }
    
    @Override
    public void updateEntity()
    {
        super.updateEntity();
        
        if(!worldObj.isRemote)
        {
            if(counter == 0)
            {
                isWorking = true;
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            }
            else if(counter == 69)
            {
                isWorking = false;
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            }

            if(counter < 70)
            {
                counter++;
            }
        }
    }
    
    public String useWrench(boolean flag)
    {
        String type;
        
        switch(TANK.getCapacity())
        {
            case Defaults.DRUM_CAPACITY_IRON: type = "Iron"; break;
            case Defaults.DRUM_CAPACITY_STEEL: type = "Steel"; break;
            default: type = "UNKNOWN"; break;
        }
        
        return type + " Drum by CDE Industries";
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        
        if(tag.hasKey("capacity"))
        {
            TANK.setCapacity(tag.getInteger("capacity"));
        
            if(tag.hasKey("color"))
            {
                color = tag.getInteger("color");
            }
            else
            {
                color = IndustryCore.getLiquidColor().getRGB();
            }
            
            if(tag.hasKey("paint"))
            {
                paint = tag.getInteger("paint");
            }
            
            if(tag.hasKey("liquid"))
            {
                TANK.setLiquid(LiquidStack.loadLiquidStackFromNBT(tag.getCompoundTag("liquid")));
            }
            else
            {
                TANK.setLiquid(null);
            }
        }
        
        if(tag.hasKey("isworking"))
        {
            isWorking = tag.getBoolean("isworking");
        }
        
        if(tag.hasKey("counter"))
        {
            counter = tag.getInteger("counter");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        
        tag.setInteger("capacity", TANK.getCapacity());
        tag.setInteger("color", color);
        tag.setInteger("paint", paint);
        
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
        
        tag.setInteger("counter", counter);
    }
    
    @Override
    public Packet getDescriptionPacket()
    {
        if(!worldObj.isRemote)
        {
            NBTTagCompound tag = new NBTTagCompound();

            writeToNBT(tag);

            return new Packet132TileEntityData(xCoord, yCoord, zCoord, 4, tag);
        }
        
        return super.getDescriptionPacket();
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
    
    @Override
    public void receivePacket(Object packet, EntityPlayer player)
    {
        if(packet instanceof PacketTileParticle)
        {
            PacketTileParticle ptp = (PacketTileParticle)packet;
            
            makeParticles(ptp.particle, ptp.count);
        }
    }
    
    public void makeParticles(String name, int count)
    {
        if(worldObj.isRemote)
        {
            double xOffset = 0.5D;
            double yOffset = 1.1D;
            double zOffset = 0.68359375D;
            
            for(int i = 0; i < 1 + count; i++)
            {
                worldObj.spawnParticle(name, xCoord + xOffset, yCoord + yOffset, zCoord + zOffset, 0.0D, 0.0D, 0.0D);
            }
        }
        else
        {
            PacketTileParticle ptp = new PacketTileParticle(this, name, count);
                    
            CDECore.proxy.sendToPlayers(ptp.getPacket(), worldObj, xCoord, yCoord, zCoord, 32);
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
        int amount = 0;
        
        if(tankIndex == 0)
        {
            boolean isEmpty = TANK.getLiquid() == null;
            
            amount = TANK.fill(resource, doFill);

            if(doFill && amount > 0)
            {
                soundUpdateNeeded = true;
                
                if(isEmpty)
                {
                    if(paint < 0 || paint > 15)
                    {
                        color = IndustryCore.getLiquidColor(resource.itemID).getRGB();
                    }
                    
                    worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                }
                
                updateCounter();
            }
        }
        
        updateSound();
        
        return amount;
    }

    @Override
    public LiquidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
    {
        return drain(0, maxDrain, doDrain);
    }

    @Override
    public LiquidStack drain(int tankIndex, int maxDrain, boolean doDrain)
    {   
        LiquidStack liquid = null;
        
        if(tankIndex == 0)
        {
            liquid = TANK.drain(maxDrain, doDrain);
            
            if(doDrain && liquid != null)
            {
                soundUpdateNeeded = true;
                
                if(TANK.getLiquid() == null)
                {
                    if(paint < 0 || paint > 15)
                    {
                        color = IndustryCore.getLiquidColor().getRGB();
                    }
                    
                    worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                }
                
                updateCounter();
            }
        }
        
        updateSound();
        
        return liquid;
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
        if(IndustryCore.getDrumPitch() != 100)
        {
            return (float)IndustryCore.getDrumPitch() / 100;
        }
        
        int amount = 0;
        
        if(TANK.getLiquid() != null)
        {
            amount = TANK.getLiquid().amount;
        }
        
        float pitch = 0.5F;
        
        pitch += 1.5F * amount / TANK.getCapacity();
        
        if(pitch > 2.0F)
        {
            pitch = 2.0F;
        }
        
        if(pitch < 0.5F)
        {
            pitch = 0.5F;
        }
        
        return pitch;
    }
    
    @Override
    protected float getDistOrRoll()
    {
        return 8.0F;
    }
        
    @Override
    public boolean tryPaint(int sub, int side, int index)
    {
        int i = 16 - index;
        
        return setDrumColor(i);
    }
    
    public Color getDrumColor()
    {
        return new Color(color);
    }
    
    public boolean setDrumColor(int index)
    {
        if(index < 0 || index > 15 || index == paint)
        {
            return false;
        }
        
        color = IndustryCore.getPaintColor(index).getRGB();
        paint = index;
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        
        return true;
    }
    
    public int getFillPercentage()
    {
        if(TANK.getLiquid() == null)
        {
            return 0;
        }
        
        int percent = TANK.getLiquid().amount * 100 / TANK.getCapacity();
        
        if(percent == 0)
        {
            percent = 1;
        }
        
        return percent;
    }
    
    private boolean isValidDirection(ForgeDirection fd)
    {
        return fd.equals(ForgeDirection.DOWN)  || fd.equals(ForgeDirection.UP);
    }
    
    private void updateCounter()
    {
        if(counter > 69)
        {
            counter = 0;
        }
        else
        {
            counter = 1;
        }
    }
    
    private void updateSound()
    {
        if(soundUpdateNeeded)
        {
            if(recentlyUpdated)
            {
                if(System.currentTimeMillis() - previousUpdateTime > NETWORK_UPDATE_INTERVAL)
                {
                    recentlyUpdated = false;
                }
            }
            else
            {
                CDECore.proxy.sendToPlayers(new PacketTileSound(this, false, true).getPacket(), worldObj, xCoord, yCoord, zCoord, 32);
                previousUpdateTime = System.currentTimeMillis();
                soundUpdateNeeded = false;
                recentlyUpdated = true;
            }
        }
    }
}
