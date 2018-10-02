/**
 *
 * @author StormTiberius
 */

package cde.world.ember;

import net.minecraft.util.ChunkCoordinates;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;

public class EmberEventManager
{
    private final int dimensionId;
    
    public EmberEventManager(int dimensionId)
    {
        this.dimensionId = dimensionId;
    }
    
    @ForgeSubscribe
    public void psibe(PlayerSleepInBedEvent event)
    {
        if(!event.entityPlayer.worldObj.isRemote && event.entityPlayer.dimension == dimensionId)
        {
            event.entityPlayer.setSpawnChunk(new ChunkCoordinates(event.x, event.y, event.z), false);
        }
    }
}
