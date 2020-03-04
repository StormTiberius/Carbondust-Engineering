/**
 *
 * @author StormTiberius
 */

package cde.core.sound;

import cde.core.network.PacketSound;
import cde.api.ISoundSource;
import cde.core.network.PacketIds;
import java.util.HashMap;
import net.minecraft.client.Minecraft;
import static net.minecraft.client.audio.SoundManager.sndSystem;
import net.minecraft.entity.player.EntityPlayer;

public class SoundManager
{
    private static final HashMap<String, ISoundSource> SOURCES = new HashMap<String, ISoundSource>();
    private static Minecraft mc;
    private static boolean init,resume;
    private static float soundVolume,masterVolume;
    
    protected static void initSoundHelper()
    {
        mc = Minecraft.getMinecraft();
        soundVolume = mc.gameSettings.soundVolume;
        init = true;
    }
    
    protected static void onUpdate()
    {
        if(init)
        {
            masterVolume = mc.gameSettings.soundVolume;
            
            if(!resume && soundVolume != masterVolume)
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
            for(ISoundSource iss : SOURCES.values())
            {
                sndSystem.setVolume(iss.getSourceName(), 0.0F);
            }
        }
        else
        {
            updateVolume();
        }
    }
    
    private static void updateVolume()
    {
        for(ISoundSource iss : SOURCES.values())
        {
            sndSystem.setVolume(iss.getSourceName(), iss.getVolume() * soundVolume);
        }
    }
    
    protected static void setVolume(ISoundSource iss)
    {
        if(SOURCES.containsKey(iss.getSourceName()))
        {
            sndSystem.setVolume(iss.getSourceName(), iss.getVolume() * soundVolume);
        }
    }
    
    protected static void setVolume(String key, float volume)
    {
        if(SOURCES.containsKey(key))
        {
            sndSystem.setVolume(key, volume * soundVolume);
        }
    }
    
    protected static void setPitch(ISoundSource iss)
    {
        if(SOURCES.containsKey(iss.getSourceName()))
        {
            sndSystem.setPitch(iss.getSourceName(), iss.getPitch());
        }
    }
    
    protected static void setPitch(String key, float pitch)
    {
        if(SOURCES.containsKey(key))
        {
            sndSystem.setPitch(key, pitch);
        }
    }
    
    protected static void playTileSound(ISoundSource iss)
    {
        if(!iss.isPlaying())
        {
            sndSystem.play(iss.getSourceName());
            iss.setPlaying(true);
        }
    }
    
    protected static void stopTileSound(ISoundSource iss)
    {
        if(iss.isPlaying())
        {
            sndSystem.stop(iss.getSourceName());
            iss.setPlaying(false);
        }
    }
    
    protected static void addSource(ISoundSource iss)
    {
        sndSystem.newSource(iss.isPriority(), iss.getSourceName(), iss.getResourceUrl(), iss.getResourceName(), iss.isLooping(), iss.getOriginX(), iss.getOriginY(), iss.getOriginZ(), iss.getAttModel(), iss.getDistOrRoll());
        sndSystem.setVolume(iss.getSourceName(), iss.getVolume() * soundVolume);
        sndSystem.setPitch(iss.getSourceName(), iss.getPitch());
        SOURCES.put(iss.getSourceName(), iss);
    }
    
    protected static void removeSource(ISoundSource iss)
    {
        if(SOURCES.containsKey(iss.getSourceName()))
        {
            retireSource(SOURCES.get(iss.getSourceName()));
        }
    }
    
    protected static void removeSource(String name)
    {
        if(SOURCES.containsKey(name))
        {
            retireSource(SOURCES.get(name));
        }
    }
    
    protected static void retireSource(ISoundSource iss)
    {
        stopTileSound(iss);
        sndSystem.removeSource(iss.getSourceName());
        SOURCES.remove(iss.getSourceName());
    }
    
    protected static void retireAll()
    {
        for(ISoundSource iss : SOURCES.values())
        {
            stopTileSound(iss);
            sndSystem.removeSource(iss.getSourceName());
        }
        
        SOURCES.clear();
    }
    
    public static void receivePacket(PacketSound packet, EntityPlayer player)
    {
        switch(packet.getID())
        {
            case PacketIds.SOUND: retireAll(); break;
        }
    }
}
