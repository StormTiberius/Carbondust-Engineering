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
        
        spawnableCreatureList.clear();
        spawnableMonsterList.clear();
        spawnableWaterCreatureList.clear();
        
        theBiomeDecorator.treesPerChunk = 0;
        theBiomeDecorator.reedsPerChunk = 50;
        theBiomeDecorator.cactiPerChunk = 10;
    }
}
