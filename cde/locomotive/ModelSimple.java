/**
 *
 * @author StormTiberius
 */

package cde.locomotive;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSimple extends ModelBase
{
    protected final ModelRenderer renderer;

    public ModelSimple(String name)
    {
        this.renderer = new ModelRenderer(this, name);
    }

    public void render(float time)
    {
        renderer.render(time);
    }

    @Override
    public void render(Entity entity, float x, float y, float z, float yaw, float pitch, float time)
    {
        renderer.render(time);
    }

    public void setRotation(float x, float y, float z)
    {
        renderer.rotateAngleX = x;
        renderer.rotateAngleY = y;
        renderer.rotateAngleZ = z;
    }

    public void rotateY(float degrees)
    {
        renderer.rotateAngleY += degrees;
    }

    public void resetRotation()
    {
        renderer.rotateAngleX = 0;
        renderer.rotateAngleY = 0;
        renderer.rotateAngleZ = 0;
    }
}
