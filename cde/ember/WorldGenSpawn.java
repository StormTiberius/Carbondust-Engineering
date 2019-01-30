package cde.ember;

import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenSpawn extends WorldGenerator
{   
    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        return true;
    }
}
