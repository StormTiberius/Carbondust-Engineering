/**
 *
 * @author StormTiberius
 */

package cde.core.resource;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import forestry.api.recipes.RecipeManagers;
import java.io.File;
import net.minecraft.block.Block;
import static net.minecraft.block.Block.soundSandFootstep;
import static net.minecraft.block.Block.soundStoneFootstep;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.oredict.OreDictionary;

public class ResourceModule
{
    private static Configuration cfg;
    private static int resourceId,geoStoneId,oilSandId;
    public static Block resource,geoStone,oilSand;
    
    public static void preInit(FMLPreInitializationEvent event) 
    {
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/resource.cfg"));
        cfg.load();
        resourceId = cfg.get(Configuration.CATEGORY_BLOCK, "resource", 180).getInt();
        geoStoneId = cfg.get(Configuration.CATEGORY_BLOCK, "geostone", 181).getInt();
        oilSandId = cfg.get(Configuration.CATEGORY_BLOCK, "oilsand", 182).getInt();
        ResourceManager.cfgInit(cfg);
        cfg.save(); 
    }

    public static void load(FMLInitializationEvent event) 
    {   
        if(resourceId > 0)
        {
            resource = new BlockResource(resourceId).setBlockName("resourceBlock");
            GameRegistry.registerBlock(resource, "resourceBlock");
            LanguageRegistry.addName(resource, "Resource");
        }
        
        if(geoStoneId > 0)
        {
            geoStone = (new BlockOreGeoStone(geoStoneId, 1)).setHardness(3.0F).setResistance(5.0F).setStepSound(soundStoneFootstep).setBlockName("oreGeoStone");
            GameRegistry.registerBlock(geoStone, "oreGeoStone");
            LanguageRegistry.addName(geoStone, "Geostone");
            MinecraftForge.setBlockHarvestLevel(geoStone, "pickaxe", 1);
            OreDictionary.registerOre("geostone", geoStone);
            
            if(Loader.isModLoaded("Forestry"))
            {
                RecipeManagers.squeezerManager.addRecipe(40, new ItemStack[]{new ItemStack(geoStone.blockID, 1, 0)}, LiquidDictionary.getLiquid("Lava", LiquidContainerRegistry.BUCKET_VOLUME), new ItemStack(Block.cobblestone.blockID, 1, 0), 100);
            }
        }
        
        if(oilSandId > 0)
        {
            oilSand = (new BlockOreOilSand(oilSandId, 0)).setHardness(0.5F).setStepSound(soundSandFootstep).setBlockName("oreOilSand");
            GameRegistry.registerBlock(oilSand, "oreOilSand");
            LanguageRegistry.addName(oilSand, "Oilsand");
            MinecraftForge.setBlockHarvestLevel(oilSand, "shovel", 0);
            OreDictionary.registerOre("oilsand", oilSand);
            
            if(Loader.isModLoaded("Forestry"))
            {
                RecipeManagers.squeezerManager.addRecipe(40, new ItemStack[]{new ItemStack(oilSand.blockID, 1, 0)}, LiquidDictionary.getLiquid("Oil", LiquidContainerRegistry.BUCKET_VOLUME), new ItemStack(Block.sand.blockID, 1, 0), 100);
            }
        }
    }
}
