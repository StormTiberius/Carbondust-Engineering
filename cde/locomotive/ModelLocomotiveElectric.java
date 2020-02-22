/**
 *
 * @author StormTiberius
 */

package cde.locomotive;

import net.minecraft.client.model.ModelRenderer;

public class ModelLocomotiveElectric extends ModelSimple
{
    public ModelLocomotiveElectric()
    {
        super("loco");
        
        renderer.setTextureSize(128, 64);
        
        setTextureOffset("loco.wheels", 1, 25);
        setTextureOffset("loco.frame", 1, 1);
        setTextureOffset("loco.engine", 67, 37);
        setTextureOffset("loco.sideA", 35, 45);
        setTextureOffset("loco.sideB", 35, 45);
        // setTextureOffset("loco.lightA", 1, 45);
        setTextureOffset("loco.lightB", 1, 55);
        
        renderer.rotationPointX = 8.0F;
        renderer.rotationPointY = 8.0F;
        renderer.rotationPointZ = 8.0F;
        
        ModelRenderer loco = renderer;
        
        loco.addBox("wheels", -20.0F, -5.0F, -16.0F, 23, 2, 16);
        loco.addBox("frame", -21.0F, -10.0F, -17.0F, 25, 5, 18);
        loco.addBox("engine", -15.0F, -19.0F, -16.0F, 13, 9, 16);
        loco.addBox("sideA", -20.0F, -17.0F, -13.0F, 5, 7, 10);
        loco.addBox("sideB", -2.0F, -17.0F, -13.0F, 5, 7, 10);
        // loco.addBox("lightA", -2.0F, -18.0F, -10.0F, 6, 4, 4);
        loco.addBox("lightB", -21.0F, -18.0F, -10.0F, 6, 4, 4);
    }
}
