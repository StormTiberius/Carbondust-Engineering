/**
 *
 * @author StormTiberius
 */

package cde.terrene;

import net.minecraft.block.Block;

public class BiomeGenTropicsOcean extends BiomeGenTerrene
{
    public BiomeGenTropicsOcean(int id)
    {
        super(id);
        
        topBlock = (byte)Block.sand.blockID;
        fillerBlock = (byte)Block.sand.blockID;
        
        spawnableCreatureList.clear();
        
        theBiomeDecorator.treesPerChunk = -999;
    }
}
