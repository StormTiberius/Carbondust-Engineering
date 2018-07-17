/**
 *
 * @author StormTiberius
 */

package cde.world.laputa;

import cde.WorldCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderLaputa extends WorldProvider
{
    @Override
    public String getDimensionName()
    {
        return "Laputa";
    }
    
    @Override
    protected void registerWorldChunkManager()
    {
        worldChunkMgr = new WorldChunkManagerLaputa(WorldCore.laputa, 0.8F, 0.4F);
        dimensionId = WorldCore.getLaputaDimensionId();
    }
    
    @Override
    public IChunkProvider createChunkGenerator()
    {
        return new ChunkProviderLaputa(worldObj, worldObj.getSeed(), false);
    }
    
    @Override
    public boolean canCoordinateBeSpawn(int par1, int par2)
    {
        return true;
    }
        
    @SideOnly(Side.CLIENT)
    @Override
    public boolean getWorldHasVoidParticles()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public double getVoidFogYFactor()
    {
        return 1.0D;
    }
}
