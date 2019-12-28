/**
 *
 * @author StormTiberius
 */

package cde.industry;

import cde.IndustryCore;
import cde.industry.render.RenderBlockDrum;
import cde.industry.render.RenderItemBlockDrum;
import cpw.mods.fml.client.registry.RenderingRegistry;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.texturepacks.ITexturePack;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

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
    
    @Override
    public void prepLiquidColors()
    {
        Map map = new HashMap<Integer, Integer>();
        
        ITexturePack pack = Minecraft.getMinecraft().texturePackList.getSelectedTexturePack();
        
        for(Map.Entry<String, LiquidStack> entry : LiquidDictionary.getLiquids().entrySet())
        {
            LiquidStack liquid = entry.getValue();
            
            if(liquid != null && liquid.asItemStack() != null && liquid.asItemStack().getItem() != null)
            {
                String texture = liquid.asItemStack().getItem().getTextureFile();
                int index = liquid.asItemStack().getIconIndex();

                try
                {
                    BufferedImage image = ImageIO.read(pack.getResourceAsStream(texture));

                    int size = image.getWidth() / 16;
                    
                    int ui = index % 16;
                    int vi = index / 16;
                    
                    int u = size * ui;
                    int v = size * vi;
                    
                    int[] rgbArray = new int[size * size];

                    if(rgbArray.length == 0)
                    {
                        return;
                    }

                    image.getRGB(u, v, size, size, rgbArray, 0, size);

                    float r = 0.0F;
                    float g = 0.0F;
                    float b = 0.0F;

                    for(int i = 0; i < rgbArray.length; i++)
                    {
                        int a = rgbArray[i];

                        r += (float)(-a >> 16 & 0xff) / 255;
                        g += (float)(-a >> 8 & 0xff) / 255;
                        b += (float)(-a & 0xff) / 255;
                    }

                    r /= rgbArray.length;
                    g /= rgbArray.length;
                    b /= rgbArray.length;

                    map.put(liquid.itemID, -((int)(r * 255) << 16 | (int)(g * 255) << 8 | (int)(b * 255)));
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        
        IndustryCore.setColorMap(map);
    }
}
