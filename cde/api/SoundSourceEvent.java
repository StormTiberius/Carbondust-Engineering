/**
 *
 * @author StormTiberius
 */

package cde.api;

import net.minecraftforge.event.Event;

public class SoundSourceEvent extends Event
{
    public static enum Type
    {
        ADD,
        REMOVE,
        PLAY,
        STOP,
        VOLUME,
        PITCH,
    }
    
    public ISoundSource iss;
    public Type type;
    
    public SoundSourceEvent(ISoundSource iss, Type type)
    {
        this.iss = iss;
        this.type = type;
    }
}
