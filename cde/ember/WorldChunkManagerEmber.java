/**
 *
 * @author StormTiberius
 */

package cde.ember;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;

public class WorldChunkManagerEmber extends WorldChunkManagerHell
{
    public WorldChunkManagerEmber(BiomeGenBase biome, float temp, float rain)
    {
        super(biome, temp, rain);
        allowedBiomes.clear();
        allowedBiomes.add(biome);
    }
}
