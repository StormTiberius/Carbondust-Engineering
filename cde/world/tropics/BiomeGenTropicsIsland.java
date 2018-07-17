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
        
        spawnableMonsterList.clear();
        spawnableWaterCreatureList.clear();
        
        theBiomeDecorator.treesPerChunk = -999;
        theBiomeDecorator.reedsPerChunk = 50;
        theBiomeDecorator.cactiPerChunk = 10;
    }
}
