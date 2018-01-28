/**
 *
 * @author StormTiberius
 */

package cde;

import cde.energy.BlockGenerator;
import cde.energy.BlockGrate;
import cde.energy.BlockHeater;
import cde.energy.BlockMixer;
import cde.energy.BlockPump;
import cde.energy.BlockSolarPanel;
import cde.energy.BlockTransformer;
import cde.energy.BlockTurbine;
import cde.energy.ItemGoggles;
import cde.energy.TileEntityGenerator;
import cde.energy.TileEntityHeater;
import cde.energy.TileEntityMixer;
import cde.energy.TileEntityPump;
import cde.energy.TileEntitySolarPanel;
import cde.energy.TileEntityTransformer;
import cde.energy.TileEntityTurbine;
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
import ic2.api.Ic2Recipes;
import ic2.api.Items;
import java.io.File;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import railcraft.common.api.core.items.ItemRegistry;

@Mod(modid="CDE|Energy", name="Energy", version="1.0", dependencies = "required-after:Forge@[6.6.2.534,);required-after:CDE|Core")
@NetworkMod(clientSideRequired=true, serverSideRequired=true)
public class EnergyCore
{
    private static final int ID_SHIFT = 256;
    public static Item goggles;
    public static Block generator,turbine,heater,pump,mixer,solarPanel,transformer,grate;
    private static int generatorId,turbineId,heaterId,pumpId,mixerId,solarPanelId,transformerId,grateId,gogglesId;
    private static int ironGearId,goldGearId,diamondGearId,tankBlockId,batteryEmptyId,batteryFullId,indigoDyeId;
    public static int generatorVolume,heaterVolume,mixerVolume,pumpVolume,solarVolume,transformerVolume,turbineVolume;
    public static int generatorPitch,heaterPitch,mixerPitch,pumpPitch,solarPitch,transformerPitch,turbinePitch;
    private static Configuration cfg;
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) 
    {
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/energy.cfg"));
        cfg.load();
        
        generatorId = cfg.get(Configuration.CATEGORY_BLOCK, "generator", 183).getInt();
        turbineId = cfg.get(Configuration.CATEGORY_BLOCK, "turbine", 184).getInt();
        heaterId = cfg.get(Configuration.CATEGORY_BLOCK, "heater", 185).getInt();
        pumpId = cfg.get(Configuration.CATEGORY_BLOCK, "pump", 186).getInt();
        mixerId = cfg.get(Configuration.CATEGORY_BLOCK, "mixer", 187).getInt();
        solarPanelId = cfg.get(Configuration.CATEGORY_BLOCK, "solarpanel", 188).getInt();
        transformerId = cfg.get(Configuration.CATEGORY_BLOCK, "transformer", 189).getInt();
        grateId = cfg.get(Configuration.CATEGORY_BLOCK, "grate", 190).getInt();
        
        gogglesId = cfg.get(Configuration.CATEGORY_ITEM, "goggles", 500).getInt() - ID_SHIFT;
        
        // Individual Machine Sound Settings.
        
        int defaultVolume = 20;
        int defaultPitch = 100;
        
        // Volume
        generatorVolume = cfg.get(Configuration.CATEGORY_GENERAL, "generatorvolume", defaultVolume, "Generator Volume").getInt();
        heaterVolume = cfg.get(Configuration.CATEGORY_GENERAL, "heatervolume", defaultVolume, "Heater Volume").getInt();
        mixerVolume = cfg.get(Configuration.CATEGORY_GENERAL, "mixervolume", defaultVolume, "Mixer Volume").getInt();
        pumpVolume = cfg.get(Configuration.CATEGORY_GENERAL, "pumpvolume", defaultVolume, "Pump Volume").getInt();
        solarVolume = cfg.get(Configuration.CATEGORY_GENERAL, "solarvolume", 0, "Solar Volume").getInt();
        transformerVolume = cfg.get(Configuration.CATEGORY_GENERAL, "transformervolume", defaultVolume, "Transformer Volume").getInt();
        turbineVolume = cfg.get(Configuration.CATEGORY_GENERAL, "turbinevolume", defaultVolume, "Turbine Volume").getInt();
        
        // Pitch
        generatorPitch = cfg.get(Configuration.CATEGORY_GENERAL, "generatorpitch", defaultPitch, "Generator Pitch").getInt();
        heaterPitch = cfg.get(Configuration.CATEGORY_GENERAL, "heaterpitch", defaultPitch, "Heater Pitch").getInt();
        mixerPitch = cfg.get(Configuration.CATEGORY_GENERAL, "mixerpitch", defaultPitch, "Mixer Pitch").getInt();
        pumpPitch = cfg.get(Configuration.CATEGORY_GENERAL, "pumppitch", defaultPitch, "Pump Pitch").getInt();
        solarPitch = cfg.get(Configuration.CATEGORY_GENERAL, "solarpitch", defaultPitch, "Solar Pitch").getInt();
        transformerPitch = cfg.get(Configuration.CATEGORY_GENERAL, "transformerpitch", defaultPitch, "Transformer Pitch").getInt();
        turbinePitch = cfg.get(Configuration.CATEGORY_GENERAL, "turbinepitch", defaultPitch, "Turbine Pitch").getInt();
        
        cfg.save();
    }

    @Init
    public void load(FMLInitializationEvent event) 
    {   
        setupIds();
        
        if(gogglesId > 0)
        {
            goggles = new ItemGoggles(gogglesId);
            GameRegistry.registerItem(goggles, "goggles");
            LanguageRegistry.addName(goggles, "Nightvision Goggles");
            
            Ic2Recipes.addCraftingRecipe(new ItemStack(goggles.itemID, 1, 0),
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
        
        if(generatorId > 0 && ironGearId > 0)
        {
            generator = new BlockGenerator(generatorId).setBlockName("cdeGenerator").setCreativeTab(CreativeTabs.tabRedstone).setHardness(0.5F);
            GameRegistry.registerBlock(generator, "cdeGenerator");
            LanguageRegistry.addName(generator, "Generator");
            GameRegistry.registerTileEntity(TileEntityGenerator.class, "cdeGeneratorTile");
            
            Ic2Recipes.addCraftingRecipe(new ItemStack(generator.blockID, 1, 0),
            "xxx",
            "byb",
            "aza",
            'x', new ItemStack(ironGearId, 1, 0),
            'y', new ItemStack(Items.getItem("machine").itemID, 1, 0),
            'z', new ItemStack(Items.getItem("electronicCircuit").itemID, 1, 0),
            'a', new ItemStack(Items.getItem("insulatedCopperCableItem").itemID, 1, 0),
            'b', new ItemStack(Items.getItem("reBattery").itemID, 1, 0));
        }
        
        if(turbineId > 0)
        {
            turbine = new BlockTurbine(turbineId).setBlockName("cdeTurbine").setCreativeTab(CreativeTabs.tabRedstone).setHardness(0.5F);
            GameRegistry.registerBlock(turbine, "cdeTurbine");
            LanguageRegistry.addName(turbine, "Turbine");
            GameRegistry.registerTileEntity(TileEntityTurbine.class, "cdeTurbineTile");
            
            Ic2Recipes.addCraftingRecipe(new ItemStack(turbine.blockID, 1, 0),
            "xxx",
            "byb",
            "aza",
            'x', new ItemStack(ItemRegistry.getItem("part.turbine.disk", 1).itemID, 1, 0),
            'y', new ItemStack(Items.getItem("machine").itemID, 1, 0),
            'z', new ItemStack(Items.getItem("electronicCircuit").itemID, 1, 0),
            'a', new ItemStack(Items.getItem("insulatedCopperCableItem").itemID, 1, 0),
            'b', new ItemStack(Items.getItem("reBattery").itemID, 1, 0));
        }
        
        if(heaterId > 0)
        {
            heater = new BlockHeater(heaterId).setBlockName("cdeHeater").setCreativeTab(CreativeTabs.tabRedstone).setHardness(0.5F);
            GameRegistry.registerBlock(heater, "cdeHeater");
            LanguageRegistry.addName(heater, "Heater");
            GameRegistry.registerTileEntity(TileEntityHeater.class, "cdeHeaterTile");
            
            Ic2Recipes.addCraftingRecipe(new ItemStack(heater.blockID, 1, 0),
            "xxx",
            "byb",
            "aza",
            'x', new ItemStack(Item.blazeRod.itemID, 1, 0),
            'y', new ItemStack(Items.getItem("machine").itemID, 1, 0),
            'z', new ItemStack(Items.getItem("electronicCircuit").itemID, 1, 0),
            'a', new ItemStack(Items.getItem("insulatedCopperCableItem").itemID, 1, 0),
            'b', new ItemStack(Items.getItem("reBattery").itemID, 1, 0));
        }
        
        if(pumpId > 0 && grateId > 0 && tankBlockId > 0)
        {
            pump = new BlockPump(pumpId).setBlockName("cdePump").setCreativeTab(CreativeTabs.tabRedstone).setHardness(0.5F);
            GameRegistry.registerBlock(pump, "cdePump");
            LanguageRegistry.addName(pump, "Pump");
            GameRegistry.registerTileEntity(TileEntityPump.class, "cdePumpTile");
            
            Ic2Recipes.addCraftingRecipe(new ItemStack(pump.blockID, 1, 0),
            "xxx",
            "byb",
            "aza",
            'x', new ItemStack(tankBlockId, 1, 0),
            'y', new ItemStack(Items.getItem("machine").itemID, 1, 0),
            'z', new ItemStack(Items.getItem("electronicCircuit").itemID, 1, 0),
            'a', new ItemStack(Items.getItem("insulatedCopperCableItem").itemID, 1, 0),
            'b', new ItemStack(Items.getItem("reBattery").itemID, 1, 0));
        }
        
        if(mixerId > 0)
        {
            mixer = new BlockMixer(mixerId).setBlockName("cdeMixer").setCreativeTab(CreativeTabs.tabRedstone).setHardness(0.5F);
            GameRegistry.registerBlock(mixer, "cdeMixer");
            LanguageRegistry.addName(mixer, "Mixer");
            GameRegistry.registerTileEntity(TileEntityMixer.class, "cdeMixerTile");
            
            Ic2Recipes.addCraftingRecipe(new ItemStack(mixer.blockID, 1, 0),
            "xxx",
            "byb",
            "aza",
            'x', new ItemStack(Items.getItem("carbonPlate").itemID, 1, 0),
            'y', new ItemStack(Items.getItem("machine").itemID, 1, 0),
            'z', new ItemStack(Items.getItem("electronicCircuit").itemID, 1, 0),
            'a', new ItemStack(Items.getItem("insulatedCopperCableItem").itemID, 1, 0),
            'b', new ItemStack(Items.getItem("reBattery").itemID, 1, 0));
        }
        
        if(solarPanelId > 0)
        {
            solarPanel = new BlockSolarPanel(solarPanelId).setBlockName("cdeSolarPanel").setCreativeTab(CreativeTabs.tabRedstone).setHardness(0.5F);
            GameRegistry.registerBlock(solarPanel, "cdeSolarPanel");
            LanguageRegistry.addName(solarPanel, "Solar Panel");
            GameRegistry.registerTileEntity(TileEntitySolarPanel.class, "cdeSolarPanelTile");
            
            Ic2Recipes.addCraftingRecipe(new ItemStack(solarPanel.blockID, 1, 0),
            "aba",
            "bab",
            "cdc",
            'a', new ItemStack(Block.glass.blockID, 1, 0),
            'b', new ItemStack(Items.getItem("coalDust").itemID, 1, 0),
            'c', new ItemStack(Items.getItem("electronicCircuit").itemID, 1, 0),
            'd', new ItemStack(Items.getItem("generator").itemID, 1, 0));
        }
                
        if(transformerId > 0)
        {
            transformer = new BlockTransformer(transformerId).setBlockName("cdeTransformer").setCreativeTab(CreativeTabs.tabRedstone).setHardness(0.5F);
            GameRegistry.registerBlock(transformer, "cdeTransformer");
            LanguageRegistry.addName(transformer, "Transformer");
            GameRegistry.registerTileEntity(TileEntityTransformer.class, "cdeTransformerTile");
            
            Ic2Recipes.addCraftingRecipe(new ItemStack(transformer.blockID, 1, 0),
            "xxx",
            "byb",
            "aza",
            'x', new ItemStack(Items.getItem("tinCableItem").itemID, 1, 10),
            'y', new ItemStack(Items.getItem("machine").itemID, 1, 0),
            'z', new ItemStack(Items.getItem("electronicCircuit").itemID, 1, 0),
            'a', new ItemStack(Items.getItem("insulatedCopperCableItem").itemID, 1, 0),
            'b', new ItemStack(Items.getItem("reBattery").itemID, 1, 0));
        }
        
        if(grateId > 0)
        {
            grate = new BlockGrate(grateId).setBlockName("cdeGrate").setCreativeTab(CreativeTabs.tabRedstone).setHardness(0.5F);
            GameRegistry.registerBlock(grate, "cdeGrate");
            LanguageRegistry.addName(grate, "Grate");
            
            Ic2Recipes.addCraftingRecipe(new ItemStack(grate.blockID, 1, 0),
            "xxx",
            "x x",
            "xxx",
            'x', new ItemStack(Block.fenceIron.blockID, 1, 0));
        }
    }

    @PostInit
    public void postInit(FMLPostInitializationEvent event) 
    {
        setupItemIds();
    }

    @ServerStarting
    public void serverStarting(FMLServerStartingEvent event)
    {

    }
    
    private void setupIds()
    {
        for(Item item : CDECore.getItemsByClass("buildcraft.core.ItemBuildCraft"))
        {
            if(item.getItemName().equalsIgnoreCase("item.ironGearItem"))
            {
                ironGearId = item.itemID;
            }
            
            else if(item.getItemName().equalsIgnoreCase("item.goldGearItem"))
            {
                goldGearId = item.itemID;
            }
            
            else if(item.getItemName().equalsIgnoreCase("item.diamondGearItem"))
            {
                diamondGearId = item.itemID;
            }
        }
        
        Block block = CDECore.getBlockByClass("buildcraft.factory.BlockTank");
        
        if(block != null)
        {
            tankBlockId = block.blockID;
        }
    }
    
    private void setupItemIds()
    {
        for(Item item : CDECore.getItemsByClass("com.eloraam.redpower.core.ItemTextured"))
        {
            if(item != null && item.getItemName().equalsIgnoreCase("item.btbattery"))
            {
                batteryEmptyId = item.itemID;
            }
        }
        
        Item item = CDECore.getItemByClass("com.eloraam.redpower.machine.ItemBattery");
        if(item != null && item.getItemName().equalsIgnoreCase("item.btbattery"))
        {
            batteryFullId = item.itemID;
        }
        
        item = CDECore.getItemByClass("com.eloraam.redpower.base.ItemDyeIndigo");
        if(item != null)
        {
            indigoDyeId = item.itemID;
        }
    }
    
    public static int getItemId(int i)
    {
        switch(i)
        {
            case 0: return batteryEmptyId;
            case 1: return batteryFullId;
            case 2: return indigoDyeId;
            default: return 0;
        }
    }
}
