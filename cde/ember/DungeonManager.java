/**
 *
 * @author StormTiberius
 */

package cde.ember;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;

public class DungeonManager
{
    private static final AxisAlignedBB BB = AxisAlignedBB.getBoundingBox(192, 0, 192, 336, 255, 336);
    
    public static void generate(World world, Random random, int x, int y, int z, boolean mapFeaturesEnabled)
    {
        ChunkCoordinates cc = world.provider.getSpawnPoint();
        
        if(cc.posX == LocationData.SPAWN_X && cc.posY == LocationData.SPAWN_Y && cc.posZ == LocationData.SPAWN_Z)
        {
            (new WorldGenDungeons(ChestGenHooks.BONUS_CHEST, Block.grass.blockID, Block.cobblestone.blockID)).generate(world, random, x, random.nextInt(128), z);
        }
        else if(BB.isVecInside(Vec3.createVectorHelper(x, y, z)) && !BB.isVecInside(Vec3.createVectorHelper(cc.posX, cc.posY, cc.posZ)))
        {
            (new WorldGenDungeons(ChestGenHooks.BONUS_CHEST, Block.grass.blockID, Block.cobblestone.blockID)).generate(world, random, x, random.nextInt(128), z);
        }
        else if(mapFeaturesEnabled)
        {
            switch(random.nextInt(4))
            {
                case 0: (new WorldGenDungeons(ChestGenHooks.PYRAMID_DESERT_CHEST, Block.cobblestoneMossy.blockID, Block.cobblestone.blockID)).generate(world, random, x, y, z); break;
                case 1: (new WorldGenDungeons(ChestGenHooks.PYRAMID_JUNGLE_CHEST, Block.cobblestoneMossy.blockID, Block.cobblestone.blockID)).generate(world, random, x, y, z); break;
                case 2: (new WorldGenDungeons(ChestGenHooks.VILLAGE_BLACKSMITH, Block.grass.blockID, Block.cobblestone.blockID)).generate(world, random, x, y, z); break;
                case 3: (new WorldGenDungeons(ChestGenHooks.DUNGEON_CHEST, Block.cobblestoneMossy.blockID, Block.cobblestone.blockID)).generate(world, random, x, y, z); break;
            }
        }
    }
}
