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
    public void preloadTexture(String resource){}
    public void setupSound(){}
    public void spawnParticle(EntityFX effect){}
    public void sendToServer(Packet packet){}
    
    public void sendToPlayers(Packet packet, World world, int x, int y, int z, int maxDistance)
    {
        if(packet != null)
        {
            for(Object o : world.playerEntities)
            {
                EntityPlayerMP player = (EntityPlayerMP)o;
                
                if(Math.abs(player.posX - x) <= maxDistance && Math.abs(player.posY - y) <= maxDistance && Math.abs(player.posZ - z) <= maxDistance)
                {
                    player.playerNetServerHandler.sendPacketToPlayer(packet);
                }
            }
        }
    }
    
    public void sendToPlayer(EntityPlayer entityplayer, Packet packet)
    {
        EntityPlayerMP player = (EntityPlayerMP)entityplayer;
        player.playerNetServerHandler.sendPacketToPlayer(packet);
    }
}
