package cde.core;

import cde.CDECore;
import net.minecraft.creativetab.CreativeTabs;
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
        return new ItemStack(CDECore.partsItem.itemID, 1, 10);
    }
	
    @Override
    public String getTranslatedTabLabel()
    {
        return "Carbondust Engineering";
    }
}
