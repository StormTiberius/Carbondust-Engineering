/**
 *
 * @author StormTiberius
 */

package cde.core.particles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class ParticleSmoke extends ParticleDiminishing
{
    public ParticleSmoke(World world, double x, double y, double z)
    {
        this(world, x, y, z, 0.0D, 0.0D, 0.0D, 3.0F, false);
    }
    
    public ParticleSmoke(World world, double x, double y, double z, double vx, double vy, double vz, float scale, boolean flag)
    {
        super(world, x, y, z, vx, vy, vz, scale);
        particleRed = particleGreen = particleBlue = (float)(Math.random() * 0.3D);
        particleMaxAge = (int)(24.0D / (Math.random() * 0.5D + 0.2D));
        particleMaxAge = (int)(particleMaxAge * scale);
        noClip = flag;
    }
}
