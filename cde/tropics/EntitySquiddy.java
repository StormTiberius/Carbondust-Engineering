/**
 *
 * @author StormTiberius
 */

package cde.tropics;

import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.world.World;

public class EntitySquiddy extends EntitySquid
{
    public EntitySquiddy(World world)
    {
        super(world);
    }
    
    @Override
    public boolean getCanSpawnHere()
    {
        return this.posY > 109.0D && this.posY < 127.0D && worldObj.checkIfAABBIsClear(boundingBox);
    }
}
