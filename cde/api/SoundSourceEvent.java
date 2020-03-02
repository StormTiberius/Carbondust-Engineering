/**
 *
 * @author StormTiberius
 */

package cde.api;

import net.minecraftforge.event.Event;

public class SoundSourceEvent extends Event
{
    public static final int PLAY = 0;
    public static final int STOP = 1;
    public static final int VOLUME = 2;
    public static final int PITCH = 3;
    public static final int LOAD = 4;
    public static final int UNLOAD = 5;
    
    public ISoundSource iss;
    public int type;
    
    public SoundSourceEvent(ISoundSource iss, int type)
    {
        this.iss = iss;
        this.type = type;
    }
}
