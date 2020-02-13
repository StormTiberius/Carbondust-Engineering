/**
 *
 * @author StormTiberius
 */

package cde.terrene;

import cde.TerreneCore;
import java.util.Calendar;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityBatTerrene extends EntityBat
{
    public EntityBatTerrene(World world)
    {
        super(world);
    }
    
    @Override
    public boolean getCanSpawnHere()
    {
        int var1 = MathHelper.floor_double(this.boundingBox.minY);

        int y = 113;
        
        if(worldObj.getWorldInfo().getTerrainType().getWorldTypeID() == TerreneCore.getEmberId())
        {
            y = 253;
        }
        
        if (var1 >= y)
        {
            return false;
        }
        else
        {
            int var2 = MathHelper.floor_double(this.posX);
            int var3 = MathHelper.floor_double(this.posZ);
            int var4 = this.worldObj.getBlockLightValue(var2, var1, var3);
            byte var5 = 4;
            Calendar var6 = this.worldObj.getCurrentDate();

            if ((var6.get(2) + 1 != 10 || var6.get(5) < 20) && (var6.get(2) + 1 != 11 || var6.get(5) > 3))
            {
                if (this.rand.nextBoolean())
                {
                    return false;
                }
            }
            else
            {
                var5 = 7;
            }

            return var4 > this.rand.nextInt(var5) ? false : getCanSpawnHereTerrene();
        }
    }
    
    private boolean getCanSpawnHereTerrene()
    {
        return this.worldObj.checkIfAABBIsClear(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
    }
}
