/**
 *
 * @author StormTiberius
 */

package cde.tropics;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldTypeTropics extends WorldType
{
    public WorldTypeTropics(int par1, String par2Str)
    {
        super(par1,par2Str,0);
    }
    
    @Override
    public WorldChunkManager getChunkManager(World world)
    {
        return new WorldChunkManagerTropics(world);
    }

    @Override
    public IChunkProvider getChunkGenerator(World world, String generatorOptions)
    {
        return new ChunkProviderTropics(world, world.getSeed(), false);
    }
    
    @Override
    public boolean hasVoidParticles(boolean var1)
    {
        return false;
    }

    @Override
    public double voidFadeMagnitude()
    {
        return 1.0D;
    }
}
