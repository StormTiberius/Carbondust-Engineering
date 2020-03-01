/**
 *
 * @author StormTiberius
 */

package cde.industry;

import cde.industry.drum.DrumModule;
import cde.industry.drum.RenderBlockDrum;
import cde.industry.drum.RenderItemBlockDrum;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy
{   
    @Override
    public void registerRenderers()
    {
        if(DrumModule.isDrumEnabled())
        {
            DrumModule.setDrumRenderId(RenderingRegistry.getNextAvailableRenderId());
            RenderingRegistry.registerBlockHandler(DrumModule.getDrumRenderId(), new RenderBlockDrum());
            MinecraftForgeClient.registerItemRenderer(DrumModule.blockDrum.blockID, new RenderItemBlockDrum());
        }
    }
}
