/**
 *
 * @author StormTiberius
 */

package cde.world.tropics;

public class BiomeGenTropicsIsland extends BiomeGenTropics
{
    public BiomeGenTropicsIsland(int id)
    {
        super(id);
        
        theBiomeDecorator.treesPerChunk = 50;
        theBiomeDecorator.reedsPerChunk = 50;
        theBiomeDecorator.cactiPerChunk = 10;
    }
}
