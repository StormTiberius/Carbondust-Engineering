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
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.WorldInfo;

public class WorldProviderTerrene extends WorldProviderSurface
{
    private static final String KEY = "EmberSpawnPoint";
    
    private SpawnPoint spawnPoint = new SpawnPoint(KEY);
    private long time;
    
    @Override
    protected void registerWorldChunkManager()
    {
        super.registerWorldChunkManager();
        
        if(isEmber())
        {
            hasNoSky = true;
            
            WorldSavedData wsd = worldObj.loadItemData(SpawnPoint.class, KEY);
            
            if(wsd != null)
            {
                spawnPoint = (SpawnPoint)wsd;
            }
        }
    }
    
    @Override
    public boolean canCoordinateBeSpawn(int par1, int par2)
    {
        if(isEmber())
        {
            return true;
        }
        
        return super.canCoordinateBeSpawn(par1, par2);
    }
    
    @Override
    public float calculateCelestialAngle(long par1, float par3)
    {
        if(isEmber())
        {
            return 0.5F;
        }
        
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
    
    @Override
    public boolean isSurfaceWorld()
    {
        if(isEmber())
        {
            return false;
        }
        
        return super.isSurfaceWorld();
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public float getCloudHeight()
    {
        if(isTropics())
        {
            return 242.0F;
        }
        
        if(isEmber())
        {
            return 256.0F;
        }
        
        return super.getCloudHeight();
    }
    
    @Override
    public String getDimensionName()
    {
        if(isTropics())
        {
            return "Tropics";
        }
        
        if(isEmber())
        {
            return "Ember";
        }
        
        return "Overworld";
    }
    
    @Override
    public ChunkCoordinates getRandomizedSpawnPoint()
    {
        if(isEmber())
        {
            getSpawnPoint();
        }
        
        return super.getRandomizedSpawnPoint();
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
        if(isTropics())
        {
            int i = 16 << TerreneCore.getIslandSize();
            
            i += 8;
            
            return new ChunkCoordinates(i, worldObj.getTopSolidOrLiquidBlock(i, i), i);
        }
        
        if(isEmber())
        {
            ChunkCoordinates cc = spawnPoint.getSpawnPoint();
            WorldInfo info = worldObj.getWorldInfo();
            
            if(cc.posX != info.getSpawnX() || cc.posY != info.getSpawnY() || cc.posZ != info.getSpawnZ())
            {
                info.setSpawnPosition(cc.posX, cc.posY, cc.posZ);
            }
            
            return cc;
        }
        
        return super.getSpawnPoint();
    }
    
    @Override
    public void setSpawnPoint(int x, int y, int z)
    {
        if(isEmber())
        {
            ChunkCoordinates cc = spawnPoint.getSpawnPoint();
            worldObj.getWorldInfo().setSpawnPosition(cc.posX, cc.posY, cc.posZ);
        }
        
        super.setSpawnPoint(x, y, z);
    }
    
    @Override
    public int getActualHeight()
    {
        if(isEmber())
        {
            return 256;
        }
        
        return super.getActualHeight();
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
    
    private boolean isTropics()
    {
        return terrainType.getWorldTypeID() == TerreneCore.getTropicsId();
    }
    
    private boolean isEmber()
    {
        return terrainType.getWorldTypeID() == TerreneCore.getEmberId();
    }
    
    public void setSpawnPoint(ChunkCoordinates ck)
    {
        spawnPoint.setSpawnPoint(ck.posX, ck.posY, ck.posZ);
    }
}
