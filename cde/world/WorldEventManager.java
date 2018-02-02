/**
 *
 * @author StormTiberius
 */

package cde.world;

import net.minecraftforge.event.Event;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate;

public class WorldEventManager
{
    @ForgeSubscribe
    public void populate(Populate event)
    {
        if(event.world.provider.getDimensionName().equalsIgnoreCase("Pacific") && event.type.equals(Populate.EventType.CUSTOM))
        {
            event.setResult(Event.Result.DENY);
        }
    }
}
