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
        if(terrainType.getWorldTypeName().contentEquals("ember"))
        {
            return "Ember";
        }
        
        return super.getDimensionName();
    }
    
    @Override
    protected void registerWorldChunkManager()
    {
        super.registerWorldChunkManager();

        if(terrainType.getWorldTypeName().contentEquals("ember"))
        {
            hasNoSky = true;
        }
    }
    
    @Override
    public boolean canCoordinateBeSpawn(int par1, int par2)
    {
        if(terrainType.getWorldTypeName().contentEquals("ember"))
        {
            return true;
        }
        
        return super.canCoordinateBeSpawn(par1, par2);
    }
    
    @Override
    public float calculateCelestialAngle(long par1, float par3)
    {
        if(terrainType.getWorldTypeName().contentEquals("ember"))
        {
            return 0.5F;
        }
        
        return super.calculateCelestialAngle(par1, par3);
    }
    
    @Override
    public boolean isSurfaceWorld()
    {
        if(terrainType.getWorldTypeName().contentEquals("ember"))
        {
            return false;
        }
        
        return super.isSurfaceWorld();
    }
    
    @Override
    public int getAverageGroundLevel()
    {
        if(terrainType.getWorldTypeName().contentEquals("ember"))
        {
            return 128;
        }
        
        return super.getAverageGroundLevel();
    }
    
    @Override
    public ChunkCoordinates getRandomizedSpawnPoint()
    {
        if(terrainType.getWorldTypeName().contentEquals("ember"))
        {
            return getSpawnPoint();
        }
        
        return super.getRandomizedSpawnPoint();
    }
    
    @Override
    public ChunkCoordinates getSpawnPoint()
    {
        if(terrainType.getWorldTypeName().contentEquals("ember"))
        {
            return SPAWN;
        }
        
        return super.getSpawnPoint();
    }
    
    @Override
    public void setSpawnPoint(int x, int y, int z)
    {
        if(terrainType.getWorldTypeName().contentEquals("ember"))
        {
            worldObj.getWorldInfo().setSpawnPosition(SPAWN.posX, SPAWN.posY, SPAWN.posZ);
        }
        else
        {
            super.setSpawnPoint(x, y, z);
        }
    }
    
    @Override
    public int getHeight()
    {
        return 256;
    }

    @Override
    public int getActualHeight()
    {
        if(terrainType.getWorldTypeName().contentEquals("ember"))
        {
            return 256;
        }
        
        return super.getActualHeight();
    }
}
