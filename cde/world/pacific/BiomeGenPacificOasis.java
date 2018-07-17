/**
 *
 * @author StormTiberius
 */

package cde.world.pacific;

public class BiomeGenPacificOasis extends BiomeGenPacific
{
    public BiomeGenPacificOasis(int id)
    {
        super(id);
        
        spawnableMonsterList.clear();
        spawnableWaterCreatureList.clear();
        
        theBiomeDecorator.treesPerChunk = -999;
        theBiomeDecorator.reedsPerChunk = 50;
        theBiomeDecorator.cactiPerChunk = 10;
    }
}
