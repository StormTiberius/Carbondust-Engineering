/**
 *
 * @author StormTiberius
 */

package cde;

import cde.core.Version;
import cde.resource.BlockMagmaticStone;
import cde.resource.BlockOilSand;
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
import static net.minecraft.block.Block.soundSandFootstep;
import static net.minecraft.block.Block.soundStoneFootstep;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;

@Mod(modid="CDE|Resource", name="Resource", version=Version.VERSION, dependencies = "required-after:Forge@[6.6.2.534,);required-after:CDE|Core")
@NetworkMod(clientSideRequired=true, serverSideRequired=true)
public class ResourceCore
{
    private static Configuration cfg;
    private static int resourceId,oilSandId,magmaticStoneId;
    public static Block resource,oilSand,magmaticStone;
    
    @PreInit
    public static void preInit(FMLPreInitializationEvent event) 
    {
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/resource.cfg"));
        cfg.load();
        resourceId = cfg.get(Configuration.CATEGORY_BLOCK, "resourceId", 183).getInt();
        oilSandId = cfg.get(Configuration.CATEGORY_BLOCK, "oilSandId", 184).getInt();
        magmaticStoneId = cfg.get(Configuration.CATEGORY_BLOCK, "magmaticStoneId", 185).getInt();
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
        }
        
        if(oilSandId > 0)
        {
            oilSand = new BlockOilSand(oilSandId).setHardness(0.5F).setStepSound(soundSandFootstep).setBlockName("oilSand").setCreativeTab(CDECore.TAB_CDE);
            GameRegistry.registerBlock(oilSand, "oilSand");
            LanguageRegistry.addName(oilSand, "Oil Sand");
            
            MinecraftForge.setBlockHarvestLevel(oilSand, "shovel", 0);
            
            if(Loader.isModLoaded("Forestry"))
            {
                RecipeManagers.squeezerManager.addRecipe(40, new ItemStack[]{new ItemStack(oilSand.blockID, 1, 0)}, LiquidDictionary.getLiquid("Oil", LiquidContainerRegistry.BUCKET_VOLUME), new ItemStack(Block.sand.blockID, 1, 0), 100);
            }
        }
                
        if(magmaticStoneId > 0)
        {
            magmaticStone = new BlockMagmaticStone(magmaticStoneId).setHardness(3.0F).setResistance(5.0F).setStepSound(soundStoneFootstep).setBlockName("magmaticStone").setCreativeTab(CDECore.TAB_CDE);
            GameRegistry.registerBlock(magmaticStone, "magmaticStone");
            LanguageRegistry.addName(magmaticStone, "Magmatic Stone");
            
            MinecraftForge.setBlockHarvestLevel(magmaticStone, "pickaxe", 1);
            
            if(Loader.isModLoaded("Forestry"))
            {
                RecipeManagers.squeezerManager.addRecipe(40, new ItemStack[]{new ItemStack(magmaticStone.blockID, 1, 0)}, LiquidDictionary.getLiquid("Lava", LiquidContainerRegistry.BUCKET_VOLUME), new ItemStack(Block.cobblestone.blockID, 1, 0), 100);
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
