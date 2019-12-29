/**
 *
 * @author StormTiberius
 */

package cde.industry;

import cde.IndustryCore;
import cde.industry.render.RenderBlockDrum;
import cde.industry.render.RenderItemBlockDrum;
import cpw.mods.fml.client.registry.RenderingRegistry;
import java.awt.Color;
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
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        ITexturePack pack = Minecraft.getMinecraft().texturePackList.getSelectedTexturePack();
        
        for(Map.Entry<String, LiquidStack> entry : LiquidDictionary.getLiquids().entrySet())
        {
            LiquidStack liquid = entry.getValue();
            
            if(liquid != null && liquid.asItemStack() != null && liquid.asItemStack().getItem() != null)
            {
                String texture = "/cde/biomass.png"; // liquid.asItemStack().getItem().getTextureFile();
                int index = liquid.asItemStack().getIconIndex();

                if(index == 0 && texture.contentEquals("/gfx/buildcraft/items/items.png"))
                {
                    index = 48; // Bugfix for BC Fuel
                }
                
                try
                {
                    BufferedImage image = ImageIO.read(pack.getResourceAsStream(texture));

                    if(image == null)// || image.getWidth() < 256 || image.getHeight() < 256)
                    {
                        continue;
                    }

                    int size = 16 / 16;
                    
                    int ui = index % 16;
                    int vi = index / 16;
                    
                    int u = size * ui;
                    int v = size * vi;

                    int[] rgbArray = image.getRGB(0, 0, 16, 512, null, 0, 16);

                    float r = 0.0F;
                    float g = 0.0F;
                    float b = 0.0F;

                    for(int i = 0; i < rgbArray.length; i++)
                    {
                        int p = rgbArray[i];

                        r += (float)(-p >> 16 & 0xFF) / 255;
                        g += (float)(-p >> 8 & 0xFF) / 255;
                        b += (float)(-p & 0xFF) / 255;
                    }

                    r /= rgbArray.length;
                    g /= rgbArray.length;
                    b /= rgbArray.length;

                    int rgb = -((int)(r * 255) << 16 | (int)(g * 255) << 8 | (int)(b * 255));
                    
                    rgb = another(rgb, 16777215);
                    
                    System.out.println(liquid.asItemStack().getDisplayName() + " *** \n" + liquid.itemID + "\nRGB " + rgb);
                    
                    map.put(liquid.itemID, rgb);
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        
        IndustryCore.setColorMap(map);
    }
    
    private int another(int color, int defaultColor)
    {
        float r = ((float)(color >> 16 & 0xFF) / 255) * ((float)(defaultColor >> 16 & 0xFF) / 255);
        float g = ((float)(color >> 8 & 0xFF) / 255) * ((float)(defaultColor >> 8 & 0xFF) / 255);
        float b = ((float)(color & 0xFF) / 255) * ((float)(defaultColor & 0xFF) / 255);
        
        int rgb = ((int)(r * 255) << 16 | (int)(g * 255) << 8 | (int)(b * 255));

        return rgb;
    }
}
