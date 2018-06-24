/**
 *
 * @author StormTiberius
 */

package cde.tropics;

public class BiomeGenTropicsBeach extends BiomeGenTropics
{
    public BiomeGenTropicsBeach(int id)
    {
        super(id);
        
        spawnableCreatureList.clear();
        spawnableMonsterList.clear();
        
        theBiomeDecorator.treesPerChunk = 50;
        theBiomeDecorator.reedsPerChunk = 50;
        theBiomeDecorator.cactiPerChunk = 10;
    }
}
