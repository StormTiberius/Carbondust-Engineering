package cde.tropics;

import cde.WorldCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderPacific extends WorldProvider
{
    @Override
    public String getDimensionName()
    {
        return "Pacific";
    }
    
    @Override
    protected void registerWorldChunkManager()
    {
        worldChunkMgr = new WorldChunkManagerPacific(worldObj);
    }

    @Override
    public IChunkProvider createChunkGenerator()
    {
        return new ChunkProviderPacific(worldObj, worldObj.getSeed(), false);
    }

    @Override
    public boolean canCoordinateBeSpawn(int par1, int par2)
    {
        int var3 = this.worldObj.getFirstUncoveredBlock(par1, par2);
        return var3 == Block.grass.blockID || var3 == Block.sand.blockID;
    }

    @Override
    public float calculateCelestialAngle(long par1, float par3)
    {
        if(!WorldCore.isDayMode(dimensionId))
        {
            return super.calculateCelestialAngle(par1, par3);
        }
        else
        {
            return 90.0F;
        }
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
