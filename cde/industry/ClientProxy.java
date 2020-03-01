/**
 *
 * @author StormTiberius
 */

package cde.industry;

import cde.industry.drum.ModuleDrum;
import cde.industry.drum.RenderBlockDrum;
import cde.industry.drum.RenderItemBlockDrum;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy
{   
    @Override
    public void registerRenderers()
    {
        if(ModuleDrum.isDrumEnabled())
        {
            ModuleDrum.setDrumRenderId(RenderingRegistry.getNextAvailableRenderId());
            RenderingRegistry.registerBlockHandler(ModuleDrum.getDrumRenderId(), new RenderBlockDrum());
            MinecraftForgeClient.registerItemRenderer(ModuleDrum.blockDrum.blockID, new RenderItemBlockDrum());
        }
    }
}
