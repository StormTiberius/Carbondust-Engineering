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
        this.maxStackSize = 1;
    }
    
    @Override
    public void addInformation(ItemStack is, EntityPlayer player, List info, boolean flag)
    {
        if(is != null && is.hasTagCompound())
        {
            if(is.stackTagCompound.hasKey("capacity") && is.stackTagCompound.hasKey("liquid"))
            {
                int capacity = is.stackTagCompound.getInteger("capacity");
                
                LiquidStack liquid = LiquidStack.loadLiquidStackFromNBT(is.stackTagCompound.getCompoundTag("liquid"));
                
                if(liquid != null)
                {
                    ItemStack ls = liquid.asItemStack();
                    
                    if(ls != null)
                    {
                        info.add(ls.getDisplayName() + ": " + liquid.amount + " / " + capacity);
                    }
                }
            }
        }
    }
    
    @Override
    public String getItemDisplayName(ItemStack is)
    {
        if(is != null && is.hasTagCompound())
        {
            if(is.stackTagCompound.hasKey("capacity"))
            {
                String type;
                
                switch(is.stackTagCompound.getInteger("capacity"))
                {
                    case BlockDrum.DRUM_CAPACITY_IRON: type = " Iron "; break;
                    case BlockDrum.DRUM_CAPACITY_STEEL: type = " Steel "; break;
                    default: type = "UNKNOWN DRUM TYPE"; break;
                }
                
                if(is.stackTagCompound.hasKey("liquid"))
                {
                    LiquidStack liquid = LiquidStack.loadLiquidStackFromNBT(is.stackTagCompound.getCompoundTag("liquid"));

                    if(liquid != null)
                    {   
                        ItemStack ls = liquid.asItemStack();

                        if(ls != null)
                        {
                            return ls.getDisplayName() + type + "Drum";
                        }
                    }
                }
                
                return "Empty" + type + "Drum";
            }
        }
        
        return "UNKNOWN DRUM TYPE";
    }
}
