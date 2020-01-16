/**
 *
 * @author StormTiberius
 */

package cde;

import cde.api.Blocks;
import cde.api.Materials;
import cde.core.Defaults;
import cde.core.Version;
import cde.industry.BlockDrum;
import cde.industry.CommonProxy;
import cde.industry.CreativeTabDrums;
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
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import railcraft.common.api.core.items.ItemRegistry;

@Mod(modid="CDE|Industry", name="Industry", version=Version.VERSION, dependencies = "required-after:Forge@[6.6.2.534,);required-after:CDE|Core")
@NetworkMod(clientSideRequired=true, serverSideRequired=true)
public class IndustryCore
{
    @SidedProxy(clientSide = "cde.industry.ClientProxy", serverSide = "cde.industry.CommonProxy")
    public static CommonProxy proxy;
    public static Block blockDrum;
    
    private static int drumBlockId,drumRenderId,drumVolume,drumPitch;
    private static boolean drumRecipeIron,drumRecipeSteel;
    private static String[] paintColors,liquidColors;
    private static ItemStack tabIconDrum;
    private static Configuration cfg;
    
    public static final CreativeTabs TAB_DRUMS = new CreativeTabDrums("industrydrums");
    
    private static final Map<String, Color> PAINT_COLOR_MAP = new HashMap<String, Color>();
    private static final Map<String, Color> NAME_COLOR_MAP = new HashMap<String, Color>();
    private static final Map<Integer, Color> ID_COLOR_MAP = new HashMap<Integer, Color>();
    
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
            blockDrum = new BlockDrum(drumBlockId).setBlockName("cdeDrumBlock").setCreativeTab(TAB_DRUMS).setHardness(1.5F);

            GameRegistry.registerBlock(blockDrum, ItemBlockDrum.class, "cdeDrumBlock");

            GameRegistry.registerTileEntity(TileEntityDrum.class, "cdeDrumTile");
            
            // Iron Drum
            ItemStack drumIron = new ItemStack(blockDrum.blockID, 1, 0);
            
            if(!drumIron.hasTagCompound())
            {
                drumIron.setTagCompound(new NBTTagCompound());
            }
            
            if(drumIron.getTagCompound().getName().isEmpty())
            {
                drumIron.getTagCompound().setName("tag");
            }
            
            drumIron.getTagCompound().setInteger("capacity", Defaults.DRUM_CAPACITY_IRON);
            drumIron.getTagCompound().setInteger("color", getPaintColor().getRGB());
            drumIron.getTagCompound().setInteger("paint", -1);
            tabIconDrum = drumIron.copy(); // Copy iron drum itemstack for creative tab display purposes.
            drumIron.setItemDamage(drumIron.getMaxDamage());
            
            Blocks.blockDrumIron = drumIron;
            
            // Steel Drum
            ItemStack drumSteel = new ItemStack(blockDrum.blockID, 1, 0);
            
            if(!drumSteel.hasTagCompound())
            {
                drumSteel.setTagCompound(new NBTTagCompound());
            }
            
            if(drumSteel.getTagCompound().getName().isEmpty())
            {
                drumSteel.getTagCompound().setName("tag");
            }
            
            drumSteel.getTagCompound().setInteger("capacity", Defaults.DRUM_CAPACITY_STEEL);
            drumSteel.getTagCompound().setInteger("color", getPaintColor().getRGB());
            drumSteel.getTagCompound().setInteger("paint", -1);
            drumSteel.setItemDamage(drumSteel.getMaxDamage());
            
            Blocks.blockDrumSteel = drumSteel;
            
            ItemStack sealant = new ItemStack(Item.slimeBall.itemID, 1, 0);
            ItemStack iron = new ItemStack(Item.ingotIron.itemID, 1, 0);
            ItemStack steel = Materials.ingotSteel.copy();
            
            if(ModLoader.isModLoaded("BuildCraft|Core"))
            {
                for(Item item : CDECore.getItemsByClass("buildcraft.core.ItemBuildCraft"))
                {
                    if(item.getItemName().contentEquals("item.pipeWaterproof"))
                    {
                        sealant = new ItemStack(item.itemID, 1, 0);
                        break;
                    }
                }
            }
            
            boolean plate = false;
            
            if(ModLoader.isModLoaded("Railcraft"))
            {
                ItemStack plateIron = ItemRegistry.getItem("part.plate.iron", 1);
                ItemStack plateSteel = ItemRegistry.getItem("part.plate.steel", 1);
                
                if(plateIron != null)
                {
                    iron = plateIron.copy();
                }
                
                if(plateSteel != null)
                {
                    steel = plateSteel.copy();
                    plate = true;
                }
            }
            
            if(drumRecipeIron)
            {
                GameRegistry.addRecipe(new ShapedOreRecipe(Blocks.blockDrumIron.copy(),
                "zpz",
                "zbz",
                "zpz",
                'z', "ingotZinc",
                'b', new ItemStack(Block.blockSteel.blockID, 1, 0),
                'p', iron,
                's', sealant));
            }
            
            if(drumRecipeSteel)
            {
                if(plate)
                {
                    GameRegistry.addRecipe(new ShapedOreRecipe(Blocks.blockDrumSteel.copy(),
                    "zpz",
                    "zbz",
                    "zpz",
                    'z', "ingotZinc",
                    'b', "blockSteel",
                    'p', steel,
                    's', sealant));
                }
                else
                {
                    GameRegistry.addRecipe(new ShapedOreRecipe(Blocks.blockDrumSteel.copy(),
                    "zpz",
                    "zbz",
                    "zpz",
                    'z', "ingotZinc",
                    'b', "blockSteel",
                    'p', "ingotSteel",
                    's', sealant));
                }
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
    
    public static ItemStack getTabIcon()
    {
        return tabIconDrum;
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
    
    public static Color getPaintColor()
    {
        return Defaults.COLOR_DEFAULT;
    }
    
    public static Color getPaintColor(int index)
    {
        if(index >= 0 && index <= 15)
        {
            return Defaults.MINECRAFT_COLORS[index];
        }
        
        return getPaintColor();
    }
    
    public static Color getLiquidColor(String s)
    {
        if(NAME_COLOR_MAP.containsKey(s))
        {
            return NAME_COLOR_MAP.get(s);
        }
        
        return getPaintColor();
    }
    
    public static Color getLiquidColor(int id)
    {
        if(ID_COLOR_MAP.containsKey(id))
        {
            return ID_COLOR_MAP.get(id);
        }
        
        return getPaintColor();
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
                    ID_COLOR_MAP.put(liquid.itemID, getPaintColor());
                    NAME_COLOR_MAP.put(entry.getKey(), getPaintColor());
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
