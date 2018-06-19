/**
 *
 * @author StormTiberius
 */

package cde.tropics;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public abstract class BiomeGenTropics extends BiomeGenBase
{
    public BiomeGenTropics(int id)
    {
        super(id);

        spawnableCreatureList.clear();
        spawnableMonsterList.clear();
        spawnableWaterCreatureList.clear();
        field_82914_M.clear();
        
        theBiomeDecorator.generateLakes = false;
        
        topBlock = (byte)Block.sand.blockID;
        fillerBlock = (byte)Block.sand.blockID;
    }
    
    @Override
    public int getSkyColorByTemp(float par1)
    {
        return 1995007;
    }
}
