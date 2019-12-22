/**
 *
 * @author StormTiberius
 */

package cde.industry;

import cde.IndustryCore;
import cde.industry.render.RenderDrum;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{   
    @Override
    public void registerRenderers()
    {
        IndustryCore.drumRenderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(IndustryCore.drumRenderId, new RenderDrum());
    }
}