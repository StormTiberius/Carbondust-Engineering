/**
 *
 * @author StormTiberius
 */

package cde.core;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Utils
{
    private static final double UV_SPACING = 1.0D / 16;
    
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
    
    public static ItemStack getNewItemStack(ItemStack is, int qty)
    {
        return new ItemStack(is.itemID, qty, is.getItemDamage());
    }
    
    public static ItemStack getNewItemStack(ItemStack is)
    {
        return getNewItemStack(is, 1);
    }
    
    public static List<Block> getBlocksByClass(String name)
    {
        List<Block> blocks = new ArrayList();
        
        for(Block block : Block.blocksList)
        {
            if(block != null && block.getClass().getName().equals(name))
            {
                blocks.add(block);
            }
        }
        
        return blocks;
    }
    
    public static Block getBlockByClass(String name)
    {
        List<Block> blocks = getBlocksByClass(name);
        
        if(!blocks.isEmpty())
        {
            return blocks.get(0);
        }
        
        return null;
    }
    
    public static List<Item> getItemsByClass(String name)
    {
        List<Item> items = new ArrayList();
        
        for(Item item : Item.itemsList)
        {
            if(item != null && item.getClass().getName().equals(name))
            {
                items.add(item);
            }
        }
        
        return items;
    }
    
    public static Item getItemByClass(String name)
    {
        List<Item> items = getItemsByClass(name);
        
        if(!items.isEmpty())
        {
            return items.get(0);
        }
        
        return null;
    }
}
