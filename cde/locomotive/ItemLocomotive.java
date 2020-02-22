/**
 *
 * @author StormTiberius
 */

package cde.locomotive;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.BlockRail;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import railcraft.common.api.carts.CartTools;
import railcraft.common.api.core.items.IMinecartItem;

public class ItemLocomotive extends ItemMinecart implements IMinecartItem
{
    public ItemLocomotive(int id, int type)
    {
        super(id,type);
    }
    
    @Override
    public boolean canBePlacedByNonPlayer(ItemStack cart)
    {
        return true;
    }
    
    @Override
    public EntityMinecart placeCart(String owner, ItemStack is, World world, int x, int y, int z)
    {
        int id = world.getBlockId(x, y, z);
        
        if(BlockRail.isRailBlock(id) && !CartTools.isMinecartAt(world, x, y, z, 0.0F))
        {
            EntityMinecart locomotive;
            
            switch(minecartType)
            {
                case 1: locomotive = new EntityLocomotiveDiesel(world); break;
                case 2: locomotive = new EntityLocomotiveElectric(world); break;
                default: locomotive = new EntityLocomotiveSteam(world); break;
            }
            
            locomotive.setPosition((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
            
            if(world.spawnEntityInWorld(locomotive))
            {
                return locomotive;
            }
        }
        
        return null;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public boolean requiresMultipleRenderPasses()
    {
        return true;
    }
    
    @Override
    public int getRenderPasses(int md)
    {
        return 2;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getColorFromItemStack(ItemStack is, int pass)
    {
        return super.getColorFromItemStack(is, pass);
    }
    
    @Override
    public int getIconIndex(ItemStack is, int pass)
    {
        return super.getIconIndex(is, pass);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack is, EntityPlayer player, List list, boolean flag)
    {
        // TODO Add color and whistle item info.
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public EnumRarity getRarity(ItemStack is)
    {
        return super.getRarity(is);
    }
    
    @Override
    public String getTextureFile()
    {
        return "/cde/locomotive/locomotive.png";
    }
    
    @Override
    public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
    {
        if(!world.isRemote)
        {
            EntityMinecart placedCart = placeCart(player.getEntityName(), is, world, x, y, z);
            
            if(placedCart != null)
            {
                is.stackSize--; // CHECK THIS
                
                return true;
            }
        }
        
        return false;
    }
}
