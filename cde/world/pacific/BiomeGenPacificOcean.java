/**
 *
 * @author StormTiberius
 */

package cde.world.pacific;

public class BiomeGenPacificOcean extends BiomeGenPacific
{
    public BiomeGenPacificOcean(int id)
    {
        super(id);
        
        spawnableCreatureList.clear();
        
        theBiomeDecorator.treesPerChunk = -999;
    }
}
