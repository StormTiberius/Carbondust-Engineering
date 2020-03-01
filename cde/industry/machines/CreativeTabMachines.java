/**
 *
 * @author StormTiberius
 */

package cde.industry.machines;

import cde.api.Blocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabMachines extends CreativeTabs
{
    public CreativeTabMachines(String name)
    {
        super(name);
    }
    
    @Override
    public ItemStack getIconItemStack()
    {
        return Blocks.machineInductionCharger.copy();
    }
    
    @Override
    public String getTranslatedTabLabel()
    {
        return "Industry Machines";
    }
}
