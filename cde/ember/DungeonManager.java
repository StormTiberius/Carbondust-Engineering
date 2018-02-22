/**
 *
 * @author StormTiberius
 */

package cde.ember;

import cde.ember.dungeons.StarterDungeon;
import cde.ember.dungeons.VanillaDungeon;
import java.util.Random;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public class DungeonManager
{
    public static boolean generate(World world, Random random, int x, int y, int z)
    {
        ChunkCoordinates ck = world.getSpawnPoint();
        
        if(ck.posX == 0 && ck.posY == 128 && ck.posZ == 0)
        {
            return new StarterDungeon().generate(world, random, x, y, z);
        }
        else
        {
            return new VanillaDungeon().generate(world, random, x, y, z);
        }
    }
}
