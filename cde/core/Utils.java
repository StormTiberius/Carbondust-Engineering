/**
 *
 * @author StormTiberius
 */

package cde.core;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class Utils
{
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
