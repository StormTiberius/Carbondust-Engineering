/**
 *
 * @author StormTiberius
 */

package cde.industry;

import cde.IndustryCore;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabDrums extends CreativeTabs
{
    public CreativeTabDrums(String s)
    {
        super(s);
    }

    @Override
    public ItemStack getIconItemStack()
    {
        return IndustryCore.getTabIcon();
    }
	
    @Override
    public String getTranslatedTabLabel()
    {
        return "Industry Drums";
    }
}
