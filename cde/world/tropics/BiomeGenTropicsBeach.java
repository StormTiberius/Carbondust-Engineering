/**
 *
 * @author StormTiberius
 */

package cde.world.tropics;

public class BiomeGenTropicsBeach extends BiomeGenTropics
{
    public BiomeGenTropicsBeach(int id)
    {
        super(id);
        
        theBiomeDecorator.treesPerChunk = 50;
        theBiomeDecorator.reedsPerChunk = 50;
        theBiomeDecorator.cactiPerChunk = 10;
    }
}
