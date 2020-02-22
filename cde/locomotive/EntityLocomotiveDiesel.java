/**
 *
 * @author StormTiberius
 */

package cde.locomotive;

import net.minecraft.world.World;

public class EntityLocomotiveDiesel extends EntityLocomotive
{
    private static final String[] TEXTURES = {"/cde/locomotives/liquid.nocolor.png",
                                              "/cde/locomotives/liquid.primary.png",
                                              "/cde/locomotives/liquid.secondary.png"};
    
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
