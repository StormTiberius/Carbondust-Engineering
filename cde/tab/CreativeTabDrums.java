/**
 *
 * @author StormTiberius
 */

package cde.tab;

import cde.api.Blocks;
import cde.core.Utils;
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
        return Utils.getNewItemStack(Blocks.drumIron);
    }
    
    @Override
    public String getTranslatedTabLabel()
    {
        return "Drums";
    }
}
