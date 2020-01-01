/**
 *
 * @author StormTiberius
 */

package cde.industry.render;

import cde.IndustryCore;
import cde.core.Defaults;
import cde.core.util.Utils;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class RenderBlockDrum implements ISimpleBlockRenderingHandler
{
    private static final double[] DRUM_UV_IRON_SIDE = Utils.getUV(Defaults.TEXTURE_DRUM_IRON_SIDE);
    private static final double[] DRUM_UV_IRON_TOP = Utils.getUV(Defaults.TEXTURE_DRUM_IRON_TOP);
    private static final double[] DRUM_UV_STEEL_SIDE = Utils.getUV(Defaults.TEXTURE_DRUM_STEEL_SIDE);
    private static final double[] DRUM_UV_STEEL_TOP = Utils.getUV(Defaults.TEXTURE_DRUM_STEEL_TOP);
    
    public static double w = 0.5D;
    private static float base_w = 0.425F;
    public static final double numTex = 3.0D;
    
    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
    {
        // --
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        int brightness = block.getMixedBrightnessForBlock(world, x, y, z);
        
        float h = 0.97F;
        float d = 0.2F;
        float h2 = 0.3125F;
        
        int l = block.colorMultiplier(world, x, y, z);
        
        int metadata = world.getBlockMetadata(x, y, z);
        
        double[] uvs = getUVS(metadata);
        double[] uvt = getUVT(metadata);
        
        float f = (float)(l >> 16 & 0xff) / 255.0F;
        float f1 = (float)(l >> 8 & 0xff) / 255.0F;
        float f2 = (float)(l & 0xff) / 255.0F;
        
        Tessellator t = Tessellator.instance;
        
        t.setBrightness(brightness);
        t.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        t.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
        
        float wu = (float)uvs[1] - (float)uvs[0];
        float wv = (float)uvs[3] - (float)uvs[2];
        float ddv = wv * 0.3125F;
        
        // SIDE RENDER
        for(int i = 0; i < 8; i++)
        {
            w = base_w * h;
            
            setB((float)(i + 1) - d, 1.0F, f, f1, f2);
            t.addVertexWithUV((double)x + dx(i + 1), y, (double)z + dz(i + 1), uvs[0] + du2(i) * (double)wu, uvs[3]);
            t.addVertexWithUV((double)x + dx(i + 1), (float)y + h2, (double)z + dz(i + 1), uvs[0] + du2(i) * (double)wu, uvs[3] - ddv);
            
            setB((float)i + d, 0.9F, f, f1, f2);
            t.addVertexWithUV((double)x + dx(i), y + h2, (double)z + dz(i), uvs[0] + du(i) * (double)wu, uvs[3] - ddv);
            t.addVertexWithUV((double)x + dx(i), (float)y, (double)z + dz(i), uvs[0] + du(i) * (double)wu, uvs[3]);
            
            setB((float)(i + 1) -d, 1.0F, f, f1, f2);
            t.addVertexWithUV((double)x + dx(i + 1), (float)y + h2 * 2.0F, (double)z + dz(i + 1), uvs[0] + du2(i) * (double)wu, uvs[2] + ddv);
            t.addVertexWithUV((double)x + dx(i + 1), (float)y + 1, (double)z + dz(i + 1), uvs[0] + du2(i) * (double)wu, uvs[2]);
            
            setB((float)i + d, 0.9F, f, f1, f2);
            t.addVertexWithUV((double)x + dx(i), (double)y + 1, (double)z + dz(i), uvs[0] + du(i) * (double)wu, uvs[2]);
            t.addVertexWithUV((double)x + dx(i), (float)y + h2 * 2.0F, (double)z + dz(i), uvs[0] + du(i) * (double)wu, uvs[2] + ddv);
            
            setB((float)(i + 1) - d, 1.0F);
            t.addVertexWithUV((double)x + dx(i + 1), (float)y + h2, (double)z + dz(i + 1), uvs[0] + du2(i) * (double)wu, uvs[3] - ddv);
            t.addVertexWithUV((double)x + dx(i + 1), (float)y + h2 * 2.0F, (double)z + dz(i + 1), uvs[0] + du2(i) * (double)wu, uvs[2] + ddv);
            
            setB((float)i + d, 1.0F);
            t.addVertexWithUV((double)x + dx(i), (float)y + h2 * 2.0F, (double)z + dz(i), uvs[0] + du(i) * (double)wu, uvs[2] + ddv);
            t.addVertexWithUV((double)x + dx(i), (float)y + h2, (double)z + dz(i), uvs[0] + du(i) * (double)wu, uvs[3] - ddv);
            
            t.setColorOpaque_F(0.65F, 0.65F, 0.65F);
            
            w = base_w;
            
            setB((float)(i + 1) - d, 0.6F);
            t.addVertexWithUV((double)x + dx(i + 1), (double)y, (double)z + dz(i + 1), uvs[0] + du2(i) * (double)wu, uvs[3]);
            t.addVertexWithUV((double)x + dx(i + 1), (double)y + 0.05D, (double)z + dz(i + 1), uvs[0] + du2(i) * (double)wu, uvs[3] - (double)wu * 0.05D);
            
            setB((float)i + d, 0.6F);
            t.addVertexWithUV((double)x + dx(i), (double)y + 0.05D, (double)z + dz(i), uvs[0] + du(i) * (double)wu, uvs[3] - (double)wu * 0.05D);
            t.addVertexWithUV((double)x + dx(i), (double)y, (double)z + dz(i), uvs[0] + du(i) * (double)wu, uvs[3]);
            
            setB((float)(i + 1) - d, 0.6F);
            t.addVertexWithUV((double)x + dx(i + 1), (double)y + 0.95D, (double)z + dz(i + 1), uvs[0] + du2(i) * (double)wu, uvs[2] + (double)wu * 0.05D);
            t.addVertexWithUV((double)x + dx(i + 1), (double)y + 1.0D, (double)z + dz(i + 1), uvs[0] + du2(i) * (double)wu, uvs[2]);
            
            setB((float)i + d, 0.6F);
            t.addVertexWithUV((double)x + dx(i), (double)y + 1.0D, (double)z + dz(i), uvs[0] + du(i) * (double)wu, uvs[2]);
            t.addVertexWithUV((double)x + dx(i), (double)y + 0.95D, (double)z + dz(i), uvs[0] + du(i) * (double)wu, uvs[2] + (double)wu * 0.05D);

            w = (double)(base_w * h) * 0.9D;
            
            setB((float)i + d, 0.6F);
            t.addVertexWithUV((double)(x + 1.0D) - dx(i), (double)y + 0.95D, (double)(z + 1.0D) - dz(i), uvs[0] + du(i) * (double)wu, uvs[3]);
            t.addVertexWithUV((double)(x + 1.0D) - dx(i), (double)y + 1.0D, (double)(z + 1.0D) - dz(i), uvs[0] + du(i) * (double)wu, uvs[3] - (double)wu * 0.05D);
            
            setB((float)(i + 1) - d, 0.6F);
            t.addVertexWithUV((double)(x + 1.0D) - dx(i + 1), (double)y + 1.0D, (double)(z + 1.0D) - dz(i + 1), uvs[0] + du2(i) * (double)wu, uvs[3] - (double)wu * 0.05D);
            t.addVertexWithUV((double)(x + 1.0D) - dx(i + 1), (double)y + 0.95D, (double)(z + 1.0D) - dz(i + 1), uvs[0] + du2(i) * (double)wu, uvs[3]);
            
            setB((float)i + d, 0.6F);
            t.addVertexWithUV((double)(x + 1.0D) - dx(i), (double)y, (double)(z + 1.0D) - dz(i), uvs[0] + du(i) * (double)wu, uvs[2]);
            t.addVertexWithUV((double)(x + 1.0D) - dx(i), (double)y + 0.05D, (double)(z + 1.0D) - dz(i), uvs[0] + du(i) * (double)wu, uvs[2] + (double)wu * 0.05D);
            
            setB((float)(i + 1) - d, 0.6F);
            t.addVertexWithUV((double)(x + 1.0D) - dx(i + 1), (double)y + 0.05D, (double)(z + 1.0D) - dz(i + 1), uvs[0] + du2(i) * (double)wu, uvs[2] + (double)wu * 0.05D);
            t.addVertexWithUV((double)(x + 1.0D) - dx(i + 1), (double)y + 0.0D, (double)(z + 1.0D) - dz(i + 1), uvs[0] + du2(i) * (double)wu, uvs[2]);            
        }
        
        w = base_w;
        
        wu = (float)uvt[1] - (float)uvt[0];
        wv = (float)uvt[3] - (float)uvt[2];
        
        // TOP RENDER
        for(int i = 0; i < 8; i++)
        {
            t.setColorOpaque_F(0.8F, 0.8F, 0.8F);
            
            w = base_w;
            t.addVertexWithUV((double)x + dx(i), (double)y + 1.0D, (double)z + dz(i), uvt[0] + dx(i) * (double)wu, uvt[3] - dz(i) * (double)wv);
            t.addVertexWithUV((double)x + dx(i + 1), (double)y + 1.0D, (double)z + dz(i + 1), uvt[0] + dx(i + 1) * (double)wu, uvt[3] - dz(i + 1) * (double)wv);
            
            w = (double)(base_w * h) * 0.9D;
            t.addVertexWithUV((double)x + dx(i + 1), (double)y + 1.0D, (double)z + dz(i + 1), uvt[0] + dx(i + 1) * (double)wu, uvt[3] - dz(i + 1) * (double)wv);
            t.addVertexWithUV((double)x + dx(i), (double)y + 1.0D, (double)z + dz(i), uvt[0] + dx(i) * (double)wu, uvt[3] - dz(i) * (double)wv);

            t.setColorOpaque_F(1.0F, 1.0F, 1.0F);

            w = (double)(base_w * h) * 0.9D;
            t.addVertexWithUV((double)x + dx(i), (double)y + h, (double)z + dz(i), uvt[0] + dx(i) * (double)wu, uvt[3] - dz(i) * (double)wv);
            t.addVertexWithUV((double)x + dx(i + 1), (double)y + h, (double)z + dz(i + 1), uvt[0] + dx(i + 1) * (double)wu, uvt[3] - dz(i + 1) * (double)wv);

            w = 0.0D;
            t.addVertexWithUV((double)x + dx(i + 1), (double)y + h, (double)z + dz(i + 1), uvt[0] + dx(i + 1) * (double)wu, uvt[3] - dz(i + 1) * (double)wv);
            t.addVertexWithUV((double)x + dx(i), (double)y + h, (double)z + dz(i), uvt[0] + dx(i) * (double)wu, uvt[3] - dz(i) * (double)wv);
        }

        t.setColorOpaque_F(0.5F, 0.5F, 0.5F);
        
        wu = (float)uvt[1] - (float)uvt[0];
        wv = (float)uvt[3] - (float)uvt[2];
        
        // BOTTOM RENDER
        for(int i = 0; i < 8; i++)
        {
            t.setColorOpaque_F(0.5F, 0.5F, 0.5F);
            
            w = (double)(base_w * h) * 0.9D;
            t.addVertexWithUV((double)x + dx(i), (double)y + 0.0D, (double)z + dz(i), uvt[0] + dx(i) * (double)wu, uvt[3] - dz(i) * (double)wv);
            t.addVertexWithUV((double)x + dx(i + 1), (double)y + 0.0D, (double)z + dz(i + 1), uvt[0] + dx(i + 1) * (double)wu, uvt[3] - dz(i + 1) * (double)wv);
            
            w = base_w;
            t.addVertexWithUV((double)x + dx(i + 1), (double)y + 0.0D, (double)z + dz(i + 1), uvt[0] + dx(i + 1) * (double)wu, uvt[3] - dz(i + 1) * (double)wv);
            t.addVertexWithUV((double)x + dx(i), (double)y + 0.0D, (double)z + dz(i), uvt[0] + dx(i) * (double)wu, uvt[3] - dz(i) * (double)wv);

            t.setColorOpaque_F(0.6F, 0.6F, 0.6F);

            w = 0.0D;
            t.addVertexWithUV((double)x + dx(i), (double)(y + 1.0D) - h, (double)z + dz(i), uvt[0] + dx(i) * (double)wu, uvt[3] - dz(i) * (double)wv);
            t.addVertexWithUV((double)x + dx(i + 1), (double)(y + 1.0D) - h, (double)z + dz(i + 1), uvt[0] + dx(i + 1) * (double)wu, uvt[3] - dz(i + 1) * (double)wv);

            w = (double)(base_w * h) * 0.9D;
            t.addVertexWithUV((double)x + dx(i + 1), (double)(y + 1.0D) - h, (double)z + dz(i + 1), uvt[0] + dx(i + 1) * (double)wu, uvt[3] - dz(i + 1) * (double)wv);
            t.addVertexWithUV((double)x + dx(i), (double)(y + 1.0D) - h, (double)z + dz(i), uvt[0] + dx(i) * (double)wu, uvt[3] - dz(i) * (double)wv);
        }
        
        return false;
    }
    
    public static void drawInvBlock(Block block, ItemStack item)
    {
        float h = 0.97F;
        float d = 0.2F;
        float h2 = 0.3125F;
        int l = getColor(item);
        
        double[] uvs = getUVS(item);
        double[] uvt = getUVT(item);
            
        float f = (float)(l >> 16 & 0xff) / 255.0F;
        float f1 = (float)(l >> 8 & 0xff) / 255.0F;
        float f2 = (float)(l & 0xff) / 255.0F;
        
        Tessellator t = Tessellator.instance;
        t.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        GL11.glTranslatef(-0.0F, -0.5F, -0.0F);
        
        float wu = (float)uvs[1] - (float)uvs[0];
        float wv = (float)uvs[3] - (float)uvs[2];
        float ddv = wv * 0.3125F;
        
        for(int i = 0; i < 8; i++)
        {
            w = base_w * h;
            
            // --
            t.startDrawingQuads();
            t.setNormal(ddx((double)i + 0.5D), 0.0F, ddz((double)i + 0.5D));
            t.setColorOpaque_F(f, f1, f2);
            
            t.addVertexWithUV(dx(i + 1), 0.0D, dz(i + 1), uvs[0] + du2(i) * (double)wu, uvs[3]);
            t.addVertexWithUV(dx(i + 1), h2, dz(i + 1), uvs[0] + du2(i) * (double)wu, uvs[3] - ddv);
            t.addVertexWithUV(dx(i), h2, dz(i), uvs[0] + du(i) * (double)wu, uvs[3] - ddv);
            t.addVertexWithUV(dx(i), 0.0D, dz(i), uvs[0] + du(i) *(double)wu, uvs[3]);
            t.addVertexWithUV(dx(i + 1), h2 * 2.0F, dz(i + 1), uvs[0] + du2(i) * (double)wu, uvs[2] + ddv);
            t.addVertexWithUV(dx(i + 1), 1.0D, dz(i + 1), uvs[0] + du2(i) * (double)wu, uvs[2]);
            t.addVertexWithUV(dx(i), 1.0D, dz(i), uvs[0]  + du(i) * (double)wu, uvs[2]);
            t.addVertexWithUV(dx(i), h2 * 2.0F, dz(i), uvs[0] + du(i) * (double)wu, uvs[2] + ddv);
            
            t.draw();
            
            // --
            t.startDrawingQuads();
            t.setColorOpaque_F(1.0F, 1.0F, 1.0F);
            t.setNormal(ddx((double)i + 0.5D), 0.0F, ddz((double)i + 0.5D));
            
            t.addVertexWithUV(dx(i + 1), h2, dz(i + 1), uvs[0] + du2(i) * (double)wu, uvs[3] -ddv);
            t.addVertexWithUV(dx(i + 1), h2 * 2.0F, dz(i + 1), uvs[0] + du2(i) * (double)wu, uvs[2] + ddv);
            t.addVertexWithUV(dx(i), h2 * 2.0F, dz(i), uvs[0] + du(i) * (double)wu, uvs[2] + ddv);
            t.addVertexWithUV(dx(i), h2, dz(i), uvs[0] + du(i) * (double)wu, uvs[3] -ddv);
            
            t.draw();
            
            w = base_w;
            
            // --
            t.startDrawingQuads();
            t.setColorOpaque_F(0.6F, 0.6F, 0.6F);
            t.setNormal(ddx((double)i + 0.5D), 0.0F, ddz((double)i + 0.5D));
            
            t.addVertexWithUV(dx(i + 1), 0.0D, dz(i + 1), uvs[0] + du2(i) * (double)wu, uvs[3]);
            t.addVertexWithUV(dx(i + 1), 0.05D, dz(i + 1), uvs[0] + du2(i) * (double)wu, uvs[3] - (double)wu * 0.05D);
            t.addVertexWithUV(dx(i), 0.05D, dz(i), uvs[0] + du(i) * (double)wu, uvs[3] - (double)wu *0.05D);
            t.addVertexWithUV(dx(i), 0.0D, dz(i), uvs[0] + du(i) * (double)wu, uvs[3]);
            t.addVertexWithUV(dx(i + 1), 0.95D, dz(i + 1), uvs[0] + du2(i) * (double)wu, uvs[2] + (double)wu * 0.05D);
            t.addVertexWithUV(dx(i + 1), 1.0D, dz(i + 1), uvs[0] + du2(i) * (double)wu, uvs[2]);
            t.addVertexWithUV(dx(i), 1.0D, dz(i), uvs[0] + du(i) * (double)wu, uvs[2]);
            t.addVertexWithUV(dx(i), 0.95D, dz(i), uvs[0] + du(i) * (double)wu, uvs[2] + (double)wu * 0.05D);
            
            w = (double)(base_w * h) * 0.9D;
            t.addVertexWithUV(1.0D - dx(i), 0.95D, 1.0D - dz(i), uvs[0] + du(i) * (double)wu, uvs[3]);
            t.addVertexWithUV(1.0D - dx(i), 1.0D, 1.0D - dz(i), uvs[0] + du(i) * (double)wu, uvs[3] - (double)wu * 0.05D);
            t.addVertexWithUV(1.0D - dx(i + 1), 1.0D, 1.0D - dz(i + 1), uvs[0] + du2(i) * (double)wu, uvs[3] - (double)wu * 0.05D);
            t.addVertexWithUV(1.0D - dx(i + 1), 0.95D, 1.0D - dz(i + 1), uvs[0] + du2(i) * (double)wu, uvs[3]);
            t.addVertexWithUV(1.0D - dx(i), 0.0D, 1.0D - dz(i), uvs[0] + du(i) * (double)wu, uvs[2]);
            t.addVertexWithUV(1.0D - dx(i), 0.05D, 1.0D - dz(i), uvs[0] + du(i) * (double)wu, uvs[2] + (double)wu * 0.05D);
            t.addVertexWithUV(1.0D - dx(i + 1), 0.05D, 1.0D - dz(i + 1), uvs[0] + du2(i) * (double)wu, uvs[2] + (double)wu * 0.05D);
            t.addVertexWithUV(1.0D - dx(i + 1), 0.0D, 1.0D - dz(i + 1), uvs[0] + du2(i) * (double)wu, uvs[2]);
            
            t.draw();
        }
        
        w = base_w;
        
        wu = (float)uvt[1] - (float)uvt[0];
        wv = (float)uvt[3] - (float)uvt[2];
        
        for(int i = 0; i < 8; i++)
        {
            // --
            t.startDrawingQuads();
            t.setColorOpaque_F(0.8F, 0.8F, 0.8F);
            t.setNormal(0.0F, 1.0F, 0.0F);
            
            w = base_w;
            t.addVertexWithUV(dx(i), 1.0D, dz(i), uvt[0] + dx(i) * (double)wu, uvt[3] - dz(i) * (double)wv);
            t.addVertexWithUV(dx(i + 1), 1.0D, dz(i + 1), uvt[0] + dx(i + 1) * (double)wu, uvt[3] - dz(i + 1) * (double)wv);
            
            w = (double)(base_w * h) * 0.9D;
            t.addVertexWithUV(dx(i + 1), 1.0D, dz(i + 1), uvt[0] + dx(i + 1) * (double)wu, uvt[3] - dz(i + 1) * (double)wv);
            t.addVertexWithUV(dx(i), 1.0D, dz(i), uvt[0] + dx(i) * (double)wu, uvt[3] - dz(i) * (double)wv);
            
            t.draw();
            
            // --
            t.startDrawingQuads();
            t.setColorOpaque_F(1.0F, 1.0F, 1.0F);
            t.setNormal(0.0F, 1.0F, 0.0F);
            
            w = (double)(base_w * h) * 0.9D;
            t.addVertexWithUV(dx(i), h, dz(i), uvt[0] + dx(i) * (double)wu, uvt[3] - dz(i) * (double)wv);
            t.addVertexWithUV(dx(i + 1), h, dz(i + 1), uvt[0] + dx(i + 1) * (double)wu, uvt[3] - dz(i + 1) * (double)wv);
            
            w = 0.0D;
            t.addVertexWithUV(dx(i + 1), h, dz(i + 1), uvt[0] + dx(i + 1) * (double)wu, uvt[3] - dz(i + 1) * (double)wv);
            t.addVertexWithUV(dx(i), h, dz(i), uvt[0] + dx(i) * (double)wu, uvt[3] - dz(i) * (double)wv);
            
            t.draw();
        }
        
        wu = (float)uvt[1] - (float)uvt[0];
        wv = (float)uvt[3] - (float)uvt[2];
        
        for(int i = 0; i < 8; i++)
        {
            // --
            t.startDrawingQuads();
            t.setColorOpaque_F(0.8F, 0.8F, 0.8F);
            t.setNormal(0.0F, -1.0F, 0.0F);
            
            w = (double)(base_w * h) * 0.9D;
            t.addVertexWithUV(dx(i), 0.0D, dz(i), uvt[0] + dx(i) * (double)wu, uvt[3] - dz(i) * (double)wv);
            t.addVertexWithUV(dx(i + 1), 0.0D, dz(i + 1), uvt[0] + dx(i + 1) * (double)wu, uvt[3] - dz(i + 1) * (double)wv);
            
            w = base_w;
            t.addVertexWithUV(dx(i + 1), 0.0D, dz(i + 1), uvt[0] + dx(i + 1) * (double)wu, uvt[3] - dz(i + 1) * (double)wv);
            t.addVertexWithUV(dx(i), 0.0D, dz(i), uvt[0] + dx(i) * (double)wu, uvt[3] - dz(i) * (double)wv);
            
            t.draw();
            
            // --            
            t.startDrawingQuads();
            t.setColorOpaque_F(1.0F, 1.0F, 1.0F);
            t.setNormal(0.0F, 1.0F, 0.0F);
            
            w = 0.0D;
            t.addVertexWithUV(dx(i), 1.0D - h, dz(i), uvt[0] + dx(i) * (double)wu, uvt[3] - dz(i) * (double)wv);
            t.addVertexWithUV(dx(i + 1), 1.0F - h, dz(i + 1), uvt[0] + dx(i + 1) * (double)wu, uvt[3] - dz(i + 1) * (double)wv);
            
            w = (double)(base_w * h) * 0.9D;
            t.addVertexWithUV(dx(i + 1), 1.0F - h, dz(i + 1), uvt[0] + dx(i + 1) * (double)wu, uvt[3] - dz(i + 1) * (double)wv);
            t.addVertexWithUV(dx(i), 1.0F - h, dz(i), uvt[0] + dx(i) * (double)wu, uvt[3] - dz(i) * (double)wv);
            
            t.draw();
        }
        
        GL11.glTranslatef(0.0F, 0.5F, 0.0F);
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
    
    public void setB(float i, float p, float r, float g, float b)
    {
        float brightness = (float)((double)p * (0.7D + Math.cos(((((double)i + 0.5D) / 4.0D) * 2.0D + 1.0D) * Math.PI) * 0.1D));
        Tessellator.instance.setColorOpaque_F(brightness * r, brightness * g, brightness * b);
    }
    
    public void setB(float i, float p)
    {
        setB(i, p, 1.0F, 1.0F, 1.0F);
    }
    
    public static double du(double i)
    {
        return (i % 3.0D) / 3.0D;
    }
    
    public static double du2(double i)
    {
        return (i % 3.0D + 1.0D) / 3.0D;
    }
    
    public static double dx(double i)
    {
        return 0.5D + (double)ddx(i) * w;
    }
    
    public static double dz(double i)
    {
        return 0.5D + (double)ddz(i) * w;
    }
    
    public static float ddx(double i)
    {
        return (float)Math.cos((-(0.5D + i) / 8.0D) * 2.0D * Math.PI);
    }
    
    public static float ddz(double i)
    {
        return (float)Math.sin((-(0.5D + i) / 8.0D) * 2.0D * Math.PI);
    }
    
    private static int getColor(ItemStack is)
    {
        int color = IndustryCore.getLiquidColor().getRGB();
        
        if(is.hasTagCompound() && is.getTagCompound().hasKey("color"))
        {
            color = is.getTagCompound().getInteger("color");
        }
        
        return color;
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
    
    private static double[] getUVS(ItemStack is)
    {
        if(isSteel(is))
        {
            return DRUM_UV_STEEL_SIDE;
        }
        
        return DRUM_UV_IRON_SIDE;
    }
    
    private static double[] getUVT(ItemStack is)
    {
        if(isSteel(is))
        {
            return DRUM_UV_STEEL_TOP;
        }
        
        return DRUM_UV_IRON_TOP;
    }
    
    private static double[] getUVS(int metadata)
    {
        switch(metadata)
        {
            case 0: return DRUM_UV_IRON_SIDE;
            case 1: return DRUM_UV_STEEL_SIDE;
            default: return DRUM_UV_IRON_SIDE;
        } 
    }
    
    private static double[] getUVT(int metadata)
    {
        switch(metadata)
        {
            case 0: return DRUM_UV_IRON_TOP;
            case 1: return DRUM_UV_STEEL_TOP;
            default: return DRUM_UV_IRON_TOP;
        }     
    }
}
