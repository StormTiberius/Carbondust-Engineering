/**
 *
 * @author StormTiberius
 */

package cde.industry;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidStack;

public class ItemBlockDrum extends ItemBlock
{
    public ItemBlockDrum(int id)
    {
        super(id);
        maxStackSize = 1;
        hasSubtypes = true;
    }
    
    @Override
    public int getMaxDamage()
    {
        return 100;
    }
    
    @Override
    public void addInformation(ItemStack is, EntityPlayer player, List info, boolean flag)
    {
        if(is != null && is.hasTagCompound() && is.stackTagCompound.hasKey("capacity"))
        {
            String liquid = "Empty";
            int amount = 0;
            int capacity = is.stackTagCompound.getInteger("capacity");
            
            if(is.stackTagCompound.hasKey("liquid"))
            {
                LiquidStack ls = LiquidStack.loadLiquidStackFromNBT(is.stackTagCompound.getCompoundTag("liquid"));

                if(ls != null)
                {
                    amount = ls.amount;
                    
                    ItemStack ais = ls.asItemStack();

                    if(ais != null)
                    {
                        liquid = ais.getDisplayName();    
                    }
                }   
            }
            
            info.add(liquid + ": " + amount + " mB / " + capacity + " mB");
        }
    }
    
    @Override
    public String getItemDisplayName(ItemStack is)
    {
        int capacity = 0;
        
        if(is != null && is.hasTagCompound() && is.stackTagCompound.hasKey("capacity"))
        {
            capacity = is.stackTagCompound.getInteger("capacity");
        }
        
        String type;
        
        switch(capacity)
        {
            case BlockDrum.DRUM_CAPACITY_IRON: type = "Iron"; break;
            case BlockDrum.DRUM_CAPACITY_STEEL: type = "Steel"; break;
            default: type = "UNKNOWN TYPE"; break;
        }
        
        return type + " Drum";
    }
}
