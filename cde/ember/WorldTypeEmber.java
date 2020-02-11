/**
 *
 * @author StormTiberius
 */

package cde.ember;

import cde.EmberCore;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldTypeEmber extends WorldType
{
    public WorldTypeEmber(int id, String name, int version)
    {
        super(id,name,version);
    }
    
    @Override
    public WorldChunkManager getChunkManager(World world)
    {
        return new WorldChunkManagerEmber(EmberCore.biome, 0.8F, 0.4F);
    }
    
    @Override
    public IChunkProvider getChunkGenerator(World world, String generatorOptions)
    {
        return new ChunkProviderEmber(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled());
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
        return new BiomeGenBase[] {EmberCore.biome};
    }
}
