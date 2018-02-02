/**
 *
 * @author StormTiberius
 */

package cde.core;

import cde.CDECore;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class RecipeManager
{
    public static void addRecipes()
    {
        // Blocks
        FurnaceRecipes.smelting().addSmelting(CDECore.oreBlock.blockID, 0, new ItemStack(CDECore.partsItem.itemID, 1, 0), 0.5F);
        FurnaceRecipes.smelting().addSmelting(CDECore.oreBlock.blockID, 1, new ItemStack(CDECore.partsItem.itemID, 1, 1), 0.5F);
        FurnaceRecipes.smelting().addSmelting(CDECore.oreBlock.blockID, 2, new ItemStack(CDECore.partsItem.itemID, 1, 2), 0.8F);
        FurnaceRecipes.smelting().addSmelting(CDECore.oreBlock.blockID, 3, new ItemStack(CDECore.partsItem.itemID, 1, 3), 0.8F);
        
        // Items
        FurnaceRecipes.smelting().addSmelting(CDECore.partsItem.itemID, 12, new ItemStack(Item.ingotIron.itemID, 1, 0), 0.7F);
        FurnaceRecipes.smelting().addSmelting(CDECore.partsItem.itemID, 13, new ItemStack(Item.ingotGold.itemID, 1, 0), 1.0F);
        
        FurnaceRecipes.smelting().addSmelting(CDECore.partsItem.itemID, 14, new ItemStack(CDECore.partsItem.itemID, 1, 0), 0.7F);
        FurnaceRecipes.smelting().addSmelting(CDECore.partsItem.itemID, 15, new ItemStack(CDECore.partsItem.itemID, 1, 1), 0.7F);
        FurnaceRecipes.smelting().addSmelting(CDECore.partsItem.itemID, 16, new ItemStack(CDECore.partsItem.itemID, 1, 2), 1.0F);
        FurnaceRecipes.smelting().addSmelting(CDECore.partsItem.itemID, 17, new ItemStack(CDECore.partsItem.itemID, 1, 3), 1.0F);
        
        FurnaceRecipes.smelting().addSmelting(CDECore.partsItem.itemID, 18, new ItemStack(CDECore.partsItem.itemID, 1, 4), 1.0F);
        FurnaceRecipes.smelting().addSmelting(CDECore.partsItem.itemID, 19, new ItemStack(CDECore.partsItem.itemID, 1, 5), 1.0F);
        
        // TODO AE SILICON FROM QUARTZ DUST
    }
}
