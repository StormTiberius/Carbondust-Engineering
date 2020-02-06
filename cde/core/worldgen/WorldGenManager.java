/**
 *
 * @author StormTiberius
 */

package cde.core.worldgen;

import cpw.mods.fml.common.IWorldGenerator;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.OreGenEvent;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType;
import net.minecraftforge.event.terraingen.TerrainGen;

public class WorldGenManager implements IWorldGenerator
{
    private static final int ENABLE = 0;
    private static final int SIZE = 1;
    private static final int AMOUNT = 2;
    private static final int MIN_Y = 3;
    private static final int MAX_Y = 4;
    
    private final int[][] CFG;
    private final String DIMENSION_NAME;
    private final WorldGenerator[] WORLD_GEN_MINABLES;
    
    public WorldGenManager(String dimensionName, int id, int[][] cfg)
    {
        DIMENSION_NAME = dimensionName;
        CFG = cfg;
        WORLD_GEN_MINABLES = new WorldGenerator[CFG.length];
        
        for(int i = 0; i < WORLD_GEN_MINABLES.length; i++)
        {
            WORLD_GEN_MINABLES[i] = new WorldGenMinable(id, i, CFG[i][SIZE]);
        }
    }
    
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        if(world.provider.getDimensionName().contentEquals(DIMENSION_NAME))
        {
            int xCoord = chunkX * 16;
            int zCoord = chunkZ * 16;
            
            int xPos,yPos,zPos;
            
            MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(world, random, xCoord, zCoord));
            
            for(int i = 0; i < WORLD_GEN_MINABLES.length; i++)
            {
                if(CFG[i][ENABLE] == 1 && TerrainGen.generateOre(world, random, WORLD_GEN_MINABLES[i], xCoord, zCoord, EventType.CUSTOM))
                {
                    for(int j = 0; j < CFG[i][AMOUNT]; j++)
                    {
                        xPos = xCoord + random.nextInt(16);
                        yPos = CFG[i][MIN_Y] + random.nextInt(CFG[i][MAX_Y] - CFG[i][MIN_Y]);
                        zPos = zCoord + random.nextInt(16);
                        
                        WORLD_GEN_MINABLES[i].generate(world, random, xPos, yPos, zPos);
                    }
                }
            }
            
            MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Post(world, random, xCoord, zCoord));
        }
    }
}
