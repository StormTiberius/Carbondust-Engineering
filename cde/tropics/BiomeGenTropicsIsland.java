/**
 *
 * @author StormTiberius
 */

package cde.tropics;

import ic2.core.block.WorldGenRubTree;
import java.util.Random;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;

public class BiomeGenTropicsIsland extends BiomeGenTropics
{
    public BiomeGenTropicsIsland(int id)
    {
        super(id);
        
        spawnableMonsterList.clear();
        spawnableWaterCreatureList.clear();
        
        theBiomeDecorator.treesPerChunk = 0;
        theBiomeDecorator.reedsPerChunk = 50;
        theBiomeDecorator.cactiPerChunk = 10;
    }
        
    @Override
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);
        
        if(ModLoader.isModLoaded("IC2"))
        {
            int rubbertrees = par2Random.nextInt(3);

            if(par2Random.nextInt(100) + 1 <= rubbertrees * 2)
            {
                (new WorldGenRubTree()).generate(par1World, par2Random, par3 + par2Random.nextInt(16), rubbertrees, par4 + par2Random.nextInt(16));
            }
        }
    }
}
