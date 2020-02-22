/**
 *
 * @author StormTiberius
 */

package cde.locomotive;

import net.minecraft.world.World;

public class EntityLocomotiveElectric extends EntityLocomotive
{
    private static final String[] TEXTURES = {"/cde/locomotive/electric.primary.png",
                                              "/cde/locomotive/electric.secondary.png",
                                              "/cde/locomotive/electric.nocolor.png"};
    
    public EntityLocomotiveElectric(World world)
    {
        super(world);
    }
    
    @Override
    public String[] getLocomotiveTextures()
    {
        return TEXTURES;
    }
}
