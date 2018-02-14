/**
 *
 * @author StormTiberius
 */

package cde.core;

import cde.CDECore;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class RecipeManager
{
    public static void addRecipes()
    {
        // Blocks
        FurnaceRecipes.smelting().addSmelting(CDECore.oreBlock.blockID, 0, new ItemStack(CDECore.materialsItem.itemID, 1, 0), 0.5F);
        FurnaceRecipes.smelting().addSmelting(CDECore.oreBlock.blockID, 1, new ItemStack(CDECore.materialsItem.itemID, 1, 1), 0.5F);
        FurnaceRecipes.smelting().addSmelting(CDECore.oreBlock.blockID, 2, new ItemStack(CDECore.materialsItem.itemID, 1, 2), 0.8F);
        FurnaceRecipes.smelting().addSmelting(CDECore.oreBlock.blockID, 3, new ItemStack(CDECore.materialsItem.itemID, 1, 3), 0.8F);
        FurnaceRecipes.smelting().addSmelting(CDECore.oreBlock.blockID, 4, new ItemStack(CDECore.materialsItem.itemID, 1, 4), 0.8F);
        
        // Items
        FurnaceRecipes.smelting().addSmelting(CDECore.materialsItem.itemID, 13, new ItemStack(Item.ingotIron.itemID, 1, 0), 0.7F);
        FurnaceRecipes.smelting().addSmelting(CDECore.materialsItem.itemID, 14, new ItemStack(Item.ingotGold.itemID, 1, 0), 1.0F);
        
        FurnaceRecipes.smelting().addSmelting(CDECore.materialsItem.itemID, 15, new ItemStack(CDECore.materialsItem.itemID, 1, 0), 0.7F);
        FurnaceRecipes.smelting().addSmelting(CDECore.materialsItem.itemID, 16, new ItemStack(CDECore.materialsItem.itemID, 1, 1), 0.7F);
        FurnaceRecipes.smelting().addSmelting(CDECore.materialsItem.itemID, 17, new ItemStack(CDECore.materialsItem.itemID, 1, 2), 1.0F);
        FurnaceRecipes.smelting().addSmelting(CDECore.materialsItem.itemID, 18, new ItemStack(CDECore.materialsItem.itemID, 1, 3), 1.0F);
        FurnaceRecipes.smelting().addSmelting(CDECore.materialsItem.itemID, 19, new ItemStack(CDECore.materialsItem.itemID, 1, 4), 1.0F);
        
        FurnaceRecipes.smelting().addSmelting(CDECore.materialsItem.itemID, 20, new ItemStack(CDECore.materialsItem.itemID, 1, 6), 1.0F);
        FurnaceRecipes.smelting().addSmelting(CDECore.materialsItem.itemID, 21, new ItemStack(CDECore.materialsItem.itemID, 1, 7), 1.0F);
        
        // TODO AE SILICON FROM QUARTZ DUST
        
        int offSet = 0;
        
        for(int i = 0; i < 12; i++)
        {
            if(i > 8)
            {
                offSet = 27;
            }
            
            GameRegistry.addRecipe(new ItemStack(CDECore.storageBlock.blockID, 1, i), 
            "xxx",
            "xxx",
            "xxx",
            'x', new ItemStack(CDECore.materialsItem.itemID, 1, i + offSet));
        }
    }
}
