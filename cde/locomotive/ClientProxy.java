/**
 *
 * @author StormTiberius
 */

package cde.locomotive;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{   
    @Override
    public void registerRenderers()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityLocomotive.class, new RenderLocomotive());
    }
}
