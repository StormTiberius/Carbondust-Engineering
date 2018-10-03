/**
 *
 * @author StormTiberius
 */

package cde.world;

import cde.WorldCore;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class WorldTeleporter extends Teleporter
{
    private final String TROPICS_LAST_X = "tropicsLastX";
    private final String TROPICS_LAST_Y = "tropicsLastY";
    private final String TROPICS_LAST_Z = "tropicsLastZ";
    private final String TROPICS_LAST_W = "tropicsLastW";
    private final String TROPICS_LAST_P = "tropicsLastP";
    
    private final String EMBER_LAST_X = "emberLastX";
    private final String EMBER_LAST_Y = "emberLastY";
    private final String EMBER_LAST_Z = "emberLastZ";
    private final String EMBER_LAST_W = "emberLastW";
    private final String EMBER_LAST_P = "emberLastP";
    
    public WorldTeleporter(WorldServer ws)
    {
        super(ws);
    }
    
    @Override
    public void placeInPortal(Entity par1Entity, double par2, double par4, double par6, float par8)
    {
        if(par1Entity instanceof EntityPlayerMP)
        {
            double xOffset = 0.5D;
            double zOffset = 0.5D;
            
            EntityPlayerMP player = (EntityPlayerMP)par1Entity;
            NBTTagCompound nbt = player.getEntityData();
            
            if(player.worldObj.provider.dimensionId == WorldCore.getEmberDimensionId())
            {
                nbt.setDouble(TROPICS_LAST_X, player.posX);
                nbt.setDouble(TROPICS_LAST_Y, player.posY);
                nbt.setDouble(TROPICS_LAST_Z, player.posZ);
                nbt.setFloat(TROPICS_LAST_W, player.rotationYaw);
                nbt.setFloat(TROPICS_LAST_P, player.rotationPitch);

                if(nbt.hasKey(EMBER_LAST_X) && nbt.hasKey(EMBER_LAST_Y) && nbt.hasKey(EMBER_LAST_Z) && nbt.hasKey(EMBER_LAST_W) && nbt.hasKey(EMBER_LAST_P))
                {
                    double xPos = nbt.getDouble(EMBER_LAST_X);
                    double zPos = nbt.getDouble(EMBER_LAST_Z);
                    
                    if(xPos < 0)
                    {
                        xOffset = -0.5D;
                    }
                    
                    if(zPos < 0)
                    {
                        zOffset = -0.5D;
                    }
                    
                    player.setLocationAndAngles(xPos + xOffset, nbt.getDouble(EMBER_LAST_Y), zPos + zOffset, nbt.getFloat(EMBER_LAST_W), nbt.getFloat(EMBER_LAST_P));
                }
                else
                {
                    ChunkCoordinates ck = player.worldObj.provider.getEntrancePortalLocation();
                    
                    if(ck.posX < 0)
                    {
                        xOffset = -0.5D;
                    }
                    
                    if(ck.posZ < 0)
                    {
                        zOffset = -0.5D;
                    }
                    
                    player.setLocationAndAngles(ck.posX + xOffset, ck.posY, ck.posZ + zOffset, 0.0F, 0.0F);
                }
            }
            else if(player.worldObj.provider.dimensionId == WorldCore.getTropicsDimensionId())
            {
                nbt.setDouble(EMBER_LAST_X, player.posX);
                nbt.setDouble(EMBER_LAST_Y, player.posY);
                nbt.setDouble(EMBER_LAST_Z, player.posZ);
                nbt.setFloat(EMBER_LAST_W, player.rotationYaw);
                nbt.setFloat(EMBER_LAST_P, player.rotationPitch);

                if(nbt.hasKey(TROPICS_LAST_X) && nbt.hasKey(TROPICS_LAST_Y) && nbt.hasKey(TROPICS_LAST_Z) && nbt.hasKey(TROPICS_LAST_W) && nbt.hasKey(TROPICS_LAST_P))
                {
                    double xPos = nbt.getDouble(TROPICS_LAST_X);
                    double zPos = nbt.getDouble(TROPICS_LAST_Z);
                    
                    if(xPos < 0)
                    {
                        xOffset = -0.5D;
                    }
                    
                    if(zPos < 0)
                    {
                        zOffset = -0.5D;
                    }
                    
                    player.setLocationAndAngles(nbt.getDouble(TROPICS_LAST_X) + xOffset, nbt.getDouble(TROPICS_LAST_Y), nbt.getDouble(TROPICS_LAST_Z) + zOffset, nbt.getFloat(TROPICS_LAST_W), nbt.getFloat(TROPICS_LAST_P));
                }
                else
                {
                    ChunkCoordinates ck = player.worldObj.provider.getEntrancePortalLocation();
                                        
                    if(ck.posX < 0)
                    {
                        xOffset = -0.5D;
                    }
                    
                    if(ck.posZ < 0)
                    {
                        zOffset = -0.5D;
                    }
                    
                    player.setLocationAndAngles(ck.posX + xOffset, ck.posY, ck.posZ + zOffset, 0.0F, 0.0F);
                }
            }
        }
    }
}
