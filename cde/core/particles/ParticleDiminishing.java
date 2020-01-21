/**
 *
 * @author StormTiberius
 */

package cde.core.particles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class ParticleDiminishing extends ParticleBase
{
    private final float ORIGINAL_SCALE;
    
    public ParticleDiminishing(World world, double x, double y, double z, double vx, double vy, double vz, float scale)
    {
        super(world, x, y, z, vx, vy, vz, scale);
        
        ORIGINAL_SCALE = particleScale;
    }
    
    @Override
    public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        float age = ((float)particleAge + par2) / (float)particleMaxAge * 32.0F;
        
        age = MathHelper.clamp_float(age, 0.0F, 1.0F);
        
        particleScale = ORIGINAL_SCALE * age;
        
        super.renderParticle(par1Tessellator, par2, par3, par4, par5, par6, par7);
    }
}
