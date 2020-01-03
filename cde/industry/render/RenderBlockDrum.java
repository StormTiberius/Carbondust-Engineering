/**
 *
 * @author StormTiberius
 */

package cde.industry.render;

import cde.IndustryCore;
import cde.core.Defaults;
import cde.core.util.Utils;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;

public class RenderBlockDrum implements ISimpleBlockRenderingHandler
{
    private static final double BASE_W = 0.425D;
    
    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
    {
        // --
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int xCoord, int yCoord, int zCoord, Block block, int modelId, RenderBlocks renderer)
    {
        Tessellator t = Tessellator.instance;
        
        t.setBrightness(block.getMixedBrightnessForBlock(world, xCoord, yCoord, zCoord));
        t.setColorRGBA(255, 255, 255, 255);
        
        Color color = new Color(block.colorMultiplier(world, xCoord, yCoord, zCoord));
        
        switch(world.getBlockMetadata(xCoord, yCoord, zCoord))
        {
            case 0:
                    renderSides(t, xCoord, yCoord, zCoord, Utils.getUV(Defaults.TEXTURE_DRUM_IRON_SIDE), color);
                    renderTop(t, xCoord, yCoord, zCoord, Utils.getUV(Defaults.TEXTURE_DRUM_IRON_TOP));
                    renderBottom(t, xCoord, yCoord, zCoord, Utils.getUV(Defaults.TEXTURE_DRUM_IRON_TOP));
                    break;
            case 1:
                    renderSides(t, xCoord, yCoord, zCoord, Utils.getUV(Defaults.TEXTURE_DRUM_STEEL_SIDE), color);
                    renderTop(t, xCoord, yCoord, zCoord, Utils.getUV(Defaults.TEXTURE_DRUM_STEEL_TOP));
                    renderBottom(t, xCoord, yCoord, zCoord, Utils.getUV(Defaults.TEXTURE_DRUM_STEEL_TOP));
                    break;
        }
 
        
        return false;
    }
    
    @Override
    public boolean shouldRender3DInInventory()
    {
        return false;
    }

    @Override
    public int getRenderId()
    {
        return IndustryCore.getDrumRenderId();
    }
    
    private void renderSides(Tessellator t, int xCoord, int yCoord, int zCoord, double[] uv, Color color)
    {
        double x,y,z,u,v;
        
        for(int i = 0; i < 8; i++)
        {   
            setColor();
            x = 0;
            y = 0;
            z = 0;
            u = 0;
            v = 0;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = 0;
            y = 0;
            z = 0;
            u = 0;
            v = 0;
            t.addVertexWithUV(x, y, z, u, v);
            
            setColor();
            
            x = 0;
            y = 0;
            z = 0;
            u = 0;
            v = 0;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = 0;
            y = 0;
            z = 0;
            u = 0;
            v = 0;
            t.addVertexWithUV(x, y, z, u, v);
            
            setColor();
            
            x = 0;
            y = 0;
            z = 0;
            u = 0;
            v = 0;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = 0;
            y = 0;
            z = 0;
            u = 0;
            v = 0;
            t.addVertexWithUV(x, y, z, u, v);
            
            setColor();
            
            x = 0;
            y = 0;
            z = 0;
            u = 0;
            v = 0;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = 0;
            y = 0;
            z = 0;
            u = 0;
            v = 0;
            t.addVertexWithUV(x, y, z, u, v);
            
            setColor();
            
            x = 0;
            y = 0;
            z = 0;
            u = 0;
            v = 0;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = 0;
            y = 0;
            z = 0;
            u = 0;
            v = 0;
            t.addVertexWithUV(x, y, z, u, v);
            
            setColor();
            
            x = 0;
            y = 0;
            z = 0;
            u = 0;
            v = 0;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = 0;
            y = 0;
            z = 0;
            u = 0;
            v = 0;
            t.addVertexWithUV(x, y, z, u, v);
            
            t.setColorOpaque_F(0.65F, 0.65F, 0.65F);
            
            setColor();
            
            x = 0;
            y = 0;
            z = 0;
            u = 0;
            v = 0;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = 0;
            y = 0;
            z = 0;
            u = 0;
            v = 0;
            t.addVertexWithUV(x, y, z, u, v);
            
            setColor();
            
            x = 0;
            y = 0;
            z = 0;
            u = 0;
            v = 0;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = 0;
            y = 0;
            z = 0;
            u = 0;
            v = 0;
            t.addVertexWithUV(x, y, z, u, v);
            
            setColor();
            
            x = 0;
            y = 0;
            z = 0;
            u = 0;
            v = 0;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = 0;
            y = 0;
            z = 0;
            u = 0;
            v = 0;
            t.addVertexWithUV(x, y, z, u, v);
            
            setColor();
            
            x = 0;
            y = 0;
            z = 0;
            u = 0;
            v = 0;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = 0;
            y = 0;
            z = 0;
            u = 0;
            v = 0;
            t.addVertexWithUV(x, y, z, u, v);
            
            setColor();

            x = 0;
            y = 0;
            z = 0;
            u = 0;
            v = 0;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = 0;
            y = 0;
            z = 0;
            u = 0;
            v = 0;
            t.addVertexWithUV(x, y, z, u, v);
            
            setColor();
            
            x = 0;
            y = 0;
            z = 0;
            u = 0;
            v = 0;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = 0;
            y = 0;
            z = 0;
            u = 0;
            v = 0;
            t.addVertexWithUV(x, y, z, u, v);
            
            setColor();
            
            x = 0;
            y = 0;
            z = 0;
            u = 0;
            v = 0;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = 0;
            y = 0;
            z = 0;
            u = 0;
            v = 0;
            t.addVertexWithUV(x, y, z, u, v);
            
            setColor();
            
            x = 0;
            y = 0;
            z = 0;
            u = 0;
            v = 0;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = 0;
            y = 0;
            z = 0;
            u = 0;
            v = 0;
            t.addVertexWithUV(x, y, z, u, v);
        }
    }
        
    private void renderTop(Tessellator t, int xCoord, int yCoord, int zCoord, double[] uv)
    {
        
    }
            
    private void renderBottom(Tessellator t, int xCoord, int yCoord, int zCoord, double[] uv)
    {
        
    }
    
    private void setColor(double i, double p, float red, float green, float blue)
    {
        float alpha = (float)(p * (0.7D + Math.cos((((i + 0.5D) / 4.0D) * 2.0D + 1.0D) * Math.PI) * 0.1D));
        Tessellator.instance.setColorRGBA_F(red, green, blue, alpha);
    }
    
    private static Color getColor(ItemStack is)
    {
        if(is.hasTagCompound() && is.getTagCompound().hasKey("color"))
        {
            return new Color(is.getTagCompound().getInteger("color"));
        }
        
        return IndustryCore.getLiquidColor();
    }
    
    private static boolean isSteel(ItemStack is)
    {
        boolean flag = false;
        
        if(is.hasTagCompound() && is.getTagCompound().hasKey("capacity"))
        {
            flag = is.getTagCompound().getInteger("capacity") == Defaults.DRUM_CAPACITY_STEEL;
        }
        
        return flag;
    }
    
    private static double[] getUV(ItemStack is)
    {
        if(isSteel(is))
        {
            return Utils.getUV(0);
        }
        
        return Utils.getUV(0);
    }
}
