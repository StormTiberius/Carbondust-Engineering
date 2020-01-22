/**
 *
 * @author StormTiberius
 */

package cde.machinery;

import cde.CDECore;
import cde.core.particles.ParticleSmoke;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySmokestack extends TileEntity
{
    private long previousSnowMeltTime;
    
    @Override
    public void updateEntity()
    {
        if(worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))
        {
            if(worldObj.isRemote && worldObj.isAirBlock(xCoord, yCoord + 1, zCoord))
            {
                double x = (double)xCoord + worldObj.rand.nextDouble();
                double y = (double)yCoord + worldObj.rand.nextDouble() * 0.5D + 1.0D;
                double z = (double)zCoord + worldObj.rand.nextDouble();
                
                CDECore.proxy.spawnParticle(new ParticleSmoke(worldObj, x, y, z));
            }
            else if(!worldObj.isRemote && worldObj.getBlockId(xCoord, yCoord + 1, zCoord) == Block.snow.blockID)
            {
                if(System.currentTimeMillis() - previousSnowMeltTime > 5000)
                {
                    worldObj.setBlock(xCoord, yCoord + 1, zCoord, 0);
                    previousSnowMeltTime = System.currentTimeMillis();
                }
            }
        }
    }
}
