/**
 *
 * @author StormTiberius
 */

package cde.terrene;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;

public class WorldGenSpawnPoint
{
    private final World WORLD;
    private final Random RANDOM;
    
    public WorldGenSpawnPoint(World world, Random random)
    {
        WORLD = world;
        RANDOM = random;
    }
    
    public void generate()
    {
        ChunkCoordinates spawnPoint = WORLD.provider.getSpawnPoint();
        
        int xMin = spawnPoint.posX - 4;
        int xMax = spawnPoint.posX + 4;
        int yMin = spawnPoint.posY - 1;
        int yMax = spawnPoint.posY + 3;
        int zMin = spawnPoint.posZ - 4;
        int zMax = spawnPoint.posZ + 4;
        
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
                        if(!WORLD.isAirBlock(x, y, z))
                        {
                            WORLD.setBlockWithNotify(x, y, z, Block.cobblestone.blockID);
                        }
                    }
                    else
                    {
                        WORLD.setBlockWithNotify(x, y, z, 0);
                    }
                }
            }
        }
        
        WORLD.setBlockWithNotify(spawnPoint.posX + 1, spawnPoint.posY, spawnPoint.posZ, Block.torchWood.blockID);
        WORLD.setBlockWithNotify(spawnPoint.posX, spawnPoint.posY, spawnPoint.posZ + 1, Block.torchWood.blockID);
        WORLD.setBlockWithNotify(spawnPoint.posX - 1, spawnPoint.posY, spawnPoint.posZ, Block.torchWood.blockID);
        WORLD.setBlockWithNotify(spawnPoint.posX, spawnPoint.posY, spawnPoint.posZ - 1, Block.torchWood.blockID);
        
        int[] sides = {RANDOM.nextInt(4), RANDOM.nextInt(4)};
        
        if(sides[1] == sides[0])
        {
            if(sides[1] == 3)
            {
                sides[1] = 0;
            }
            else
            {
                sides[1]++;
            }
        }
        
        for(int i : sides)
        {
            int j = RANDOM.nextInt(5);
            
            ChunkCoordinates ck = new ChunkCoordinates();
            
            switch(i)
            {
                case 0: ck.set(xMin + 2 + j, spawnPoint.posY, zMin + 1); break;
                case 1: ck.set(xMin + 1, spawnPoint.posY, zMin + 2 + j); break;
                case 2: ck.set(xMax - 2 - j, spawnPoint.posY, zMax - 1); break;
                case 3: ck.set(xMax - 1, spawnPoint.posY, zMax - 2 - j); break;
            }
            
            WORLD.setBlockWithNotify(ck.posX, ck.posY, ck.posZ, Block.chest.blockID);
            
            TileEntity te = WORLD.getBlockTileEntity(ck.posX, ck.posY, ck.posZ);
            
            if(te instanceof TileEntityChest)
            {
                ChestGenHooks info = ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST);
                WeightedRandomChestContent.generateChestContents(RANDOM, info.getItems(RANDOM), (TileEntityChest)te, info.getCount(RANDOM));
            }
        }
    }
}
