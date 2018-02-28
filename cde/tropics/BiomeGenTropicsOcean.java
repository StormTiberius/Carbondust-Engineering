/**
 *
 * @author StormTiberius
 */

package cde.tropics;

public class BiomeGenTropicsOcean extends BiomeGenTropics
{
    public BiomeGenTropicsOcean(int id)
    {
        super(id);
        
        theBiomeDecorator.treesPerChunk = -999;
    }
}
