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
    private final WorldGenerator copper,tin,silver,lead,uranium,sulfur,saltpeter,quartz,apatite,ruby,jade,sapphire;
    
    public WorldGenManager(String dimensionName, int id, int[][] cfg)
    {
        DIMENSION_NAME = dimensionName;
        
        CFG = cfg;
        
        copper = new WorldGenMinable(id, 0, CFG[0][1]);
        tin = new WorldGenMinable(id, 1, CFG[1][1]);
        silver = new WorldGenMinable(id, 2, CFG[2][1]);
        lead = new WorldGenMinable(id, 3, CFG[3][1]);
        uranium = new WorldGenMinable(id, 4, CFG[4][1]);
        sulfur = new WorldGenMinable(id, 5, CFG[5][1]);
        saltpeter = new WorldGenMinable(id, 6, CFG[6][1]);
        quartz = new WorldGenMinable(id, 7, CFG[7][1]);
        apatite = new WorldGenMinable(id, 8, CFG[8][1]);
        ruby = new WorldGenMinable(id, 9, CFG[9][1]);
        jade = new WorldGenMinable(id, 10, CFG[10][1]);
        sapphire = new WorldGenMinable(id, 11, CFG[11][1]);
    }
    
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        int xCoord = chunkX * 16;
        int zCoord = chunkZ * 16;
        
        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(world, random, xCoord, zCoord));
        
        int xPos,yPos,zPos;
        
        if(CFG[0][0] == 1 && TerrainGen.generateOre(world, random, copper, xCoord, zCoord, EventType.CUSTOM))
        {
            for(int i = 0; i < CFG[0][2]; i++)
            {
                xPos = xCoord + random.nextInt(16);
                yPos = CFG[0][3] + random.nextInt(CFG[0][4] - CFG[0][3]);
                zPos = zCoord + random.nextInt(16);

                copper.generate(world, random, xPos, yPos, zPos);
            }
        }
        
        if(CFG[1][0] == 1 && TerrainGen.generateOre(world, random, tin, xCoord, zCoord, EventType.CUSTOM))
        {
            for(int i = 0; i < CFG[1][2]; i++)
            {
                xPos = xCoord + random.nextInt(16);
                yPos = CFG[1][3] + random.nextInt(CFG[1][4] - CFG[1][3]);
                zPos = zCoord + random.nextInt(16);

                tin.generate(world, random, xPos, yPos, zPos);
            }
        }
                
        if(CFG[2][0] == 1 && TerrainGen.generateOre(world, random, silver, xCoord, zCoord, EventType.CUSTOM))
        {
            for(int i = 0; i < CFG[2][2]; i++)
            {
                xPos = xCoord + random.nextInt(16);
                yPos = CFG[2][3] + random.nextInt(CFG[2][4] - CFG[2][3]);
                zPos = zCoord + random.nextInt(16);

                silver.generate(world, random, xPos, yPos, zPos);
            }
        }
        
        if(CFG[3][0] == 1 && TerrainGen.generateOre(world, random, lead, xCoord, zCoord, EventType.CUSTOM))
        {
            for(int i = 0; i < CFG[3][2]; i++)
            {
                xPos = xCoord + random.nextInt(16);
                yPos = CFG[3][3] + random.nextInt(CFG[3][4] - CFG[3][3]);
                zPos = zCoord + random.nextInt(16);

                lead.generate(world, random, xPos, yPos, zPos);
            }
        }
                        
        if(CFG[4][0] == 1 && TerrainGen.generateOre(world, random, uranium, xCoord, zCoord, EventType.CUSTOM))
        {
            for(int i = 0; i < CFG[4][2]; i++)
            {
                xPos = xCoord + random.nextInt(16);
                yPos = CFG[4][3] + random.nextInt(CFG[4][4] - CFG[4][3]);
                zPos = zCoord + random.nextInt(16);

                uranium.generate(world, random, xPos, yPos, zPos);
            }
        }
        
        if(CFG[5][0] == 1 && TerrainGen.generateOre(world, random, sulfur, xCoord, zCoord, EventType.CUSTOM))
        {
            for(int i = 0; i < CFG[5][2]; i++)
            {
                xPos = xCoord + random.nextInt(16);
                yPos = CFG[5][3] + random.nextInt(CFG[5][4] - CFG[5][3]);
                zPos = zCoord + random.nextInt(16);

                sulfur.generate(world, random, xPos, yPos, zPos);
            }
        }
        
        if(CFG[6][0] == 1 && TerrainGen.generateOre(world, random, saltpeter, xCoord, zCoord, EventType.CUSTOM))
        {
            for(int i = 0; i < CFG[6][2]; i++)
            {
                xPos = xCoord + random.nextInt(16);
                yPos = CFG[6][3] + random.nextInt(CFG[6][4] - CFG[6][3]);
                zPos = zCoord + random.nextInt(16);

                saltpeter.generate(world, random, xPos, yPos, zPos);
            }
        }
                        
        if(CFG[7][0] == 1 && TerrainGen.generateOre(world, random, quartz, xCoord, zCoord, EventType.CUSTOM))
        {
            for(int i = 0; i < CFG[7][2]; i++)
            {
                xPos = xCoord + random.nextInt(16);
                yPos = CFG[7][3] + random.nextInt(CFG[7][4] - CFG[7][3]);
                zPos = zCoord + random.nextInt(16);

                quartz.generate(world, random, xPos, yPos, zPos);
            }
        }
        
        if(CFG[8][0] == 1 && TerrainGen.generateOre(world, random, apatite, xCoord, zCoord, EventType.CUSTOM))
        {
            for(int i = 0; i < CFG[8][2]; i++)
            {
                xPos = xCoord + random.nextInt(16);
                yPos = CFG[8][3] + random.nextInt(CFG[8][4] - CFG[8][3]);
                zPos = zCoord + random.nextInt(16);

                apatite.generate(world, random, xPos, yPos, zPos);
            }
        }
                                                                        
        if(CFG[9][0] == 1 && TerrainGen.generateOre(world, random, ruby, xCoord, zCoord, EventType.CUSTOM))
        {
            for(int i = 0; i < CFG[9][2]; i++)
            {
                xPos = xCoord + random.nextInt(16);
                yPos = CFG[9][3] + random.nextInt(CFG[9][4] - CFG[9][3]);
                zPos = zCoord + random.nextInt(16);

                ruby.generate(world, random, xPos, yPos, zPos);
            }
        }
        
        if(CFG[10][0] == 1 && TerrainGen.generateOre(world, random, jade, xCoord, zCoord, EventType.CUSTOM))
        {
            for(int i = 0; i < CFG[10][2]; i++)
            {
                xPos = xCoord + random.nextInt(16);
                yPos = CFG[10][3] + random.nextInt(CFG[10][4] - CFG[10][3]);
                zPos = zCoord + random.nextInt(16);

                jade.generate(world, random, xPos, yPos, zPos);
            }
        }
        
        if(CFG[11][0] == 1 && TerrainGen.generateOre(world, random, sapphire, xCoord, zCoord, EventType.CUSTOM))
        {
            for(int i = 0; i < CFG[11][2]; i++)
            {
                xPos = xCoord + random.nextInt(16);
                yPos = CFG[11][3] + random.nextInt(CFG[11][4] - CFG[11][3]);
                zPos = zCoord + random.nextInt(16);

                sapphire.generate(world, random, xPos, yPos, zPos);
            }
        }
        
        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Post(world, random, xCoord, zCoord));
    }
}
