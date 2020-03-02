/**
 *
 * @author StormTiberius
 */

package cde.core.sound;

import cde.CDECore;
import cde.api.ISoundSource;
import cde.api.SoundSourceEvent;
import cde.core.TileEntityCde;
import java.net.URL;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import paulscode.sound.SoundSystemConfig;

public abstract class TileEntityWithSound extends TileEntityCde implements ISoundSource
{
    protected abstract boolean getIsActive();
    
    protected int volume,pitch;
    private boolean init,active,flag,isMuted,isPlaying;
    
    TileEntityWithSound(int volume, int pitch)
    {
        this.volume = volume;
        this.pitch = pitch;
    }
    
    private boolean getIsTileSoundEnabled()
    {
        return CDECore.playSounds() && !getResourceName().isEmpty();
    }
    
    private void init()
    {
        if(worldObj.isRemote)
        {
            if(getIsTileSoundEnabled())
            {
                MinecraftForge.EVENT_BUS.post(new SoundSourceEvent(this, SoundSourceEvent.LOAD));
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
                if(getIsTileSoundEnabled())
                {
                    flag = getIsActive();

                    if(active != flag)
                    {
                        active = flag;
                        
                        SoundSourceEvent event;
                        
                        if(active)
                        {
                            event = new SoundSourceEvent(this, SoundSourceEvent.PLAY);
                        }
                        else
                        {
                            event = new SoundSourceEvent(this, SoundSourceEvent.STOP);
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
            if(getIsTileSoundEnabled())
            {
                MinecraftForge.EVENT_BUS.post(new SoundSourceEvent(this, SoundSourceEvent.UNLOAD));
            }
        }
                
        super.invalidate();
    }

    @Override
    public void onChunkUnload()
    {
        if(worldObj.isRemote)
        {
            if(getIsTileSoundEnabled())
            {
                MinecraftForge.EVENT_BUS.post(new SoundSourceEvent(this, SoundSourceEvent.UNLOAD));
            }
        }
        
        super.onChunkUnload();
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        volume = tag.getInteger("volume");
        pitch = tag.getInteger("pitch");
        isMuted = tag.getBoolean("muted");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        tag.setInteger("volume", volume);
        tag.setInteger("pitch", pitch);
        tag.setBoolean("muted", isMuted);
    }
    
    @Override
    public boolean getIsMuted()
    {
        return isMuted;
    }
    
    @Override
    public void setIsMuted(boolean flag)
    {
        if(flag != isMuted)
        {
            SoundSourceEvent event;
            
            if(flag)
            {
                event = new SoundSourceEvent(this, SoundSourceEvent.UNLOAD);
            }
            else
            {
                event = new SoundSourceEvent(this, SoundSourceEvent.LOAD);
            }
            
            MinecraftForge.EVENT_BUS.post(event);
            
            isMuted = flag;
        }
    }
    
    @Override
    public boolean getIsPlaying()
    {
        return isPlaying;
    }
    
    @Override
    public void setIsPlaying(boolean flag)
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
    public boolean getIsPriority()
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
    public boolean getIsLooping()
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
        return 0.01F * volume;
    }
    
    @Override
    public float getPitch()
    {
        return 0.01F * pitch;
    }
}
