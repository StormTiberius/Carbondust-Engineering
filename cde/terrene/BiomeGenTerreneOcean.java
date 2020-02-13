/**
 *
 * @author StormTiberius
 */

package cde.terrene;

import net.minecraft.block.Block;

public class BiomeGenTerreneOcean extends BiomeGenTerrene
{
    public BiomeGenTerreneOcean(int id)
    {
        super(id);
        
        topBlock = (byte)Block.sand.blockID;
        fillerBlock = (byte)Block.sand.blockID;
        
        spawnableCreatureList.clear();
        
        theBiomeDecorator.treesPerChunk = -999;
    }
}
