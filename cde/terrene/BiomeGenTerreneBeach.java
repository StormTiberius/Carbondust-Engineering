/**
 *
 * @author StormTiberius
 */

package cde.terrene;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.world.biome.SpawnListEntry;

public class BiomeGenTerreneBeach extends BiomeGenTropics
{
    public BiomeGenTerreneBeach(int id)
    {
        super(id);
        
        topBlock = (byte)Block.sand.blockID;
        fillerBlock = (byte)Block.sand.blockID;
        
        spawnableCreatureList.clear();
        spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
        spawnableWaterCreatureList.clear();
        
        theBiomeDecorator.treesPerChunk = 50;
        theBiomeDecorator.reedsPerChunk = 50;
        theBiomeDecorator.cactiPerChunk = 10;
    }
}
