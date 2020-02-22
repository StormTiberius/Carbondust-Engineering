/**
 *
 * @author StormTiberius
 */

package cde.locomotive;

import net.minecraft.world.World;

public class EntityLocomotiveDiesel extends EntityLocomotive
{
    private static final String[] TEXTURES = {"/cde/locomotive/liquid.primary.png",
                                              "/cde/locomotive/liquid.secondary.png",
                                              "/cde/locomotive/liquid.nocolor.png"};
    
    public EntityLocomotiveDiesel(World world)
    {
        super(world);
    }
    
    @Override
    public String[] getLocomotiveTextures()
    {
        return TEXTURES;
    }
}
