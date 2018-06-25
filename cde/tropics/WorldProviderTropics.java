/**
 *
 * @author StormTiberius
 */

package cde.tropics;

import cde.TropicsCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderTropics extends WorldProvider
{
    @Override
    public String getDimensionName()
    {
        return "Tropics";
    }
    
    @Override
    protected void registerWorldChunkManager()
    {
        worldChunkMgr = new WorldChunkManagerTropics(worldObj);
        dimensionId = TropicsCore.getDimensionId();
    }
    
    @Override
    public IChunkProvider createChunkGenerator()
    {
        return new ChunkProviderTropics(worldObj, worldObj.getSeed(), worldObj.getWorldInfo().isMapFeaturesEnabled());
    }
        
    @Override
    public boolean canCoordinateBeSpawn(int par1, int par2)
    {
        int var3 = this.worldObj.getFirstUncoveredBlock(par1, par2);
        return var3 == Block.grass.blockID || var3 == Block.sand.blockID;
    }
        
    @SideOnly(Side.CLIENT)
    @Override
    public float getCloudHeight()
    {
        return 256.0F;
    }
    
    @Override
    public int getAverageGroundLevel()
    {
        return 128;
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
    public double getHorizon()
    {
        return 127.0D;
    }
}