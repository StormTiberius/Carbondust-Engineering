/**
 *
 * @author StormTiberius
 */

package cde.core.util;

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
}
