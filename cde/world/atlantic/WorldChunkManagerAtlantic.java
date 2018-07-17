/**
 *
 * @author StormTiberius
 */

package cde.world.atlantic;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;

public class WorldChunkManagerAtlantic extends WorldChunkManagerHell
{
    private final List biomesToSpawnIn;
    
    public WorldChunkManagerAtlantic(BiomeGenBase biome, float temp, float rain)
    {
        super(biome, temp, rain);
        biomesToSpawnIn = new ArrayList();
        biomesToSpawnIn.add(biome);
    }
    
    @Override
    public List getBiomesToSpawnIn()
    {
        return biomesToSpawnIn;
    }
}
