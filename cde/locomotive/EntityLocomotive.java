/**
 *
 * @author StormTiberius
 */

package cde.locomotive;

import cde.LocomotiveCore.LocomotiveType;
import net.minecraft.world.World;
import railcraft.common.api.carts.CartBase;

public abstract class EntityLocomotive extends CartBase
{
    protected static final String PREFIX = "/cde/locomotive/";
    
    public EntityLocomotive(World world)
    {
        super(world);
    }
    
    public EntityLocomotive(World world, double x, double y, double z)
    {
        this(world);
        
        posX = x;
        posY = y + (double)yOffset;
        posZ = z;
        
        prevPosX = x;
        prevPosY = y;
        prevPosZ = z;
        
        fuel = 0;
    }
    
    public abstract LocomotiveType getLocomotiveType();
    public abstract String[] getLocomotiveTextures();
    public abstract int getPrimaryColor();
    public abstract int getSecondaryColor();
}
