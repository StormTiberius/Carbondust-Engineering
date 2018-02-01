/**
 *
 * @author StormTiberius
 */

package cde.core;

import cde.CDECore;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FuelManager implements IFuelHandler
{
    @Override
    public int getBurnTime(ItemStack fuel)
    {
        if(fuel.itemID == CDECore.partsItem.itemID && fuel.getItemDamage() == 52)
        {
            return GameRegistry.getFuelValue(new ItemStack(Item.coal.itemID, 1, 0)) * 2;
        }
        
        return 0;
    }
}
