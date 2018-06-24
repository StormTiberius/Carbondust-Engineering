/**
 *
 * @author StormTiberius
 */

package cde.tropics;

import net.minecraft.world.biome.SpawnListEntry;

public class BiomeGenTropicsOcean extends BiomeGenTropics
{
    public BiomeGenTropicsOcean(int id)
    {
        super(id);
        
        spawnableCreatureList.clear();
        
        spawnableWaterCreatureList.add(new SpawnListEntry(EntitySquiddy.class, 10, 4, 4));
        
        theBiomeDecorator.treesPerChunk = -999;
    }
}
