/**
 *
 * @author StormTiberius
 */

package cde.ember;

import cde.EmberCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderEmber extends WorldProvider
{
    @Override
    public String getDimensionName()
    {
        return "Ember";
    }
    
    @Override
    protected void registerWorldChunkManager()
    {
        worldChunkMgr = new WorldChunkManagerHell(EmberCore.ember, 0.5F, 0.5F);
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
    public ChunkCoordinates getRandomizedSpawnPoint()
    {
        ChunkCoordinates var5 = new ChunkCoordinates(this.worldObj.getSpawnPoint());

        int spawnFuzz = terrainType.getSpawnFuzz();
        int spawnFuzzHalf = spawnFuzz / 2;

        int yPos = var5.posY;

        for(int i = 0; i < 20; i++)
        {
            var5.posX += this.worldObj.rand.nextInt(spawnFuzz) - spawnFuzzHalf;
            var5.posZ += this.worldObj.rand.nextInt(spawnFuzz) - spawnFuzzHalf;
        
            for(int y = 11; y < 55; y++)
            {
                if(worldObj.getBlockId(var5.posX, y, var5.posZ) == 0)
                {
                    var5.posY = y;
                    break;
                }
            }
            
            if(yPos != var5.posY && (worldObj.getBlockId(var5.posX, var5.posY - 1, var5.posZ) != Block.lavaMoving.blockID && worldObj.getBlockId(var5.posX, var5.posY - 1, var5.posZ) != Block.lavaStill.blockID))
            {
                break;
            }
        }

        return var5;
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
