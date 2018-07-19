/**
 *
 * @author StormTiberius
 */

package cde.laputa;

import cde.LaputaCore;
import cde.laputa.gog.SkyRendererGoG;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.client.IRenderHandler;

public class WorldProviderLaputa extends WorldProvider
{
    private final IRenderHandler skyRendererGoG;
    
    private long time;
    
    public WorldProviderLaputa()
    {
        skyRendererGoG = new SkyRendererGoG();
    }
    
    @Override
    public String getDimensionName()
    {
        return "Laputa";
    }
    
    @Override
    protected void registerWorldChunkManager()
    {
        worldChunkMgr = new WorldChunkManagerLaputa(LaputaCore.laputa, 0.8F, 0.4F);
        dimensionId = LaputaCore.getDimensionId();
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
    
    @Override
    public float calculateCelestialAngle(long par1, float par3)
    {
        int var4 = (int)(par1 % (24000L * LaputaCore.getDayCycleDurationMultiplier()));
        float var5 = ((float)var4 + par3) / (24000.0F * LaputaCore.getDayCycleDurationMultiplier()) - 0.25F;

        if (var5 < 0.0F)
        {
            ++var5;
        }

        if (var5 > 1.0F)
        {
            --var5;
        }

        float var6 = var5;
        var5 = 1.0F - (float)((Math.cos((double)var5 * Math.PI) + 1.0D) / 2.0D);
        var5 = var6 + (var5 - var6) / 3.0F;
        return var5;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getMoonPhase(long par1, float par3)
    {
        return (int)(par1 / (24000L * LaputaCore.getDayCycleDurationMultiplier())) % 8;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public float getCloudHeight()
    {
        return 85.0F;
    }
    
    @Override
    public ChunkCoordinates getEntrancePortalLocation()
    {
        return getSpawnPoint();
    }
    
    @Override
    public int getAverageGroundLevel()
    {
        return 21;
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
        
    @SideOnly(Side.CLIENT)
    @Override
    public IRenderHandler getSkyRenderer()
    {
        return skyRendererGoG;
    }
    
    @Override
    public ChunkCoordinates getRandomizedSpawnPoint()
    {
        return getSpawnPoint();
    }
    
    @Override
    public int getRespawnDimension(EntityPlayerMP player)
    {
        return dimensionId;
    }

    @Override
    public void updateWeather()
    {   
        WorldInfo worldInfo = worldObj.getWorldInfo();
        
        time = worldInfo.getWorldTime();
        
        if (!this.hasNoSky)
        {
            int var1 = worldInfo.getThunderTime();

            if (var1 <= 0)
            {
                if (worldInfo.isThundering())
                {
                    worldInfo.setThunderTime(worldObj.rand.nextInt(LaputaCore.getWeatherDuration(0)) + LaputaCore.getWeatherDuration(1));
                }
                else
                {
                    worldInfo.setThunderTime(worldObj.rand.nextInt(LaputaCore.getWeatherDuration(2)) + LaputaCore.getWeatherDuration(3));
                }
            }
            else
            {
                --var1;
                worldInfo.setThunderTime(var1);

                if (var1 <= 0)
                {
                    worldInfo.setThundering(!worldInfo.isThundering());
                }
            }

            int var2 = worldInfo.getRainTime();

            if (var2 <= 0)
            {
                if (worldInfo.isRaining())
                {
                    worldInfo.setRainTime(worldObj.rand.nextInt(LaputaCore.getWeatherDuration(4)) + LaputaCore.getWeatherDuration(5));
                }
                else
                {
                    worldInfo.setRainTime(worldObj.rand.nextInt(LaputaCore.getWeatherDuration(6)) + LaputaCore.getWeatherDuration(7));
                }
            }
            else
            {
                --var2;
                worldInfo.setRainTime(var2);

                if (var2 <= 0)
                {
                    worldInfo.setRaining(!worldInfo.isRaining());
                }
            }

            worldObj.prevRainingStrength = worldObj.rainingStrength;

            if (worldInfo.isRaining())
            {
                worldObj.rainingStrength = (float)((double)worldObj.rainingStrength + 0.01D);
            }
            else
            {
                worldObj.rainingStrength = (float)((double)worldObj.rainingStrength - 0.01D);
            }

            if (worldObj.rainingStrength < 0.0F)
            {
                worldObj.rainingStrength = 0.0F;
            }

            if (worldObj.rainingStrength > 1.0F)
            {
                worldObj.rainingStrength = 1.0F;
            }

            worldObj.prevThunderingStrength = worldObj.thunderingStrength;

            if (worldInfo.isThundering())
            {
                worldObj.thunderingStrength = (float)((double)worldObj.thunderingStrength + 0.01D);
            }
            else
            {
                worldObj.thunderingStrength = (float)((double)worldObj.thunderingStrength - 0.01D);
            }

            if (worldObj.thunderingStrength < 0.0F)
            {
                worldObj.thunderingStrength = 0.0F;
            }

            if (worldObj.thunderingStrength > 1.0F)
            {
                worldObj.thunderingStrength = 1.0F;
            }
        }
    }
    
    @Override
    public ChunkCoordinates getSpawnPoint()
    {
        return new ChunkCoordinates(264, 20, 264);
    }
    
    @Override
    public double getHorizon()
    {
        return 20.0D;
    }
    
    @Override
    public void resetRainAndThunder()
    {
        WorldInfo worldInfo = worldObj.getWorldInfo();
        
        if(time != worldInfo.getWorldTime())
        {
            long var2 = time + (24000L * LaputaCore.getDayCycleDurationMultiplier());
            worldInfo.setWorldTime(var2 - var2 % (24000L * LaputaCore.getDayCycleDurationMultiplier()));
        }
        
        super.resetRainAndThunder();
    }
}
