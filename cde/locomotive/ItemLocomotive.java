/**
 *
 * @author StormTiberius
 */

package cde.locomotive;

import net.minecraft.block.BlockRail;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemLocomotive extends ItemMinecart
{
    public ItemLocomotive(int id, int type)
    {
        super(id,type);
    }
    
    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        int var11 = par3World.getBlockId(par4, par5, par6);

        if (BlockRail.isRailBlock(var11))
        {
            if (!par3World.isRemote)
            {
                switch(minecartType)
                {
                    case 0: par3World.spawnEntityInWorld(new EntityLocomotiveSteam(par3World, (double)((float)par4 + 0.5F), (double)((float)par5 + 0.5F), (double)((float)par6 + 0.5F))); break;
                    case 1: par3World.spawnEntityInWorld(new EntityLocomotiveDiesel(par3World, (double)((float)par4 + 0.5F), (double)((float)par5 + 0.5F), (double)((float)par6 + 0.5F))); break;
                    case 2: par3World.spawnEntityInWorld(new EntityLocomotiveElectric(par3World, (double)((float)par4 + 0.5F), (double)((float)par5 + 0.5F), (double)((float)par6 + 0.5F))); break;
                }
            }

            --par1ItemStack.stackSize;
            return true;
        }
        else
        {
            return false;
        }
    }
}
