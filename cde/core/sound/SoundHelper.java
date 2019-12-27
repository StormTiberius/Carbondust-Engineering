/**
 *
 * @author StormTiberius
 */

package cde.core.sound;

import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import static net.minecraft.client.audio.SoundManager.sndSystem;
import net.minecraft.entity.player.EntityPlayer;

public class SoundHelper
{
    private static final ArrayList SOURCES = new ArrayList<TileEntityWithSound>();
    private static Minecraft mc;
    private static boolean init,resume;
    private static float soundVolume,masterVolume;
    
    public static void initSoundHelper()
    {
        mc = Minecraft.getMinecraft();
        soundVolume = mc.gameSettings.soundVolume;
        init = true;
    }
    
    public static void onUpdate()
    {
        if(init)
        {
            masterVolume = mc.gameSettings.soundVolume;
            
            if(soundVolume != masterVolume)
            {
                soundVolume = masterVolume;
                updateVolume();
            }
            
            if(mc.isGamePaused && !resume)
            {
                muteSources(true);
                resume = true;
            }
            else if(!mc.isGamePaused && resume)
            {
                muteSources(false);
                resume = false;
            }
        }
    }
    
    private static void muteSources(boolean flag)
    {
        if(flag)
        {
            if(!SOURCES.isEmpty())
            {
                for(Object object : SOURCES)
                {
                    TileEntityWithSound te = (TileEntityWithSound)object;
                    sndSystem.setVolume(te.getSourceName(), 0.0F);
                }
            }
        }
        else
        {
            updateVolume();
        }
    }
    
    private static void updateVolume()
    {
        if(!SOURCES.isEmpty())
        {
            for(Object object : SOURCES)
            {
                TileEntityWithSound te = (TileEntityWithSound)object;
                sndSystem.setVolume(te.getSourceName(), te.getVolume() * soundVolume);
            }
        }
    }
    
    public static void setVolume(TileEntityWithSound a)
    {
        if(!SOURCES.isEmpty())
        {
            for(Object object : SOURCES)
            {
                TileEntityWithSound b = (TileEntityWithSound)object;
                
                if(a.xCoord == b.xCoord && a.yCoord == b.yCoord && a.zCoord == b.zCoord)
                {
                    sndSystem.setVolume(a.getSourceName(), a.getVolume() * soundVolume);
                    break;
                }
            }
        }
    }
        
    public static void setPitch(TileEntityWithSound a)
    {
        if(!SOURCES.isEmpty())
        {
            for(Object object : SOURCES)
            {
                TileEntityWithSound b = (TileEntityWithSound)object;
                
                if(a.xCoord == b.xCoord && a.yCoord == b.yCoord && a.zCoord == b.zCoord)
                {
                    sndSystem.setPitch(a.getSourceName(), a.getPitch());
                    break;
                }
            }
        }
    }
    
    public static void updateTileSound(TileEntityWithSound te, boolean active)
    {
        if(active)
        {
            playTileSound(te);
        }
        else
        {
            stopTileSound(te);
        }
    }
    
    private static void playTileSound(TileEntityWithSound te)
    {
        if(!te.isPlaying())
        {
            sndSystem.play(te.getSourceName());
            te.setPlaying(true);
        }
    }
    
    private static void stopTileSound(TileEntityWithSound te)
    {
        if(te.isPlaying())
        {
            sndSystem.stop(te.getSourceName());
            te.setPlaying(false);
        }
    }
    
    public static void addSource(TileEntityWithSound te)
    {
        SoundSource src = te.getSoundSource();
        
        sndSystem.newSource(src.priority, src.sourceName, src.url, src.identifier, src.toLoop, src.x, src.y, src.z, src.attModel, src.distOrRoll);
        sndSystem.setVolume(src.sourceName, te.getVolume() * soundVolume);
        sndSystem.setPitch(src.sourceName, te.getPitch());
        SOURCES.add(te);
    }
    
    public static void removeSource(TileEntityWithSound a)
    {
        Object temp = null;
        
        for(Object object : SOURCES)
        {
            TileEntityWithSound b = (TileEntityWithSound)object;
            
            if(a.xCoord == b.xCoord && a.yCoord == b.yCoord && a.zCoord == b.zCoord)
            {
                temp = object;
                break;
            }
        }
        
        if(temp != null)
        {
            SOURCES.remove(temp);
            stopTileSound(a);
            sndSystem.removeSource(a.getSourceName());
        }
    }
    
    public static void removeAll()
    {
        if(!SOURCES.isEmpty())
        {
            for(Object object : SOURCES)
            {
                TileEntityWithSound te = (TileEntityWithSound)object;
                stopTileSound(te);
                sndSystem.removeSource(te.getSourceName());
            }

            SOURCES.clear();
        }
    }
    
    public static void receivePacket(PacketTileSound packet, EntityPlayer player)
    {
        for(Object o : SOURCES)
        {
            TileEntityWithSound te = (TileEntityWithSound)o;

            if(te.xCoord == packet.xCoord && te.yCoord == packet.yCoord && te.zCoord == packet.zCoord)
            {
                if(packet.updateVolume)
                {
                    sndSystem.setVolume(te.getSourceName(), packet.volume * soundVolume);
                }

                if(packet.updatePitch)
                {
                    sndSystem.setPitch(te.getSourceName(), packet.pitch);
                }
            }
        }
    }
}
