/**
 *
 * @author StormTiberius
 */

package cde.locomotive;

import net.minecraft.world.World;

public class EntityLocomotiveSteam extends EntityLocomotive
{
    private static final String[] TEXTURES = {"/cde/locomotives/solid.nocolor.png",
                                              "/cde/locomotives/solid.primary.png",
                                              "/cde/locomotives/solid.secondary.png"};
    
    public EntityLocomotiveSteam(World world)
    {
        super(world);
    }
    
    @Override
    public String[] getLocomotiveTextures()
    {
        return TEXTURES;
    }
}
