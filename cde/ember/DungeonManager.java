/**
 *
 * @author StormTiberius
 */

package cde.ember;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;

public class DungeonManager
{
    public static boolean generate(World world, Random random, int x, int y, int z)
    {
        ChunkCoordinates ck = world.getSpawnPoint();
        
        if(ck.posX == 0 && ck.posY == 128 && ck.posZ == 0)
        {
            return new WorldGenDungeons(ChestGenHooks.BONUS_CHEST, Block.grass.blockID, Block.cobblestone.blockID).generate(world, random, x, y, z);
        }
        else
        {
            switch(random.nextInt(4))
            {
                case 0: return new WorldGenDungeons(ChestGenHooks.PYRAMID_DESERT_CHEST, Block.cobblestoneMossy.blockID, Block.cobblestone.blockID).generate(world, random, x, y, z);
                case 1: return new WorldGenDungeons(ChestGenHooks.PYRAMID_JUNGLE_CHEST, Block.cobblestoneMossy.blockID, Block.cobblestone.blockID).generate(world, random, x, y, z);
                case 2: return new WorldGenDungeons(ChestGenHooks.VILLAGE_BLACKSMITH, Block.cobblestoneMossy.blockID, Block.cobblestone.blockID).generate(world, random, x, y, z);
                default: return new WorldGenDungeons(ChestGenHooks.DUNGEON_CHEST, Block.cobblestoneMossy.blockID, Block.cobblestone.blockID).generate(world, random, x, y, z);
            }
        }
    }
}