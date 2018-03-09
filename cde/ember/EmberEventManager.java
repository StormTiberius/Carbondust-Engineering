/**
 *
 * @author StormTiberius
 */

package cde.ember;

import net.minecraft.util.ChunkCoordinates;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;

public class EmberEventManager
{
    @ForgeSubscribe
    public void psibe(PlayerSleepInBedEvent event)
    {
        if(!event.entityPlayer.worldObj.isRemote)
        {
            event.entityPlayer.setSpawnChunk(new ChunkCoordinates(event.x, event.y, event.z), false);
        }
    }
}
