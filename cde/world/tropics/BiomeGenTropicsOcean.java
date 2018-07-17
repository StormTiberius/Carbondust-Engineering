/**
 *
 * @author StormTiberius
 */

package cde.world.tropics;

public class BiomeGenTropicsOcean extends BiomeGenTropics
{
    public BiomeGenTropicsOcean(int id)
    {
        super(id);
        
        spawnableCreatureList.clear();
        
        theBiomeDecorator.treesPerChunk = -999;
    }
}
