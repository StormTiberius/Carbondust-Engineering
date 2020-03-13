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
    private int delay;
    private long time;
    private boolean flag;
    
    @Override
    public void updateEntity()
    {
        if(worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))
        {
            if(worldObj.isRemote)
            {
                if(worldObj.isAirBlock(xCoord, yCoord + 1, zCoord))
                {
                    double x = (double)xCoord + worldObj.rand.nextDouble();
                    double y = (double)yCoord + worldObj.rand.nextDouble() * 0.5D + 1.0D;
                    double z = (double)zCoord + worldObj.rand.nextDouble();
                    
                    CDECore.proxy.spawnParticle(new ParticleSmoke(worldObj, x, y, z));
                }
            }
            else
            {
                if(!flag && worldObj.getBlockId(xCoord, yCoord + 1, zCoord) == Block.snow.blockID)
                {
                    delay = 1000 + worldObj.rand.nextInt(4000);
                    time = System.currentTimeMillis();
                    flag = true;
                }
                
                if(flag && System.currentTimeMillis() - time > delay)
                {
                    if(worldObj.getBlockId(xCoord, yCoord + 1, zCoord) == Block.snow.blockID)
                    {
                        worldObj.setBlock(xCoord, yCoord + 1, zCoord, 0);
                        flag = false;
                    }
                }
            }
        }
    }
}
