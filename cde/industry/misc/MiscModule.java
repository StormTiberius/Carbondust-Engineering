/**
 *
 * @author StormTiberius
 */

package cde.industry.misc;

import cde.core.Defaults;
import cde.core.Namings;
import cde.industry.machine.MachineModule;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import java.io.File;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class MiscModule
{
    private static Configuration cfg;
    
    private static final String[] DEFAULT_SOUNDS = {
    "computalk1.wav",
    "computalk2.wav",
    "techamb1.wav",
    "techamb2.wav",

    "boiler.ogg",
    "chemical-plant.ogg",
    "electric-furnace.ogg",
    "electric-mining-drill.ogg",
    "furnace.ogg",
    "lab.ogg",
    "oil-refinery.ogg",
    "pipe.ogg",
    "pumpjack.ogg",
    "steam-engine-90bpm.ogg",
    "storage-tank.ogg",
    "substation.ogg",
    "train-engine.ogg"};
    
    private static final int[] DEFAULT_VOLUMES = {20, 20, 20, 20, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40};
    private static final int[] DEFAULT_PITCHS = {100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100};
    
    protected static String[] sounds;
    protected static int[] volumes,pitchs;
    
    private static int blockMiscId;
    public static Block blockMisc;
    
    private static final boolean[] CRAFTABLE = new boolean[2];
    
    public static void preInit(FMLPreInitializationEvent event) 
    {
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/speaker.cfg"));
        cfg.load();
        
        blockMiscId = cfg.get(Configuration.CATEGORY_BLOCK, "misc", Defaults.BLOCK_INDUSTRY_MISC_ID).getInt();
        
        sounds = cfg.get(Configuration.CATEGORY_GENERAL, "sounds", DEFAULT_SOUNDS, "Sound File Names").valueList;
        volumes = cfg.get(Configuration.CATEGORY_GENERAL, "volumes", DEFAULT_VOLUMES, "Sound Volumes").getIntList();
        pitchs = cfg.get(Configuration.CATEGORY_GENERAL, "pitchs", DEFAULT_PITCHS, "Sound Pitchs").getIntList();
        
        CRAFTABLE[0] = cfg.get(Configuration.CATEGORY_GENERAL, "speaker", true, "Enable/Disable Crafting Recipe").getBoolean(true);
        CRAFTABLE[1] = cfg.get(Configuration.CATEGORY_GENERAL, "smokestack", true, "Enable/Disable Crafting Recipe").getBoolean(true);
        
        cfg.save();
    }

    public static void init(FMLInitializationEvent event) 
    {   
        if(blockMiscId > 0)
        {
            blockMisc = new BlockMisc(blockMiscId).setBlockName("cdeMisc").setCreativeTab(MachineModule.TAB_MACHINES).setHardness(2.0F);
            GameRegistry.registerBlock(blockMisc, ItemBlockMisc.class, "cdeMisc");
            MinecraftForge.setBlockHarvestLevel(blockMisc, "axe", 1);
            
            // Speaker
            ItemStack is = new ItemStack(blockMisc.blockID, 1, 0);
            
            cde.api.Blocks.industrySpeaker = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MISC_BLOCK_NAMES[0]);
            GameRegistry.registerTileEntity(TileEntitySpeaker.class, "cdeSpeaker");
            
            if(CRAFTABLE[0])
            {
                GameRegistry.addRecipe(new ShapedOreRecipe(is,
                "xxx",
                "xyx",
                "xxx",
                'x', "plankWood",
                'y', new ItemStack(Item.emerald.itemID, 1, 0)));
            }
            
            // Smokestack
            is = new ItemStack(blockMisc.blockID, 1, 1);
            
            cde.api.Blocks.industrySmokestack = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_INDUSTRY_MISC_BLOCK_NAMES[1]);
            GameRegistry.registerTileEntity(TileEntitySmokestack.class, "cdeSmokestack");
            
            if(CRAFTABLE[1])
            {
                GameRegistry.addRecipe(is,
                " x ",
                "zyz",
                "   ",
                'x', new ItemStack(Block.netherrack.blockID, 1, 10),
                'y', new ItemStack(Item.cauldron.itemID, 1, 0),
                'z', new ItemStack(Item.redstone.itemID, 1, 0));
            }
        }
    }
    
    public static void postInit(FMLPostInitializationEvent event) 
    {   
        
    }
}
