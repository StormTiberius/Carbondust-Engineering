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
                    player.setLocationAndAngles(nbt.getDouble(EMBER_LAST_X) + 0.5D, nbt.getDouble(EMBER_LAST_Y), nbt.getDouble(EMBER_LAST_Z) + 0.5D, nbt.getFloat(EMBER_LAST_W), nbt.getFloat(EMBER_LAST_P));
                }
                else
                {
                    ChunkCoordinates ck = player.worldObj.provider.getEntrancePortalLocation();
                    
                    player.setLocationAndAngles(ck.posX + 0.5D, ck.posY, ck.posZ + 0.5D, 0.0F, 0.0F);
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
                    player.setLocationAndAngles(nbt.getDouble(TROPICS_LAST_X) + 0.5D, nbt.getDouble(TROPICS_LAST_Y), nbt.getDouble(TROPICS_LAST_Z) + 0.5D, nbt.getFloat(TROPICS_LAST_W), nbt.getFloat(TROPICS_LAST_P));
                }
                else
                {
                    ChunkCoordinates ck = player.worldObj.provider.getEntrancePortalLocation();
                    
                    player.setLocationAndAngles(ck.posX + 0.5D, ck.posY, ck.posZ + 0.5D, 0.0F, 0.0F);
                }
            }
        }
    }
}
