/**
 *
 * @author StormTiberius
 */

package cde.world.atlantic;

import cde.WorldCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderAtlantic extends WorldProvider
{
    @Override
    public String getDimensionName()
    {
        return "Atlantic";
    }
    
    @Override
    protected void registerWorldChunkManager()
    {
        worldChunkMgr = new WorldChunkManagerAtlantic(WorldCore.atlantic, 0.5F, 0.5F);
        dimensionId = WorldCore.getAtlanticDimensionId();
    }
    
    @Override
    public IChunkProvider createChunkGenerator()
    {
        return new ChunkProviderAtlantic(worldObj, worldObj.getSeed(), worldObj.getWorldInfo().isMapFeaturesEnabled());
    }
    
    @Override
    public boolean canCoordinateBeSpawn(int par1, int par2)
    {
        int var3 = this.worldObj.getFirstUncoveredBlock(par1, par2);
        return var3 == Block.grass.blockID || var3 == Block.sand.blockID;
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
