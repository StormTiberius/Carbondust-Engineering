/**
 *
 * @author StormTiberius
 */

package cde.locomotive;

import cde.LocomotiveCore.LocomotiveType;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class RenderLocomotive extends Render
{
    protected ModelBase modelMinecart;
    protected String[] textures;
    
    private final ModelBase STEAM,DIESEL,ELECTRIC;
 
    public RenderLocomotive()
    {
        shadowSize = 0.5F;
        STEAM = new ModelLocomotiveSteam();
        DIESEL = new ModelLocomotiveDiesel();
        ELECTRIC = new ModelLocomotiveElectric();
    }
    
    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        renderTheMinecart((EntityMinecart)par1Entity, par2, par4, par6, par8, par9);
    }
    
    private void renderTheMinecart(Entity entity, double x, double y, double z, float yaw, float time)
    {
        if(entity instanceof EntityLocomotive)
        {
            LocomotiveType lt = ((EntityLocomotive)entity).getLocomotiveType();
            
            switch(lt)
            {
                case STEAM: renderSteamLocomotive((EntityLocomotiveSteam)entity, x, y, z, yaw, time); break;
                case DIESEL: renderDieselLocomotive((EntityLocomotiveDiesel)entity, x, y, z, yaw, time); break;
                case ELECTRIC: renderElectricLocomotive((EntityLocomotiveElectric)entity, x, y, z, yaw, time); break;
            }
        }
    }
    
    private void renderSteamLocomotive(EntityLocomotiveSteam loco, double x, double y, double z, float var8, float var9)
    {
        modelMinecart = STEAM;
        textures = loco.getLocomotiveTextures();
        renderLocomotive(loco, x, y, z, var8, var9);
    }
    
    private void renderDieselLocomotive(EntityLocomotiveDiesel loco, double x, double y, double z, float var8, float var9)
    {
        modelMinecart = DIESEL;
        textures = loco.getLocomotiveTextures();
        renderLocomotive(loco, x, y, z, var8, var9);
    }
    
    private void renderElectricLocomotive(EntityLocomotiveElectric loco, double x, double y, double z, float var8, float var9)
    {
        modelMinecart = ELECTRIC;
        textures = loco.getLocomotiveTextures();
        renderLocomotive(loco, x, y, z, var8, var9);
    }
    
    public void renderLocomotive(EntityMinecart par1EntityMinecart, double par2, double par4, double par6, float par8, float par9)
    {
        GL11.glPushMatrix();
        long var10 = (long)par1EntityMinecart.entityId * 493286711L;
        var10 = var10 * var10 * 4392167121L + var10 * 98761L;
        float var12 = (((float)(var10 >> 16 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        float var13 = (((float)(var10 >> 20 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        float var14 = (((float)(var10 >> 24 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        GL11.glTranslatef(var12, var13, var14);
        double var15 = par1EntityMinecart.lastTickPosX + (par1EntityMinecart.posX - par1EntityMinecart.lastTickPosX) * (double)par9;
        double var17 = par1EntityMinecart.lastTickPosY + (par1EntityMinecart.posY - par1EntityMinecart.lastTickPosY) * (double)par9;
        double var19 = par1EntityMinecart.lastTickPosZ + (par1EntityMinecart.posZ - par1EntityMinecart.lastTickPosZ) * (double)par9;
        double var21 = 0.30000001192092896D;
        Vec3 var23 = par1EntityMinecart.func_70489_a(var15, var17, var19);
        float var24 = par1EntityMinecart.prevRotationPitch + (par1EntityMinecart.rotationPitch - par1EntityMinecart.prevRotationPitch) * par9;

        if (var23 != null)
        {
            Vec3 var25 = par1EntityMinecart.func_70495_a(var15, var17, var19, var21);
            Vec3 var26 = par1EntityMinecart.func_70495_a(var15, var17, var19, -var21);

            if (var25 == null)
            {
                var25 = var23;
            }

            if (var26 == null)
            {
                var26 = var23;
            }

            par2 += var23.xCoord - var15;
            par4 += (var25.yCoord + var26.yCoord) / 2.0D - var17;
            par6 += var23.zCoord - var19;
            Vec3 var27 = var26.addVector(-var25.xCoord, -var25.yCoord, -var25.zCoord);

            if (var27.lengthVector() != 0.0D)
            {
                var27 = var27.normalize();
                par8 = (float)(Math.atan2(var27.zCoord, var27.xCoord) * 180.0D / Math.PI);
                var24 = (float)(Math.atan(var27.yCoord) * 73.0D);
            }
        }

        GL11.glTranslatef((float)par2, (float)par4, (float)par6);
        GL11.glRotatef(180.0F - par8, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-var24, 0.0F, 0.0F, 1.0F);
        float var28 = (float)par1EntityMinecart.getRollingAmplitude() - par9;
        float var30 = (float)par1EntityMinecart.getDamage() - par9;

        if (var30 < 0.0F)
        {
            var30 = 0.0F;
        }

        if (var28 > 0.0F)
        {
            GL11.glRotatef(MathHelper.sin(var28) * var28 * var30 / 10.0F * (float)par1EntityMinecart.getRollingDirection(), 1.0F, 0.0F, 0.0F);
        }

        //
        
        float light = par1EntityMinecart.getBrightness(0);
        light += (1.0F - light) * 0.4F;
        float f5 = 0.75F;
        GL11.glScalef(f5, f5, f5);
        GL11.glPushAttrib(64);
        
        // *********
        
        GL11.glScalef(1.0F / f5, 1.0F / f5, 1.0F / f5);
        GL11.glPopAttrib();
        
        // LOCO MODEL
        
        GL11.glScalef(-1.0F, -1.0F, 1.0F);
        
        for(int i = 0; i < 3; i++)
        {
            this.loadTexture(EntityLocomotive.PREFIX + textures[i]);

            int c = 0xffffff;

            float dim = 1.0F;
            float c1 = (float) (c >> 16 & 255) / 255.0F;
            float c2 = (float) (c >> 8 & 255) / 255.0F;
            float c3 = (float) (c & 255) / 255.0F;
            GL11.glColor4f(c1 * dim, c2 * dim, c3 * dim, 1);
            modelMinecart.render(par1EntityMinecart, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        }
        
        //
        
        GL11.glPopMatrix();
    }
}
