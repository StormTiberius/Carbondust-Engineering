/**
 *
 * @author StormTiberius
 */

package cde.core.item;

import net.minecraft.item.Item;

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
}
