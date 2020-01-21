/**
 *
 * @author StormTiberius
 */

package cde.core.particles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class ParticleSteam extends ParticleDiminishing
{   
    public ParticleSteam(World world, double x, double y, double z, double vx, double vy, double vz)
    {
        this(world, x, y, z, vx, vy, vz, 1.0F);
    }
    
    public ParticleSteam(World world, double x, double y, double z, double vx, double vy, double vz, float scale)
    {
        super(world, x, y, z, vx, vy, vz, scale);
        
        particleRed = particleGreen = particleBlue = (float)(Math.random() * 0.4D) + 0.4F;
        particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
        particleMaxAge = (int)((float)particleMaxAge * scale);
    }
}
