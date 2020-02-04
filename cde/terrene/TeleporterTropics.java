/**
 *
 * @author StormTiberius
 */

package cde.terrene;

import cde.TropicsCore;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterTropics extends Teleporter
{
    public TeleporterTropics(WorldServer ws)
    {
        super(ws);
    }
    
    @Override
    public void placeInPortal(Entity entity, double posX, double posY, double posZ, float rotationYaw)
    {
        if(entity instanceof EntityPlayerMP)
        {
            EntityPlayerMP player = (EntityPlayerMP)entity;
            String prefix = getPrefix(player);
            NBTTagCompound tag = player.getEntityData();
            
            if(hasKeys(player))
            {
                player.setLocationAndAngles(tag.getDouble(prefix + "posX"),
                                            tag.getDouble(prefix + "posY"),
                                            tag.getDouble(prefix + "posZ"),
                                            tag.getFloat(prefix + "rotationYaw"),
                                            tag.getFloat(prefix + "rotationPitch"));
                
                player.motionX = player.motionY = player.motionZ = 0.0D;
            }
        }
    }
    
    public static String getPrefix(EntityPlayerMP player)
    {
        StringBuilder sb = new StringBuilder("CDE_");
        
        if(player.dimension == TropicsCore.getDimensionId())
        {
            sb.append("Tropics");
        }
        else if(player.dimension == TropicsCore.getEmberDimensionId())
        {
            sb.append("Ember");
        }
        
        sb.append("_");
        
        return sb.toString();
    }
    
    public static boolean hasKeys(EntityPlayerMP player)
    {
        String prefix = getPrefix(player);
        
        NBTTagCompound tag = player.getEntityData();
        
        return tag.hasKey(prefix + "posX") &&
               tag.hasKey(prefix + "posY") &&
               tag.hasKey(prefix + "posZ") &&
               tag.hasKey(prefix + "rotationYaw") &&
               tag.hasKey(prefix + "rotationPitch");
    }
    
    public static void setDepartureCoords(EntityPlayerMP player)
    {
        String prefix = getPrefix(player);
        
        NBTTagCompound tag = player.getEntityData();
        
        tag.setDouble(prefix + "posX", player.posX);
        tag.setDouble(prefix + "posY", player.posY);
        tag.setDouble(prefix + "posZ", player.posZ);
        tag.setFloat(prefix + "rotationYaw", player.rotationYaw);
        tag.setFloat(prefix + "rotationPitch", player.rotationPitch);
    }
}
