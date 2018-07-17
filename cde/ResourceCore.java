/**
 *
 * @author StormTiberius
 */

package cde;

import cde.core.Version;
import cde.resource.BlockResource;
import cde.resource.ResourceManager;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import forestry.api.recipes.RecipeManagers;
import java.io.File;
import net.minecraft.block.Block;
import static net.minecraft.block.Block.soundStoneFootstep;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.liquids.LiquidDictionary;

@Mod(modid="CDE|Resource", name="Resource", version=Version.VERSION, dependencies = "required-after:Forge@[6.6.2.534,);required-after:CDE|Core")
@NetworkMod(clientSideRequired=true, serverSideRequired=true)
public class ResourceCore
{
    private static Configuration cfg;
    private static int resourceId,lavaPerObsidian,oilPerSand,oilPerGravel;
    public static Block resource;
    
    @PreInit
    public static void preInit(FMLPreInitializationEvent event) 
    {
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/resource.cfg"));
        cfg.load();
        resourceId = cfg.get(Configuration.CATEGORY_BLOCK, "resource", 183).getInt();
        lavaPerObsidian = cfg.get(Configuration.CATEGORY_GENERAL, "lavaperobsidian", 1000, "Millibuckets of lava per obsidian block").getInt();
        oilPerSand = cfg.get(Configuration.CATEGORY_GENERAL, "oilpersand", 7, "Millibuckets of oil per sand block").getInt();
        oilPerGravel = cfg.get(Configuration.CATEGORY_GENERAL, "oilpergravel", 7, "Millibuckets of oil per gravel block").getInt();
        ResourceManager.cfgInit(cfg);
        cfg.save();
    }

    @Init
    public static void init(FMLInitializationEvent event) 
    {
        if(resourceId > 0)
        {
            resource = new BlockResource(resourceId).setBlockName("resourceBlock").setBlockUnbreakable().setResistance(6000000.0F).setStepSound(soundStoneFootstep).setCreativeTab(CDECore.TAB_CDE);
            GameRegistry.registerBlock(resource, "resourceBlock");
            LanguageRegistry.addName(resource, "Resource");
            
            if(Loader.isModLoaded("Forestry"))
            {
                RecipeManagers.squeezerManager.addRecipe(40, new ItemStack[]{new ItemStack(Block.obsidian.blockID, 1, 0)}, LiquidDictionary.getLiquid("Lava", lavaPerObsidian), new ItemStack(Block.cobblestone.blockID, 1, 0), 0);
                RecipeManagers.squeezerManager.addRecipe(40, new ItemStack[]{new ItemStack(Block.sand.blockID, 1, 0)}, LiquidDictionary.getLiquid("Oil", oilPerSand), new ItemStack(Block.cobblestone.blockID, 1, 0), 0);
                RecipeManagers.squeezerManager.addRecipe(40, new ItemStack[]{new ItemStack(Block.gravel.blockID, 1, 0)}, LiquidDictionary.getLiquid("Oil", oilPerGravel), new ItemStack(Block.cobblestone.blockID, 1, 0), 0);
            }
        }
    }
    
    @PostInit
    public void postInit(FMLPostInitializationEvent event) 
    {
        
    }

    @ServerStarting
    public void serverStarting(FMLServerStartingEvent event)
    {
        
    }
}
