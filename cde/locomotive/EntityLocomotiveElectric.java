/**
 *
 * @author StormTiberius
 */

package cde.locomotive;

import cde.LocomotiveCore.LocomotiveType;
import net.minecraft.world.World;

public class EntityLocomotiveElectric extends EntityLocomotive
{
    private static final String NOCOLOR = "locomotive.model.electric.default.nocolor.png";
    private static final String PRIMARY = "locomotive.model.electric.default.primary.png";
    private static final String SECONDARY = "locomotive.model.electric.default.secondary.png";
    
    public EntityLocomotiveElectric(World world)
    {
        super(world);
    }
    
    public EntityLocomotiveElectric(World world, double x, double y, double z)
    {
        super(world,x,y,z);
    }
        
    @Override
    public LocomotiveType getLocomotiveType()
    {
        return LocomotiveType.ELECTRIC;
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
