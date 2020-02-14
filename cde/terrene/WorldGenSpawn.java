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
        
        for(int y = spawnPoint.posY - 1; y < spawnPoint.posX + 4; y++)
        {
            for(int x = spawnPoint.posX - 4; x < spawnPoint.posX + 6; x++)
            {
                for(int z = spawnPoint.posZ - 4; z < spawnPoint.posZ + 6; z++)
                {
                    if(y == -1)
                    {
                        WORLD.setBlockWithNotify(x, y, z, Block.grass.blockID);
                    }
                    else if(x == spawnPoint.posX - 4 || x == spawnPoint.posX + 6 || z == spawnPoint.posZ - 4 || z == spawnPoint.posZ + 6)
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
