/**
 *
 * @author StormTiberius
 */

package cde;

import cde.api.Blocks;
import cde.core.Defaults;
import cde.core.Version;
import cde.industry.BlockDrum;
import cde.industry.CommonProxy;
import cde.industry.ItemBlockDrum;
import cde.industry.TileEntityDrum;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import java.awt.Color;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

@Mod(modid="CDE|Industry", name="Industry", version=Version.VERSION, dependencies = "required-after:Forge@[6.6.2.534,);required-after:CDE|Core")
@NetworkMod(clientSideRequired=true, serverSideRequired=true)
public class IndustryCore
{
    @SidedProxy(clientSide = "cde.industry.ClientProxy", serverSide = "cde.industry.CommonProxy")
    
    public static CommonProxy proxy;
    
    private static Configuration cfg;
    
    private static int drumBlockId,drumRenderId,drumVolume,drumPitch;
    
    public static Block blockDrum;
    
    private static final Map<String, Color> NAME_COLOR_MAP = new HashMap<String, Color>();
    private static final Map<Integer, Color> ID_COLOR_MAP = new HashMap<Integer, Color>();
    private static final Map<String, Color> PAINT_COLOR_MAP = new HashMap<String, Color>();
    
    private static String[] paintColors,liquidColors;
    
    private static boolean drumRecipeIron,drumRecipeSteel;
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) 
    {
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/industry.cfg"));
        cfg.load();
        
        String[] paintArray = new String[Defaults.DYE_ORE_DICTIONARY_NAMES.length];
        
        for(int i = 0; i < Defaults.DYE_ORE_DICTIONARY_NAMES.length; i++)
        {
            String name = Defaults.DYE_ORE_DICTIONARY_NAMES[i];
            
            Color color = Defaults.MC_COLORS.get(name);
            
            paintArray[i] = name + "." + color.getRed() + "." + color.getGreen() + "." + color.getBlue();
        }
        
        paintColors = cfg.get(Configuration.CATEGORY_GENERAL, "paintcolors", paintArray, "color.r.g.b").valueList;
        liquidColors = cfg.get(Configuration.CATEGORY_GENERAL, "liquidcolors", new String[]{"Liquid.255.255.255"}, "liquid.r.g.b").valueList;
        
        drumBlockId = cfg.get(Configuration.CATEGORY_BLOCK, "drumblockid", Defaults.BLOCK_DRUM_ID).getInt();
        
        // Individual TileEntity Sound Settings.
        int defaultVolume = 20;
        int defaultPitch = 100;
        
        // Volume
        drumVolume = cfg.get(Configuration.CATEGORY_GENERAL, "drumvolume", defaultVolume, "Drum Volume").getInt();
        
        // Pitch
        drumPitch = cfg.get(Configuration.CATEGORY_GENERAL, "drumpitch", defaultPitch, "Drum Pitch").getInt();
        
        // Recipes
        drumRecipeIron = cfg.get(Configuration.CATEGORY_GENERAL, "drumrecipeiron", true, "Enable/Disable Iron Drum Recipe").getBoolean(true);
        drumRecipeSteel = cfg.get(Configuration.CATEGORY_GENERAL, "drumrecipesteel", true, "Enable/Disable Steel Drum Recipe").getBoolean(true);
        
        cfg.save();
    }

    @Init
    public void init(FMLInitializationEvent event) 
    {   
        if(drumBlockId > 0)
        {
            blockDrum = new BlockDrum(drumBlockId).setBlockName("cdeDrumBlock").setCreativeTab(CDECore.TAB_CDE).setHardness(1.5F);

            GameRegistry.registerBlock(blockDrum, ItemBlockDrum.class, "cdeDrumBlock");

            GameRegistry.registerTileEntity(TileEntityDrum.class, "cdeDrumTile");
            
            // Iron Drum
            ItemStack drumIron = new ItemStack(blockDrum.blockID, 1, 0);
            
            if(!drumIron.hasTagCompound())
            {
                drumIron.setTagCompound(new NBTTagCompound());
            }
            
            drumIron.getTagCompound().setInteger("capacity", Defaults.DRUM_CAPACITY_IRON);
            drumIron.getTagCompound().setInteger("color", getLiquidColor().getRGB());
            drumIron.setItemDamage(drumIron.getMaxDamage());
            
            Blocks.blockDrumIron = drumIron;
            
            // Steel Drum
            ItemStack drumSteel = new ItemStack(blockDrum.blockID, 1, 0);
            
            if(!drumSteel.hasTagCompound())
            {
                drumSteel.setTagCompound(new NBTTagCompound());
            }
            
            drumSteel.getTagCompound().setInteger("capacity", Defaults.DRUM_CAPACITY_STEEL);
            drumSteel.getTagCompound().setInteger("color", getLiquidColor().getRGB());
            drumSteel.setItemDamage(drumSteel.getMaxDamage());
            
            Blocks.blockDrumSteel = drumSteel;
            
            if(drumRecipeIron)
            {
                GameRegistry.addRecipe(Blocks.blockDrumIron,
                "xzx",
                "xyx",
                "xzx",
                'x', new ItemStack(Item.ingotIron.itemID, 1, 0),
                'y', new ItemStack(Item.cauldron.itemID, 1, 0),
                'z', new ItemStack(Block.pressurePlatePlanks.blockID, 1, 0));
            }
                        
            if(drumRecipeSteel)
            {
                GameRegistry.addRecipe(new ShapedOreRecipe(Blocks.blockDrumSteel,
                "xzx",
                "xyx",
                "xzx",
                'x', "ingotSteel",
                'y', new ItemStack(Item.cauldron.itemID, 1, 0),
                'z', new ItemStack(Block.pressurePlateStone.blockID, 1, 0)));
            }
        }
        
        proxy.registerRenderers();
    }

    @PostInit
    public void postInit(FMLPostInitializationEvent event) 
    {
        initNameColorMap();
        parseLiquidColors();
        initIdColorMap();
        parsePaintColors();
    }

    @ServerStarting
    public void serverStarting(FMLServerStartingEvent event)
    {
        
    }
    
    public static boolean isDrumEnabled()
    {
        return drumBlockId > 0;
    }
    
    public static int getDrumVolume()
    {
        return drumVolume;
    }
    
    public static int getDrumPitch()
    {
        return drumPitch;
    }
    
    public static int getDrumRenderId()
    {
        return drumRenderId;
    }
    
    public static void setDrumRenderId(int id)
    {
        drumRenderId = id;
    }
    
    public static Color getPaintColor(int index)
    {
        if(index >= 0 && index <= 15)
        {
            return PAINT_COLOR_MAP.get(Defaults.DYE_ORE_DICTIONARY_NAMES[index]);
        }
        
        return new Color(255,255,255);
    }
    
    public static Color getLiquidColor(String s)
    {
        if(NAME_COLOR_MAP.containsKey(s))
        {
            return NAME_COLOR_MAP.get(s);
        }
        
        return Defaults.COLOR_DEFAULT;
    }
    
    public static Color getLiquidColor(int id)
    {
        if(ID_COLOR_MAP.containsKey(id))
        {
            return ID_COLOR_MAP.get(id);
        }
        
        return Defaults.COLOR_DEFAULT;
    }
    
    public static Color getLiquidColor()
    {
        return Defaults.COLOR_DEFAULT;
    }
    
    private static void initNameColorMap()
    {
        NAME_COLOR_MAP.put("mead", Defaults.COLOR_MEAD);
        NAME_COLOR_MAP.put("coolant", Defaults.COLOR_COOLANT);
        NAME_COLOR_MAP.put("biomass", Defaults.COLOR_BIOMASS);
        NAME_COLOR_MAP.put("Steam", Defaults.COLOR_STEAM);
        NAME_COLOR_MAP.put("biofuel", Defaults.COLOR_BIOFUEL);
        NAME_COLOR_MAP.put("Water", Defaults.COLOR_WATER);
        NAME_COLOR_MAP.put("Creosote Oil", Defaults.COLOR_CREOSOTE);
        NAME_COLOR_MAP.put("honey", Defaults.COLOR_HONEY);
        NAME_COLOR_MAP.put("Oil", Defaults.COLOR_OIL);
        NAME_COLOR_MAP.put("ice", Defaults.COLOR_ICE);
        NAME_COLOR_MAP.put("milk", Defaults.COLOR_MILK);
        NAME_COLOR_MAP.put("seedoil", Defaults.COLOR_SEEDOIL);
        NAME_COLOR_MAP.put("juice", Defaults.COLOR_JUICE);
        NAME_COLOR_MAP.put("Lava", Defaults.COLOR_LAVA);
        NAME_COLOR_MAP.put("Fuel", Defaults.COLOR_FUEL);
        NAME_COLOR_MAP.put("Glass", Defaults.COLOR_GLASS);
    }
    
    private static void parseLiquidColors()
    {
        for(String s : liquidColors)
        {
            try
            {
                String[] splits = s.split("\\.", 4);

                int r = Integer.parseInt(splits[1]);
                int g = Integer.parseInt(splits[2]);
                int b = Integer.parseInt(splits[3]);
                
                if(splits[0].contentEquals("Liquid") && r == 255 && g == 255 && b == 255)
                {
                    continue;
                }
                
                if(!splits[0].isEmpty() && isValidRange(r, g, b))
                {
                    NAME_COLOR_MAP.put(splits[0], new Color(r, g, b));
                }
            }
            catch(Exception e)
            {
                System.out.println("IndustryCore config error at parseLiquidColors");
            }
        }
    }
    
    private static void initIdColorMap()
    {
        for(Map.Entry<String, LiquidStack> entry : LiquidDictionary.getLiquids().entrySet())
        {
            LiquidStack liquid = entry.getValue();
            
            if(liquid != null)
            {
                if(NAME_COLOR_MAP.containsKey(entry.getKey()))
                {
                    ID_COLOR_MAP.put(liquid.itemID, NAME_COLOR_MAP.get(entry.getKey()));
                }
                else
                {
                    ID_COLOR_MAP.put(liquid.itemID, Defaults.COLOR_DEFAULT);
                    NAME_COLOR_MAP.put(entry.getKey(), Defaults.COLOR_DEFAULT);
                }
            }
        }
    }
        
    private static void parsePaintColors()
    {
        for(String s : paintColors)
        {
            try
            {
                String[] splits = s.split("\\.", 4);

                int r = Integer.parseInt(splits[1]);
                int g = Integer.parseInt(splits[2]);
                int b = Integer.parseInt(splits[3]);
                
                if(!splits[0].isEmpty() && isValidRange(r, g, b))
                {
                    PAINT_COLOR_MAP.put(splits[0], new Color(r, g, b));
                }
            }
            catch(Exception e)
            {
                System.out.println("IndustryCore config error at parsePaintColors");
            }
        }
    }
    
    private static boolean isValidRange(int r, int g, int b)
    {
        return r >= 0 && r <= 255 && g >= 0 && g <= 255 && b >= 0 && b <= 255;
    }
}
