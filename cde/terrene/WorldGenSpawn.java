/**
 *
 * @author StormTiberius
 */

package cde.terrene;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public class WorldGenSpawn
{
    private final World WORLD;
    private final Random RANDOM;
    
    public WorldGenSpawn(World world, Random random)
    {
        WORLD = world;
        RANDOM = random;
    }
    
    public void generate()
    {
        ChunkCoordinates spawnPoint = WORLD.provider.getSpawnPoint();
        
        int xMin = spawnPoint.posX - 4;
        int xMax = spawnPoint.posX + 5;
        int yMin = spawnPoint.posY - 1;
        int yMax = spawnPoint.posY + 3;
        int zMin = spawnPoint.posZ - 4;
        int zMax = spawnPoint.posZ + 5;
        
        for(int y = yMin; y <= yMax; y++)
        {
            for(int x = xMin; x <= xMax; x++)
            {
                for(int z = zMin; z <= zMax; z++)
                {
                    if(y == yMin)
                    {
                        WORLD.setBlockWithNotify(x, y, z, Block.grass.blockID);
                    }
                    else if(x == xMin || x == xMax || z == zMin || z == zMax)
                    {
                        WORLD.setBlockWithNotify(x, y, z, Block.cobblestone.blockID);
                    }
                    else
                    {
                        WORLD.setBlockWithNotify(x, y, z, 0);
                    }
                }
            }
        }
    }
}
