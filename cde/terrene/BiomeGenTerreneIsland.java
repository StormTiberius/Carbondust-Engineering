/**
 *
 * @author StormTiberius
 */

package cde.terrene;

import cde.TerreneCore;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenFlowers;

public class BiomeGenTerreneIsland extends BiomeGenTerrene
{
    private boolean flag;
    
    public BiomeGenTerreneIsland(int id)
    {
        super(id);
        
        flag = true;
        
        spawnableWaterCreatureList.clear();
        
        theBiomeDecorator.treesPerChunk = 0;
        theBiomeDecorator.reedsPerChunk = 50;
        theBiomeDecorator.cactiPerChunk = 10;
        theBiomeDecorator.waterlilyPerChunk = 4;
    }
    
    @Override
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);
        
        int x = par3 + par2Random.nextInt(16) + 8;
        int y = 50 + par2Random.nextInt(128);
        int z = par4 + par2Random.nextInt(16) + 8;

        if(flag)
        {
            flag = !(new WorldGenFlowers(Block.carrot.blockID)).generate(par1World, par2Random, x, y, z);
        }
        else
        {
            flag = (new WorldGenFlowers(Block.potato.blockID)).generate(par1World, par2Random, x, y, z);
        }
        
        int id = TerreneCore.getFlowerId();
        
        if(id != 0)
        {
            x = par3 + par2Random.nextInt(16) + 8;
            y = 50 + par2Random.nextInt(128);
            z = par4 + par2Random.nextInt(16) + 8;

            (new WorldGenFlowers(id)).generate(par1World, par2Random, x, y, z);
        }
    }
}
