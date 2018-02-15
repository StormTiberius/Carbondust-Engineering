/**
 *
 * @author StormTiberius
 */

package cde.core;

import cde.CDECore;
import cpw.mods.fml.common.registry.GameRegistry;
import ic2.api.Ic2Recipes;
import java.util.LinkedHashMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.src.ModLoader;
import railcraft.common.api.crafting.RailcraftCraftingManager;

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
        
        // Iron Nugget -> Steel Nugget
        FurnaceRecipes.smelting().addSmelting(CDECore.materialsItem.itemID, 27, new ItemStack(CDECore.materialsItem.itemID, 1, 35), 0.1F);
        
        GameRegistry.addShapelessRecipe(new ItemStack(CDECore.materialsItem.itemID, 2, 20), 
                new ItemStack(CDECore.materialsItem.itemID, 1, 15),
                new ItemStack(CDECore.materialsItem.itemID, 1, 15),
                new ItemStack(CDECore.materialsItem.itemID, 1, 15),
                new ItemStack(CDECore.materialsItem.itemID, 1, 16)
        );
        
        GameRegistry.addShapelessRecipe(new ItemStack(CDECore.materialsItem.itemID, 2, 20), 
                new ItemStack(CDECore.materialsItem.itemID, 1, 15),
                new ItemStack(CDECore.materialsItem.itemID, 1, 15),
                new ItemStack(CDECore.materialsItem.itemID, 1, 19),
                new ItemStack(CDECore.materialsItem.itemID, 1, 16)
        );
        
        GameRegistry.addShapelessRecipe(new ItemStack(CDECore.materialsItem.itemID, 2, 21), 
                new ItemStack(CDECore.materialsItem.itemID, 1, 15),
                new ItemStack(CDECore.materialsItem.itemID, 1, 15),
                new ItemStack(CDECore.materialsItem.itemID, 1, 15),
                new ItemStack(CDECore.materialsItem.itemID, 1, 17)
        );
                
        GameRegistry.addShapelessRecipe(new ItemStack(CDECore.materialsItem.itemID, 2, 21), 
                new ItemStack(CDECore.materialsItem.itemID, 1, 15),
                new ItemStack(CDECore.materialsItem.itemID, 1, 15),
                new ItemStack(CDECore.materialsItem.itemID, 1, 19),
                new ItemStack(CDECore.materialsItem.itemID, 1, 17)
        );
        
        GameRegistry.addShapelessRecipe(new ItemStack(CDECore.materialsItem.itemID, 9, 27), new ItemStack(Item.ingotIron.itemID, 1, 0));
        
        int output;
        
        for(int i = 0; i < 8; i++)
        {
            switch(i)
            {
                case 5:
                case 6:
                case 7: output = i + 1; break;
                default: output = i; break;
            }
            
            GameRegistry.addShapelessRecipe(new ItemStack(CDECore.materialsItem.itemID, 9, i + 28), new ItemStack(CDECore.materialsItem.itemID, 1, output));
        }
        
        GameRegistry.addRecipe(new ItemStack(Item.ingotIron.itemID, 1, 0), 
        "xxx",
        "xxx",
        "xxx",
        'x', new ItemStack(CDECore.materialsItem.itemID, 1, 27));
        
        for(int i = 0; i < 8; i++)
        {
            switch(i)
            {
                case 5:
                case 6:
                case 7: output = i + 1; break;
                default: output = i; break;
            }
            
            GameRegistry.addRecipe(new ItemStack(CDECore.materialsItem.itemID, 1, output), 
            "xxx",
            "xxx",
            "xxx",
            'x', new ItemStack(CDECore.materialsItem.itemID, 1, i + 28));
        }
        
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
            
            GameRegistry.addShapelessRecipe(new ItemStack(CDECore.materialsItem.itemID, 9, i + offSet), new ItemStack(CDECore.storageBlock.blockID, 1, i));
        }
        
        if(ModLoader.isModLoaded("IC2"))
        {
            Ic2Recipes.addMaceratorRecipe(new ItemStack(CDECore.oreBlock.blockID, 1, 2), new ItemStack(CDECore.materialsItem.itemID, 2, 17));
            Ic2Recipes.addMaceratorRecipe(new ItemStack(CDECore.oreBlock.blockID, 1, 4), new ItemStack(CDECore.materialsItem.itemID, 2, 19));
            
            Ic2Recipes.addMaceratorRecipe(new ItemStack(CDECore.materialsItem.itemID, 1, 2), new ItemStack(CDECore.materialsItem.itemID, 1, 17));
            Ic2Recipes.addMaceratorRecipe(new ItemStack(CDECore.materialsItem.itemID, 1, 4), new ItemStack(CDECore.materialsItem.itemID, 1, 19)); 
        }
        
        if(ModLoader.isModLoaded("Railcraft"))
        {
            LinkedHashMap zd = new LinkedHashMap();
            LinkedHashMap ld = new LinkedHashMap();
            
            zd.put(new ItemStack(CDECore.materialsItem.itemID, 2, 17), 1.0F);            
            ld.put(new ItemStack(CDECore.materialsItem.itemID, 2, 19), 1.0F);
            
            RailcraftCraftingManager.rockCrusher.addRecipe(new ItemStack(CDECore.oreBlock.blockID, 1, 2), zd);
            RailcraftCraftingManager.rockCrusher.addRecipe(new ItemStack(CDECore.oreBlock.blockID, 1, 4), ld);
            
            zd = new LinkedHashMap();
            ld = new LinkedHashMap();
            
            zd.put(new ItemStack(CDECore.materialsItem.itemID, 1, 17), 1.0F);
            ld.put(new ItemStack(CDECore.materialsItem.itemID, 1, 19), 1.0F);
            
            RailcraftCraftingManager.rockCrusher.addRecipe(new ItemStack(CDECore.materialsItem.itemID, 1, 2), zd);
            RailcraftCraftingManager.rockCrusher.addRecipe(new ItemStack(CDECore.materialsItem.itemID, 1, 4), ld);
        }
    }
}
