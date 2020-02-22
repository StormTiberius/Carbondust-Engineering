/**
 *
 * @author StormTiberius
 */

package cde.locomotive;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import railcraft.common.api.carts.CartBase;
import railcraft.common.api.carts.ILinkableCart;

public abstract class EntityLocomotive extends CartBase implements ILinkableCart
{
    public EntityLocomotive(World world)
    {
        super(world);
    }
    
    @Override
    public boolean isLinkable()
    {
        return true;
    }
    
    @Override
    public boolean canLinkWithCart(EntityMinecart cart)
    {
        return true; // TODO
    }
    
    @Override
    public boolean hasTwoLinks()
    {
        return true;
    }
    
    @Override
    public float getLinkageDistance(EntityMinecart cart)
    {
        return 1.25F;
    }
    
    @Override
    public float getOptimalDistance(EntityMinecart cart)
    {
        return 0.9F;
    }
    
    @Override
    public boolean canBeAdjusted(EntityMinecart cart)
    {
        return true;
    }
    
    @Override
    public void onLinkCreated(EntityMinecart cart)
    {
        
    }
    
    @Override
    public void onLinkBroken(EntityMinecart cart)
    {
        
    }
}
