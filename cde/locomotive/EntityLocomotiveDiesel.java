/**
 *
 * @author StormTiberius
 */

package cde.locomotive;

import cde.LocomotiveCore.LocomotiveType;
import net.minecraft.world.World;

public class EntityLocomotiveDiesel extends EntityLocomotive
{
    private static final String NOCOLOR = "locomotive.model.steam.solid.default.nocolor.png";
    private static final String PRIMARY = "locomotive.model.steam.solid.default.primary.png";
    private static final String SECONDARY = "locomotive.model.steam.solid.default.secondary.png";
    
    public EntityLocomotiveDiesel(World world)
    {
        super(world);
    }
    
    public EntityLocomotiveDiesel(World world, double x, double y, double z)
    {
        super(world,x,y,z);
    }
    
    @Override
    public LocomotiveType getLocomotiveType()
    {
        return LocomotiveType.DIESEL;
    }
    
    @Override
    public String[] getLocomotiveTextures()
    {
        return new String[]{PREFIX + NOCOLOR, PREFIX + PRIMARY, PREFIX + SECONDARY};
    }
    
    @Override
    public int getPrimaryColor()
    {
        return 0xffffff;
    }
        
    @Override
    public int getSecondaryColor()
    {
        return 0xffffff;
    }
}
