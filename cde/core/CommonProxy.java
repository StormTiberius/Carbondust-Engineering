/**
 *
 * @author StormTiberius
 */

package cde.core;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.packet.Packet;
import net.minecraft.world.World;

public class CommonProxy
{
    public void preloadTextures(){}
    public void sendToServer(Packet packet){}
    public void registerSoundEventManager(){}
    public void spawnParticle(EntityFX effect){}
    
    public void sendNetworkPacketToPlayer(EntityPlayer entityPlayer, Packet packet)
    {
        if(entityPlayer instanceof EntityPlayerMP)
        {
            EntityPlayerMP player = (EntityPlayerMP)entityPlayer;
            player.playerNetServerHandler.sendPacketToPlayer(packet);
        }
    }
    
    public void sendNetworkPacketToPlayers(Packet packet, World world, int x, int y, int z, int maxDistance)
    {
        for(Object o : world.playerEntities)
        {
            if(o instanceof EntityPlayerMP)
            {
                EntityPlayerMP player = (EntityPlayerMP)o;
                
                if(Math.abs(player.posX - x) <= maxDistance && Math.abs(player.posY - y) <= maxDistance && Math.abs(player.posZ - z) <= maxDistance)
                {
                    player.playerNetServerHandler.sendPacketToPlayer(packet);
                }
            }
        }
    }
}
