/**
 *
 * @author StormTiberius
 */

package cde.terrene;

import cde.TerreneCore;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.world.World;

public class EntitySquidTerrene extends EntitySquid
{
    private double minY = 95.0D;
    private double maxY = 113.0D;
    
    public EntitySquidTerrene(World world)
    {
        super(world);
    }
    
    @Override
    public boolean getCanSpawnHere()
    {
        if(worldObj.getWorldInfo().getTerrainType().getWorldTypeID() == TerreneCore.getEmberId())
        {
            minY = 20.0D;
            maxY = 253.0D;
        }
        
        return this.posY > minY && this.posY < maxY && this.worldObj.checkIfAABBIsClear(this.boundingBox);
    }
}
