/**
 *
 * @author StormTiberius
 */

package cde.energy;

import ic2.api.ElectricItem;
import ic2.api.IElectricItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemGoggles extends Item implements IElectricItem
{
    private int counter = 240;
    
    public ItemGoggles(int par1)
    {
        super(par1);
        this.setMaxDamage(13);
        this.setMaxStackSize(1);
        this.setNoRepair();
    }
    
    @Override
    public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack)
    {
        if(!world.isRemote)
        {
            if(counter > 240)
            {
                counter--;
            }
            
            ElectricItem.chargeFromArmor(itemStack, player);

            if(ElectricItem.discharge(itemStack, 1, 1, true, false) >= 1)
            {
                if(counter == 240)
                {
                    counter = 259;
                    player.addPotionEffect(new PotionEffect(Potion.nightVision.id, counter, 0, true));     
                }
            }
        }
    }
    
    @Override
    public String getItemName()
    {
        return "item.goggles";
    }
    
    @Override
    public String getItemNameIS(ItemStack par1ItemStack)
    {
        return getItemName();
    }
    
    @Override
    public boolean isValidArmor(ItemStack stack, int armorType)
    {
        return armorType == 0;
    }
    
    @Override
    public String getTextureFile()
    {
        return "/ic2/sprites/item_0.png";
    }
    
    @Override
    public int getIconFromDamage(int par1)
    {
        return 232;
    }    
    
    // IElectricItem methods
    @Override
    public boolean canProvideEnergy()
    {
        return false;
    }

    @Override
    public int getChargedItemId()
    {
        return itemID;
    }

    @Override
    public int getEmptyItemId()
    {
        return itemID;
    }

    @Override
    public int getMaxCharge()
    {
        return 20000;
    }

    @Override
    public int getTier()
    {
        return 1;
    }

    @Override
    public int getTransferLimit()
    {
        return 200;
    }
}
