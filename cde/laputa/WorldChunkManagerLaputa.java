/**
 *
 * @author StormTiberius
 */

package cde.laputa;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;

public class WorldChunkManagerLaputa extends WorldChunkManagerHell
{
    private final List biomesToSpawnIn;
    
    public WorldChunkManagerLaputa(BiomeGenBase biome, float temp, float rain)
    {
        super(biome,temp,rain);
        biomesToSpawnIn = new ArrayList();
        biomesToSpawnIn.add(biome);
    }
    
    @Override
    public List getBiomesToSpawnIn()
    {
        return biomesToSpawnIn;
    }
}
