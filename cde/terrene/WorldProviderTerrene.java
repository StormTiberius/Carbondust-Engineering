/**
 *
 * @author StormTiberius
 */

package cde.terrene;

import cde.TerreneCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.WorldInfo;

public class WorldProviderTerrene extends WorldProviderSurface
{
    private long time;
    
    @Override
    public String getDimensionName()
    {
        return "Terrene";
    }
    
    @Override
    protected void registerWorldChunkManager()
    {
        worldChunkMgr = new WorldChunkManagerTerrene(worldObj);
    }
    
    @Override
    public IChunkProvider createChunkGenerator()
    {
        return new ChunkProviderTerrene(worldObj, worldObj.getSeed(), worldObj.getWorldInfo().isMapFeaturesEnabled());
    }
    
    @Override
    public float calculateCelestialAngle(long par1, float par3)
    {
        int var4 = (int)(par1 % (24000L * TerreneCore.getDayCycleDurationMultiplier()));
        float var5 = ((float)var4 + par3) / (24000.0F * TerreneCore.getDayCycleDurationMultiplier()) - 0.25F;
        
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
        return (int)(par1 / (24000L * TerreneCore.getDayCycleDurationMultiplier())) % 8;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public float getCloudHeight()
    {
        return 242.0F;
    }
    
    @Override
    public int getAverageGroundLevel()
    {
        return 114;
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
    
    @Override
    public void updateWeather()
    {
        if(worldObj.isRemote)
        {
            super.updateWeather();
        }
        else
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
                        worldInfo.setThunderTime(worldObj.rand.nextInt(TerreneCore.getWeatherDuration(0)) + TerreneCore.getWeatherDuration(1));
                    }
                    else
                    {
                        worldInfo.setThunderTime(worldObj.rand.nextInt(TerreneCore.getWeatherDuration(2)) + TerreneCore.getWeatherDuration(3));
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
                        worldInfo.setRainTime(worldObj.rand.nextInt(TerreneCore.getWeatherDuration(4)) + TerreneCore.getWeatherDuration(5));
                    }
                    else
                    {
                        worldInfo.setRainTime(worldObj.rand.nextInt(TerreneCore.getWeatherDuration(6)) + TerreneCore.getWeatherDuration(7));
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
    }
    
    @Override
    public ChunkCoordinates getSpawnPoint()
    {
        int i = 16 << TerreneCore.getIslandSize();
        
        i += 8;
        
        return new ChunkCoordinates(i, worldObj.getTopSolidOrLiquidBlock(i, i), i);
    }
    
    @Override
    public int getHeight()
    {
        return 256;
    }
    
    @Override
    public int getActualHeight()
    {
        return 256;
    }
    
    @Override
    public double getHorizon()
    {
        return 113.0D;
    }
    
    @Override
    public void resetRainAndThunder()
    {
        if(!worldObj.isRemote)
        {
            WorldInfo worldInfo = worldObj.getWorldInfo();
            
            if(time != worldInfo.getWorldTime())
            {
                long var2 = time + (24000L * TerreneCore.getDayCycleDurationMultiplier());
                worldInfo.setWorldTime(var2 - var2 % (24000L * TerreneCore.getDayCycleDurationMultiplier()));
            }
        }
        
        super.resetRainAndThunder();
    }
}
