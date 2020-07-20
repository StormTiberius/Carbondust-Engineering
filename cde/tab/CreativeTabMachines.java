/**
 *
 * @author StormTiberius
 */

package cde.tab;

import cde.api.Blocks;
import cde.core.Utils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabMachines extends CreativeTabs
{
    public CreativeTabMachines(String s)
    {
        super(s);
    }
    
    @Override
    public ItemStack getIconItemStack()
    {
        return Utils.getNewItemStack(Blocks.machineGenerator);
    }
    
    @Override
    public String getTranslatedTabLabel()
    {
        return "Machines";
    }
}
