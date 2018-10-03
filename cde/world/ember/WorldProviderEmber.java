/**
 *
 * @author StormTiberius
 */

package cde.world.ember;

import cde.WorldCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderEmber extends WorldProvider
{
    private LocationData ld = new LocationData(WorldCore.EMBER_SPAWN_LOCATION_KEYWORD);
    
    @Override
    public String getDimensionName()
    {
        return "Ember";
    }
    
    @Override
    protected void registerWorldChunkManager()
    {
        worldChunkMgr = new WorldChunkManagerEmber(WorldCore.ember, 0.5F, 0.5F);
        hasNoSky = true;
        dimensionId = WorldCore.getEmberDimensionId();
        
        LocationData data = (LocationData)worldObj.loadItemData(LocationData.class, WorldCore.EMBER_SPAWN_LOCATION_KEYWORD);
        
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
        return false;
    }
    
    @Override
    public boolean canRespawnHere()
    {
        return false;
    }
    
    @Override
    public ChunkCoordinates getEntrancePortalLocation()
    {
        return ld.getSpawnLocation();
    }
    
    @Override
    public int getRespawnDimension(EntityPlayerMP player)
    {
        return WorldCore.getTropicsDimensionId();
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
        
        ChunkCoordinates ck = ld.getSpawnLocation();
        
        for(Object o : worldObj.playerEntities)
        {
            if(o instanceof EntityPlayerMP)
            {
                EntityPlayerMP player = (EntityPlayerMP)o;
                
                player.setLocationAndAngles(ck.posX + 0.5D, ck.posY, ck.posZ + 0.5D, 0.0F, 0.0F);
            }
        }
    }
}
