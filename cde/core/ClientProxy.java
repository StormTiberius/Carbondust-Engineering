/**
 *
 * @author StormTiberius
 */

package cde.core;

import cde.core.sound.SoundEventManager;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.network.packet.Packet;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy
{
    @Override
    public void preloadTexture(String resource)
    {
        MinecraftForgeClient.preloadTexture(resource);
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
}
