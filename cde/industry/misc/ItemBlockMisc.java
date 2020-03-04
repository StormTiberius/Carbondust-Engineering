/**
 *
 * @author StormTiberius
 */

package cde.industry.misc;

import cde.core.Namings;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockMisc extends ItemBlock
{
    public ItemBlockMisc(int id)
    {
        super(id);
    }
    
    @Override
    public int getMaxDamage()
    {
        return 0;
    }
    
    @Override
    public boolean getHasSubtypes()
    {
        return true;
    }
    
    @Override
    public int getMetadata(int md)
    {
        return md;
    }
    
    @Override
    public String getItemNameIS(ItemStack is)
    {
        if(is.getItemDamage() > Namings.INTERNAL_INDUSTRY_MISC_BLOCK_NAMES.length - 1)
        {
            return "NONAME";
        }
        
        return Namings.INTERNAL_INDUSTRY_MISC_BLOCK_NAMES[is.getItemDamage()];
    }
    
    @Override
    public void getSubItems(int id, CreativeTabs tab, List list)
    {
        for (int i = 0; i < Namings.INTERNAL_INDUSTRY_MISC_BLOCK_NAMES.length; i++)
        {
            list.add(new ItemStack(id, 1, i));
        }
    }
}
