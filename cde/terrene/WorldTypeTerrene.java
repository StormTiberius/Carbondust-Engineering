/**
 *
 * @author StormTiberius
 */

package cde.terrene;

import cde.TerreneCore;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldTypeTerrene extends WorldType
{
    private static final BiomeGenBase[] TERRENE_BIOMES = {TerreneCore.island};
    
    public WorldTypeTerrene(int id, String name, int version)
    {
        super(id,name,version);
    }
    
    @Override
    public WorldChunkManager getChunkManager(World world)
    {
        return new WorldChunkManagerTerrene(world);
    }
    
    @Override
    public IChunkProvider getChunkGenerator(World world, String generatorOptions)
    {
        return new ChunkProviderTerrene(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled());
    }
    
    @Override
    public int getMinimumSpawnHeight(World world)
    {
        return 114;
    }
    
    @Override
    public double getHorizon(World world)
    {
        return 113.0D;
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
    
    @Override
    public BiomeGenBase[] getBiomesForWorldType()
    {
        return TERRENE_BIOMES;
    }
}
