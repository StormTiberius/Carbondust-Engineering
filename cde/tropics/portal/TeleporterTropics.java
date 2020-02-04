/**
 *
 * @author StormTiberius
 */

package cde.tropics.portal;

import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterTropics extends Teleporter
{
    public TeleporterTropics(WorldServer ws)
    {
        super(ws);
    }
    
    @Override
    public void placeInPortal(Entity entity, double posX, double posY, double posZ, float rotationYaw)
    {
        
    }
}
