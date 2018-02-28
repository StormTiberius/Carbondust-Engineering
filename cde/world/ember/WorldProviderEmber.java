/**
 *
 * @author StormTiberius
 */

package cde.world.ember;

import cde.world.EmberModule;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderEmber extends WorldProvider
{
    private final String ID = "EmberSpawnLocation";
    private LocationData ld = new LocationData(ID);
    
    @Override
    public String getDimensionName()
    {
        return "Ember";
    }
    
    @Override
    protected void registerWorldChunkManager()
    {
        worldChunkMgr = new WorldChunkManagerHell(EmberModule.ember, 0.5F, 0.5F);
        dimensionId = EmberModule.getDimId();
        hasNoSky = true;
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
        ld.setSpawn(x, y, z);
        MinecraftServer.getServer().worldServerForDimension(dimensionId).perWorldStorage.setData(ID, ld);
    }
        
    @Override
    public ChunkCoordinates getSpawnPoint()
    {
        if(ld.isValid())
        {
            return ld.getSpawn();
        }
        else
        {
            WorldSavedData wsd = MinecraftServer.getServer().worldServerForDimension(dimensionId).perWorldStorage.loadData(LocationData.class, ID);
            
            if(wsd != null)
            {
                ld = (LocationData)wsd;
            }
            
            return ld.getSpawn();
        }
    }
        
    @Override
    public ChunkCoordinates getRandomizedSpawnPoint()
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
}
