/**
 *
 * @author StormTiberius
 */

package cde.tropics;

public class BiomeGenPacificIsland extends BiomeGenPacific
{
    public BiomeGenPacificIsland(int id)
    {
        super(id);
        
        theBiomeDecorator.treesPerChunk = 50;
        theBiomeDecorator.reedsPerChunk = 50;
        theBiomeDecorator.cactiPerChunk = 10;
    }
}
