/**
 *
 * @author StormTiberius
 */

package cde.locomotive;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy
{
    @Override
    public void registerRenderers()
    {
        MinecraftForgeClient.preloadTexture("/cde/locomotive/locomotives.png");
        RenderingRegistry.registerEntityRenderingHandler(EntityLocomotive.class, new RenderLocomotive());
    }
}
