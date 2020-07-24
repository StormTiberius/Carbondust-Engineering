/**
 *
 * @author StormTiberius
 */

package cde.manager;

import cde.core.Defaults;
import cpw.mods.fml.common.IFuelHandler;
import net.minecraft.item.ItemStack;

public class FuelManager implements IFuelHandler
{
    @Override
    public int getBurnTime(ItemStack fuel)
    {
        if(fuel.itemID == ItemManager.materialItem.itemID)
        {
            switch(fuel.getItemDamage())
            {
                case 9: return Defaults.PEAT_FUEL_VALUE;
                case 10: return Defaults.BITUMINOUS_PEAT_FUEL_VALUE;
                case 36: return Defaults.COKE_FUEL_VALUE;
            }
        }
        
        return 0;
    }
}
