/**
 *
 * @author StormTiberius
 */

package cde.industry.render;

import cde.IndustryCore;
import cde.industry.ItemBlockDrum;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.liquids.LiquidStack;
import org.lwjgl.opengl.GL11;

public class RenderDrum implements ISimpleBlockRenderingHandler
{
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
        float f = (float)(1 >> 16 & 0xff) / 255.0F;
        float f1 = (float)(1 >> 8 & 0xff) / 255.0F;
        float f2 = (float)(1 & 0xff) / 255.0F;
        
        Tessellator t = Tessellator.instance;
        
        t.setBrightness(brightness);
        t.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        t.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
        
        int icon = block.getBlockTextureFromSideAndMetadata(2, 0); // TODO ICON?
        
        if(true) // (renderer.overrideBlockTexture != null) // TODO CHECK LATER
        {
            icon = renderer.overrideBlockTexture;
        }
        
        // func_94212_f,getMaxU,0,Returns the maximum U coordinate to use when rendering with this icon.
        // func_94209_e,getMinU,0,Returns the minimum U coordinate to use when rendering with this icon.
        // func_94210_h,getMaxV,0,Returns the maximum V coordinate to use when rendering with this icon.
        // func_94206_g,getMinV,0,Returns the minimum V coordinate to use when rendering with this icon.
        
        float wu = 0.0F; // icon.getMaxU() - icon.getMinU();
        float wv = 0.0F; // icon.getMaxV() - icon.getMinV();
        float ddv = wv * 0.3125F;
        
        for(int i = 0; i < 8; i++)
        {
            w = base_w * h;
            
            setB((float)(i + 1) - d, 1.0F, f, f1, f2);
            t.addVertexWithUV((double)x + dx(i + 1), y, (double)z + dz(i + 1), (double)icon/*getMinU*/ + du2(i) * (double)wu, icon/*getMaxV*/);
            t.addVertexWithUV((double)x + dx(i + 1), (float)y + h2, (double)z + dz(i + 1), (double)icon/*getMinU*/ + du2(i) * (double)wu, icon/*getMaxV*/ - ddv);
            
            setB((float)i + d, 0.9F, f, f1, f2);
            t.addVertexWithUV((double)x + dx(i), y + h2, (double)z + dz(i), (double)icon/*getMinU*/ + du(i) * (double)wu, icon/*getMaxV*/ - ddv);
            t.addVertexWithUV((double)x + dx(i), (float)y, (double)z + dz(i), (double)icon/*getMinU*/ + du(i) * (double)wu, icon/*getMaxV*/);
            
            setB((float)(i + 1) -d, 1.0F, f, f1, f2);
            t.addVertexWithUV((double)x + dx(i + 1), (float)y + h2 * 2.0F, (double)z + dz(i + 1), (double)icon/*getMinU*/ + du2(i) * (double)wu, icon/*getMinV*/ + ddv);
            t.addVertexWithUV((double)x + dx(i + 1), (float)y + 1, (double)z + dz(i + 1), (double)icon/*getMinU*/ + du2(i) * (double)wu, icon/*getMinV*/);
            
            setB((float)i + d, 0.9F, f, f1, f2);
            t.addVertexWithUV((double)x + dx(i), (double)y + 1, (double)z + dz(i), (double)icon/*getMinU*/ + du(i) * (double)wu, icon/*getMinV*/);
            t.addVertexWithUV((double)x + dx(i), (float)y + h2 * 2.0F, (double)z + dz(i), (double)icon/*getMinU*/ + du(i) * (double)wu, icon/*getMinV*/ + ddv);
            
            setB((float)(i + 1) - d, 1.0F);
            t.addVertexWithUV((double)x + dx(i + 1), (float)y + h2, (double)z + dz(i + 1), (double)icon/*getMinU*/ + du2(i) * (double)wu, icon/*getMaxV*/ - ddv);
            t.addVertexWithUV((double)x + dx(i + 1), (float)y + h2 * 2.0F, (double)z + dz(i + 1), (double)icon/*getMinU*/ + du2(i) * (double)wu, icon/*getMinV*/ + ddv);
            
            setB((float)i + d, 1.0F);
            t.addVertexWithUV((double)x + dx(i), (float)y + h2 * 2.0F, (double)z + dz(i), (double)icon/*getMinU*/ + du(i) * (double)wu, icon/*getMinV*/ + ddv);
            t.addVertexWithUV((double)x + dx(i), (float)y + h2, (double)z + dz(i), (double)icon/*getMinU*/ + du(i) * (double)wu, icon/*getMaxV*/ - ddv);
            
            t.setColorOpaque_F(0.65F, 0.65F, 0.65F);
            
            w = base_w;
            
            // func_94212_f,getMaxU,0,Returns the maximum U coordinate to use when rendering with this icon.
            // func_94209_e,getMinU,0,Returns the minimum U coordinate to use when rendering with this icon.
            // func_94210_h,getMaxV,0,Returns the maximum V coordinate to use when rendering with this icon.
            // func_94206_g,getMinV,0,Returns the minimum V coordinate to use when rendering with this icon.
            
            setB((float)(i + 1) - d, 0.6F);
            t.addVertexWithUV((double)x + dx(i + 1), (double)y, (double)z + dz(i + 1), (double)icon/*getMinU*/ + du2(i) * (double)wu, (double)icon/*getMaxV*/);
            t.addVertexWithUV((double)x + dx(i + 1), (double)y + 0.05D, (double)z + dz(i + 1), (double)icon/*getMinU*/ + du2(i) * (double)wu, (double)icon/*getMaxV*/ - (double)wu * 0.05D);
            
            setB((float)i + d, 0.6F);
            t.addVertexWithUV((double)x + dx(i), (double)y + 0.05D, (double)z + dz(i), (double)icon/*getMinU*/ + du(i) * (double)wu, (double)icon/*getMaxV*/ - (double)wu * 0.05D);
            t.addVertexWithUV((double)x + dx(i), (double)y, (double)z + dz(i), (double)icon/*getMinU*/ + du(i) * (double)wu, (double)icon/*getMaxV*/);
            
            setB((float)(i + 1) - d, 0.6F);
            t.addVertexWithUV((double)x + dx(i + 1), (double)y + 0.95D, (double)z + dz(i + 1), (double)icon/*getMinU*/ + du2(i) * (double)wu, (double)icon/*getMinV*/ + (double)wu * 0.05D);
            t.addVertexWithUV((double)x + dx(i + 1), (double)y + 1.0D, (double)z + dz(i + 1), (double)icon/*getMinU*/ + du2(i) * (double)wu, (double)icon/*getMinV*/);
            
            setB((float)i + d, 0.6F);
            t.addVertexWithUV((double)x + dx(i), (double)y + 1.0D, (double)z + dz(i), (double)icon/*getMinU*/ + du(i) * (double)wu, (double)icon/*getMinV*/);
            t.addVertexWithUV((double)x + dx(i), (double)y + 0.95D, (double)z + dz(i), (double)icon/*getMinU*/ + du(i) * (double)wu, (double)icon/*getMinV*/ + (double)wu * 0.05D);

            w = (double)(base_w * h) * 0.9D;
            
            setB((float)i + d, 0.6F);
            t.addVertexWithUV((double)(x + 1.0D) - dx(i), (double)y + 0.95D, (double)(z + 1.0D) - dz(i), (double)icon/*getMinU*/ + du(i) * (double)wu, (double)icon/*getMaxV*/);
            t.addVertexWithUV((double)(x + 1.0D) - dx(i), (double)y + 1.0D, (double)(z + 1.0D) - dz(i), (double)icon/*getMinU*/ + du(i) * (double)wu, (double)icon/*getMaxV*/ - (double)wu * 0.05D);
            
            setB((float)(i + 1) - d, 0.6F);
            t.addVertexWithUV((double)(x + 1.0D) - dx(i + 1), (double)y + 1.0D, (double)(z + 1.0D) - dz(i + 1), (double)icon/*getMinU*/ + du2(i) * (double)wu, (double)icon/*getMaxV*/ - (double)wu * 0.05D);
            t.addVertexWithUV((double)(x + 1.0D) - dx(i + 1), (double)y + 0.95D, (double)(z + 1.0D) - dz(i + 1), (double)icon/*getMinU*/ + du2(i) * (double)wu, (double)icon/*getMaxV*/);
            
            setB((float)i + d, 0.6F);
            t.addVertexWithUV((double)(x + 1.0D) - dx(i), (double)y, (double)(z + 1.0D) - dz(i), (double)icon/*getMinU*/ + du(i) * (double)wu, (double)icon/*getMinV*/);
            t.addVertexWithUV((double)(x + 1.0D) - dx(i), (double)y + 0.05D, (double)(z + 1.0D) - dz(i), (double)icon/*getMinU*/ + du(i) * (double)wu, (double)icon/*getMinV*/ + (double)wu * 0.05D);
            
            setB((float)(i + 1) - d, 0.6F);
            t.addVertexWithUV((double)(x + 1.0D) - dx(i + 1), (double)y + 0.05D, (double)(z + 1.0D) - dz(i + 1), (double)icon/*getMinU*/ + du2(i) * (double)wu, (double)icon/*getMinV*/ + (double)wu * 0.05D);
            t.addVertexWithUV((double)(x + 1.0D) - dx(i + 1), (double)y + 0.0D, (double)(z + 1.0D) - dz(i + 1), (double)icon/*getMinU*/ + du2(i) * (double)wu, (double)icon/*getMinV*/);            
        }
        
        w = base_w;
        
        icon = block.getBlockTextureFromSideAndMetadata(1, 0);
        
        if(true) // (renderer.overrideBlockTexture != null) // TODO CHECK LATER
        {
            icon = renderer.overrideBlockTexture;
        }
        
        wu = 0.0F; // icon.getMaxU() - icon.getMinU();
        wv = 0.0F; // icon.getMaxV() - icon.getMinV();
        
        for(int i = 0; i < 8; i++)
        {
            t.setColorOpaque_F(0.8F, 0.8F, 0.8F);
            
            w = base_w;
            t.addVertexWithUV((double)x + dx(i), (double)y + 1.0D, (double)z + dz(i), (double)icon/*getMinU*/ + dx(i) * (double)wu, (double)icon/*getMaxV*/ - dz(i) * (double)wv);
            t.addVertexWithUV((double)x + dx(i + 1), (double)y + 1.0D, (double)z + dz(i + 1), (double)icon/*getMinU*/ + dx(i + 1) * (double)wu, (double)icon/*getMaxV*/ - dz(i - 1) * (double)wv);
            
            w = (double)(base_w * h) * 0.9D;
            t.addVertexWithUV((double)x + dx(i + 1), (double)y + 1.0D, (double)z + dz(i + 1), (double)icon/*getMinU*/ + dx(i + 1) * (double)wu, (double)icon/*getMaxV*/ - dz(i + 1) * (double)wv);
            t.addVertexWithUV((double)x + dx(i), (double)y + 1.0D, (double)z + dz(i), (double)icon/*getMinU*/ + dx(i) * (double)wu, (double)icon/*getMaxV*/ - dz(i) * (double)wv);

            t.setColorOpaque_F(1.0F, 1.0F, 1.0F);

            // func_94212_f,getMaxU,0,Returns the maximum U coordinate to use when rendering with this icon.
            // func_94209_e,getMinU,0,Returns the minimum U coordinate to use when rendering with this icon.
            // func_94210_h,getMaxV,0,Returns the maximum V coordinate to use when rendering with this icon.
            // func_94206_g,getMinV,0,Returns the minimum V coordinate to use when rendering with this icon.
            
            w = (double)(base_w * h) * 0.9D;
            t.addVertexWithUV((double)x + dx(i), (double)y + h, (double)z + dz(i), (double)icon/*getMinU*/ + dx(i) * (double)wu, (double)icon/*getMaxV*/ - dz(i) * (double)wv);
            t.addVertexWithUV((double)x + dx(i + 1), (double)y + h, (double)z + dz(i + 1), (double)icon/*getMinU*/ + dx(i + 1) * (double)wu, (double)icon/*getMaxV*/ - dz(i + 1) * (double)wv);

            w = 0.0D;
            t.addVertexWithUV((double)x + dx(i + 1), (double)y + h, (double)z + dz(i + 1), (double)icon/*getMinU*/ + dx(i + 1) * (double)wu, (double)icon/*getMaxV*/ - dz(i + 1) * (double)wv);
            t.addVertexWithUV((double)x + dx(i), (double)y + h, (double)z + dz(i), (double)icon/*getMinU*/ + dx(i) * (double)wu, (double)icon/*getMaxV*/ - dz(i) * (double)wv);
        }

        t.setColorOpaque_F(0.5F, 0.5F, 0.5F);
        icon = block.getBlockTextureFromSideAndMetadata(0, 0);
        
        if(true) // (renderer.overrideBlockTexture != null) // TODO CHECK LATER
        {
            icon = renderer.overrideBlockTexture;
        }
        
        wu = 0.0F; // icon.getMaxU() - icon.getMinU();
        wv = 0.0F; // icon.getMaxV() - icon.getMinV();
        
        for(int i = 0; i < 8; i++)
        {
            t.setColorOpaque_F(0.5F, 0.5F, 0.5F);
            
            w = (double)(base_w * h) * 0.9D;
            t.addVertexWithUV((double)x + dx(i), (double)y + 0.0D, (double)z + dz(i), (double)icon/*getMinU*/ + dx(i) * (double)wu, (double)icon/*getMaxV*/ - dz(i) * (double)wv);
            t.addVertexWithUV((double)x + dx(i + 1), (double)y + 0.0D, (double)z + dz(i + 1), (double)icon/*getMinU*/ + dx(i + 1) * (double)wu, (double)icon/*getMaxV*/ - dz(i - 1) * (double)wv);
            
            w = base_w;
            t.addVertexWithUV((double)x + dx(i + 1), (double)y + 0.0D, (double)z + dz(i + 1), (double)icon/*getMinU*/ + dx(i + 1) * (double)wu, (double)icon/*getMaxV*/ - dz(i + 1) * (double)wv);
            t.addVertexWithUV((double)x + dx(i), (double)y + 0.0D, (double)z + dz(i), (double)icon/*getMinU*/ + dx(i) * (double)wu, (double)icon/*getMaxV*/ - dz(i) * (double)wv);

            t.setColorOpaque_F(0.6F, 0.6F, 0.6F);

            // func_94212_f,getMaxU,0,Returns the maximum U coordinate to use when rendering with this icon.
            // func_94209_e,getMinU,0,Returns the minimum U coordinate to use when rendering with this icon.
            // func_94210_h,getMaxV,0,Returns the maximum V coordinate to use when rendering with this icon.
            // func_94206_g,getMinV,0,Returns the minimum V coordinate to use when rendering with this icon.
            
            w = 0.0D;
            t.addVertexWithUV((double)x + dx(i), (double)(y + 1.0D) - h, (double)z + dz(i), (double)icon/*getMinU*/ + dx(i) * (double)wu, (double)icon/*getMaxV*/ - dz(i) * (double)wv);
            t.addVertexWithUV((double)x + dx(i + 1), (double)(y + 1.0D) - h, (double)z + dz(i + 1), (double)icon/*getMinU*/ + dx(i + 1) * (double)wu, (double)icon/*getMaxV*/ - dz(i + 1) * (double)wv);

            w = (double)(base_w * h) * 0.9D;
            t.addVertexWithUV((double)x + dx(i + 1), (double)(y + 1.0D) - h, (double)z + dz(i + 1), (double)icon/*getMinU*/ + dx(i + 1) * (double)wu, (double)icon/*getMaxV*/ - dz(i + 1) * (double)wv);
            t.addVertexWithUV((double)x + dx(i), (double)(y + 1.0D) - h, (double)z + dz(i), (double)icon/*getMinU*/ + dx(i) * (double)wu, (double)icon/*getMaxV*/ - dz(i) * (double)wv);
        }
        
        return false;
    }

    public static void drawInvBlock(Block block, ItemStack item)
    {
        float h = 0.97F;
        float d = 0.2F;
        float h2 = 0.3125F;
        int l = 0xffffff;
        
        if(item.hasTagCompound() && item.stackTagCompound.hasKey("color"))
        {
            l = item.stackTagCompound.getInteger("color");
        }
            
        LiquidStack fluid = ((ItemBlockDrum)item.getItem()).getFluid(item); // TODO NEEDS CHECKING
        l = 0; // LiquidColorRegistry.getFluidColor(fluid); // TODO NEEDS CHECKING
            
        float f = (float)(1 >> 16 & 0xff) / 255.0F;
        float f1 = (float)(1 >> 8 & 0xff) / 255.0F;
        float f2 = (float)(1 & 0xff) / 255.0F;
        
        Tessellator t = Tessellator.instance;
        t.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        GL11.glTranslatef(-0.0F, -0.5F, -0.0F);
        int icon = block.getBlockTextureFromSideAndMetadata(2, 0);
        
        float wu = 0.0F; // icon.getMaxU() - icon.getMinU();
        float wv = 0.0F; // icon.getMaxV() - icon.getMinV();
        float ddv = wv * 0.3125F;
        
        for(int i = 0; i < 8; i++)
        {
            w = base_w * h;
            
            // func_94206_g,getMinV,0,Returns the minimum V coordinate to use when rendering with this icon.
            // func_94209_e,getMinU,0,Returns the minimum U coordinate to use when rendering with this icon.
            // func_94210_h,getMaxV,0,Returns the maximum V coordinate to use when rendering with this icon.
            // func_94212_f,getMaxU,0,Returns the maximum U coordinate to use when rendering with this icon.
            
            t.startDrawingQuads();
            t.setNormal(ddx((double)i + 0.5D), 0.0F, ddz((double)i + 0.5D));
            t.setColorOpaque_F(f, f1, f2);
            t.addVertexWithUV(dx(i + 1), 0.0D, dz(i + 1), (double)icon/*getMinU*/ + du2(i) * (double)wu, (double)icon/*getMaxV*/);
            t.addVertexWithUV(dx(i + 1), h2, dz(i + 1), (double)icon/*getMinU*/ + du2(i) * (double)wu, (double)icon/*getMaxV*/ - ddv);
            t.addVertexWithUV(dx(i), h2, dz(i), (double)icon/*getMinU*/ + du(i) * (double)wu, (double)icon/*getMaxV*/ - ddv);
            t.addVertexWithUV(dx(i), 0.0D, dz(i), (double)icon/*getMinU*/ + du(i) *(double)wu, (double)icon/*getMaxV*/);
            t.addVertexWithUV(dx(i + 1), h2 * 2.0F, dz(i + 1), (double)icon/*getMinU*/ + du2(i) * (double)wu, (double)icon/*getMinV*/ + ddv);
            t.addVertexWithUV(dx(i + 1), 1.0D, dz(i + 1), (double)icon/*getMinU*/ + du2(i) * (double)wu, (double)icon/*getMinV*/);
            t.addVertexWithUV(dx(i), 1.0D, dz(i), (double)icon/*getMinU*/  + du(i) * (double)wu, (double)icon/*getMinV*/);
            t.addVertexWithUV(dx(i), h2 * 2.0F, dz(i), (double)icon/*getMinU*/ + du(i) * (double)wu, (double)icon/*getMinV*/ + ddv);
            t.draw();
            
            t.startDrawingQuads();
            t.setColorOpaque_F(1.0F, 1.0F, 1.0F);
            t.setNormal(ddx((double)i + 0.5D), 0.0F, ddz((double)i + 0.5D));
            t.addVertexWithUV(dx(i + 1), h2, dz(i + 1), (double)icon/*getMinU*/ + du2(i) * (double)wu, (double)icon/*getMaxV*/ -ddv);
            t.addVertexWithUV(dx(i + 1), h2 * 2.0F, dz(i + 1), (double)icon/*getMinU*/ + du2(i) * (double)wu, (double)icon/*getMinV*/ + ddv);
            t.addVertexWithUV(dx(i), h2 * 2.0F, dz(i), (double)icon/*getMinU*/ + du(i) * (double)wu, (double)icon/*getMinV*/ + ddv);
            t.addVertexWithUV(dx(i), h2, dz(i), (double)icon/*getMinU*/ + du(i) * (double)wu, (double)icon/*getMaxV*/ -ddv);
            t.draw();
            
            w = base_w;
            
            t.startDrawingQuads();
            t.setColorOpaque_F(0.6F, 0.6F, 0.6F);
            t.setNormal(ddx((double)i + 0.5D), 0.0F, ddz((double)i + 0.5D));
            t.addVertexWithUV(dx(i + 1), 0.0D, dz(i + 1), (double)icon/*getMinU*/ + du2(i) * (double)wu, (double)icon/*getMaxV*/);
            t.addVertexWithUV(dx(i + 1), 0.05D, dz(i + 1), (double)icon/*getMinU*/ + du2(i) * (double)wu, (double)icon/*getMaxV*/ - (double)wu * 0.05D);
            t.addVertexWithUV(dx(i), 0.05D, dz(i), (double)icon/*getMinU*/ + du(i) * (double)wu, (double)icon/*getMaxV*/ - (double)wu *0.05D);
            t.addVertexWithUV(dx(i), 0.0D, dz(i), (double)icon/*getMinU*/ + du(i) * (double)wu, (double)icon/*getMaxV*/);
            t.addVertexWithUV(dx(i + 1), 0.95D, dz(i + 1), (double)icon/*getMinU*/ + du2(i) * (double)wu, (double)icon/*getMinV*/ + (double)wu * 0.05D);
            t.addVertexWithUV(dx(i + 1), 1.0D, dz(i + 1), (double)icon/*getMinU*/ + du2(i) * (double)wu, (double)icon/*getMinV*/);
            t.addVertexWithUV(dx(i), 1.0D, dz(i), (double)icon/*getMinU*/ + du(i) * (double)wu, (double)icon/*getMinV*/);
            t.addVertexWithUV(dx(i), 0.95D, dz(i), (double)icon/*getMinU*/ + du(i) * (double)wu, (double)icon/*getMinV*/ + (double)wu * 0.05D);
            
            w = (double)(base_w * h) * 0.9D;
            
            t.addVertexWithUV(1.0D - dx(i), 0.95D, 1.0D - dz(i), (double)icon/*getMinU*/ + du(i) * (double)wu, (double)icon/*getMaxV*/);
            t.addVertexWithUV(1.0D - dx(i), 1.0D, 1.0D - dz(i), (double)icon/*getMinU*/ + du(i) * (double)wu, (double)icon/*getMaxV*/ - (double)wu * 0.05D);
            t.addVertexWithUV(1.0D - dx(i + 1), 1.0D, 1.0D - dz(i + 1), (double)icon/*getMinU*/ + du2(i) * (double)wu, (double)icon/*getMaxV*/ - (double)wu * 0.05D);
            t.addVertexWithUV(1.0D - dx(i + 1), 0.95D, 1.0D - dz(i + 1), (double)icon/*getMinU*/ + du2(i) * (double)wu, (double)icon/*getMaxV*/);
            t.addVertexWithUV(1.0D - dx(i), 0.0D, 1.0D - dz(i), (double)icon/*getMinU*/ + du(i) * (double)wu, (double)icon/*getMinV*/);
            t.addVertexWithUV(1.0D - dx(i), 0.05D, 1.0D - dz(i), (double)icon/*getMinU*/ + du(i) * (double)wu, (double)icon/*getMinV*/ + (double)wu * 0.05D);
            t.addVertexWithUV(1.0D - dx(i + 1), 0.05D, 1.0D - dz(i + 1), (double)icon/*getMinU*/ + du2(i) * (double)wu, (double)icon/*getMinV*/ + (double)wu * 0.05D);
            t.addVertexWithUV(1.0D - dx(i + 1), 0.0D, 1.0D - dz(i + 1), (double)icon/*getMinU*/ + du2(i) * (double)wu, (double)icon/*getMinV*/);
            t.draw();
            
            // func_94206_g,getMinV,0,Returns the minimum V coordinate to use when rendering with this icon.
            // func_94209_e,getMinU,0,Returns the minimum U coordinate to use when rendering with this icon.
            // func_94210_h,getMaxV,0,Returns the maximum V coordinate to use when rendering with this icon.
            // func_94212_f,getMaxU,0,Returns the maximum U coordinate to use when rendering with this icon.
        }
        
        w = base_w;
        
        icon = block.getBlockTextureFromSideAndMetadata(1, 0);
        
        wu = 0.0F; // icon.getMaxU() - icon.getMinU();
        wv = 0.0F; // icon.getMaxV() - icon.getMinV();
        
        for(int i = 0; i < 8; i++)
        {
            t.startDrawingQuads();
            t.setColorOpaque_F(0.8F, 0.8F, 0.8F);
            t.setNormal(0.0F, 1.0F, 0.0F);
            
            w = base_w;
            t.addVertexWithUV(dx(i), 1.0D, dz(i), (double)icon/*getMinU*/ + dx(i) * (double)wu, (double)icon/*getMaxV*/ - dz(i) * (double)wv);
            t.addVertexWithUV(dx(i + 1), 1.0D, dz(i + 1), (double)icon/*getMinU*/ + dx(i + 1) * (double)wu, (double)icon/*getMaxV*/ - dz(i + 1) * (double)wv);
            
            w = (double)(base_w * h) * 0.9D;
            t.addVertexWithUV(dx(i + 1), 1.0D, dz(i + 1), (double)icon/*getMinU*/ + dx(i + 1) * (double)wu, (double)icon/*getMaxV*/ - dz(i + 1) * (double)wv);
            t.addVertexWithUV(dx(i), 1.0D, dz(i), (double)icon/*getMinU*/ + dx(i) * (double)wu, (double)icon/*getMaxV*/ - dz(i) * (double)wv);
            t.draw();
            
            t.startDrawingQuads();
            t.setColorOpaque_F(1.0F, 1.0F, 1.0F);
            t.setNormal(0.0F, 1.0F, 0.0F);
            w = (double)(base_w * h) * 0.9D;
            
            t.addVertexWithUV(dx(i), h, dz(i), (double)icon/*getMinU*/ + dx(i) * (double)wu, (double)icon/*getMaxV*/ - dz(i) * (double)wv);
            t.addVertexWithUV(dx(i + 1), h, dz(i + 1), (double)icon/*getMinU*/ + dx(i + 1) * (double)wu, (double)icon/*getMaxV*/ - dz(i + 1) * (double)wv);
            
            w = 0.0D;
            t.addVertexWithUV(dx(i + 1), h, dz(i + 1), (double)icon/*getMinU*/ + dx(i + 1) * (double)wu, (double)icon/*getMaxV*/ - dz(i + 1) * (double)wv);
            t.addVertexWithUV(dx(i), h, dz(i), (double)icon/*getMinU*/ + dx(i) * (double)wu, (double)icon/*getMaxV*/ - dz(i) * (double)wv);
            t.draw();
        }
        
        icon = block.getBlockTextureFromSideAndMetadata(0, 0);
        
        wu = 0.0F; // icon.getMaxU() - icon.getMinU();
        wv = 0.0F; // icon.getMaxV() - icon.getMinV();
        
        for(int i = 0; i < 8; i++)
        {
            t.startDrawingQuads();
            t.setColorOpaque_F(0.8F, 0.8F, 0.8F);
            t.setNormal(0.0F, -1.0F, 0.0F);
            
            w = (double)(base_w * h) * 0.9D;
            t.addVertexWithUV(dx(i), 0.0D, dz(i), (double)icon/*getMinU*/ + dx(i) * (double)wu, (double)icon/*getMaxV*/ - dz(i) * (double)wv);
            t.addVertexWithUV(dx(i + 1), 0.0D, dz(i + 1), (double)icon/*getMinU*/ + dx(i + 1) * (double)wu, (double)icon/*getMaxV*/ - dz(i + 1) * (double)wv);
            
            w = base_w;
            t.addVertexWithUV(dx(i + 1), 0.0D, dz(i + 1), (double)icon/*getMinU*/ + dx(i + 1) * (double)wu, (double)icon/*getMaxV*/ - dz(i + 1) * (double)wv);
            t.addVertexWithUV(dx(i), 0.0D, dz(i), (double)icon/*getMinU*/ + dx(i) * (double)wu, (double)icon/*getMaxV*/ - dz(i) * (double)wv);
            t.draw();
            
            t.startDrawingQuads();
            t.setColorOpaque_F(1.0F, 1.0F, 1.0F);
            t.setNormal(0.0F, 1.0F, 0.0F);
            
            w = 0.0D;
            t.addVertexWithUV(dx(i), 1.0D - h, dz(i), (double)icon/*getMinU*/ + dx(i) * (double)wu, (double)icon/*getMaxV*/ - dz(i) * (double)wv);
            t.addVertexWithUV(dx(i + 1), 1.0F - h, dz(i + 1), (double)icon/*getMinU*/ + dx(i + 1) * (double)wu, (double)icon/*getMaxV*/ - dz(i + 1) * (double)wv);
            
            w = (double)(base_w * h) * 0.9D;
            t.addVertexWithUV(dx(i + 1), 1.0F - h, dz(i + 1), (double)icon/*getMinU*/ + dx(i + 1) * (double)wu, (double)icon/*getMaxV*/ - dz(i + 1) * (double)wv);
            t.addVertexWithUV(dx(i), 1.0F - h, dz(i), (double)icon/*getMinU*/ + dx(i) * (double)wu, (double)icon/*getMaxV*/ - dz(i) * (double)wv);
            t.draw();
        }
        
        // func_94206_g,getMinV,0,Returns the minimum V coordinate to use when rendering with this icon.
        // func_94209_e,getMinU,0,Returns the minimum U coordinate to use when rendering with this icon.
        // func_94210_h,getMaxV,0,Returns the maximum V coordinate to use when rendering with this icon.
        // func_94212_f,getMaxU,0,Returns the maximum U coordinate to use when rendering with this icon.
        
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
        return IndustryCore.drumRenderId;
    }
    
    public void setB(float i, float p, float r, float g, float b)
    {
        float brightness = (float)((double)p * (0.69999999999999996D + Math.cos(((((double)i + 0.5D) / 4.0D) * 2.0D + 1.0D) * Math.PI) * 0.10000000000000001D));
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
}
