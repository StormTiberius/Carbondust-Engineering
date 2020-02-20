/**
 *
 * @author StormTiberius
 */

package cde.terrene;

import net.minecraft.block.Block;

public class BiomeGenTerreneBeach extends BiomeGenTerrene
{
    public BiomeGenTerreneBeach(int id)
    {
        super(id);
        
        topBlock = (byte)Block.sand.blockID;
        fillerBlock = (byte)Block.sand.blockID;
        
        spawnableCreatureList.clear();
        spawnableWaterCreatureList.clear();
        
        theBiomeDecorator.treesPerChunk = 50;
        theBiomeDecorator.reedsPerChunk = 50;
        theBiomeDecorator.cactiPerChunk = 10;
    }
}
