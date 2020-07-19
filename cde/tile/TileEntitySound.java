/**
 *
 * @author StormTiberius
 */

package cde.tile;

import cde.Carbon;
import cde.api.ISoundSource;
import cde.api.SoundSourceEvent;
import cde.core.Config;
import java.net.URL;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import paulscode.sound.SoundSystemConfig;

public abstract class TileEntitySound extends TileEntityBase implements ISoundSource
{
    protected abstract boolean emitSound();
    
    private boolean isPlaying,isMuted,isEmitting,flag,init;
    
    private boolean isTileAudioEnabled()
    {
        return Config.isAudioEnabled() && !getResourceName().isEmpty();
    }
    
    private void init()
    {
        if(worldObj.isRemote)
        {
            if(isTileAudioEnabled())
            {
                MinecraftForge.EVENT_BUS.post(new SoundSourceEvent(this, SoundSourceEvent.Type.ADD));
            }
        }
        
        init = true;
    }
    
    @Override
    public void updateEntity()
    {
        if(init)
        {
            if(worldObj.isRemote)
            {
                if(isTileAudioEnabled())
                {
                    flag = emitSound();
                    
                    if(isEmitting != flag)
                    {
                        isEmitting = flag;
                        
                        if(isEmitting)
                        {
                            MinecraftForge.EVENT_BUS.post(new SoundSourceEvent(this, SoundSourceEvent.Type.PLAY));
                        }
                        else
                        {
                            MinecraftForge.EVENT_BUS.post(new SoundSourceEvent(this, SoundSourceEvent.Type.STOP));
                        }
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
            if(isTileAudioEnabled())
            {
                MinecraftForge.EVENT_BUS.post(new SoundSourceEvent(this, SoundSourceEvent.Type.REMOVE));
            }
        }
        
        super.invalidate();
    }
    
    @Override
    public void onChunkUnload()
    {
        if(worldObj.isRemote)
        {
            if(isTileAudioEnabled())
            {
                MinecraftForge.EVENT_BUS.post(new SoundSourceEvent(this, SoundSourceEvent.Type.REMOVE));
            }
        }
        
        super.onChunkUnload();
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        
        isMuted = tag.getBoolean("isMuted");
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        
        tag.setBoolean("isMuted", isMuted);
    }
    
    @Override
    public boolean isMuted()
    {
        return isMuted;
    }
    
    @Override
    public void setMuted(boolean flag)
    {
        if(worldObj.isRemote)
        {
            if(isTileAudioEnabled())
            {
                if(flag)
                {
                    MinecraftForge.EVENT_BUS.post(new SoundSourceEvent(this, SoundSourceEvent.Type.MUTE));
                }
                else
                {
                    MinecraftForge.EVENT_BUS.post(new SoundSourceEvent(this, SoundSourceEvent.Type.UNMUTE));
                }
            }
        }
        
        isMuted = flag;
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
    public boolean isPriority()
    {
        return true;
    }
    
    @Override
    public String getSourceName()
    {
        return "cde_x" + xCoord + "_y" + yCoord + "_z" + zCoord;
    }
    
    protected URL getUrl()
    {
        return Carbon.class.getResource((new StringBuilder()).append("/cde/sounds/machine/").append(getResourceName()).toString());
    }
    
    /*
    @Override
    public String getResourceName()
    {
        return "";
    }
    */
    
    @Override
    public boolean isLooping()
    {
        return true;
    }
    
    @Override
    public float getOriginX()
    {
        return 0.5F + xCoord;
    }
    
    @Override
    public float getOriginY()
    {
        return 0.5F + yCoord;
    }
    
    @Override
    public float getOriginZ()
    {
        return 0.5F + zCoord;
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
        return 1.0F;
    }
    
    @Override
    public float getPitch()
    {
        return 1.0F;
    }
}
