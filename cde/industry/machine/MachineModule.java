/**
 *
 * @author StormTiberius
 */

package cde.industry.machine;

import cde.CDECore;
import cde.api.Blocks;
import cde.api.Materials;
import cde.core.Defaults;
import cde.core.Namings;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import ic2.api.Items;
import java.io.File;
import java.util.Arrays;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.ShapedOreRecipe;
import railcraft.common.api.core.items.ItemRegistry;

public class MachineModule
{
    public static final CreativeTabs TAB_MACHINES = new CreativeTabMachines("industrymachines");
    
    private static final int ENTRIES = 35;
    
    private static boolean[] craftable = new boolean[ENTRIES];
    private static boolean[] sound = new boolean[ENTRIES];
    private static int[] volume = new int[ENTRIES];
    private static int[] pitch = new int[ENTRIES];
    
    private static int ironGearId,tankBlockId,batteryEmptyId,batteryFullId;
    private static int blockMachineAlphaId,blockMachineBetaId,blockMachineGammaId,blockMachineDeltaId;
    private static Configuration cfg;
    
    public static Block blockMachineAlpha,blockMachineBeta,blockMachineGamma,blockMachineDelta;
    
    public static void preInit(FMLPreInitializationEvent event) 
    {
        Arrays.fill(volume, 100);
        Arrays.fill(pitch, 100);
        
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/machine.cfg"));
        cfg.load();
        
        blockMachineAlphaId = cfg.get(Configuration.CATEGORY_BLOCK, "blockMachineAlphaId", Defaults.BLOCK_INDUSTRY_ALPHA_ID).getInt();
        blockMachineBetaId = cfg.get(Configuration.CATEGORY_BLOCK, "blockMachineBetaId", Defaults.BLOCK_INDUSTRY_BETA_ID).getInt();
        blockMachineGammaId = cfg.get(Configuration.CATEGORY_BLOCK, "blockMachineGammaId", Defaults.BLOCK_INDUSTRY_GAMMA_ID).getInt();
        blockMachineDeltaId = cfg.get(Configuration.CATEGORY_BLOCK, "blockMachineDeltaId", Defaults.BLOCK_INDUSTRY_DELTA_ID).getInt();
        
        craftable = cfg.get(Configuration.CATEGORY_GENERAL, "craftable", craftable, "Enable/Disable Recipe").getBooleanList();
        sound = cfg.get(Configuration.CATEGORY_GENERAL, "sound", sound, "Enable/Disable Sound").getBooleanList();
        volume = cfg.get(Configuration.CATEGORY_GENERAL, "volume", volume, "Volume Percent").getIntList();
        pitch = cfg.get(Configuration.CATEGORY_GENERAL, "pitch", pitch, "Pitch Percent").getIntList();
        
        cfg.save();
    }
    
    public static void init(FMLInitializationEvent event) 
    {
        ItemStack is;
        
        if(blockMachineAlphaId > 0)
        {
            blockMachineAlpha = new BlockMachineAlpha(blockMachineAlphaId).setBlockName("cdeIndustryMachineAlpha").setCreativeTab(TAB_MACHINES).setHardness(3.5F);
            
            GameRegistry.registerBlock(blockMachineAlpha, ItemBlockAlpha.class, "blockMachineAlpha");
            MinecraftForge.setBlockHarvestLevel(blockMachineAlpha, "pickaxe", 1);
            
            // Iron Furnace
            is = new ItemStack(blockMachineAlpha.blockID, 1, 0);
            
            cde.api.Blocks.machineIronFurnace = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MACHINE_ALPHA_BLOCK_NAMES[0]);
            GameRegistry.registerTileEntity(TileEntityFurnace.class, "cdeMachineFurnace");
            
            if(craftable[0])
            {
                GameRegistry.addRecipe(is,
                "xxx",
                "x x",
                "xxx",
                'x', new ItemStack(Item.ingotIron.itemID, 1, 0));
                
                GameRegistry.addRecipe(is,
                " x ",
                "x x",
                "xzx",
                'x', new ItemStack(Item.ingotIron.itemID, 1, 0),
                'z', new ItemStack(Block.stoneOvenIdle.blockID, 1, 0));
            }
            
            // Electric Furnace
            is = new ItemStack(blockMachineAlpha.blockID, 1, 1);
            
            cde.api.Blocks.machineElectricFurnace = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MACHINE_ALPHA_BLOCK_NAMES[1]);
            // GameRegistry.registerTileEntity(TileEntityMachineFurnace.class, "cdeMachineFurnace");
            
            if(craftable[1])
            {
                GameRegistry.addRecipe(is,
                " x ",
                "zyz",
                "   ",
                'x', Materials.circuitBoardSingle.copy(),
                'y', Blocks.machineIronFurnace.copy(),
                'z', new ItemStack(Item.redstone.itemID, 1, 0));
            }
            
            // Induction Furnace
            is = new ItemStack(blockMachineAlpha.blockID, 1, 2);
            
            cde.api.Blocks.machineInductionFurnace = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MACHINE_ALPHA_BLOCK_NAMES[2]);
            // GameRegistry.registerTileEntity(TileEntityMachineFurnace.class, "cdeMachineFurnace");
            
            if(craftable[2])
            {
                GameRegistry.addRecipe(new ShapedOreRecipe(is,
                "aaa",
                "aba",
                "aca",
                'a', "ingotCopper",
                'b', Blocks.machineElectricFurnace.copy(),
                'c', new ItemStack(Items.getItem("advancedMachine").itemID, 1, 0)));
            }
            
            // Macerator
            is = new ItemStack(blockMachineAlpha.blockID, 1, 3);
            
            cde.api.Blocks.machineMacerator = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MACHINE_ALPHA_BLOCK_NAMES[3]);
            GameRegistry.registerTileEntity(TileEntityMacerator.class, "cdeMacerator");
            
            if(craftable[3])
            {
                GameRegistry.addRecipe(new ShapedOreRecipe(is,
                "aaa",
                "bcb",
                " d ",
                'a', new ItemStack(Item.flint.itemID, 1, 0),
                'b', new ItemStack(Block.cobblestone.blockID, 1, 0),
                'c', new ItemStack(Items.getItem("machine").itemID, 1, 0),
                'd', Materials.circuitBoardSingle.copy()));
            }
            
            // Compressor
            is = new ItemStack(blockMachineAlpha.blockID, 1, 4);
            
            cde.api.Blocks.machineCompressor = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MACHINE_ALPHA_BLOCK_NAMES[4]);
            GameRegistry.registerTileEntity(TileEntityCompressor.class, "cdeCompressor");
            
            if(craftable[4])
            {
                GameRegistry.addRecipe(is,
                "a a",
                "aba",
                "aca",
                'a', new ItemStack(Block.stone.blockID, 1, 0),
                'b', new ItemStack(Items.getItem("machine").itemID, 1, 0),
                'c', Materials.circuitBoardSingle.copy());
            }
            
            // Extractor
            is = new ItemStack(blockMachineAlpha.blockID, 1, 5);
            
            cde.api.Blocks.machineExtractor = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MACHINE_ALPHA_BLOCK_NAMES[5]);
            GameRegistry.registerTileEntity(TileEntityExtractor.class, "cdeExtractor");
            
            if(craftable[5])
            {
                GameRegistry.addRecipe(is,
                "aba",
                "aca",
                "   ",
                'a', new ItemStack(Items.getItem("treetap").itemID, 1, 0),
                'b', new ItemStack(Items.getItem("machine").itemID, 1, 0),
                'c', Materials.circuitBoardSingle.copy());
            }
            
            // Canning Machine
            is = new ItemStack(blockMachineAlpha.blockID, 1, 6);
            
            cde.api.Blocks.machineCanningMachine = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MACHINE_ALPHA_BLOCK_NAMES[6]);
            GameRegistry.registerTileEntity(TileEntityCanningMachine.class, "cdeCanningMachine");
            
            if(craftable[6])
            {
                GameRegistry.addRecipe(new ShapedOreRecipe(is,
                "aba",
                "aca",
                "aaa",
                'a', "ingotTin",
                'b', Materials.circuitBoardSingle.copy(),
                'c', new ItemStack(Items.getItem("machine").itemID, 1, 0)));
            }
            
            // Rolling Machine
            is = new ItemStack(blockMachineAlpha.blockID, 1, 7);
            
            cde.api.Blocks.machineRollingMachine = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MACHINE_ALPHA_BLOCK_NAMES[7]);
            GameRegistry.registerTileEntity(TileEntityRollingMachine.class, "cdeRollingMachine");
            
            if(craftable[7])
            {
                GameRegistry.addRecipe(new ShapedOreRecipe(is,
                "aba",
                "bcb",
                "aba",
                'a', "ingotSteel",
                'b', new ItemStack(Block.pistonBase.blockID, 1, 0),
                'c', new ItemStack(Block.workbench.blockID, 1, 0)));
            }
            
            // Assembling Machine
            is = new ItemStack(blockMachineAlpha.blockID, 1, 8);
            
            cde.api.Blocks.machineAssemblingMachine = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MACHINE_ALPHA_BLOCK_NAMES[8]);
            GameRegistry.registerTileEntity(TileEntityAssemblingMachine.class, "cdeAssemblingMachine");
            
            if(craftable[8])
            {
                GameRegistry.addRecipe(new ShapedOreRecipe(is,
                "dad",
                "ebe",
                "ece",
                'a', Materials.circuitBoardMulti.copy(),
                'b', new ItemStack(Block.workbench.blockID, 1, 0),
                'c', new ItemStack(Block.chest.blockID, 1, 0),
                'd', "bronzeGear",
                'e', "plankWood"));
            }
            
            // Recycler
            is = new ItemStack(blockMachineAlpha.blockID, 1, 9);
            
            cde.api.Blocks.machineRecycler = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MACHINE_ALPHA_BLOCK_NAMES[9]);
            GameRegistry.registerTileEntity(TileEntityRecycler.class, "cdeRecycler");
            
            if(craftable[9])
            {
                GameRegistry.addRecipe(new ShapedOreRecipe(is,
                " a ",
                "cbc",
                "dcd",
                'a', new ItemStack(Item.lightStoneDust.itemID, 1, 0),
                'b', Blocks.machineCompressor.copy(),
                'c', new ItemStack(Block.dirt.blockID, 1, 0),
                'd', "ingotSteel"));
            }
            
            // Sawmill
            is = new ItemStack(blockMachineAlpha.blockID, 1, 10);
            
            cde.api.Blocks.machineSawmill = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MACHINE_ALPHA_BLOCK_NAMES[10]);
            GameRegistry.registerTileEntity(TileEntitySawmill.class, "cdeSawmill");
            
            if(craftable[10])
            {
                GameRegistry.addRecipe(new ShapedOreRecipe(is,
                " a ",
                "dbd",
                "ece",
                'a', new ItemStack(Item.axeSteel.itemID, 1, 0),
                'b', new ItemStack(Items.getItem("machine").itemID, 1, 0),
                'c', Materials.circuitBoardSingle.copy(),
                'd', "plankWood",
                'e', "gearCopper"));
            }
            
            // Heater
            is = new ItemStack(blockMachineAlpha.blockID, 1, 11);
            
            cde.api.Blocks.machineHeater = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MACHINE_ALPHA_BLOCK_NAMES[11]);
            GameRegistry.registerTileEntity(TileEntityHeater.class, "cdeHeater");
            
            if(craftable[11])
            {
                GameRegistry.addRecipe(is,
                "aaa",
                "dbd",
                "ece",
                'a', new ItemStack(Item.blazeRod.itemID, 1, 0),
                'b', new ItemStack(Items.getItem("machine").itemID, 1, 0),
                'c', Materials.circuitBoardDouble.copy(),
                'd', new ItemStack(Items.getItem("reBattery").itemID, 1, 0),
                'e', new ItemStack(Items.getItem("insulatedCopperCableItem").itemID, 1, 0));
            }
            
            // Pump
            is = new ItemStack(blockMachineAlpha.blockID, 1, 12);
            
            cde.api.Blocks.machinePump = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MACHINE_ALPHA_BLOCK_NAMES[12]);
            GameRegistry.registerTileEntity(TileEntityPump.class, "cdePump");
            
            if(craftable[12])
            {
                GameRegistry.addRecipe(is,
                "aaa",
                "dbd",
                "dcd",
                'a', new ItemStack(tankBlockId, 1, 0),
                'b', new ItemStack(Items.getItem("machine").itemID, 1, 0),
                'c', Materials.circuitBoardSingle.copy(),
                'd', new ItemStack(Items.getItem("reBattery").itemID, 1, 0),
                'e', new ItemStack(Items.getItem("insulatedCopperCableItem").itemID, 1, 0));
            }
        }
        
        if(blockMachineBetaId > 0)
        {
            blockMachineBeta = new BlockMachineBeta(blockMachineBetaId).setBlockName("cdeIndustryMachineBeta").setCreativeTab(TAB_MACHINES).setHardness(3.5F);
            
            GameRegistry.registerBlock(blockMachineBeta, ItemBlockBeta.class, "blockMachineBeta");
            MinecraftForge.setBlockHarvestLevel(blockMachineBeta, "pickaxe", 1);
            
            // Stirling Generator
            is = new ItemStack(blockMachineBeta.blockID, 1, 0);
            
            cde.api.Blocks.machineStirlingGenerator = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MACHINE_BETA_BLOCK_NAMES[0]);
            GameRegistry.registerTileEntity(TileEntityStirlingGenerator.class, "cdeStirlingGenerator");
            
            if(craftable[13])
            {
                GameRegistry.addRecipe(new ShapedOreRecipe(is,
                " a ",
                "bbb",
                " c ",
                'a', new ItemStack(Items.getItem("reBattery").itemID, 1, 0),
                'b', "ingotSteel",
                'c', Blocks.machineIronFurnace.copy()));
                
                GameRegistry.addRecipe(new ShapedOreRecipe(is,
                " a ",
                "bbb",
                " c ",
                'a', new ItemStack(Items.getItem("chargedReBattery").itemID, 1, 0),
                'b', "ingotSteel",
                'c', Blocks.machineIronFurnace.copy()));
                
                GameRegistry.addRecipe(is,
                " a ",
                "bbb",
                " c ",
                'a', new ItemStack(Items.getItem("reBattery").itemID, 1, 0),
                'b', new ItemStack(Items.getItem("machine").itemID, 1, 0),
                'c', new ItemStack(Block.stoneOvenIdle.blockID, 1, 0));
                
                GameRegistry.addRecipe(is,
                " a ",
                "bbb",
                " c ",
                'a', new ItemStack(Items.getItem("chargedReBattery").itemID, 1, 0),
                'b', new ItemStack(Items.getItem("machine").itemID, 1, 0),
                'c', new ItemStack(Block.stoneOvenIdle.blockID, 1, 0));
            }
            
            // Geothermal Generator
            is = new ItemStack(blockMachineBeta.blockID, 1, 1);
            
            cde.api.Blocks.machineGeothermalGenerator = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MACHINE_BETA_BLOCK_NAMES[1]);
            GameRegistry.registerTileEntity(TileEntityGeothermalGenerator.class, "cdeGeothermalGenerator");
            
            if(craftable[14])
            {
                GameRegistry.addRecipe(new ShapedOreRecipe(is,
                "aba",
                "aba",
                "dcd",
                'a', new ItemStack(Block.glass.blockID, 1, 0),
                'b', new ItemStack(Items.getItem("cell").itemID, 1, 0),
                'c', Blocks.machineStirlingGenerator.copy(),
                'd', "ingotSteel"));
            }
            
            // Tidal Generator
            is = new ItemStack(blockMachineBeta.blockID, 1, 2);
            
            cde.api.Blocks.machineTidalGenerator = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MACHINE_BETA_BLOCK_NAMES[2]);
            GameRegistry.registerTileEntity(TileEntityTidalGenerator.class, "cdeTidalGenerator");
            
            if(craftable[15])
            {
                GameRegistry.addRecipe(new ShapedOreRecipe(is,
                "aba",
                "bcb",
                "aba",
                'a', "stickWood",
                'b', "plankWood",
                'c', Blocks.machineStirlingGenerator.copy()));
            }
            
            // Wind Generator
            is = new ItemStack(blockMachineBeta.blockID, 1, 3);
            
            cde.api.Blocks.machineWindGenerator = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MACHINE_BETA_BLOCK_NAMES[3]);
            GameRegistry.registerTileEntity(TileEntityWindGenerator.class, "cdeWindGenerator");
            
            if(craftable[16])
            {
                GameRegistry.addRecipe(is,
                "a a",
                " b ",
                "a a",
                'a', new ItemStack(Item.ingotIron.itemID, 1, 0),
                'b', Blocks.machineStirlingGenerator.copy());
            }
            
            // Solar Panel
            is = new ItemStack(blockMachineBeta.blockID, 1, 4);
            
            cde.api.Blocks.machineSolarPanel = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MACHINE_BETA_BLOCK_NAMES[4]);
            GameRegistry.registerTileEntity(TileEntitySolarPanel.class, "cdeSolarPanel");
            
            if(craftable[17])
            {
                GameRegistry.addRecipe(new ShapedOreRecipe(is,
                "aba",
                "bab",
                "cdc",
                'a', new ItemStack(Block.glass.blockID, 1, 0),
                'b', "dustCoal",
                'c', Materials.circuitBoardSingle.copy(),
                'd', Blocks.machineStirlingGenerator.copy()));
            }
            
            // Nuclear Reactor
            is = new ItemStack(blockMachineBeta.blockID, 1, 5);
            
            cde.api.Blocks.machineNuclearReactor = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MACHINE_BETA_BLOCK_NAMES[5]);
            GameRegistry.registerTileEntity(TileEntityNuclearReactor.class, "cdeNuclearReactor");
            
            if(craftable[18])
            {
                GameRegistry.addRecipe(is,
                " a ",
                "bbb",
                " c ",
                'a', Materials.circuitBoardMulti.copy(),
                'b', new ItemStack(Items.getItem("reactorChamber").itemID, 1, 0),
                'c', Blocks.machineStirlingGenerator.copy());
            }
            
            // Dynamo
            is = new ItemStack(blockMachineBeta.blockID, 1, 6);
            
            cde.api.Blocks.machineElectricDynamo = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MACHINE_BETA_BLOCK_NAMES[6]);
            GameRegistry.registerTileEntity(TileEntityDynamo.class, "cdeElectricDynamo");
            
            if(craftable[19])
            {
                GameRegistry.addRecipe(is,
                "xxx",
                "byb",
                "aza",
                'x', new ItemStack(ironGearId, 1, 0),
                'y', new ItemStack(Items.getItem("machine").itemID, 1, 0),
                'z', Materials.circuitBoardSingle.copy(),
                'a', new ItemStack(Items.getItem("insulatedCopperCableItem").itemID, 1, 0),
                'b', new ItemStack(Items.getItem("reBattery").itemID, 1, 0));
            }
            
            // Turbine
            is = new ItemStack(blockMachineBeta.blockID, 1, 7);
            
            cde.api.Blocks.machineSteamTurbine = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MACHINE_BETA_BLOCK_NAMES[7]);
            GameRegistry.registerTileEntity(TileEntitySteamTurbine.class, "cdeSteamTurbine");
            
            if(craftable[20])
            {
                GameRegistry.addRecipe(is,
                "aaa",
                "ebe",
                "dcd",
                'a', new ItemStack(ItemRegistry.getItem("part.turbine.disk", 1).itemID, 1, 0),
                'b', new ItemStack(Items.getItem("machine").itemID, 1, 0),
                'c', Materials.circuitBoardDouble.copy(),
                'd', new ItemStack(Items.getItem("insulatedCopperCableItem").itemID, 1, 0),
                'e', new ItemStack(Items.getItem("reBattery").itemID, 1, 0));
            }
        }
        
        if(blockMachineGammaId > 0)
        {
            blockMachineGamma = new BlockMachineGamma(blockMachineGammaId).setBlockName("cdeIndustryMachineGamma").setCreativeTab(TAB_MACHINES).setHardness(3.5F);
            
            GameRegistry.registerBlock(blockMachineGamma, ItemBlockGamma.class, "blockMachineGamma");
            MinecraftForge.setBlockHarvestLevel(blockMachineGamma, "pickaxe", 1);
            
            // LV Energy Storage Unit
            is = new ItemStack(blockMachineGamma.blockID, 1, 0);
            
            cde.api.Blocks.machineEnergyStorageUnitLV = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MACHINE_GAMMA_BLOCK_NAMES[0]);
            GameRegistry.registerTileEntity(TileEntityEnergyStorageUnit.class, "cdeEnergyStorageUnit");
            
            if(craftable[21])
            {
                GameRegistry.addRecipe(new ShapedOreRecipe(is,
                "xyx",
                "zzz",
                "xxx",
                'x', "plankWood",
                'y', new ItemStack(Items.getItem("insulatedCopperCableItem").itemID, 1, 0),
                'z', new ItemStack(Items.getItem("reBattery").itemID, 1, 0)));
            }
            
            // MV Energy Storage Unit
            is = new ItemStack(blockMachineGamma.blockID, 1, 1);
            
            cde.api.Blocks.machineEnergyStorageUnitMV = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MACHINE_GAMMA_BLOCK_NAMES[1]);
            // GameRegistry.registerTileEntity(TileEntityMachineEnergyStorageUnit.class, "cdeEnergyStorageUnit");
            
            if(craftable[22])
            {
                GameRegistry.addRecipe(is,
                "zyz",
                "yxy",
                "zyz",
                'x', new ItemStack(Items.getItem("machine").itemID, 1, 0),
                'y', new ItemStack(Items.getItem("energyCrystal").itemID, 1, 0),
                'z', new ItemStack(Items.getItem("doubleInsulatedGoldCableItem").itemID, 1, 0));
            }
            
            // HV Energy Storage Unit
            is = new ItemStack(blockMachineGamma.blockID, 1, 2);
            
            cde.api.Blocks.machineEnergyStorageUnitHV = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MACHINE_GAMMA_BLOCK_NAMES[2]);
            // GameRegistry.registerTileEntity(TileEntityMachineEnergyStorageUnit.class, "cdeEnergyStorageUnit");
            
            if(craftable[23])
            {
                GameRegistry.addRecipe(is,
                "axa",
                "aya",
                "aza",
                'a', new ItemStack(Items.getItem("lapotronCrystal").itemID, 1, 0),
                'x', Materials.circuitBoardMulti.copy(),
                'y', Blocks.machineEnergyStorageUnitMV.copy(),
                'z', new ItemStack(Items.getItem("advancedMachine").itemID, 1, 0));
            }
            
            // LV Transformer
            is = new ItemStack(blockMachineGamma.blockID, 1, 3);
            
            cde.api.Blocks.machineTransformerLV = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MACHINE_GAMMA_BLOCK_NAMES[3]);
            GameRegistry.registerTileEntity(TileEntityTransformer.class, "cdeTransformer");
            
            if(craftable[24])
            {
                GameRegistry.addRecipe(new ShapedOreRecipe(is,
                "xzx",
                "yyy",
                "xzx",
                'x', "plankWood",
                'y', "ingotCopper",
                'z', new ItemStack(Items.getItem("insulatedCopperCableItem").itemID, 1, 0)));
            }
            
            // MV Transformer
            is = new ItemStack(blockMachineGamma.blockID, 1, 4);
            
            cde.api.Blocks.machineTransformerMV = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MACHINE_GAMMA_BLOCK_NAMES[4]);
            // GameRegistry.registerTileEntity(TileEntityMachineTransformer.class, "cdeEnergyStorageUnit");
            
            if(craftable[25])
            {
                GameRegistry.addRecipe(is,
                " y ",
                " x ",
                " y ",
                'x', new ItemStack(Items.getItem("machine").itemID, 1, 0),
                'y', new ItemStack(Items.getItem("doubleInsulatedGoldCableItem").itemID, 1, 0));
            }
            
            // HV Transformer
            is = new ItemStack(blockMachineGamma.blockID, 1, 5);
            
            cde.api.Blocks.machineTransformerHV = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MACHINE_GAMMA_BLOCK_NAMES[5]);
            // GameRegistry.registerTileEntity(TileEntityMachineTransformer.class, "cdeEnergyStorageUnit");
            
            if(craftable[26])
            {
                GameRegistry.addRecipe(is,
                " a ",
                "xyz",
                " a ",
                'a', new ItemStack(Items.getItem("trippleInsulatedIronCableItem").itemID, 1, 0),
                'x', Materials.circuitBoardDouble.copy(),
                'y', Blocks.machineTransformerMV.copy(),
                'z', new ItemStack(Items.getItem("energyCrystal").itemID, 1, 0));
            }
            
            // LV Charging Bench
            is = new ItemStack(blockMachineGamma.blockID, 1, 6);
            
            cde.api.Blocks.machineChargingBenchLV = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MACHINE_GAMMA_BLOCK_NAMES[6]);
            GameRegistry.registerTileEntity(TileEntityChargingBench.class, "cdeChargingBench");
            
            if(craftable[27])
            {
                GameRegistry.addRecipe(new ShapedOreRecipe(is,
                "aaa",
                "dbd",
                "dcd",
                'a', new ItemStack(Items.getItem("insulatedCopperCableItem").itemID, 1, 0),
                'b', Materials.circuitBoardSingle.copy(),
                'c', Blocks.machineEnergyStorageUnitLV.copy(),
                'd', "plankWood"));
            }
            
            // MV Charging Bench
            is = new ItemStack(blockMachineGamma.blockID, 1, 7);
            
            cde.api.Blocks.machineChargingBenchMV = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MACHINE_GAMMA_BLOCK_NAMES[7]);
            // GameRegistry.registerTileEntity(TileEntityMachineChargingBench.class, "cdeChargingBench");
            
            if(craftable[28])
            {
                GameRegistry.addRecipe(new ShapedOreRecipe(is,
                "aaa",
                "dbd",
                "dcd",
                'a', new ItemStack(Items.getItem("doubleInsulatedGoldCableItem").itemID, 1, 0),
                'b', Materials.circuitBoardDouble.copy(),
                'c', Blocks.machineEnergyStorageUnitMV.copy(),
                'd', "plankWood"));
            }
            
            // HV Charging Bench
            is = new ItemStack(blockMachineGamma.blockID, 1, 8);
            
            cde.api.Blocks.machineChargingBenchHV = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MACHINE_GAMMA_BLOCK_NAMES[8]);
            // GameRegistry.registerTileEntity(TileEntityMachineChargingBench.class, "cdeChargingBench");
            
            if(craftable[29])
            {
                GameRegistry.addRecipe(new ShapedOreRecipe(is,
                "aaa",
                "dbd",
                "dcd",
                'a', new ItemStack(Items.getItem("trippleInsulatedIronCableItem").itemID, 1, 0),
                'b', Materials.circuitBoardMulti.copy(),
                'c', Blocks.machineEnergyStorageUnitHV.copy(),
                'd', "plankWood"));
            }
            
            // LV Battery Station
            is = new ItemStack(blockMachineGamma.blockID, 1, 9);
            
            cde.api.Blocks.machineBatteryStationLV = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MACHINE_GAMMA_BLOCK_NAMES[9]);
            GameRegistry.registerTileEntity(TileEntityBatteryStation.class, "cdeBatteryStation");
            
            if(craftable[30])
            {
                GameRegistry.addRecipe(new ShapedOreRecipe(is,
                "aaa",
                "dbd",
                "dcd",
                'a', new ItemStack(Items.getItem("insulatedCopperCableItem").itemID, 1, 0),
                'b', Materials.circuitBoardSingle.copy(),
                'c', Blocks.machineTransformerLV.copy(),
                'd', "plankWood"));
            }
            
            // MV Battery Station
            is = new ItemStack(blockMachineGamma.blockID, 1, 10);
            
            cde.api.Blocks.machineBatteryStationMV = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MACHINE_GAMMA_BLOCK_NAMES[10]);
            // GameRegistry.registerTileEntity(TileEntityMachineChargingBench.class, "cdeChargingBench");
            
            if(craftable[31])
            {
                GameRegistry.addRecipe(new ShapedOreRecipe(is,
                "aaa",
                "dbd",
                "dcd",
                'a', new ItemStack(Items.getItem("doubleInsulatedGoldCableItem").itemID, 1, 0),
                'b', Materials.circuitBoardDouble.copy(),
                'c', Blocks.machineTransformerMV.copy(),
                'd', "plankWood"));
            }
            
            // HV Charging Bench
            is = new ItemStack(blockMachineGamma.blockID, 1, 11);
            
            cde.api.Blocks.machineBatteryStationHV = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MACHINE_GAMMA_BLOCK_NAMES[11]);
            // GameRegistry.registerTileEntity(TileEntityMachineChargingBench.class, "cdeChargingBench");
            
            if(craftable[32])
            {
                GameRegistry.addRecipe(new ShapedOreRecipe(is,
                "aaa",
                "dbd",
                "dcd",
                'a', new ItemStack(Items.getItem("trippleInsulatedIronCableItem").itemID, 1, 0),
                'b', Materials.circuitBoardMulti.copy(),
                'c', Blocks.machineTransformerHV.copy(),
                'd', "plankWood"));
            }
            
            // Induction Charger
            is = new ItemStack(blockMachineGamma.blockID, 1, 12);
            
            cde.api.Blocks.machineInductionCharger = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MACHINE_GAMMA_BLOCK_NAMES[12]);
            GameRegistry.registerTileEntity(TileEntityInductionCharger.class, "cdeInductionCharger");
            
            if(craftable[33])
            {
                GameRegistry.addRecipe(is,
                "aaa",
                "dbd",
                "ece",
                'a', new ItemStack(Items.getItem("tinCableItem").itemID, 1, 0),
                'b', new ItemStack(Items.getItem("machine").itemID, 1, 0),
                'c', Materials.circuitBoardMulti.copy(),
                'd', new ItemStack(Items.getItem("reBattery").itemID, 1, 0),
                'e', new ItemStack(Items.getItem("insulatedCopperCableItem").itemID, 1, 0));
            }
            
            // Electrolyzer
            is = new ItemStack(blockMachineGamma.blockID, 1, 13);
            
            cde.api.Blocks.machineElectrolyzer = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MACHINE_GAMMA_BLOCK_NAMES[13]);
            GameRegistry.registerTileEntity(TileEntityElectrolyzer.class, "cdeElectrolyzer");
            
            if(craftable[34])
            {
                GameRegistry.addRecipe(is,
                "a a",
                "aca",
                "bdb",
                'a', new ItemStack(Items.getItem("insulatedCopperCableItem").itemID, 1, 0),
                'b', new ItemStack(Items.getItem("cell").itemID, 1, 0),
                'c', Materials.circuitBoardDouble.copy(),
                'd', new ItemStack(Items.getItem("machine").itemID, 1, 0));
            }
        }
        
        if(blockMachineDeltaId > 0 && false)
        {
            blockMachineDelta = new BlockMachineDelta(blockMachineDeltaId).setBlockName("cdeIndustryMachineDelta").setCreativeTab(TAB_MACHINES).setHardness(3.5F);
            
            GameRegistry.registerBlock(blockMachineDelta, ItemBlockDelta.class, "blockMachineDelta");
            
        }
    }
    
    public static void postInit(FMLPostInitializationEvent event) 
    {
        
    }
    
    private void setupItemIds()
    {
        for(Item item : CDECore.getItemsByClass("buildcraft.core.ItemBuildCraft"))
        {
            if(item.getItemName().equalsIgnoreCase("item.ironGearItem"))
            {
                ironGearId = item.itemID;
            }
        }
        
        Block block = CDECore.getBlockByClass("buildcraft.factory.BlockTank");
        
        if(block != null)
        {
            tankBlockId = block.blockID;
        }
        
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
    }
    
    protected static int getItemId(int i)
    {
        switch(i)
        {
            case 0: return batteryEmptyId;
            case 1: return batteryFullId;
            default: return 0;
        }
    }
}
