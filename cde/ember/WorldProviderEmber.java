/**
 *
 * @author StormTiberius
 */

package cde.ember;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.WorldProviderSurface;

public class WorldProviderEmber extends WorldProviderSurface
{
    private static final ChunkCoordinates SPAWN = new ChunkCoordinates(264,20,264);
    
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
        if(isEmber())
        {
            return SPAWN;
        }
        
        return super.getSpawnPoint();
    }
    
    @Override
    public void setSpawnPoint(int x, int y, int z)
    {
        if(isEmber())
        {
            worldObj.getWorldInfo().setSpawnPosition(SPAWN.posX, SPAWN.posY, SPAWN.posZ);
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
}
