/**
 *
 * @author StormTiberius
 */

package cde.tropics;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;

public abstract class BiomeGenTropics extends BiomeGenBase
{
    public BiomeGenTropics(int id)
    {
        super(id);

        spawnableWaterCreatureList.clear();
        field_82914_M.clear();
        
        field_82914_M.add(new SpawnListEntry(EntityBatty.class, 10, 8, 8));
        
        theBiomeDecorator = new BiomeDecoratorTropics(this);
    }
    
    @Override
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);
        int var5 = (3 + par2Random.nextInt(6)) * 2;
        int var6;
        int var7;
        int var8;

        for (var6 = 0; var6 < var5; ++var6)
        {
            var7 = par3 + par2Random.nextInt(16);
            var8 = par2Random.nextInt(56) + 4;
            int var9 = par4 + par2Random.nextInt(16);
            int var10 = par1World.getBlockId(var7, var8, var9);

            if (var10 == Block.stone.blockID)
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
