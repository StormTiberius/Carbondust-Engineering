/**
 *
 * @author StormTiberius
 */

package cde.core.sound;

import cde.CDECore;
import cde.api.SoundSourceEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent.Unload;

public class SoundEventManager
{
    @ForgeSubscribe
    public void sem(SoundSourceEvent event)
    {
        if(event.world.isRemote)
        {
            switch(event.type)
            {
                case SoundSourceEvent.STOP: SoundManager.stopTileSound(event.iss); break;
                case SoundSourceEvent.PLAY: SoundManager.playTileSound(event.iss); break;
                case SoundSourceEvent.VOLUME: SoundManager.setVolume(event.iss); break;
                case SoundSourceEvent.PITCH: SoundManager.setPitch(event.iss); break;
                case SoundSourceEvent.LOAD: SoundManager.addSource(event.iss); break;
                case SoundSourceEvent.UNLOAD: SoundManager.removeSource(event.iss); break;
            }
        }
    }
    
    @ForgeSubscribe
    public void sem(Unload event)
    {
        SoundManager.retireAll();
    }
    
    @ForgeSubscribe
    public void sem(SoundLoadEvent event)
    {
        SoundManager.initSoundHelper();
        
        if(CDECore.altRainSounds())
        {
            for(int i = 1; i < 9; i++)
            {
                String name = "cde/ambient/weather/rain" + i + ".ogg";
                String path = "/cde/sounds/ambient/weather/rain" + i + ".ogg";

                event.manager.soundPoolSounds.addSound(name, CDECore.class.getResource((new StringBuilder()).append(path).toString()));
            }
        }
        
        if(CDECore.altExplosionSounds())
        {
            for(int i = 1; i < 5; i++)
            {
                String name = "cde/random/explode" + i + ".ogg";
                String path = "/cde/sounds/random/explode" + i + ".ogg";

                event.manager.soundPoolSounds.addSound(name, CDECore.class.getResource((new StringBuilder()).append(path).toString()));
            }
        }
    }
    
    @ForgeSubscribe
    public void sem(PlaySoundEvent event)
    {
        if(CDECore.altRainSounds())
        {
            if(event.name.equalsIgnoreCase("ambient.weather.rain"))
            {
                event.result = event.manager.soundPoolSounds.getRandomSoundFromSoundPool("cde.ambient.weather.rain");
            }
        }
        
        if(CDECore.altExplosionSounds())
        {
            if(event.name.equalsIgnoreCase("random.explode"))
            {
                event.result = event.manager.soundPoolSounds.getRandomSoundFromSoundPool("cde.random.explode");
            }
        }
    }
}
