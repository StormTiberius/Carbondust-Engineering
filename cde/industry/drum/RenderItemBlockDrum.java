/**
 *
 * @author StormTiberius
 */

package cde.industry.drum;

import cde.IndustryCore;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import org.lwjgl.opengl.GL11;

public class RenderItemBlockDrum implements IItemRenderer
{
    public RenderItemBlockDrum(){}    
    
    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        switch(type)
        {
            case ENTITY: return true;
            case EQUIPPED: return true;
            case INVENTORY: return true;
            case FIRST_PERSON_MAP: return true;
            default: return false;
        }
    }
    
    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return true;
    }
    
    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        if(item != null && item.itemID == IndustryCore.blockDrum.blockID)
        {
            Block block = Block.blocksList[item.itemID];
        
            if(block != null)
            {
                RenderBlocks renderer = (RenderBlocks)data[0];
                
                block.setBlockBoundsForItemRender();

                renderer.setRenderBoundsFromBlock(block);

                GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);

                switch(type)
                {
                    case EQUIPPED:
                    case FIRST_PERSON_MAP:
                        GL11.glTranslatef(-1.0F, 0.5F, 0.0F);
                        break;
                    default:
                        GL11.glTranslatef(-0.5F, -0.0F, -0.5F);
                        break;
                }

                RenderBlockDrum.drawInvBlock(block, item);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glTranslatef(0.5F, 0.0F, 0.5F);
            }
        }
    }
}
