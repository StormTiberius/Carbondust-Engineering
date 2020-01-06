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
import org.lwjgl.opengl.GL11;

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
            
            x = (double)xCoord + getOffsetX(i + 1, w);
            y = (double)yCoord;
            z = (double)zCoord + getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetU(i, 1.0D) * uLength;
            v = uv[MAX_V];
            t.addVertexWithUV(x, y, z, u, v);
            
            x = (double)xCoord + getOffsetX(i + 1, w);
            y = (double)yCoord + PART_H;
            z = (double)zCoord + getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetU(i, 1.0D) * uLength;
            v = uv[MAX_V] - vOffset;
            t.addVertexWithUV(x, y, z, u, v);
            
            setColor((double)i + COLOR_BCV, 0.9D, color);
            
            x = (double)xCoord + getOffsetX(i, w);
            y = (double)yCoord + PART_H;
            z = (double)zCoord + getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetU(i, 0.0D) * uLength;
            v = uv[MAX_V] - vOffset;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = (double)xCoord + getOffsetX(i, w);
            y = (double)yCoord;
            z = (double)zCoord + getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetU(i, 0.0D) * uLength;
            v = uv[MAX_V];
            t.addVertexWithUV(x, y, z, u, v);
            
            setColor((double)(i + 1) - COLOR_BCV, 1.0D, color);
            
            x = (double)xCoord + getOffsetX(i + 1, w);
            y = (double)yCoord + PART_H * 2.0D;
            z = (double)zCoord + getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetU(i, 1.0D) * uLength;
            v = uv[MIN_V] + vOffset;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = (double)xCoord + getOffsetX(i + 1, w);
            y = (double)(yCoord + 1);
            z = (double)zCoord + getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetU(i, 1.0D) * uLength;
            v = uv[MIN_V];
            t.addVertexWithUV(x, y, z, u, v);
            
            setColor((double)i + COLOR_BCV, 0.9D, color);
            
            x = (double)xCoord + getOffsetX(i, w);
            y = (double)(yCoord + 1);
            z = (double)zCoord + getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetU(i, 0.0D) * uLength;
            v = uv[MIN_V];
            t.addVertexWithUV(x, y, z, u, v);
            
            x = (double)xCoord + getOffsetX(i, w);
            y = (double)yCoord + PART_H * 2.0D;
            z = (double)zCoord + getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetU(i, 0.0D) * uLength;
            v = uv[MIN_V] + vOffset;
            t.addVertexWithUV(x, y, z, u, v);
            
            setColor((double)(i + 1) - COLOR_BCV, 1.0D);
            
            x = (double)xCoord + getOffsetX(i + 1, w);
            y = (double)yCoord + PART_H;
            z = (double)zCoord + getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetU(i, 1.0D) * uLength;
            v = uv[MAX_V] - vOffset;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = (double)xCoord + getOffsetX(i + 1, w);
            y = (double)yCoord + PART_H * 2.0D;
            z = (double)zCoord + getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetU(i, 1.0D) * uLength;
            v = uv[MIN_V] + vOffset;
            t.addVertexWithUV(x, y, z, u, v);
            
            setColor((double)i + COLOR_BCV, 1.0D);
            
            x = (double)xCoord + getOffsetX(i, w);
            y = (double)yCoord + PART_H * 2.0D;
            z = (double)zCoord + getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetU(i, 0.0D) * uLength;
            v = uv[MIN_V] + vOffset;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = (double)xCoord + getOffsetX(i, w);
            y = (double)yCoord + PART_H;
            z = (double)zCoord + getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetU(i, 0.0D) * uLength;
            v = uv[MAX_V] - vOffset;
            t.addVertexWithUV(x, y, z, u, v);
            
            w = BASE_W;
            
            setColor((double)(i + 1) - COLOR_BCV, 0.6D);
            
            x = (double)xCoord + getOffsetX(i + 1, w);
            y = (double)yCoord;
            z = (double)zCoord + getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetU(i, 1.0D) * uLength;
            v = uv[MAX_V];
            t.addVertexWithUV(x, y, z, u, v);
            
            x = (double)xCoord + getOffsetX(i + 1, w);
            y = (double)yCoord + 0.05D;
            z = (double)zCoord + getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetU(i, 1.0D) * uLength;
            v = uv[MAX_V] - uLength * 0.05D;
            t.addVertexWithUV(x, y, z, u, v);
            
            setColor((double)i + COLOR_BCV, 0.6D);
            
            x = (double)xCoord + getOffsetX(i, w);
            y = (double)yCoord + 0.05D;
            z = (double)zCoord + getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetU(i, 0.0D) * uLength;
            v = uv[MAX_V] - uLength * 0.05D;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = (double)xCoord + getOffsetX(i, w);
            y = (double)yCoord;
            z = (double)zCoord + getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetU(i, 0.0D) * uLength;
            v = uv[MAX_V];
            t.addVertexWithUV(x, y, z, u, v);
            
            setColor((double)(i + 1) - COLOR_BCV, 0.6D);
            
            x = (double)xCoord + getOffsetX(i + 1, w);
            y = (double)yCoord + 0.95D;
            z = (double)zCoord + getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetU(i, 1.0D) * uLength;
            v = uv[MIN_V] + uLength * 0.05D;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = (double)xCoord + getOffsetX(i + 1, w);
            y = (double)(yCoord + 1);
            z = (double)zCoord + getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetU(i, 1.0D) * uLength;
            v = uv[MIN_V];
            t.addVertexWithUV(x, y, z, u, v);
            
            setColor((double)i + COLOR_BCV, 0.6D);
            
            x = (double)xCoord + getOffsetX(i, w);
            y = (double)(yCoord + 1);
            z = (double)zCoord + getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetU(i, 0.0D) * uLength;
            v = uv[MIN_V];
            t.addVertexWithUV(x, y, z, u, v);
            
            x = (double)xCoord + getOffsetX(i, w);
            y = (double)yCoord + 0.95D;
            z = (double)zCoord + getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetU(i, 0.0D) * uLength;
            v = uv[MIN_V] + uLength * 0.05D;
            t.addVertexWithUV(x, y, z, u, v);
            
            w = BASE_W * TOTAL_H * 0.9D;
            
            setColor((double)i + COLOR_BCV, 0.6D);

            x = (double)(xCoord + 1) - getOffsetX(i, w);
            y = (double)yCoord + 0.95D;
            z = (double)(zCoord + 1) - getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetU(i, 0.0D) * uLength;
            v = uv[MAX_V];
            t.addVertexWithUV(x, y, z, u, v);
            
            x = (double)(xCoord + 1) - getOffsetX(i, w);
            y = (double)(yCoord + 1);
            z = (double)(zCoord + 1) - getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetU(i, 0.0D) * uLength;
            v = uv[MAX_V] - uLength * 0.05D;
            t.addVertexWithUV(x, y, z, u, v);
            
            setColor((double)(i + 1) - COLOR_BCV, 0.6D);
            
            x = (double)(xCoord + 1) - getOffsetX(i + 1, w);
            y = (double)(yCoord + 1);
            z = (double)(zCoord + 1) - getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetU(i, 1.0D) * uLength;
            v = uv[MAX_V] - uLength * 0.05D;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = (double)(xCoord + 1) - getOffsetX(i + 1, w);
            y = (double)yCoord + 0.95D;
            z = (double)(zCoord + 1) - getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetU(i, 1.0D) * uLength;
            v = uv[MAX_V];
            t.addVertexWithUV(x, y, z, u, v);
            
            setColor((double)i + COLOR_BCV, 0.6D);
            
            x = (double)(xCoord + 1) - getOffsetX(i, w);
            y = (double)yCoord;
            z = (double)(zCoord + 1) - getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetU(i, 0.0D) * uLength;
            v = uv[MIN_V];
            t.addVertexWithUV(x, y, z, u, v);
            
            x = (double)(xCoord + 1) - getOffsetX(i, w);
            y = (double)yCoord + 0.05D;
            z = (double)(zCoord + 1) - getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetU(i, 0.0D) * uLength;
            v = uv[MIN_V] + uLength * 0.05D;
            t.addVertexWithUV(x, y, z, u, v);
            
            setColor((double)(i + 1) - COLOR_BCV, 0.6D);
            
            x = (double)(xCoord + 1) - getOffsetX(i + 1, w);
            y = (double)yCoord + 0.05D;
            z = (double)(zCoord + 1) - getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetU(i, 1.0D) * uLength;
            v = uv[MIN_V] + uLength * 0.05D;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = (double)(xCoord + 1) - getOffsetX(i + 1, w);
            y = (double)yCoord;
            z = (double)(zCoord + 1) - getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetU(i, 1.0D) * uLength;
            v = uv[MIN_V];
            t.addVertexWithUV(x, y, z, u, v);
        }
    }
        
    private void renderTop(Tessellator t, int xCoord, int yCoord, int zCoord, double[] uv)
    {
        double x,y,z,u,v,w;
        
        double uLength = uv[MAX_U] - uv[MIN_U];
        double vLength = uv[MAX_V] - uv[MIN_V];
        
        for(int i = 0; i < 8; i++)
        {
            t.setColorOpaque_F(0.8F, 0.8F, 0.8F);
            
            w = BASE_W;
            
            x = (double)xCoord + getOffsetX(i, w);
            y = (double)(yCoord + 1);
            z = (double)zCoord + getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetX(i, w) * uLength;
            v = uv[MAX_V] - getOffsetZ(i, w) * vLength;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = (double)xCoord + getOffsetX(i + 1, w);
            y = (double)(yCoord + 1);
            z = (double)zCoord + getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetX(i + 1, w) * uLength;
            v = uv[MAX_V] - getOffsetZ(i + 1, w) * vLength;
            t.addVertexWithUV(x, y, z, u, v);
            
            w = BASE_W * TOTAL_H * 0.9D;
            
            x = (double)xCoord + getOffsetX(i + 1, w);
            y = (double)(yCoord + 1);
            z = (double)zCoord + getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetX(i + 1, w) * uLength;
            v = uv[MAX_V] - getOffsetZ(i + 1, w) * vLength;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = (double)xCoord + getOffsetX(i, w);
            y = (double)(yCoord + 1);
            z = (double)zCoord + getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetX(i, w) * uLength;
            v = uv[MAX_V] - getOffsetZ(i, w) * vLength;
            t.addVertexWithUV(x, y, z, u, v);

            t.setColorOpaque_F(1.0F, 1.0F, 1.0F);
            
            x = (double)xCoord + getOffsetX(i, w);
            y = (double)yCoord + TOTAL_H;
            z = (double)zCoord + getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetX(i, w) * uLength;
            v = uv[MAX_V] - getOffsetZ(i, w) * vLength;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = (double)xCoord + getOffsetX(i + 1, w);
            y = (double)yCoord + TOTAL_H;
            z = (double)zCoord + getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetX(i + 1, w) * uLength;
            v = uv[MAX_V] - getOffsetZ(i + 1, w) * vLength;
            t.addVertexWithUV(x, y, z, u, v);

            w = 0.0D;
            
            x = (double)xCoord + getOffsetX(i + 1, w);
            y = (double)yCoord + TOTAL_H;
            z = (double)zCoord + getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetX(i + 1, w) * uLength;
            v = uv[MAX_V] - getOffsetZ(i + 1, w) * vLength;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = (double)xCoord + getOffsetX(i, w);
            y = (double)yCoord + TOTAL_H;
            z = (double)zCoord + getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetX(i, w) * uLength;
            v = uv[MAX_V] - getOffsetZ(i, w) * vLength;
            t.addVertexWithUV(x, y, z, u, v);   
        }
    }
            
    private void renderBottom(Tessellator t, int xCoord, int yCoord, int zCoord, double[] uv)
    {
        double x,y,z,u,v,w;
        
        double uLength = uv[MAX_U] - uv[MIN_U];
        double vLength = uv[MAX_V] - uv[MIN_V];
        
        for(int i = 0; i < 8; i++)
        {
            t.setColorOpaque_F(0.5F, 0.5F, 0.5F);
            
            w = BASE_W * TOTAL_H * 0.9D;
            
            x = (double)xCoord + getOffsetX(i, w);
            y = (double)yCoord;
            z = (double)zCoord + getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetX(i, w) * uLength;
            v = uv[MAX_V] - getOffsetZ(i, w) * vLength;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = (double)xCoord + getOffsetX(i + 1, w);
            y = (double)yCoord;
            z = (double)zCoord + getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetX(i + 1, w) * uLength;
            v = uv[MAX_V] - getOffsetZ(i + 1, w) * vLength;
            t.addVertexWithUV(x, y, z, u, v);
            
            w = BASE_W;
            
            x = (double)xCoord + getOffsetX(i + 1, w);
            y = (double)yCoord;
            z = (double)zCoord + getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetX(i + 1, w) * uLength;
            v = uv[MAX_V] - getOffsetZ(i + 1, w) * vLength;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = (double)xCoord + getOffsetX(i, w);
            y = (double)yCoord;
            z = (double)zCoord + getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetX(i, w) * uLength;
            v = uv[MAX_V] - getOffsetZ(i, w) * vLength;
            t.addVertexWithUV(x, y, z, u, v);
            
            t.setColorOpaque_F(0.6F, 0.6F, 0.6F);

            w = 0.0D;
            
            x = (double)xCoord + getOffsetX(i, w);
            y = (double)(yCoord + 1) - TOTAL_H;
            z = (double)zCoord + getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetX(i, w) * uLength;
            v = uv[MAX_V] - getOffsetZ(i, w) * vLength;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = (double)xCoord + getOffsetX(i + 1, w);
            y = (double)(yCoord + 1) - TOTAL_H;
            z = (double)zCoord + getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetX(i + 1, w) * uLength;
            v = uv[MAX_V] - getOffsetZ(i + 1, w) * vLength;
            t.addVertexWithUV(x, y, z, u, v);
            
            w = BASE_W * TOTAL_H * 0.9D;
            
            x = (double)xCoord + getOffsetX(i + 1, w);
            y = (double)(yCoord + 1) - TOTAL_H;
            z = (double)zCoord + getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetX(i + 1, w) * uLength;
            v = uv[MAX_V] - getOffsetZ(i + 1, w) * vLength;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = (double)xCoord + getOffsetX(i, w);
            y = (double)(yCoord + 1) - TOTAL_H;
            z = (double)zCoord + getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetX(i, w) * uLength;
            v = uv[MAX_V] - getOffsetZ(i, w) * vLength;
            t.addVertexWithUV(x, y, z, u, v);
        }
    }
    
    public static void drawInvBlock(Block block, ItemStack item)
    {
        Tessellator t = Tessellator.instance;
        GL11.glTranslatef(-0.0F, -0.5F, -0.0F);
        
        Color color = getColor(item);
        
        switch(getType(item))
        {
            case 0:
                    drawInvBlockSides(t, Utils.getUV(Defaults.TEXTURE_DRUM_IRON_SIDE), color);
                    drawInvBlockTop(t, Utils.getUV(Defaults.TEXTURE_DRUM_IRON_TOP));
                    drawInvBlockBottom(t, Utils.getUV(Defaults.TEXTURE_DRUM_IRON_TOP));
                    break;
            case 1:
                    drawInvBlockSides(t, Utils.getUV(Defaults.TEXTURE_DRUM_STEEL_SIDE), color);
                    drawInvBlockTop(t, Utils.getUV(Defaults.TEXTURE_DRUM_STEEL_TOP));
                    drawInvBlockBottom(t, Utils.getUV(Defaults.TEXTURE_DRUM_STEEL_TOP));
                    break;
        }
        
        GL11.glTranslatef(0.0F, 0.5F, 0.0F);
    }
    
    private static void drawInvBlockSides(Tessellator t, double[] uv, Color color)
    {
        double x,y,z,u,v,w;
        
        double uLength = uv[MAX_U] - uv[MIN_U];
        double vLength = uv[MAX_V] - uv[MIN_V];
        double vOffset = vLength * PART_H;
        
        for(int i = 0; i < 8; i++)
        {
            w = BASE_W * TOTAL_H;
            
            t.startDrawingQuads();
            t.setColorOpaque(color.getRed(), color.getGreen(), color.getBlue());
            t.setNormal((float)getX((double)i + 0.5D), 0.0F, (float)getZ((double)i + 0.5D));
            
            x = getOffsetX(i + 1, w);
            y = 0.0D;
            z = getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetU(i, 1.0D) * uLength;
            v = uv[MAX_V];
            t.addVertexWithUV(x, y, z, u, v);
            
            x = getOffsetX(i + 1, w);
            y = PART_H;
            z = getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetU(i, 1.0D) * uLength;
            v = uv[MAX_V] - vOffset;
            t.addVertexWithUV(x, y, z, u, v);
                        
            x = getOffsetX(i, w);
            y = PART_H;
            z = getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetU(i, 0.0D) * uLength;
            v = uv[MAX_V] - vOffset;
            t.addVertexWithUV(x, y, z, u, v);
               
            x = getOffsetX(i, w);
            y = 0.0D;
            z = getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetU(i, 0.0D) * uLength;
            v = uv[MAX_V];
            t.addVertexWithUV(x, y, z, u, v);
            
            x = getOffsetX(i + 1, w);
            y = PART_H * 2.0D;
            z = getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetU(i, 1.0D) * uLength;
            v = uv[MIN_V] + vOffset;
            t.addVertexWithUV(x, y, z, u, v);
              
            x = getOffsetX(i + 1, w);
            y = 1.0D;
            z = getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetU(i, 1.0D) * uLength;
            v = uv[MIN_V];
            t.addVertexWithUV(x, y, z, u, v);
                        
            x = getOffsetX(i, w);
            y = 1.0D;
            z = getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetU(i, 0.0D) * uLength;
            v = uv[MIN_V];
            t.addVertexWithUV(x, y, z, u, v);
                        
            x = getOffsetX(i, w);
            y = PART_H * 2.0D;
            z = getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetU(i, 0.0D) * uLength;
            v = uv[MIN_V] + vOffset;
            t.addVertexWithUV(x, y, z, u, v);            
            
            t.draw();
            
            t.startDrawingQuads();
            t.setColorOpaque_F(1.0F, 1.0F, 1.0F);
            t.setNormal((float)getX((double)i + 0.5D), 0.0F, (float)getZ((double)i + 0.5D));
                   
            x = getOffsetX(i + 1, w);
            y = PART_H;
            z = getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetU(i, 1.0D) * uLength;
            v = uv[MAX_V] - vOffset;
            t.addVertexWithUV(x, y, z, u, v);
                
            x = getOffsetX(i + 1, w);
            y = PART_H * 2.0D;
            z = getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetU(i, 1.0D) * uLength;
            v = uv[MIN_V] + vOffset;
            t.addVertexWithUV(x, y, z, u, v);
                        
            x = getOffsetX(i, w);
            y = PART_H * 2.0D;
            z = getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetU(i, 0.0D) * uLength;
            v = uv[MIN_V] + vOffset;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = getOffsetX(i, w);
            y = PART_H;
            z = getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetU(i, 0.0D) * uLength;
            v = uv[MAX_V] - vOffset;
            t.addVertexWithUV(x, y, z, u, v);
            
            t.draw();
            
            w = BASE_W;
            
            t.startDrawingQuads();
            t.setColorOpaque_F(0.6F, 0.6F, 0.6F);
            t.setNormal((float)getX((double)i + 0.5D), 0.0F, (float)getZ((double)i + 0.5D));
                        
            x = getOffsetX(i + 1, w);
            y = 0.0D;
            z = getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetU(i, 1.0D) * uLength;
            v = uv[MAX_V];
            t.addVertexWithUV(x, y, z, u, v);
                        
            x = getOffsetX(i + 1, w);
            y = 0.05D;
            z = getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetU(i, 1.0D) * uLength;
            v = uv[MAX_V] - uLength * 0.05D;
            t.addVertexWithUV(x, y, z, u, v);
                        
            x = getOffsetX(i, w);
            y = 0.05D;
            z = getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetU(i, 0.0D) * uLength;
            v = uv[MAX_V] - uLength * 0.05D;
            t.addVertexWithUV(x, y, z, u, v);
                        
            x = getOffsetX(i, w);
            y = 0.0D;
            z = getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetU(i, 0.0D) * uLength;
            v = uv[MAX_V];
            t.addVertexWithUV(x, y, z, u, v);
                        
            x = getOffsetX(i + 1, w);
            y = 0.95D;
            z = getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetU(i, 1.0D) * uLength;
            v = uv[MIN_V] + uLength * 0.05D;
            t.addVertexWithUV(x, y, z, u, v);
                        
            x = getOffsetX(i + 1, w);
            y = 1.0D;
            z = getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetU(i, 1.0D) * uLength;
            v = uv[MIN_V];
            t.addVertexWithUV(x, y, z, u, v);
                        
            x = getOffsetX(i, w);
            y = 1.0D;
            z = getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetU(i, 0.0D) * uLength;
            v = uv[MIN_V];
            t.addVertexWithUV(x, y, z, u, v);
                        
            x = getOffsetX(i, w);
            y = 0.95D;
            z = getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetU(i, 0.0D) * uLength;
            v = uv[MIN_V] + uLength * 0.05D;
            t.addVertexWithUV(x, y, z, u, v);
            
            w = BASE_W * TOTAL_H * 0.9D;
                   
            x = 1.0D - getOffsetX(i, w);
            y = 0.95D;
            z = 1.0D - getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetU(i, 0.0D) * uLength;
            v = uv[MAX_V];
            t.addVertexWithUV(x, y, z, u, v);
                        
            x = 1.0D - getOffsetX(i, w);
            y = 1.0D;
            z = 1.0D - getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetU(i, 0.0D) * uLength;
            v = uv[MAX_V] - uLength * 0.05D;
            t.addVertexWithUV(x, y, z, u, v);
                        
            x = 1.0D - getOffsetX(i + 1, w);
            y = 1.0D;
            z = 1.0D - getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetU(i, 1.0D) * uLength;
            v = uv[MAX_V] - uLength * 0.05D;
            t.addVertexWithUV(x, y, z, u, v);
                        
            x = 1.0D - getOffsetX(i + 1, w);
            y = 0.95D;
            z = 1.0D - getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetU(i, 1.0D) * uLength;
            v = uv[MAX_V];
            t.addVertexWithUV(x, y, z, u, v);
                        
            x = 1.0D - getOffsetX(i, w);
            y = 0.0D;
            z = 1.0D - getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetU(i, 0.0D) * uLength;
            v = uv[MIN_V];
            t.addVertexWithUV(x, y, z, u, v);
                        
            x = 1.0D - getOffsetX(i, w);
            y = 0.05D;
            z = 1.0D - getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetU(i, 0.0D) * uLength;
            v = uv[MIN_V] + uLength * 0.05D;
            t.addVertexWithUV(x, y, z, u, v);
                        
            x = 1.0D - getOffsetX(i + 1, w);
            y = 0.05D;
            z = 1.0D - getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetU(i, 1.0D) * uLength;
            v = uv[MIN_V] + uLength * 0.05D;
            t.addVertexWithUV(x, y, z, u, v);
                        
            x = 1.0D - getOffsetX(i + 1, w);
            y = 0.0D;
            z = 1.0D - getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetU(i, 1.0D) * uLength;
            v = uv[MIN_V];
            t.addVertexWithUV(x, y, z, u, v);
            
            t.draw();
        }
    }
    
    private static void drawInvBlockTop(Tessellator t, double[] uv)
    {
        double x,y,z,u,v,w;
        
        double uLength = uv[MAX_U] - uv[MIN_U];
        double vLength = uv[MAX_V] - uv[MIN_V];
        
        for(int i = 0; i < 8; i++)
        {
            t.startDrawingQuads();
            t.setColorOpaque_F(0.8F, 0.8F, 0.8F);
            t.setNormal(0.0F, 1.0F, 0.0F);
            
            w = BASE_W;
            
            x = getOffsetX(i, w);
            y = 1.0D;
            z = getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetX(i, w) * uLength;
            v = uv[MAX_V] - getOffsetZ(i, w) * vLength;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = getOffsetX(i + 1, w);
            y = 1.0D;
            z = getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetX(i + 1, w) * uLength;
            v = uv[MAX_V] - getOffsetZ(i + 1, w) * vLength;
            t.addVertexWithUV(x, y, z, u, v);
            
            w = BASE_W * TOTAL_H * 0.9D;
            
            x = getOffsetX(i + 1, w);
            y = 1.0D;
            z = getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetX(i + 1, w) * uLength;
            v = uv[MAX_V] - getOffsetZ(i + 1, w) * vLength;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = getOffsetX(i, w);
            y = 1.0D;
            z = getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetX(i, w) * uLength;
            v = uv[MAX_V] - getOffsetZ(i, w) * vLength;
            t.addVertexWithUV(x, y, z, u, v);
            
            t.draw();
            
            t.startDrawingQuads();
            t.setColorOpaque_F(1.0F, 1.0F, 1.0F);
            t.setNormal(0.0F, 1.0F, 0.0F);
            
            w = BASE_W * TOTAL_H * 0.9D;
            
            x = getOffsetX(i, w);
            y = TOTAL_H;
            z = getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetX(i, w) * uLength;
            v = uv[MAX_V] - getOffsetZ(i, w) * vLength;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = getOffsetX(i + 1, w);
            y = TOTAL_H;
            z = getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetX(i + 1, w) * uLength;
            v = uv[MAX_V] - getOffsetZ(i + 1, w) * vLength;
            t.addVertexWithUV(x, y, z, u, v);
            
            w = 0.0D;
            
            x = getOffsetX(i + 1, w);
            y = TOTAL_H;
            z = getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetX(i + 1, w) * uLength;
            v = uv[MAX_V] - getOffsetZ(i + 1, w) * vLength;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = getOffsetX(i, w);
            y = TOTAL_H;
            z = getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetX(i, w) * uLength;
            v = uv[MAX_V] - getOffsetZ(i, w) * vLength;
            t.addVertexWithUV(x, y, z, u, v);
            
            t.draw();
        }
    }
        
    private static void drawInvBlockBottom(Tessellator t, double[] uv)
    {
        double x,y,z,u,v,w;
        
        double uLength = uv[MAX_U] - uv[MIN_U];
        double vLength = uv[MAX_V] - uv[MIN_V];
    
        for(int i = 0; i < 8; i++)
        {
            t.startDrawingQuads();
            t.setColorOpaque_F(0.8F, 0.8F, 0.8F);
            t.setNormal(0.0F, -1.0F, 0.0F);
            
            w = BASE_W * TOTAL_H * 0.9D;
            
            x = getOffsetX(i, w);
            y = 0.0D;
            z = getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetX(i, w) * uLength;
            v = uv[MAX_V] - getOffsetZ(i, w) * vLength;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = getOffsetX(i + 1, w);
            y = 0.0D;
            z = getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetX(i + 1, w) * uLength;
            v = uv[MAX_V] - getOffsetZ(i + 1, w) * vLength;
            t.addVertexWithUV(x, y, z, u, v);
            
            w = BASE_W;
            
            x = getOffsetX(i + 1, w);
            y = 0.0D;
            z = getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetX(i + 1, w) * uLength;
            v = uv[MAX_V] - getOffsetZ(i + 1, w) * vLength;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = getOffsetX(i, w);
            y = 0.0D;
            z = getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetX(i, w) * uLength;
            v = uv[MAX_V] - getOffsetZ(i, w) * vLength;
            t.addVertexWithUV(x, y, z, u, v);
            
            t.draw();
            
            t.startDrawingQuads();
            t.setColorOpaque_F(1.0F, 1.0F, 1.0F);
            t.setNormal(0.0F, 1.0F, 0.0F);
            
            w = 0.0D;
            
            x = getOffsetX(i, w);
            y = 1.0D - TOTAL_H;
            z = getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetX(i, w) * uLength;
            v = uv[MAX_V] - getOffsetZ(i, w) * vLength;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = getOffsetX(i + 1, w);
            y = 1.0D - TOTAL_H;
            z = getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetX(i + 1, w) * uLength;
            v = uv[MAX_V] - getOffsetZ(i + 1, w) * vLength;
            t.addVertexWithUV(x, y, z, u, v);
            
            w = BASE_W * TOTAL_H * 0.9D;
            
            x = getOffsetX(i + 1, w);
            y = 1.0D - TOTAL_H;
            z = getOffsetZ(i + 1, w);
            u = uv[MIN_U] + getOffsetX(i + 1, w) * uLength;
            v = uv[MAX_V] - getOffsetZ(i + 1, w) * vLength;
            t.addVertexWithUV(x, y, z, u, v);
            
            x = getOffsetX(i, w);
            y = 1.0D - TOTAL_H;
            z = getOffsetZ(i, w);
            u = uv[MIN_U] + getOffsetX(i, w) * uLength;
            v = uv[MAX_V] - getOffsetZ(i, w) * vLength;
            t.addVertexWithUV(x, y, z, u, v);
            
            t.draw();
        }
    }
    
    private static double getOffsetU(double i, double d)
    {
        return (i % 3.0D + d) / 3.0D;
    }
    
    private static double getOffsetX(double i, double w)
    {
        return 0.5D + getX(i) * w;
    }
    
    private static double getOffsetZ(double i, double w)
    {
        return 0.5D + getZ(i) * w;
    }
    
    private static double getX(double i)
    {
        return Math.cos((-(0.5D + i) / 8.0D) * 2.0D * Math.PI);
    }
    
    private static double getZ(double i)
    {
        return Math.sin((-(0.5D + i) / 8.0D) * 2.0D * Math.PI);
    }
    
    private static void setColor(double i, double p, Color color)
    {
        float brightness = (float)(p * (0.7D + Math.cos((((i + 0.5D) / 4.0D) * 2.0D + 1.0D) * Math.PI) * 0.1D));
        
        float red = (float)color.getRed() / 255;
        float green = (float)color.getGreen() / 255;
        float blue = (float)color.getBlue() / 255;
        
        Tessellator.instance.setColorOpaque_F(brightness * red, brightness * green, brightness * blue);
    }
    
    private static void setColor(double i, double p)
    {
        setColor(i, p, IndustryCore.getLiquidColor());
    }
    
    private static Color getColor(ItemStack is)
    {
        if(is.hasTagCompound() && is.getTagCompound().hasKey("color"))
        {
            return new Color(is.getTagCompound().getInteger("color"));
        }
        
        return IndustryCore.getLiquidColor();
    }
    
    private static int getType(ItemStack is)
    {
        if(is.hasTagCompound() && is.getTagCompound().hasKey("capacity"))
        {
            switch(is.getTagCompound().getInteger("capacity"))
            {
                case Defaults.DRUM_CAPACITY_IRON: return 0;
                case Defaults.DRUM_CAPACITY_STEEL: return 1;
            }
        }
        
        return 0;
    }
}
