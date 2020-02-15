/**
 *
 * @author StormTiberius
 */

package cde.terrene;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;

public abstract class BiomeGenTerrene extends BiomeGenBase
{
    public BiomeGenTerrene(int id)
    {
        super(id);
        
        theBiomeDecorator = new BiomeDecoratorTerrene(this);
        
        spawnableWaterCreatureList.clear();
        spawnableWaterCreatureList.add(new SpawnListEntry(EntitySquidTerrene.class, 10, 4, 4));
        
        field_82914_M.clear();
        field_82914_M.add(new SpawnListEntry(EntityBatTerrene.class, 10, 8, 8));
    }
    
    @Override
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);
        
        int var5 = 6 + par2Random.nextInt(11);
        int var6;
        int var7;
        int var8;

        for(var6 = 0; var6 < var5; ++var6)
        {
            var7 = par3 + par2Random.nextInt(16);
            var8 = par2Random.nextInt(56) + 8;
            int var9 = par4 + par2Random.nextInt(16);
            int var10 = par1World.getBlockId(var7, var8, var9);

            if(var10 == Block.stone.blockID)
            {
                par1World.setBlock(var7, var8, var9, Block.oreEmerald.blockID);
            }
        }
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getSkyColorByTemp(float par1)
    {
        return 1995007;
    }
}
