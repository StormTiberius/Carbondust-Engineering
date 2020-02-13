/**
 *
 * @author StormTiberius
 */

package cde.ember;

import cde.EmberCore;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.storage.WorldInfo;

public class WorldProviderEmber extends WorldProviderSurface
{
    private ChunkCoordinates spawnPoint;
    private LocationData ld = new LocationData(EmberCore.EMBER_SPAWN_LOCATION_KEYWORD);
    
    @Override
    public String getDimensionName()
    {
        if(isEmber())
        {
            return "Ember";
        }
        
        return super.getDimensionName();
    }
    
    @Override
    protected void registerWorldChunkManager()
    {
        super.registerWorldChunkManager();

        if(isEmber())
        {
            hasNoSky = true;
            
            LocationData data = (LocationData)worldObj.loadItemData(LocationData.class, EmberCore.EMBER_SPAWN_LOCATION_KEYWORD);

            if(data != null)
            {
                ld = data;
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
        
        return super.calculateCelestialAngle(par1, par3);
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
    
    @Override
    public int getAverageGroundLevel()
    {
        if(isEmber())
        {
            return 128;
        }
        
        return super.getAverageGroundLevel();
    }
    
    @Override
    public ChunkCoordinates getRandomizedSpawnPoint()
    {
        if(isEmber())
        {
            return getSpawnPoint();
        }
        
        return super.getRandomizedSpawnPoint();
    }
    
    @Override
    public ChunkCoordinates getSpawnPoint()
    {
        ChunkCoordinates cc = ld.getSpawnLocation();
        WorldInfo info = worldObj.getWorldInfo();
        
        if(cc.posX != info.getSpawnX() || cc.posY != info.getSpawnY() || cc.posZ != info.getSpawnZ())
        {
            info.setSpawnPosition(cc.posX, cc.posY, cc.posZ);
        }
        
        return cc;
    }
    
    @Override
    public void setSpawnPoint(int x, int y, int z)
    {
        if(isEmber())
        {
            ChunkCoordinates cc = ld.getSpawnLocation();
            worldObj.getWorldInfo().setSpawnPosition(cc.posX, cc.posY, cc.posZ);
        }
        else
        {
            super.setSpawnPoint(x, y, z);
        }
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
    
    private boolean isEmber()
    {
        return terrainType.getWorldTypeName().contentEquals("ember");
    }
    
    public void setLocationData(int x, int y, int z)
    {
        ld.setSpawnLocation(x, y, z);
    }
}
