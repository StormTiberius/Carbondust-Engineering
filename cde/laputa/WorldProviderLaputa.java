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
        worldChunkMgr = new WorldChunkManagerLaputa(LaputaCore.laputa, 0.8F, 0.4F);
        dimensionId = LaputaCore.getDimensionId();
        setSkyRenderer(new SkyRendererGoG());
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
        int var4 = (int)(par1 % 72000L);
        float var5 = ((float)var4 + par3) / 72000.0F - 0.25F;

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
        return (int)(par1 / 72000L) % 8;
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
        if (!this.provider.hasNoSky)
        {
            int var1 = this.worldInfo.getThunderTime();

            if (var1 <= 0)
            {
                if (this.worldInfo.isThundering())
                {
                    this.worldInfo.setThunderTime(this.rand.nextInt(12000) + 3600);
                }
                else
                {
                    this.worldInfo.setThunderTime(this.rand.nextInt(168000) + 12000);
                }
            }
            else
            {
                --var1;
                this.worldInfo.setThunderTime(var1);

                if (var1 <= 0)
                {
                    this.worldInfo.setThundering(!this.worldInfo.isThundering());
                }
            }

            int var2 = this.worldInfo.getRainTime();

            if (var2 <= 0)
            {
                if (this.worldInfo.isRaining())
                {
                    this.worldInfo.setRainTime(this.rand.nextInt(12000) + 12000);
                }
                else
                {
                    this.worldInfo.setRainTime(this.rand.nextInt(168000) + 12000);
                }
            }
            else
            {
                --var2;
                this.worldInfo.setRainTime(var2);

                if (var2 <= 0)
                {
                    this.worldInfo.setRaining(!this.worldInfo.isRaining());
                }
            }

            this.prevRainingStrength = this.rainingStrength;

            if (this.worldInfo.isRaining())
            {
                this.rainingStrength = (float)((double)this.rainingStrength + 0.01D);
            }
            else
            {
                this.rainingStrength = (float)((double)this.rainingStrength - 0.01D);
            }

            if (this.rainingStrength < 0.0F)
            {
                this.rainingStrength = 0.0F;
            }

            if (this.rainingStrength > 1.0F)
            {
                this.rainingStrength = 1.0F;
            }

            this.prevThunderingStrength = this.thunderingStrength;

            if (this.worldInfo.isThundering())
            {
                this.thunderingStrength = (float)((double)this.thunderingStrength + 0.01D);
            }
            else
            {
                this.thunderingStrength = (float)((double)this.thunderingStrength - 0.01D);
            }

            if (this.thunderingStrength < 0.0F)
            {
                this.thunderingStrength = 0.0F;
            }

            if (this.thunderingStrength > 1.0F)
            {
                this.thunderingStrength = 1.0F;
            }
        }
    }
    
    @Override
    public void setWorldTime(long time)
    {
        worldObj.getWorldInfo().setWorldTime(time);
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
}
