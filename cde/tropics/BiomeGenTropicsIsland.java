/**
 *
 * @author StormTiberius
 */

package cde.tropics;

public class BiomeGenTropicsIsland extends BiomeGenTropics
{
    public BiomeGenTropicsIsland(int id)
    {
        super(id);
        
        theBiomeDecorator.treesPerChunk = -999;
        theBiomeDecorator.reedsPerChunk = 50;
        theBiomeDecorator.cactiPerChunk = 10;
    }
}