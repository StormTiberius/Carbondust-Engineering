/**
 *
 * @author StormTiberius
 */

package cde.manager;

import cpw.mods.fml.common.IFuelHandler;
import net.minecraft.item.ItemStack;

public class FuelManager implements IFuelHandler
{
    @Override
    public int getBurnTime(ItemStack fuel)
    {
        return 0;
    }
}
