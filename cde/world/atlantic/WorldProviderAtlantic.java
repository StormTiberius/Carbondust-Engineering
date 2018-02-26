package cde.world.atlantic;

import cde.WorldCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderAtlantic extends WorldProvider
{
    @Override
    public String getDimensionName()
    {
        return "Atlantic";
    }
    
    @Override
    protected void registerWorldChunkManager()
    {
        worldChunkMgr = new WorldChunkManagerHell(WorldCore.atlantic, 0.8F, 0.4F);
    }

    @Override
    public IChunkProvider createChunkGenerator()
    {
        return new ChunkProviderAtlantic(worldObj, worldObj.getSeed(), worldObj.getWorldInfo().isMapFeaturesEnabled());
    }

    @Override
    public float calculateCelestialAngle(long par1, float par3)
    {
        if(!WorldCore.isDayMode(dimensionId))
        {
            return super.calculateCelestialAngle(par1, par3);
        }
        else
        {
            return 90.0F;
        }
    }
    
    @Override
    public boolean canRespawnHere()
    {
        return false;
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
