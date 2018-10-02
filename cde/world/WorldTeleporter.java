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
                nbt.setDouble("tropicsLastX", player.posX);
                nbt.setDouble("tropicsLastY", player.posY);
                nbt.setDouble("tropicsLastZ", player.posZ);
                nbt.setFloat("tropicsLastYaw", player.rotationYaw);
                nbt.setFloat("tropicsLastPitch", player.rotationPitch);

                if(nbt.hasKey("emberLastX") && nbt.hasKey("emberLastY") && nbt.hasKey("emberLastZ") && nbt.hasKey("emberLastYaw") && nbt.hasKey("emberLastPitch"))
                {
                    player.setLocationAndAngles(nbt.getDouble("emberLastX"), nbt.getDouble("emberLastY"), nbt.getDouble("emberLastZ"), nbt.getFloat("emberLastYaw"), nbt.getFloat("emberLastPitch"));
                }
                else
                {
                    ChunkCoordinates ck = player.worldObj.provider.getSpawnPoint();
                    
                    player.setLocationAndAngles(ck.posX, ck.posY, ck.posZ, 0.0F, 0.0F);
                }
            }
            else if(player.worldObj.provider.dimensionId == WorldCore.getTropicsDimensionId())
            {
                nbt.setDouble("emberLastX", player.posX);
                nbt.setDouble("emberLastY", player.posY);
                nbt.setDouble("emberLastZ", player.posZ);
                nbt.setFloat("emberLastYaw", player.rotationYaw);
                nbt.setFloat("emberLastPitch", player.rotationPitch);

                if(nbt.hasKey("tropicsLastX") && nbt.hasKey("tropicsLastY") && nbt.hasKey("tropicsLastZ") && nbt.hasKey("tropicsLastYaw") && nbt.hasKey("tropicsLastPitch"))
                {
                    player.setLocationAndAngles(nbt.getDouble("tropicsLastX"), nbt.getDouble("tropicsLastY"), nbt.getDouble("tropicsLastZ"), nbt.getFloat("tropicsLastYaw"), nbt.getFloat("tropicsLastPitch"));
                }
                else
                {
                    ChunkCoordinates ck = player.worldObj.provider.getSpawnPoint();
                    
                    player.setLocationAndAngles(ck.posX, ck.posY, ck.posZ, 0.0F, 0.0F);
                }
            }
        }
    }
}
