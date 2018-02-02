/**
 *
 * @author StormTiberius
 */

package cde.core;

import cde.CDECore;
import cpw.mods.fml.common.IFuelHandler;
import net.minecraft.item.ItemStack;

public class FuelManager implements IFuelHandler
{
    @Override
    public int getBurnTime(ItemStack fuel)
    {
        if(fuel.itemID == CDECore.partsItem.itemID)
        {
            switch(fuel.getItemDamage())
            {
                case 8: return 6500;
                case 9: return 7000;
                case 52: return 6400;
            }
        }
        
        return 0;
    }
}
