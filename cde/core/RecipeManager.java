/**
 *
 * @author StormTiberius
 */

package cde.core;

import cde.api.Blocks;
import cde.api.Materials;
import cde.core.util.Utils;
import cpw.mods.fml.common.registry.GameRegistry;
import ic2.api.Ic2Recipes;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.src.ModLoader;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import railcraft.common.api.crafting.RailcraftCraftingManager;

public class RecipeManager
{
    public static void addRecipes()
    {
        // Blocks
        FurnaceRecipes.smelting().addSmelting(Blocks.oreCopper.itemID, Blocks.oreCopper.getItemDamage(), Utils.getNewItemStackWithQuantity(Materials.ingotCopper, 1), 0.5F);
        FurnaceRecipes.smelting().addSmelting(Blocks.oreTin.itemID, Blocks.oreTin.getItemDamage(), Utils.getNewItemStackWithQuantity(Materials.ingotTin, 1), 0.5F);
        FurnaceRecipes.smelting().addSmelting(Blocks.oreSilver.itemID, Blocks.oreSilver.getItemDamage(), Utils.getNewItemStackWithQuantity(Materials.ingotSilver, 1), 0.8F);
        FurnaceRecipes.smelting().addSmelting(Blocks.oreLead.itemID, Blocks.oreLead.getItemDamage(), Utils.getNewItemStackWithQuantity(Materials.ingotLead, 1), 0.8F);
        // FurnaceRecipes.smelting().addSmelting(Blocks.oreUranium.itemID, Blocks.oreUranium.getItemDamage(), Utils.getNewItemStackWithQuantity(Materials.ingotUranium, 1), 0.8F);
        
        // Items
        FurnaceRecipes.smelting().addSmelting(Materials.dustIron.itemID, Materials.dustIron.getItemDamage(), new ItemStack(Item.ingotIron.itemID, 1, 0), 0.7F);
        FurnaceRecipes.smelting().addSmelting(Materials.dustGold.itemID, Materials.dustGold.getItemDamage(), new ItemStack(Item.ingotGold.itemID, 1, 0), 1.0F);
        
        FurnaceRecipes.smelting().addSmelting(Materials.dustCopper.itemID, Materials.dustCopper.getItemDamage(), Utils.getNewItemStackWithQuantity(Materials.ingotCopper, 1), 0.7F);
        FurnaceRecipes.smelting().addSmelting(Materials.dustTin.itemID, Materials.dustTin.getItemDamage(), Utils.getNewItemStackWithQuantity(Materials.ingotTin, 1), 0.7F);
        FurnaceRecipes.smelting().addSmelting(Materials.dustSilver.itemID, Materials.dustSilver.getItemDamage(), Utils.getNewItemStackWithQuantity(Materials.ingotSilver, 1), 1.0F);
        FurnaceRecipes.smelting().addSmelting(Materials.dustLead.itemID, Materials.dustLead.getItemDamage(), Utils.getNewItemStackWithQuantity(Materials.ingotLead, 1), 1.0F);
        
        FurnaceRecipes.smelting().addSmelting(Materials.dustZinc.itemID, Materials.dustZinc.getItemDamage(), Utils.getNewItemStackWithQuantity(Materials.ingotZinc, 1), 1.0F);
        
        FurnaceRecipes.smelting().addSmelting(Materials.dustBronze.itemID, Materials.dustBronze.getItemDamage(), Utils.getNewItemStackWithQuantity(Materials.ingotBronze, 1), 1.0F);
        FurnaceRecipes.smelting().addSmelting(Materials.dustBrass.itemID, Materials.dustBrass.getItemDamage(), Utils.getNewItemStackWithQuantity(Materials.ingotBrass, 1), 1.0F);
        
        // Iron Nugget -> Steel Nugget
        FurnaceRecipes.smelting().addSmelting(Materials.nuggetIron.itemID, Materials.nuggetIron.getItemDamage(), Utils.getNewItemStackWithQuantity(Materials.nuggetSteel, 1), 0.1F);
        
        GameRegistry.addRecipe(new ShapelessOreRecipe(Utils.getNewItemStackWithQuantity(Materials.dustBronze, 2), "dustCopper", "dustCopper", "dustCopper", "dustTin"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Utils.getNewItemStackWithQuantity(Materials.dustBronze, 2), "dustCopper", "dustCopper", "dustLead", "dustTin"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Utils.getNewItemStackWithQuantity(Materials.dustBrass, 2), "dustCopper", "dustCopper", "dustCopper", "dustZinc"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Utils.getNewItemStackWithQuantity(Materials.dustBrass, 2), "dustCopper", "dustCopper", "dustLead", "dustZinc"));
        
        // Nugget Recipes
        GameRegistry.addShapelessRecipe(Utils.getNewItemStackWithQuantity(Materials.nuggetIron, 9), new ItemStack(Item.ingotIron.itemID, 1, 0));        
        GameRegistry.addShapelessRecipe(Utils.getNewItemStackWithQuantity(Materials.nuggetCopper, 9), Utils.getNewItemStackWithQuantity(Materials.ingotCopper, 1));
        GameRegistry.addShapelessRecipe(Utils.getNewItemStackWithQuantity(Materials.nuggetTin, 9), Utils.getNewItemStackWithQuantity(Materials.ingotTin, 1));
        GameRegistry.addShapelessRecipe(Utils.getNewItemStackWithQuantity(Materials.nuggetSilver, 9), Utils.getNewItemStackWithQuantity(Materials.ingotSilver, 1));
        GameRegistry.addShapelessRecipe(Utils.getNewItemStackWithQuantity(Materials.nuggetLead, 9), Utils.getNewItemStackWithQuantity(Materials.ingotLead, 1));
        GameRegistry.addShapelessRecipe(Utils.getNewItemStackWithQuantity(Materials.nuggetZinc, 9), Utils.getNewItemStackWithQuantity(Materials.ingotZinc, 1));
        GameRegistry.addShapelessRecipe(Utils.getNewItemStackWithQuantity(Materials.nuggetBronze, 9), Utils.getNewItemStackWithQuantity(Materials.ingotBronze, 1));
        GameRegistry.addShapelessRecipe(Utils.getNewItemStackWithQuantity(Materials.nuggetBrass, 9), Utils.getNewItemStackWithQuantity(Materials.ingotBrass, 1));
        GameRegistry.addShapelessRecipe(Utils.getNewItemStackWithQuantity(Materials.nuggetSteel, 9), Utils.getNewItemStackWithQuantity(Materials.ingotSteel, 1));

        // Nugget Reverse Recipes
        GameRegistry.addRecipe(new ItemStack(Item.ingotIron.itemID, 1, 0), 
        "xxx",
        "xxx",
        "xxx",
        'x', Utils.getNewItemStackWithQuantity(Materials.nuggetIron, 1));
        
        GameRegistry.addRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotCopper, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', Utils.getNewItemStackWithQuantity(Materials.nuggetCopper, 1));
        
        GameRegistry.addRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotTin, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', Utils.getNewItemStackWithQuantity(Materials.nuggetTin, 1));
        
        GameRegistry.addRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotSilver, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', Utils.getNewItemStackWithQuantity(Materials.nuggetSilver, 1));
        
        GameRegistry.addRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotLead, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', Utils.getNewItemStackWithQuantity(Materials.nuggetLead, 1));
        
        GameRegistry.addRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotZinc, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', Utils.getNewItemStackWithQuantity(Materials.nuggetZinc, 1));
        
        GameRegistry.addRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotBronze, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', Utils.getNewItemStackWithQuantity(Materials.nuggetBronze, 1));
        
        GameRegistry.addRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotBrass, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', Utils.getNewItemStackWithQuantity(Materials.nuggetBrass, 1));
        
        GameRegistry.addRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotSteel, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', Utils.getNewItemStackWithQuantity(Materials.nuggetSteel, 1));
        
        // Storage Block Recipes
        GameRegistry.addRecipe(Utils.getNewItemStackWithQuantity(Blocks.storageCopper, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', Utils.getNewItemStackWithQuantity(Materials.ingotCopper, 1));

        GameRegistry.addRecipe(Utils.getNewItemStackWithQuantity(Blocks.storageTin, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', Utils.getNewItemStackWithQuantity(Materials.ingotTin, 1));
        
        GameRegistry.addRecipe(Utils.getNewItemStackWithQuantity(Blocks.storageSilver, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', Utils.getNewItemStackWithQuantity(Materials.ingotSilver, 1));
        
        GameRegistry.addRecipe(Utils.getNewItemStackWithQuantity(Blocks.storageLead, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', Utils.getNewItemStackWithQuantity(Materials.ingotLead, 1));
        
        GameRegistry.addRecipe(Utils.getNewItemStackWithQuantity(Blocks.storageUranium, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', Utils.getNewItemStackWithQuantity(Materials.ingotUranium, 1));
        
        GameRegistry.addRecipe(Utils.getNewItemStackWithQuantity(Blocks.storageZinc, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', Utils.getNewItemStackWithQuantity(Materials.ingotZinc, 1));      
        
        GameRegistry.addRecipe(Utils.getNewItemStackWithQuantity(Blocks.storageBronze, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', Utils.getNewItemStackWithQuantity(Materials.ingotBronze, 1));
        
        GameRegistry.addRecipe(Utils.getNewItemStackWithQuantity(Blocks.storageBrass, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', Utils.getNewItemStackWithQuantity(Materials.ingotBrass, 1));
        
        GameRegistry.addRecipe(Utils.getNewItemStackWithQuantity(Blocks.storageSteel, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', Utils.getNewItemStackWithQuantity(Materials.ingotSteel, 1));
        
        GameRegistry.addRecipe(Utils.getNewItemStackWithQuantity(Blocks.storageRuby, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', Utils.getNewItemStackWithQuantity(Materials.gemRuby, 1));
                
        GameRegistry.addRecipe(Utils.getNewItemStackWithQuantity(Blocks.storageJade, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', Utils.getNewItemStackWithQuantity(Materials.gemJade, 1));
                        
        GameRegistry.addRecipe(Utils.getNewItemStackWithQuantity(Blocks.storageSapphire, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', Utils.getNewItemStackWithQuantity(Materials.gemSapphire, 1));
        
        // Reverse Storage Block Recipes
        GameRegistry.addShapelessRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotCopper, 9), Utils.getNewItemStackWithQuantity(Blocks.storageCopper, 1));
        GameRegistry.addShapelessRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotTin, 9), Utils.getNewItemStackWithQuantity(Blocks.storageTin, 1));
        GameRegistry.addShapelessRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotSilver, 9), Utils.getNewItemStackWithQuantity(Blocks.storageSilver, 1));
        GameRegistry.addShapelessRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotLead, 9), Utils.getNewItemStackWithQuantity(Blocks.storageLead, 1));
        GameRegistry.addShapelessRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotUranium, 9), Utils.getNewItemStackWithQuantity(Blocks.storageUranium, 1));
        GameRegistry.addShapelessRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotZinc, 9), Utils.getNewItemStackWithQuantity(Blocks.storageZinc, 1));
        GameRegistry.addShapelessRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotBronze, 9), Utils.getNewItemStackWithQuantity(Blocks.storageBronze, 1));
        GameRegistry.addShapelessRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotBrass, 9), Utils.getNewItemStackWithQuantity(Blocks.storageBrass, 1));
        GameRegistry.addShapelessRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotSteel, 9), Utils.getNewItemStackWithQuantity(Blocks.storageSteel, 1));
        GameRegistry.addShapelessRecipe(Utils.getNewItemStackWithQuantity(Materials.gemRuby, 9), Utils.getNewItemStackWithQuantity(Blocks.storageRuby, 1));
        GameRegistry.addShapelessRecipe(Utils.getNewItemStackWithQuantity(Materials.gemJade, 9), Utils.getNewItemStackWithQuantity(Blocks.storageJade, 1));
        GameRegistry.addShapelessRecipe(Utils.getNewItemStackWithQuantity(Materials.gemSapphire, 9), Utils.getNewItemStackWithQuantity(Blocks.storageSapphire, 1));
        
        
        if(ModLoader.isModLoaded("IC2"))
        {
            Ic2Recipes.addMaceratorRecipe(Utils.getNewItemStackWithQuantity(Blocks.oreLead, 1), Utils.getNewItemStackWithQuantity(Materials.dustLead, 2));
            Ic2Recipes.addMaceratorRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotLead, 1), Utils.getNewItemStackWithQuantity(Materials.dustLead, 1));
            Ic2Recipes.addMaceratorRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotZinc, 1), Utils.getNewItemStackWithQuantity(Materials.dustZinc, 1));
            
            addCompressorRecipes();
            addAERecipes();
        }
        
        if(ModLoader.isModLoaded("Railcraft"))
        {
            LinkedHashMap output = new LinkedHashMap();
            
            output.put(Utils.getNewItemStackWithQuantity(Materials.dustLead, 2), 1.0F);
            output.put(Utils.getNewItemStackWithQuantity(Materials.dustZinc, 1), 0.25F);
            
            RailcraftCraftingManager.rockCrusher.addRecipe(Utils.getNewItemStackWithQuantity(Blocks.oreLead, 1), output);
        }
    }
    
    private static void addAERecipes()
    {
        if(ModLoader.isModLoaded("AppliedEnergistics") && appeng.api.Materials.matSilicon != null)
        {
            FurnaceRecipes.smelting().addSmelting(Materials.dustQuartz.itemID, Materials.dustQuartz.getItemDamage(), Utils.getNewItemStackWithQuantity(appeng.api.Materials.matSilicon, 1), 0.2F);
        
            Ic2Recipes.addCompressorRecipe(Utils.getNewItemStackWithQuantity(Materials.dustQuartz, 4), Utils.getNewItemStackWithQuantity(Materials.gemQuartz, 1));

            Ic2Recipes.addMaceratorRecipe(Utils.getNewItemStackWithQuantity(Materials.gemQuartz, 1), Utils.getNewItemStackWithQuantity(Materials.dustQuartz, 4));
            Ic2Recipes.addMaceratorRecipe(Utils.getNewItemStackWithQuantity(Blocks.oreQuartz, 1), Utils.getNewItemStackWithQuantity(Materials.dustQuartz, 10));
        }
    }
    
    private static void addCompressorRecipes()
    {
        // ArrayList copper = OreDictionary.getOres("ingotCopper");
        // ArrayList tin = OreDictionary.getOres("ingotTin");
        // ArrayList silver = OreDictionary.getOres("ingotSilver");
        ArrayList lead = OreDictionary.getOres("ingotLead");
        // ArrayList uranium = OreDictionary.getOres("ingotUranium");
        ArrayList zinc = OreDictionary.getOres("ingotZinc");
        ArrayList bronze = OreDictionary.getOres("ingotBronze");
        ArrayList brass = OreDictionary.getOres("ingotBrass");
        ArrayList steel = OreDictionary.getOres("ingotSteel");
        ArrayList ruby = OreDictionary.getOres("gemRuby");
        ArrayList jade = OreDictionary.getOres("gemJade");
        ArrayList sapphire = OreDictionary.getOres("gemSapphire");
        
        // for(Object o : copper)
        // {
        //     Ic2Recipes.addCompressorRecipe(Utils.getNewItemStackWithQuantity((ItemStack)o, 9), Utils.getNewItemStackWithQuantity(Blocks.storageCopper, 1));
        // }
        
        // for(Object o : tin)
        // {
        //     Ic2Recipes.addCompressorRecipe(Utils.getNewItemStackWithQuantity((ItemStack)o, 9), Utils.getNewItemStackWithQuantity(Blocks.storageTin, 1));
        // }
                
        // for(Object o : silver)
        // {
        //     Ic2Recipes.addCompressorRecipe(Utils.getNewItemStackWithQuantity((ItemStack)o, 9), Utils.getNewItemStackWithQuantity(Blocks.storageSilver, 1));
        // }
        
        for(Object o : lead)
        {
            Ic2Recipes.addCompressorRecipe(Utils.getNewItemStackWithQuantity((ItemStack)o, 9), Utils.getNewItemStackWithQuantity(Blocks.storageLead, 1));
        }
                
        // for(Object o : uranium)
        // {
        //     Ic2Recipes.addCompressorRecipe(Utils.getNewItemStackWithQuantity((ItemStack)o, 9), Utils.getNewItemStackWithQuantity(Blocks.storageUranium, 1));
        // }
        
        for(Object o : zinc)
        {
            Ic2Recipes.addCompressorRecipe(Utils.getNewItemStackWithQuantity((ItemStack)o, 9), Utils.getNewItemStackWithQuantity(Blocks.storageZinc, 1));
        }
                                
        for(Object o : bronze)
        {
            Ic2Recipes.addCompressorRecipe(Utils.getNewItemStackWithQuantity((ItemStack)o, 9), Utils.getNewItemStackWithQuantity(Blocks.storageBronze, 1));
        }
                                        
        for(Object o : brass)
        {
            Ic2Recipes.addCompressorRecipe(Utils.getNewItemStackWithQuantity((ItemStack)o, 9), Utils.getNewItemStackWithQuantity(Blocks.storageBrass, 1));
        }
                                                
        for(Object o : steel)
        {
            Ic2Recipes.addCompressorRecipe(Utils.getNewItemStackWithQuantity((ItemStack)o, 9), Utils.getNewItemStackWithQuantity(Blocks.storageSteel, 1));
        }
                                                        
        for(Object o : ruby)
        {
            Ic2Recipes.addCompressorRecipe(Utils.getNewItemStackWithQuantity((ItemStack)o, 9), Utils.getNewItemStackWithQuantity(Blocks.storageRuby, 1));
        }
                                                                
        for(Object o : jade)
        {
            Ic2Recipes.addCompressorRecipe(Utils.getNewItemStackWithQuantity((ItemStack)o, 9), Utils.getNewItemStackWithQuantity(Blocks.storageJade, 1));
        }
        
        for(Object o : sapphire)
        {
            Ic2Recipes.addCompressorRecipe(Utils.getNewItemStackWithQuantity((ItemStack)o, 9), Utils.getNewItemStackWithQuantity(Blocks.storageSapphire, 1));
        }
    }
}
