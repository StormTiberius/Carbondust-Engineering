/**
 *
 * @author StormTiberius
 */

package cde.core;

import cde.CDECore;
import cde.core.sound.SoundEventManager;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy
{
    @Override
    public void preloadTextures()
    {
        MinecraftForgeClient.preloadTexture(CDECore.CDE_BLOCKS);
        MinecraftForgeClient.preloadTexture(CDECore.CDE_ITEMS);
    }
    
    @Override
    public void setupSound()
    {
        MinecraftForge.EVENT_BUS.register(new SoundEventManager());
    }
    
    @Override
    public void spawnParticle(EntityFX effect)
    {
        FMLClientHandler.instance().getClient().effectRenderer.addEffect(effect);
    }
    
    @Override
    public void sendToServer(Packet packet)
    {
        FMLClientHandler.instance().getClient().getSendQueue().addToSendQueue(packet);
    }
    
    @Override
    public void sendToPlayers(Packet packet, World world, int x, int y, int z, int maxDistance){}
    
    @Override
    public void sendToPlayer(EntityPlayer entityplayer, Packet packet){}
}
