/**
 *
 * @author StormTiberius
 */

package cde.api;

import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent;

public class SoundSourceEvent extends WorldEvent
{
    public static enum Action
    {
        ADD,
        REMOVE,
        PLAY,
        STOP,
        VOLUME,
        PITCH,
        MUTE,
        UNMUTE,
    }
    
    public ISoundSource iss;
    public Action action;
    
    public SoundSourceEvent(World world, ISoundSource iss, Action action)
    {
        super(world);
        
        this.iss = iss;
        this.action = action;
    }
}
