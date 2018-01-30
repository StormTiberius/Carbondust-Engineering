package cde.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabCDE extends CreativeTabs
{
    public CreativeTabCDE(String s)
    {
        super(s);
    }

    @Override
    public ItemStack getIconItemStack()
    {
        return new ItemStack(Item.coal.itemID, 1, 0);
    }
	
    @Override
    public String getTranslatedTabLabel()
    {
        return "Carbondust Engineering";
    }
}
