/**
 *
 * @author StormTiberius
 */

package cde.world.ember;

import cde.EmberCore;
import cpw.mods.fml.common.IWorldGenerator;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldGenOil implements IWorldGenerator
{
    private final int dimensionId;
    
    public WorldGenOil(int dimensionId)
    {
        this.dimensionId = dimensionId;
    }
    
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        int xPos = chunkX * 16;
        int zPos = chunkZ * 16;
        
        if(world.provider.dimensionId == dimensionId)
        {
            boolean mediumDeposit = random.nextDouble() <= (0.15 / 100.0);
            boolean largeDeposit = random.nextDouble() <= (0.005 / 100.0);

            if(mediumDeposit || largeDeposit )
            {
                int yPos = 40 + random.nextInt(20);

                int r = 0;

                if(mediumDeposit)
                {
                    r = 4 + random.nextInt(4);
                }
                else if(largeDeposit)
                {
                    r = 8 + random.nextInt(9);
                }

                int b = r * r;
        
                for(int x = -r; x <= r; x++)
                {
                    for(int y = -r; y <= r; y++)
                    {
                        for(int z = -r; z <= r; z++)
                        {
                            int a = x * x + y * y + z * z;

                            if(a <= b)
                            {
                                world.setBlockWithNotify(xPos + x, yPos + y, zPos + z, EmberCore.getLiquidId());
                            }
                        }
                    }
                }
            }
        }
    }
}
