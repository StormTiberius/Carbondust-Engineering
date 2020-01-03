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
    private static final int MIN_U = 0;
    private static final int MAX_U = 1;
    private static final int MIN_V = 2;
    private static final int MAX_V = 3;
    
    private static final double BASE_W = 0.425D;
    private static final double TOTAL_H = 0.97D;
    private static final double PART_H = 0.3125D;
    private static final double COLOR_BCV = 0.2D;
    
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
        double x,y,z,u,v,w;
        
        double uLength = uv[MAX_U] - uv[MIN_U];
        double vLength = uv[MAX_V] - uv[MIN_V];
        double vOffset = vLength * PART_H;
        
        for(int i = 0; i < 8; i++)
        {   
            w = BASE_W * TOTAL_H;
            
            setColor((double)(i + 1) - COLOR_BCV, 1.0D, color);
            
            x = (double)xCoord + getX(i + 1, w);
            y = (double)yCoord;
            z = (double)zCoord + getZ(i + 1, w);
            u = uv[MIN_U] + getU(i, true) * uLength;
            v = uv[MAX_V];
            t.addVertexWithUV(x, y, z, u, v);
            
            x = (double)xCoord + getX(i + 1, w);
            y = (double)yCoord + PART_H;
            z = (double)zCoord + getZ(i + 1, w);
            u = uv[MIN_U] + getU(i, true) * uLength;
            v = uv[MAX_V] - vOffset;
            t.addVertexWithUV(x, y, z, u, v);
            
            setColor((double)i + COLOR_BCV, 0.9D, color);
            
            x = (double)xCoord + getX(i, w);
            y = (double)yCoord + PART_H;
            z = (double)zCoord + getZ(i, w);
            u = uv[MIN_U] + getU(i, false) * uLength;
            v = uv[MAX_V] - vOffset;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = (double)xCoord + getX(i, w);
            y = (double)yCoord;
            z = (double)zCoord + getZ(i, w);
            u = uv[MIN_U] + getU(i, false) * uLength;
            v = uv[MAX_V];
            t.addVertexWithUV(x, y, z, u, v);
            
            setColor((double)(i + 1) - COLOR_BCV, 1.0D, color);
            
            x = (double)xCoord + getX(i + 1, w);
            y = (double)yCoord + PART_H * 2.0D;
            z = (double)zCoord + getZ(i + 1, w);
            u = uv[MIN_U] + getU(i, true) * uLength;
            v = uv[MIN_V] + vOffset;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = (double)xCoord + getX(i + 1, w);
            y = (double)(yCoord + 1);
            z = (double)zCoord + getZ(i + 1, w);
            u = uv[MIN_U] + getU(i, true) * uLength;
            v = uv[MIN_V];
            t.addVertexWithUV(x, y, z, u, v);
            
            setColor((double)i + COLOR_BCV, 0.9D, color);
            
            x = (double)xCoord + getX(i, w);
            y = (double)(yCoord + 1);
            z = (double)zCoord + getZ(i, w);
            u = uv[MIN_U] + getU(i, false) * uLength;
            v = uv[MIN_V];
            t.addVertexWithUV(x, y, z, u, v);
            
            x = (double)xCoord + getX(i, w);
            y = (double)yCoord + PART_H * 2.0D;
            z = (double)zCoord + getZ(i, w);
            u = uv[MIN_U] + getU(i, false) * uLength;
            v = uv[MIN_V] + vOffset;
            t.addVertexWithUV(x, y, z, u, v);
            
            setColor((double)(i + 1) - COLOR_BCV, 1.0D);
            
            x = (double)xCoord + getX(i + 1, w);
            y = (double)yCoord + PART_H;
            z = (double)zCoord + getZ(i + 1, w);
            u = uv[MIN_U] + getU(i, true) * uLength;
            v = uv[MAX_V] - vOffset;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = (double)xCoord + getX(i + 1, w);
            y = (double)yCoord + PART_H * 2.0D;
            z = (double)zCoord + getZ(i + 1, w);
            u = uv[MIN_U] + getU(i, true) * uLength;
            v = uv[MIN_V] + vOffset;
            t.addVertexWithUV(x, y, z, u, v);
            
            setColor((double)i + COLOR_BCV, 1.0D);
            
            x = (double)xCoord + getX(i, w);
            y = (double)yCoord + PART_H * 2.0D;
            z = (double)zCoord + getZ(i, w);
            u = uv[MIN_U] + getU(i, false) * uLength;
            v = uv[MIN_V] + vOffset;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = (double)xCoord + getX(i, w);
            y = (double)yCoord + PART_H;
            z = (double)zCoord + getZ(i, w);
            u = uv[MIN_U] + getU(i, false) * uLength;
            v = uv[MAX_V] - vOffset;
            t.addVertexWithUV(x, y, z, u, v);
            
            t.setColorOpaque_F(0.65F, 0.65F, 0.65F);
            
            w = BASE_W;
            
            setColor((double)(i + 1) - COLOR_BCV, 0.6D);
            
            x = (double)xCoord + getX(i + 1, w);
            y = (double)yCoord;
            z = (double)zCoord + getZ(i + 1, w);
            u = uv[MIN_U] + getU(i, true) * uLength;
            v = uv[MAX_V];
            t.addVertexWithUV(x, y, z, u, v);
            
            x = (double)xCoord + getX(i + 1, w);
            y = (double)yCoord + 0.05D;
            z = (double)zCoord + getZ(i + 1, w);
            u = uv[MIN_U] + getU(i, true) * uLength;
            v = uv[MAX_V] - uLength * 0.05D;
            t.addVertexWithUV(x, y, z, u, v);
            
            setColor((double)i + COLOR_BCV, 0.6D);
            
            x = (double)xCoord + getX(i, w);
            y = (double)yCoord + 0.05D;
            z = (double)zCoord + getZ(i, w);
            u = uv[MIN_U] + getU(i, false) * uLength;
            v = uv[MAX_V] - uLength * 0.05D;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = (double)xCoord + getX(i, w);
            y = (double)yCoord;
            z = (double)zCoord + getZ(i, w);
            u = uv[MIN_U] + getU(i, false) * uLength;
            v = uv[MAX_V];
            t.addVertexWithUV(x, y, z, u, v);
            
            setColor((double)(i + 1) - COLOR_BCV, 0.6D);
            
            x = (double)xCoord + getX(i + 1, w);
            y = (double)yCoord + 0.95D;
            z = (double)zCoord + getZ(i + 1, w);
            u = uv[MIN_U] + getU(i, true) * uLength;
            v = uv[MIN_V] + uLength * 0.05D;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = (double)xCoord + getX(i + 1, w);
            y = (double)yCoord + 1.0D;
            z = (double)zCoord + getZ(i + 1, w);
            u = uv[MIN_U] + getU(i, true) * uLength;
            v = uv[MIN_V];
            t.addVertexWithUV(x, y, z, u, v);
            
            setColor((double)i + COLOR_BCV, 0.6D);
            
            x = (double)xCoord + getX(i, w);
            y = (double)yCoord + 1.0D;
            z = (double)zCoord + getZ(i, w);
            u = uv[MIN_U] + getU(i, false) * uLength;
            v = uv[MIN_V];
            t.addVertexWithUV(x, y, z, u, v);
            
            x = (double)xCoord + getX(i, w);
            y = (double)yCoord + 0.95D;
            z = (double)zCoord + getZ(i, w);
            u = uv[MIN_U] + getU(i, false) * uLength;
            v = uv[MIN_V] + uLength * 0.05D;
            t.addVertexWithUV(x, y, z, u, v);
            
            w = BASE_W * TOTAL_H * 0.9D;
            
            setColor((double)i + COLOR_BCV, 0.6D);

            x = (double)(xCoord + 1) - getX(i, w);
            y = (double)yCoord + 0.95D;
            z = (double)(zCoord + 1) - getZ(i, w);
            u = uv[MIN_U] + getU(i, false) * uLength;
            v = uv[MAX_V];
            t.addVertexWithUV(x, y, z, u, v);
            
            x = (double)(xCoord + 1) - getX(i, w);
            y = (double)yCoord + 1.0D;
            z = (double)(zCoord + 1) - getZ(i, w);
            u = uv[MIN_U] + getU(i, false) * uLength;
            v = uv[MAX_V] - uLength * 0.05D;
            t.addVertexWithUV(x, y, z, u, v);
            
            setColor((double)(i + 1) - COLOR_BCV, 0.6D);
            
            x = (double)(xCoord + 1) - getX(i + 1, w);
            y = (double)yCoord + 1.0D;
            z = (double)(zCoord + 1) - getZ(i + 1, w);
            u = uv[MIN_U] + getU(i, true) * uLength;
            v = uv[MAX_V] - uLength * 0.05D;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = (double)(xCoord + 1) - getX(i + 1, w);
            y = (double)yCoord + 0.95D;
            z = (double)(zCoord + 1) - getZ(i + 1, w);
            u = uv[MIN_U] + getU(i, true) * uLength;
            v = uv[MAX_V];
            t.addVertexWithUV(x, y, z, u, v);
            
            setColor((double)i + COLOR_BCV, 0.6D);
            
            x = (double)(xCoord + 1) - getX(i, w);
            y = (double)yCoord;
            z = (double)(zCoord + 1) - getZ(i, w);
            u = uv[MIN_U] + getU(i, false) * uLength;
            v = uv[MIN_V];
            t.addVertexWithUV(x, y, z, u, v);
            
            x = (double)(xCoord + 1) - getX(i, w);
            y = (double)yCoord + 0.05D;
            z = (double)(zCoord + 1) - getZ(i, w);
            u = uv[MIN_U] + getU(i, false) * uLength;
            v = uv[MIN_V] + uLength * 0.05D;
            t.addVertexWithUV(x, y, z, u, v);
            
            setColor((double)(i + 1) - COLOR_BCV, 0.6D);
            
            x = (double)(xCoord + 1) - getX(i + 1, w);
            y = (double)yCoord + 0.05D;
            z = (double)(zCoord + 1) - getZ(i + 1, w);
            u = uv[MIN_U] + getU(i, true) * uLength;
            v = uv[MIN_V] + uLength * 0.05D;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = (double)(xCoord + 1) - getX(i + 1, w);
            y = (double)yCoord;
            z = (double)(zCoord + 1) - getZ(i + 1, w);
            u = uv[MIN_U] + getU(i, true) * uLength;
            v = uv[MIN_V];
            t.addVertexWithUV(x, y, z, u, v);
        }
    }
        
    private void renderTop(Tessellator t, int xCoord, int yCoord, int zCoord, double[] uv)
    {
        
    }
            
    private void renderBottom(Tessellator t, int xCoord, int yCoord, int zCoord, double[] uv)
    {
        
    }
    
    private static double getU(double i, boolean flag)
    {
        if(flag)
        {
            return (i % 3.0D + 1.0D) / 3.0D;
        }
        
        return (i % 3.0D) / 3.0D;
    }
    
    private static double getX(double i, double w)
    {
        return 0.5D + (double)getddx(i) * w;
    }
    
    private static double getZ(double i, double w)
    {
        return 0.5D + (double)getddz(i) * w;
    }
    
    private static float getddx(double i)
    {
        return (float)Math.cos((-(0.5D + i) / 8.0D) * 2.0D * Math.PI);
    }
    
    private static float getddz(double i)
    {
        return (float)Math.sin((-(0.5D + i) / 8.0D) * 2.0D * Math.PI);
    }
    
    private void setColor(double i, double p, Color color)
    {
        float brightness = (float)(p * (0.7D + Math.cos((((i + 0.5D) / 4.0D) * 2.0D + 1.0D) * Math.PI) * 0.1D));
        
        float red = (float)color.getRed() / 255;
        float green = (float)color.getGreen() / 255;
        float blue = (float)color.getBlue() / 255;
        
        Tessellator.instance.setColorOpaque_F(brightness * red, brightness * green, brightness * blue);
    }
    
    private void setColor(double i, double p)
    {
        this.setColor(i, p, Color.WHITE);
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
