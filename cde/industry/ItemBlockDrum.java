/**
 *
 * @author StormTiberius
 */

package cde.industry;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.LiquidStack;

public class ItemBlockDrum extends ItemBlock implements ILiquidTank
{
    public ItemBlockDrum(int id)
    {
        super(id);
    }
    
    @Override
    public void addInformation(ItemStack is, EntityPlayer player, List list, boolean flag)
    {
        
    }
    
    @Override
    public String getItemDisplayName(ItemStack is)
    {
        return "";
    }
    
    /**
     * @return LiquidStack representing the liquid contained in the tank, null if empty.
     */
    @Override
    public LiquidStack getLiquid()
    {
        return null;
    }

    /**
     * @return capacity of this tank
     */
    @Override
    public int getCapacity()
    {
        return 0;
    }

    /**
     *
     * @param resource
     * @param doFill
     * @return Amount of liquid used for filling.
     */
    @Override
    public int fill(LiquidStack resource, boolean doFill)
    {
        return 0;
    }
    
    /**
     *
     * @param maxDrain
     * @param doDrain
     * @return Null if nothing was drained, otherwise a LiquidStack containing the drained.
     */
    @Override
    public LiquidStack drain(int maxDrain, boolean doDrain)
    {
        return null;
    }

    /**
     * Positive values indicate a positive liquid pressure (liquid wants to leave this tank)
     * Negative values indicate a negative liquid pressure (liquid wants to fill this tank)
     * Zero indicates no pressure
     *
     * @return a number indicating tank pressure
     */
    @Override
    public int getTankPressure()
    {
        return 0;
    }
}
