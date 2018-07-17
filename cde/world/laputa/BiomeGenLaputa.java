/**
 *
 * @author StormTiberius
 */

package cde.world.laputa;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenLaputa extends BiomeGenBase
{
    public BiomeGenLaputa(int id)
    {
        super(id);
        
        spawnableCreatureList.clear();
        spawnableMonsterList.clear();
        spawnableWaterCreatureList.clear();
        field_82914_M.clear();
        
        theBiomeDecorator = new BiomeDecoratorLaputa(this);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getSkyColorByTemp(float par1)
    {
        return 1995007;
    }
}
