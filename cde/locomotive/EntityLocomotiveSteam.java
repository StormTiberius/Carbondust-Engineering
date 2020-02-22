/**
 *
 * @author StormTiberius
 */

package cde.locomotive;

import net.minecraft.world.World;

public class EntityLocomotiveSteam extends EntityLocomotive
{
    private static final String[] TEXTURES = {"/cde/locomotive/solid.primary.png",
                                              "/cde/locomotive/solid.secondary.png",
                                              "/cde/locomotive/solid.nocolor.png"};
    
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
