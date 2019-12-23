/**
 *
 * @author StormTiberius
 */

package cde.industry;

import cde.IndustryCore;
import cde.industry.render.RenderBlockDrum;
import cde.industry.render.RenderItemBlockDrum;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy
{   
    @Override
    public void registerRenderers()
    {
        if(IndustryCore.isDrumEnabled())
        {
            IndustryCore.setDrumRenderId(RenderingRegistry.getNextAvailableRenderId());
            RenderingRegistry.registerBlockHandler(IndustryCore.getDrumRenderId(), new RenderBlockDrum());

            MinecraftForgeClient.registerItemRenderer(IndustryCore.blockDrum.blockID, new RenderItemBlockDrum());
        }
    }
}
