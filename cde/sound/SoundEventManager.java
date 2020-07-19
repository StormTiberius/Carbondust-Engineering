/**
 *
 * @author StormTiberius
 */

package cde.sound;

import cde.Carbon;
import cde.api.SoundSourceEvent;
import cde.core.Config;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent.Unload;

public class SoundEventManager
{
    @ForgeSubscribe
    public void sem(SoundSourceEvent event)
    {
        switch(event.type)
        {
            case ADD: SoundHelper.addSoundSource(event.iss); break;
            case REMOVE: SoundHelper.removeSoundSource(event.iss); break;
            case PLAY: SoundHelper.playSoundSource(event.iss); break;
            case STOP: SoundHelper.stopSoundSource(event.iss); break;
            case VOLUME: SoundHelper.updateSoundSourceVolume(event.iss); break;
            case PITCH: SoundHelper.updateSoundSourcePitch(event.iss); break;
            case MUTE: SoundHelper.muteSoundSource(event.iss); break;
            case UNMUTE: SoundHelper.unmuteSoundSource(event.iss); break;
        }
    }
    
    @ForgeSubscribe
    public void sem(Unload event)
    {
        SoundHelper.removeAll();
    }
    
    @ForgeSubscribe
    public void sem(SoundLoadEvent event)
    {
        SoundHelper.initSoundHelper();
        
        if(Config.isAlternateRainSoundsEnabled())
        {
            for(int i = 1; i < 9; i++)
            {
                String name = "cde/ambient/weather/rain" + i + ".ogg";
                String path = "/cde/sounds/ambient/weather/rain" + i + ".ogg";
                
                event.manager.soundPoolSounds.addSound(name, Carbon.class.getResource((new StringBuilder()).append(path).toString()));
            }
        }
        
        if(Config.isAlternateExplosionSoundsEnabled())
        {
            for(int i = 1; i < 5; i++)
            {
                String name = "cde/random/explode" + i + ".ogg";
                String path = "/cde/sounds/random/explode" + i + ".ogg";
                
                event.manager.soundPoolSounds.addSound(name, Carbon.class.getResource((new StringBuilder()).append(path).toString()));
            }
        }
    }
    
    @ForgeSubscribe
    public void sem(PlaySoundEvent event)
    {
        if(Config.isAlternateRainSoundsEnabled())
        {
            if(event.name.equalsIgnoreCase("ambient.weather.rain"))
            {
                event.result = event.manager.soundPoolSounds.getRandomSoundFromSoundPool("cde.ambient.weather.rain");
            }
        }
        
        if(Config.isAlternateExplosionSoundsEnabled())
        {
            if(event.name.equalsIgnoreCase("random.explode"))
            {
                event.result = event.manager.soundPoolSounds.getRandomSoundFromSoundPool("cde.random.explode");
            }
        }
    }
}
