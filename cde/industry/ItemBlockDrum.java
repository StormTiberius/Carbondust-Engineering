/**
 *
 * @author StormTiberius
 */

package cde.industry;

import java.util.List;
import java.util.Map;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

public class ItemBlockDrum extends ItemBlock
{
    public ItemBlockDrum(int id)
    {
        super(id);
    }
    
    public LiquidStack getLiquid(ItemStack container)
    {
        return LiquidContainerRegistry.getLiquidForFilledItem(container);
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
        
            return "NONAME";
        
        
        
    }

    @Override
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
         
        
            par3List.add(new ItemStack(par1, 1, 0));
        
    }
}
