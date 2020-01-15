/**
 *
 * @author StormTiberius
 */

package cde.core.util;

import cde.core.Defaults;
import java.awt.Color;
import net.minecraft.item.ItemStack;

public class Utils
{
    private static final double UV_SPACING = 1.0D / 16;
    
    public static ItemStack getNewItemStackWithQuantity(ItemStack is, int qty)
    {
        return new ItemStack(is.itemID, qty, is.getItemDamage());
    }
    
    public static double[] getUV(int index)
    {
        double u = index % 16;
        double v = index / 16;
        
        double min_u = UV_SPACING * u;
        double max_u = min_u + UV_SPACING;
        
        double min_v = UV_SPACING * v;
        double max_v = min_v + UV_SPACING;
        
        
        return new double[] {min_u, max_u, min_v, max_v};
    }
    
    public static int getClosestMinecraftColor(Color color)
    {
        int[] r = new int[16];
        int[] g = new int[16];
        int[] b = new int[16];
        int[] d = new int[16];
        
        for(int i = 0; i < 16; i++)
        {
            r[i] = Defaults.MINECRAFT_COLORS[i].getRed() - color.getRed();
            g[i] = Defaults.MINECRAFT_COLORS[i].getGreen() - color.getGreen();
            b[i] = Defaults.MINECRAFT_COLORS[i].getBlue() - color.getBlue();
            
            if(r[i] < 0)
            {
                r[i] *= -1;
            }
            
            if(g[i] < 0)
            {
                g[i] *= -1;
            }
            
            if(b[i] < 0)
            {
                b[i] *= -1;
            }
            
            d[i] = r[i] + g[i] + b[i];
        }
        
        int distance = d[0];
        int index = 0;

        for(int i = 0; i < 16; i++)
        {
            if(d[i] < distance)
            {
                distance = d[i];
                index = i;
            }
        }
        
        return index;
    }
}
