/**
 *
 * @author StormTiberius
 */

package cde.core.sound;

import cde.CDECore;
import cde.api.ISoundSource;
import cde.core.TileEntityCDE;
import java.net.URL;
import paulscode.sound.SoundSystemConfig;

public abstract class TileEntityWithSound extends TileEntityCDE implements ISoundSource
{
    protected abstract boolean isWorking();
    
    private boolean init,playing,active,flag;
    
    private void init()
    {
        if(worldObj.isRemote)
        {
            if(tileSoundsEnabled())
            {
                SoundHelper.addSource(this);
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
                if(tileSoundsEnabled())
                {
                    flag = isWorking();

                    if(active != flag)
                    {
                        active = flag;
                        SoundHelper.updateTileSound(this, active);
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
            if(tileSoundsEnabled())
            {
                SoundHelper.removeSource(this);
            }
        }
                
        super.invalidate();
    }

    @Override
    public void onChunkUnload()
    {
        if(worldObj.isRemote)
        {
            if(tileSoundsEnabled())
            {
                SoundHelper.removeSource(this);
            }
        }
        
        super.onChunkUnload();
    }
    
    private boolean tileSoundsEnabled()
    {
        return CDECore.playSounds() && getVolume() > 0.0F;
    }
    
    @Override
    public boolean isPriority()
    {
        return true;
    }
    
    @Override
    public boolean isLooping()
    {
        return true;
    }
    
    @Override
    public boolean isPlaying()
    {
        return playing;
    }
    
    @Override
    public void setPlaying(boolean flag)
    {
        playing = flag;
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
    public float getOriginX()
    {
        float f = 0.5F;
        
        if(xCoord < 0)
        {
            f *= -1;
        }
        
        return f + xCoord;
    }
    
    @Override
    public float getOriginY()
    {
        return 0.5F + yCoord;
    }
    
    @Override
    public float getOriginZ()
    {
        float f = 0.5F;
        
        if(zCoord < 0)
        {
            f *= -1;
        }
        
        return f + zCoord;
    }
    
    @Override
    public String getSourceName()
    {
        return "cde_x" + xCoord + "_y" + yCoord + "_z" + zCoord;
    }
    
    @Override
    public URL getResourceUrl()
    {
        return CDECore.class.getResource((new StringBuilder()).append("/cde/sounds/machine/").append(getResourceName()).toString());
    }
}
