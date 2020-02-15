/**
 *
 * @author StormTiberius
 */

package cde.terrene;

import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.world.World;

public class EntitySquidTerrene extends EntitySquid
{
    public EntitySquidTerrene(World world)
    {
        super(world);
    }
    
    @Override
    public boolean getCanSpawnHere()
    {
        return this.posY > 95.0D && this.posY < 113.0D && this.worldObj.checkIfAABBIsClear(this.boundingBox);
    }
}
