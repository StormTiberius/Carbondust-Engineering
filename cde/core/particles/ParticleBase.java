/**
 *
 * @author StormTiberius
 */

package cde.core.particles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class ParticleBase extends EntityFX
{
    public double gravity = 0.004D;
    
    public ParticleBase(World world, double x, double y, double z)
    {
        this(world, x, y, z, 0.0D, 0.0D, 0.0D, 3.0F);
    }
    
    public ParticleBase(World world, double x, double y, double z, double vx, double vy, double vz, float scale)
    {
        super(world, x, y, z, 0.0D, 0.0D, 0.0D);
        motionX *= 0.1D;
        motionY *= 0.1D;
        motionZ *= 0.1D;
        motionX += vx;
        motionY += vy;
        motionZ += vz;
        particleScale *= 0.75F;
        particleScale *= scale;
        particleMaxAge = (int)(24.0D / (Math.random()* 0.5D + 0.2D));
        particleMaxAge = (int)(particleMaxAge * scale);
        noClip = true;
    }
    
    @Override
    public void onUpdate()
    {
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
        
        if(particleAge++ >= particleMaxAge)
        {
            setDead();
        }
        
        setParticleTextureIndex(7 - particleAge * 8 / particleMaxAge);
        motionY += gravity;
        moveEntity(motionX, motionY, motionZ);
        
        if(posY == prevPosY)
        {
            motionX *= 1.1D;
            motionZ *= 1.1D;
        }
        
        motionX *= 0.96D;
        motionY *= 0.96D;
        motionZ *= 0.96D;
        
        if(onGround)
        {
            motionX *= 0.67D;
            motionZ *= 0.67D;
        }
    }
}
