/**
 *
 * @author StormTiberius
 */

package cde.locomotive;

import net.minecraft.world.World;

public class EntityLocomotiveElectric extends EntityLocomotive
{
    private static final String[] TEXTURES = {"/cde/locomotives/electric.nocolor.png",
                                              "/cde/locomotives/electric.primary.png",
                                              "/cde/locomotives/electric.secondary.png"};
    
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
