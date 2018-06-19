/**
 *
 * @author StormTiberius
 */

package cde.tropics;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getSkyColorByTemp(float par1)
    {
        return 1995007;
    }
}
