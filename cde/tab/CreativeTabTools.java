/**
 *
 * @author StormTiberius
 */

package cde.tab;

import cde.api.Items;
import cde.core.Utils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabTools extends CreativeTabs
{
    public CreativeTabTools(String s)
    {
        super(s);
    }
    
    @Override
    public ItemStack getIconItemStack()
    {
        return Utils.getNewItemStack(Items.equipmentUtilityHeadGoggles);
    }
    
    @Override
    public String getTranslatedTabLabel()
    {
        return "Tools";
    }
}
