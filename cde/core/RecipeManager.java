/**
 *
 * @author StormTiberius
 */

package cde.core;

import cde.CDECore;
import cpw.mods.fml.common.registry.GameRegistry;
import ic2.api.Ic2Recipes;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.src.ModLoader;
import net.minecraftforge.oredict.OreDictionary;
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
            
            addCompressorRecipes();
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
    
    public static void addCompressorRecipes()
    {
        // ArrayList copper = OreDictionary.getOres("ingotCopper");
        // ArrayList tin = OreDictionary.getOres("ingotTin");
        ArrayList zinc = OreDictionary.getOres("ingotZinc");
        // ArrayList silver = OreDictionary.getOres("ingotSilver");
        ArrayList lead = OreDictionary.getOres("ingotLead");
        // ArrayList uranium = OreDictionary.getOres("ingotUranium");
        ArrayList bronze = OreDictionary.getOres("ingotBronze");
        ArrayList brass = OreDictionary.getOres("ingotBrass");
        ArrayList steel = OreDictionary.getOres("ingotSteel");
        ArrayList ruby = OreDictionary.getOres("gemRuby");
        ArrayList jade = OreDictionary.getOres("gemJade");
        ArrayList sapphire = OreDictionary.getOres("gemSapphire");
        
        // for(Object o : copper)
        // {
        //     ItemStack is = ((ItemStack)o).copy();
        //     
        //     Ic2Recipes.addCompressorRecipe(new ItemStack(is.itemID, 9, is.getItemDamage()), new ItemStack(CDECore.storageBlock.blockID, 1, 0));
        // }
        
        // for(Object o : tin)
        // {
        //     ItemStack is = (ItemStack)o;
        //     
        //     Ic2Recipes.addCompressorRecipe(new ItemStack(is.itemID, 9, is.getItemDamage()), new ItemStack(CDECore.storageBlock.blockID, 1, 1));
        // }
        
        for(Object o : zinc)
        {
            ItemStack is = ((ItemStack)o).copy();
            
            Ic2Recipes.addCompressorRecipe(new ItemStack(is.itemID, 9, is.getItemDamage()), new ItemStack(CDECore.storageBlock.blockID, 1, 2));
        }
        
        // for(Object o : silver)
        // {
        //     ItemStack is = ((ItemStack)o).copy();
        //     
        //     Ic2Recipes.addCompressorRecipe(new ItemStack(is.itemID, 9, is.getItemDamage()), new ItemStack(CDECore.storageBlock.blockID, 1, 3));
        // }
        
        for(Object o : lead)
        {
            ItemStack is = ((ItemStack)o).copy();
            
            Ic2Recipes.addCompressorRecipe(new ItemStack(is.itemID, 9, is.getItemDamage()), new ItemStack(CDECore.storageBlock.blockID, 1, 4));
        }
        
        // for(Object o : uranium)
        // {
        //     ItemStack is = ((ItemStack)o).copy();
        //     
        //     Ic2Recipes.addCompressorRecipe(new ItemStack(is.itemID, 9, is.getItemDamage()), new ItemStack(CDECore.storageBlock.blockID, 1, 5));
        // }
        
        for(Object o : bronze)
        {
            ItemStack is = ((ItemStack)o).copy();
            
            Ic2Recipes.addCompressorRecipe(new ItemStack(is.itemID, 9, is.getItemDamage()), new ItemStack(CDECore.storageBlock.blockID, 1, 6));
        }
        
        for(Object o : brass)
        {
            ItemStack is = ((ItemStack)o).copy();
            
            Ic2Recipes.addCompressorRecipe(new ItemStack(is.itemID, 9, is.getItemDamage()), new ItemStack(CDECore.storageBlock.blockID, 1, 7));
        }
        
        for(Object o : steel)
        {
            ItemStack is = ((ItemStack)o).copy();
            
            Ic2Recipes.addCompressorRecipe(new ItemStack(is.itemID, 9, is.getItemDamage()), new ItemStack(CDECore.storageBlock.blockID, 1, 8));
        }
        
        for(Object o : ruby)
        {
            ItemStack is = ((ItemStack)o).copy();
            
            Ic2Recipes.addCompressorRecipe(new ItemStack(is.itemID, 9, is.getItemDamage()), new ItemStack(CDECore.storageBlock.blockID, 1, 9));
        }
                
        for(Object o : jade)
        {
            ItemStack is = ((ItemStack)o).copy();
            
            Ic2Recipes.addCompressorRecipe(new ItemStack(is.itemID, 9, is.getItemDamage()), new ItemStack(CDECore.storageBlock.blockID, 1, 10));
        }
                        
        for(Object o : sapphire)
        {
            ItemStack is = ((ItemStack)o).copy();
            
            Ic2Recipes.addCompressorRecipe(new ItemStack(is.itemID, 9, is.getItemDamage()), new ItemStack(CDECore.storageBlock.blockID, 1, 11));
        }
    }
}
