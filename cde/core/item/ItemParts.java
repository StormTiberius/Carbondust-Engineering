/**
 *
 * @author StormTiberius
 */

package cde.core.item;

import cde.CDECore;
import cde.core.Namings;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemParts extends Item
{
    public ItemParts(int id)
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
    public int getMetadata(int meta)
    {
        return meta;
    }

    @Override
    public String getItemNameIS(ItemStack is)
    {
        if(is.getItemDamage() > Namings.INTERNAL_PART_ITEM_NAMES.length - 1)
        {
            return "NONAME";
        }
        
        return Namings.INTERNAL_PART_ITEM_NAMES[is.getItemDamage()];
    }

    @Override
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int i = 0; i < Namings.INTERNAL_PART_ITEM_NAMES.length; i++)
        {   
            par3List.add(new ItemStack(par1, 1, i));
        }
    }
    
    @Override
    public int getIconFromDamage(int i)
    {
        int offset;
        
        if(i < 10)
        {
            offset = 0;
        }
        else if(i < 25)
        {
            offset = 6;
        }
        else if(i < 32)
        {
            offset = 7;
        }
        else if(i < 39)
        {
            offset = 16;
        }
        else if(i < 44)
        {
            offset = 25;
        }
        else if(i < 52)
        {
            offset = 36;
        }
        else
        {
            offset = 44;
        }

        return i + offset;
    }
    
    @Override
    public String getTextureFile()
    {
        return CDECore.CDE_ITEMS;
    }
}
