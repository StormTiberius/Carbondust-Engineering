/**
 *
 * @author StormTiberius
 */

package cde;

import cde.core.Defaults;
import cde.core.Namings;
import cde.core.Version;
import cde.industry.CommonProxy;
import cde.industry.drum.DrumModule;
import cde.industry.equipment.ItemGoggles;
import cde.industry.machine.MachineModule;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import ic2.api.Ic2Recipes;
import ic2.api.Items;
import java.io.File;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.Configuration;

@Mod(modid="CDE|Industry", name="Industry", version=Version.VERSION, dependencies = "required-after:Forge@[6.6.2.534,);required-after:CDE|Core")
@NetworkMod(clientSideRequired=true, serverSideRequired=true)
public class IndustryCore
{
    @Instance("CDE|Industry")
    public static IndustryCore instance;
    
    @SidedProxy(clientSide = "cde.industry.ClientProxy", serverSide = "cde.industry.CommonProxy")
    public static CommonProxy proxy;
    
    private static boolean drumModuleEnabled,machineModuleEnabled;
    private static Configuration cfg;
    
    private static int nightVisionGogglesId;
    private static boolean nightVisionGogglesCraftable;
    public static Item nightVisionGoggles;
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event)
    {
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/industry.cfg"));
        cfg.load();
        
        drumModuleEnabled = cfg.get(Configuration.CATEGORY_GENERAL, "drumModuleEnabled", true, "Enable/Disable Drum Module").getBoolean(true);
        machineModuleEnabled = cfg.get(Configuration.CATEGORY_GENERAL, "machineModuleEnabled", true, "Enable/Disable Machine Module").getBoolean(true);
        
        nightVisionGogglesId = cfg.get(Configuration.CATEGORY_ITEM, "nightVisionGogglesId", Defaults.ITEM_NIGHT_VISION_GOGGLES_ID).getInt() + CDECore.ID_SHIFT;
        nightVisionGogglesCraftable = cfg.get(Configuration.CATEGORY_GENERAL, "nightVisionGogglesCraftable", true, "Enable/Disable Crafting Recipe").getBoolean(true);
        
        cfg.save();
        
        if(drumModuleEnabled)
        {
            DrumModule.preInit(event);
        }
        
        if(machineModuleEnabled)
        {
            MachineModule.preInit(event);
        }
    }
    
    @Init
    public void init(FMLInitializationEvent event)
    {
        if(drumModuleEnabled)
        {
            DrumModule.init(event);
        }
        
        if(machineModuleEnabled)
        {
            MachineModule.init(event);
        }
        
        if(nightVisionGogglesId > 0 && machineModuleEnabled)
        {
            String textureFile = "";
            int spriteIndex = 0;
            int renderId = 0;
            
            if(ModLoader.isModLoaded("IC2") && FMLCommonHandler.instance().getSide().isClient())
            {
                ItemStack nvg = Items.getItem("nightvisionGoggles");
                
                if(nvg != null)
                {
                    spriteIndex = nvg.getIconIndex();
                    
                    Item item = nvg.getItem();

                    if(item instanceof ItemArmor)
                    {
                        textureFile = item.getTextureFile();
                        renderId = ((ItemArmor)item).renderIndex;
                    }
                }
            }
            
            nightVisionGoggles = new ItemGoggles(nightVisionGogglesId, spriteIndex, renderId, textureFile).setItemName("nightVisionGoggles").setCreativeTab(MachineModule.TAB_MACHINES);
            GameRegistry.registerItem(nightVisionGoggles, Namings.INTERNAL_GOGGLES_NAME);
            LanguageRegistry.addName(nightVisionGoggles, Namings.EXTERNAL_GOGGLES_NAME);
            
            ItemStack is = new ItemStack(nightVisionGoggles.itemID, 1, 0);
            
            cde.api.Items.nightVisionGoggles = is;
            
            if(nightVisionGogglesCraftable)
            {
                Ic2Recipes.addCraftingRecipe(is,
                "xyx",
                "aza",
                "bcb",
                'x', new ItemStack(Items.getItem("reactorHeatSwitchDiamond").itemID, 1, 0),
                'y', new ItemStack(Items.getItem("reBattery").itemID, 1, 0),
                'z', new ItemStack(Items.getItem("reinforcedGlass").itemID, 1, 0),
                'a', new ItemStack(Items.getItem("luminator").itemID, 1, 0),
                'b', new ItemStack(Items.getItem("rubber").itemID, 1, 0),
                'c', new ItemStack(Items.getItem("advancedCircuit").itemID, 1, 0));
            }
        }
        
        proxy.registerRenderers();
    }
    
    @PostInit
    public void postInit(FMLPostInitializationEvent event)
    {
        if(drumModuleEnabled)
        {
            DrumModule.postInit(event);
        }
        
        if(machineModuleEnabled)
        {
            MachineModule.postInit(event);
        }
    }
}
