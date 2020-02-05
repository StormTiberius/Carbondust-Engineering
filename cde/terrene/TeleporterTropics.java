/**
 *
 * @author StormTiberius
 */

package cde.terrene;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

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
            String prefix = getPrefix(player.dimension);
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
    
    public static String getPrefix(int dimension)
    {
        StringBuilder sb = new StringBuilder("CDE_");
        
        WorldProvider provider = DimensionManager.getProvider(dimension);
        
        if(provider != null)
        {
            sb.append(provider.getDimensionName());
        }
        else
        {
            sb.append("NULL");
        }
        
        sb.append("_");
        
        return sb.toString();
    }
    
    public static boolean hasKeys(EntityPlayerMP player)
    {
        String prefix = getPrefix(player.dimension);
        
        NBTTagCompound tag = player.getEntityData();
        
        return tag.hasKey(prefix + "posX") && 
               tag.hasKey(prefix + "posY") && 
               tag.hasKey(prefix + "posZ") && 
               tag.hasKey(prefix + "rotationYaw") && 
               tag.hasKey(prefix + "rotationPitch");
    }
    
    public static void setDepartureCoords(EntityPlayerMP player)
    {
        String prefix = getPrefix(player.dimension);
        
        NBTTagCompound tag = player.getEntityData();
        
        tag.setDouble(prefix + "posX", player.posX);
        tag.setDouble(prefix + "posY", player.posY);
        tag.setDouble(prefix + "posZ", player.posZ);
        tag.setFloat(prefix + "rotationYaw", player.rotationYaw);
        tag.setFloat(prefix + "rotationPitch", player.rotationPitch);
    }
}
