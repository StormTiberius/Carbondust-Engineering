/**
 *
 * @author StormTiberius
 */

package cde.core.sound;

import cde.CDECore;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent.Unload;

public class SoundEventManager
{
    @ForgeSubscribe
    public void sem(SoundLoadEvent event)
    {
        SoundHelper.initSoundHelper();
        
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
    public void sem(Unload event)
    {   
        SoundHelper.removeAll();
    }
    
    @ForgeSubscribe
    public void sem(PlaySoundEvent event)
    {        
        if(CDECore.altExplosionSounds())
        {
            if(event.name.equalsIgnoreCase("random.explode"))
            {
                event.result = event.manager.soundPoolSounds.getRandomSoundFromSoundPool("cde.random.explode");
            }
        }
    }
}
