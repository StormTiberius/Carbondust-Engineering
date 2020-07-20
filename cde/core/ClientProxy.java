/**
 *
 * @author StormTiberius
 */

package cde.core;

import cde.sound.SoundEventManager;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.network.packet.Packet;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy
{
    @Override
    public void preloadTextures()
    {
        MinecraftForgeClient.preloadTexture(Info.CDE_BLOCKS);
        MinecraftForgeClient.preloadTexture(Info.CDE_ITEMS);
    }
    
    @Override
    public void sendToServer(Packet packet)
    {
        FMLClientHandler.instance().getClient().getSendQueue().addToSendQueue(packet);
    }
    
    @Override
    public void registerSoundEventManager()
    {
        MinecraftForge.EVENT_BUS.register(new SoundEventManager());
    }
    
    @Override
    public void spawnParticle(EntityFX effect)
    {
        FMLClientHandler.instance().getClient().effectRenderer.addEffect(effect);
    }
}
