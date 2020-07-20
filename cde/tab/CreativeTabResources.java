/**
 *
 * @author StormTiberius
 */

package cde.tab;

import cde.api.Materials;
import cde.core.Utils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabResources extends CreativeTabs
{
    public CreativeTabResources(String s)
    {
        super(s);
    }
    
    @Override
    public ItemStack getIconItemStack()
    {
        return Utils.getNewItemStack(Materials.dustCoal);
    }
    
    @Override
    public String getTranslatedTabLabel()
    {
        return "Resources";
    }
}
