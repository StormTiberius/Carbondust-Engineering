/**
 *
 * @author StormTiberius
 */

package cde.sound;

import cde.api.ISoundSource;
import cde.network.PacketSound;
import java.util.HashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.entity.player.EntityPlayer;

public class SoundHelper
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
            for(ISoundSource iss : SOURCES.values())
            {
                SoundManager.sndSystem.setVolume(iss.getSourceName(), 0.0F);
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
            SoundManager.sndSystem.setVolume(iss.getSourceName(), iss.getVolume() * soundVolume);
        }
    }
    
    protected static void addSoundSource(ISoundSource iss)
    {
        SoundManager.sndSystem.newSource(iss.isPriority(), iss.getSourceName(), iss.getResourceUrl(), iss.getResourceName(), iss.isLooping(), iss.getOriginX(), iss.getOriginY(), iss.getOriginZ(), iss.getAttModel(), iss.getDistOrRoll());
        SoundManager.sndSystem.setVolume(iss.getSourceName(), iss.getVolume() * soundVolume);
        SoundManager.sndSystem.setPitch(iss.getSourceName(), iss.getPitch());
        SOURCES.put(iss.getResourceName(), iss);
    }
    
    protected static void removeSoundSource(ISoundSource iss)
    {
        if(SOURCES.containsKey(iss.getSourceName()))
        {
            SOURCES.remove(iss.getSourceName());
            stopSoundSource(iss);
            SoundManager.sndSystem.removeSource(iss.getSourceName());
        }
    }
    
    protected static void playSoundSource(ISoundSource iss)
    {
        if(!iss.isPlaying())
        {
            SoundManager.sndSystem.play(iss.getSourceName());
            iss.setPlaying(true);
        }
    }
    
    protected static void stopSoundSource(ISoundSource iss)
    {
        if(iss.isPlaying())
        {
            SoundManager.sndSystem.stop(iss.getSourceName());
            iss.setPlaying(false);
        }
    }
    
    protected static void updateSoundSourceVolume(ISoundSource iss)
    {
        if(SOURCES.containsKey(iss.getSourceName()))
        {
            SoundManager.sndSystem.setVolume(iss.getSourceName(), iss.getVolume() * soundVolume);
        }
    }
    
    protected static void updateSoundSourcePitch(ISoundSource iss)
    {
        if(SOURCES.containsKey(iss.getSourceName()))
        {
            SoundManager.sndSystem.setPitch(iss.getSourceName(), iss.getPitch());
        }
    }
    
    protected static void removeAll()
    {
        for(ISoundSource iss : SOURCES.values())
        {
            stopSoundSource(iss);
            SoundManager.sndSystem.removeSource(iss.getSourceName());
        }
        
        SOURCES.clear();
    }
    
    public static void onNetworkPacketArrival(PacketSound packet, EntityPlayer player)
    {
        if(packet.playerChangedDimension)
        {
            removeAll();
        }
    }
}
