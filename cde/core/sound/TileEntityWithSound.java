/**
 *
 * @author StormTiberius
 */

package cde.core.sound;

import cde.CDECore;
import cde.api.INetwork;
import cde.api.ISoundSource;
import cde.api.SoundSourceEvent;
import cde.core.TileEntityCde;
import cde.core.network.PacketTileSound;
import java.net.URL;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import paulscode.sound.SoundSystemConfig;

public abstract class TileEntityWithSound extends TileEntityCde implements ISoundSource, INetwork
{
    protected abstract boolean getEmitSound();
    
    private boolean init,isMuted,isPlaying,defaultVolume,defaultPitch;
    protected int volumePercent,pitchPercent;
    protected float volume,pitch;
    
    public TileEntityWithSound()
    {
        super();
        this.defaultVolume = true;
        this.defaultPitch = true;
    }
    
    private void init()
    {
        if(worldObj.isRemote)
        {
            if(isTileSoundEnabled())
            {
                MinecraftForge.EVENT_BUS.post(new SoundSourceEvent(worldObj, this, SoundSourceEvent.LOAD));
            }
        }
        
        init = true;
    }
    
    protected boolean isTileSoundEnabled()
    {
        return CDECore.playSounds() && !getResourceName().isEmpty();
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        
        isMuted = tag.getBoolean("isMuted");
        defaultVolume = tag.getBoolean("defaultVolume");
        defaultPitch = tag.getBoolean("defaultPitch");
        volumePercent = tag.getInteger("volumePercent");
        pitchPercent = tag.getInteger("pitchPercent");
        volume = tag.getFloat("volume");
        pitch = tag.getFloat("pitch");
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        
        tag.setBoolean("isMuted", isMuted);
        tag.setBoolean("defaultVolume", defaultVolume);
        tag.setBoolean("defaultPitch", defaultPitch);
        tag.setInteger("volumePercent", volumePercent);
        tag.setInteger("pitchPercent", pitchPercent);
        tag.setFloat("volume", volume);
        tag.setFloat("pitch", pitch);
    }
    
    @Override
    public void updateEntity()
    {
        if(init)
        {   
            if(worldObj.isRemote)
            {
                if(isTileSoundEnabled())
                {
                    boolean flag = getEmitSound();
                    
                    if(flag != isPlaying())
                    {
                        // isPlaying() is set in SoundManager so no need to set it here.
                        
                        SoundSourceEvent event;
                        
                        if(flag)
                        {
                            event = new SoundSourceEvent(worldObj, this, SoundSourceEvent.PLAY);
                        }
                        else
                        {
                            event = new SoundSourceEvent(worldObj, this, SoundSourceEvent.STOP);
                        }
                        
                        MinecraftForge.EVENT_BUS.post(event);
                    }
                }
            }
        }
        else
        {
            init();
        }
    }
    
    @Override
    public void invalidate()
    {
        if(worldObj.isRemote)
        {
            if(isTileSoundEnabled())
            {
                MinecraftForge.EVENT_BUS.post(new SoundSourceEvent(worldObj, this, SoundSourceEvent.UNLOAD));
            }
        }
                
        super.invalidate();
    }
    
    @Override
    public void onChunkUnload()
    {
        if(worldObj.isRemote)
        {
            if(isTileSoundEnabled())
            {
                MinecraftForge.EVENT_BUS.post(new SoundSourceEvent(worldObj, this, SoundSourceEvent.UNLOAD));
            }
        }
        
        super.onChunkUnload();
    }
    
    @Override
    public boolean isMuted()
    {
        return isMuted;
    }
    
    @Override
    public void setMuted(boolean flag)
    {
        if(flag != isMuted)
        {
            SoundSourceEvent event;
            
            if(flag)
            {
                event = new SoundSourceEvent(worldObj, this, SoundSourceEvent.UNLOAD);
            }
            else
            {
                event = new SoundSourceEvent(worldObj, this, SoundSourceEvent.LOAD);
            }
            
            MinecraftForge.EVENT_BUS.post(event);
            
            isMuted = flag;
        }
    }
    
    @Override
    public boolean isPlaying()
    {
        return isPlaying;
    }
    
    @Override
    public void setPlaying(boolean flag)
    {
        isPlaying = flag;
    }
    
    @Override
    public int getSourceX()
    {
        return xCoord;
    }
    
    @Override
    public int getSourceY()
    {
        return yCoord;
    }
    
    @Override
    public int getSourceZ()
    {
        return zCoord;
    }
    
    @Override
    public boolean isPriority()
    {
        return true;
    }
    
    @Override
    public String getSourceName()
    {
        return "cde_x" + getSourceX() + "_y" + getSourceY() + "_z" + getSourceZ();
    }
    
    @Override
    public URL getResourceUrl()
    {
        return CDECore.class.getResource((new StringBuilder()).append("/cde/sounds/machine/").append(getResourceName()).toString());
    }
    
    @Override
    public boolean isLooping()
    {
        return true;
    }
    
    @Override
    public float getOriginX()
    {
        return 0.5F + getSourceX();
    }
    
    @Override
    public float getOriginY()
    {
        return 0.5F + getSourceY();
    }
    
    @Override
    public float getOriginZ()
    {
        return 0.5F + getSourceZ();
    }
    
    @Override
    public int getAttModel()
    {
        return SoundSystemConfig.ATTENUATION_LINEAR;
    }
    
    @Override
    public float getDistOrRoll()
    {
        return 16.0F;
    }
    
    @Override
    public float getVolume()
    {
        if(defaultVolume)
        {
            return 0.01F * volumePercent;
        }
        
        return volume;
    }
    
    @Override
    public float getPitch()
    {
        if(defaultPitch)
        {
            return 0.01F * pitchPercent;
        }
        
        return pitch;
    }
    
    @Override
    public void receivePacket(Object packet, EntityPlayer player)
    {
        if(packet instanceof PacketTileSound)
        {
            PacketTileSound pts = (PacketTileSound)packet;
            
            if(pts.updateVolume)
            {
                volume = pts.volume;
                
                if(defaultVolume)
                {
                    defaultVolume = false;
                }
                
                MinecraftForge.EVENT_BUS.post(new SoundSourceEvent(worldObj, this, SoundSourceEvent.VOLUME));
            }
            
            if(pts.updatePitch)
            {
                pitch = pts.pitch;
                
                if(defaultPitch)
                {
                    defaultPitch = false;
                }
                
                MinecraftForge.EVENT_BUS.post(new SoundSourceEvent(worldObj, this, SoundSourceEvent.PITCH));
            }
        }
    }
}
