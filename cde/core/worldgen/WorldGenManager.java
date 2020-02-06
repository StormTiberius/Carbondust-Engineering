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
    private final int[][] CFG;
    private final String DIMENSION_NAME;
    private final WorldGenerator[] WORLD_GEN_MINABLES;
    
    public WorldGenManager(String dimensionName, int id, int[][] cfg)
    {
        DIMENSION_NAME = dimensionName;
        CFG = cfg;
        WORLD_GEN_MINABLES = new WorldGenerator[CFG.length];
        
        for(int i = 0; i < CFG.length; i++)
        {
            WORLD_GEN_MINABLES[i] = new WorldGenMinable(id, i, CFG[i][1]);
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
                if(CFG[i][0] == 1 && TerrainGen.generateOre(world, random, WORLD_GEN_MINABLES[i], xCoord, zCoord, EventType.CUSTOM))
                {
                    for(int j = 0; j < CFG[i][2]; j++)
                    {
                        xPos = xCoord + random.nextInt(16);
                        yPos = CFG[i][3] + random.nextInt(CFG[i][4] - CFG[i][3]);
                        zPos = zCoord + random.nextInt(16);

                        WORLD_GEN_MINABLES[i].generate(world, random, xPos, yPos, zPos);
                    }
                }
            }
            
            MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Post(world, random, xCoord, zCoord));
        }
    }
}
