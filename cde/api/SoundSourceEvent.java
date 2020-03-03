/**
 *
 * @author StormTiberius
 */

package cde.api;

import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent;

public class SoundSourceEvent extends WorldEvent
{
    public static final int PLAY = 0;
    public static final int STOP = 1;
    public static final int VOLUME = 2;
    public static final int PITCH = 3;
    public static final int LOAD = 4;
    public static final int UNLOAD = 5;
    
    public final ISoundSource iss;
    public final int type;
    
    public SoundSourceEvent(World world, ISoundSource iss, int type)
    {
        super(world);
        this.iss = iss;
        this.type = type;
    }
}
