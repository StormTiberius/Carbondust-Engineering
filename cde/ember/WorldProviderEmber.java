/**
 *
 * @author StormTiberius
 */

package cde.ember;

import cde.EmberCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.WorldInfo;

public class WorldProviderEmber extends WorldProvider
{
    private LocationData ld = new LocationData(EmberCore.EMBER_SPAWN_LOCATION_KEYWORD);
    
    @Override
    public String getDimensionName()
    {
        return "Ember";
    }
    
    @Override
    protected void registerWorldChunkManager()
    {
        worldChunkMgr = new WorldChunkManagerEmber(EmberCore.ember, 0.5F, 0.5F);
        hasNoSky = true;
        dimensionId = EmberCore.getDimensionId();
        
        LocationData data = (LocationData)worldObj.loadItemData(LocationData.class, EmberCore.EMBER_SPAWN_LOCATION_KEYWORD);
        
        if(data != null)
        {
            ld = data;
        }
    }
    
    @Override
    public IChunkProvider createChunkGenerator()
    {
        return new ChunkProviderEmber(worldObj, worldObj.getSeed(), worldObj.getWorldInfo().isMapFeaturesEnabled());
    }
    
    @Override
    public boolean isSurfaceWorld()
    {
        return false;
    }
    
    @Override
    public int getAverageGroundLevel()
    {
        return 128;
    }
    
    @Override
    public boolean canCoordinateBeSpawn(int par1, int par2)
    {
        return true;
    }
    
    @Override
    public void setSpawnPoint(int x, int y, int z)
    {
        ChunkCoordinates cc = ld.getSpawnLocation();
        worldObj.getWorldInfo().setSpawnPosition(cc.posX, cc.posY, cc.posZ);
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
    public ChunkCoordinates getRandomizedSpawnPoint()
    {
        return getSpawnPoint();
    }
    
    @Override
    public ChunkCoordinates getEntrancePortalLocation()
    {
        return getSpawnPoint();
    }
    
    @Override
    public int getActualHeight()
    {
        return 256;
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
    
    public void setLocationData(LocationData ld)
    {
        this.ld = ld;
    }
}
