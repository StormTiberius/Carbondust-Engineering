/**
 *
 * @author StormTiberius
 */

package cde;

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
import net.minecraftforge.common.Configuration;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

@Mod(modid="CDE|Industry", name="Industry", version=Version.VERSION, dependencies = "required-after:Forge@[6.6.2.534,);required-after:CDE|Core")
@NetworkMod(clientSideRequired=true, serverSideRequired=true)
public class IndustryCore
{
    @SidedProxy(clientSide = "cde.industry.ClientProxy", serverSide = "cde.industry.CommonProxy")
    
    public static CommonProxy proxy;
    
    private static Configuration cfg;
    
    private static int drumVolume;
    private static int drumPitch;
    private static int drumRenderId;
    private static int drumBlockId;
    
    public static Block blockDrum;
    
    private static final Color MEAD = new Color(220,190,115);
    private static final Color COOLANT = new Color(50,210,255);
    private static final Color BIOMASS = new Color(105,200,60);
    private static final Color STEAM = new Color(138,138,138);
    private static final Color BIOFUEL = new Color(238,140,20);
    private static final Color WATER = new Color(47,68,245);
    private static final Color CREOSOTE = new Color(105,105,10);
    private static final Color HONEY = new Color(235,200,60);
    private static final Color OIL = new Color(21,21,21);
    private static final Color ICE = new Color(198,253,253);
    private static final Color MILK = new Color(245,245,245);
    private static final Color SEEDOIL = new Color(225,225,150);
    private static final Color JUICE = new Color(145,200,70);
    private static final Color LAVA = new Color(213,91,19);
    private static final Color FUEL = new Color(200,200,5);
    private static final Color GLASS = new Color(224,224,224);
    private static final Color DEFAULT = new Color(255,255,255);
    
    private static final Map<String, Color> NAME_COLOR_MAP = new HashMap<String, Color>();
    private static final Map<Integer, Color> ID_COLOR_MAP = new HashMap<Integer, Color>();

    private static String[] liquidColors;
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) 
    {
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/industry.cfg"));
        cfg.load();
        
        liquidColors = cfg.get(Configuration.CATEGORY_GENERAL, "liquidcolors", new String[]{"Liquid.255.255.255"}, "name.r.g.b").valueList;
        
        drumBlockId = cfg.get(Configuration.CATEGORY_BLOCK, "drumblockid", Defaults.BLOCK_DRUM_ID).getInt();
        
        // Individual TileEntity Sound Settings.
        int defaultVolume = 20;
        int defaultPitch = 100;
        
        // Volume
        drumVolume = cfg.get(Configuration.CATEGORY_GENERAL, "drumvolume", defaultVolume, "Drum Volume").getInt();
        
        // Pitch
        drumPitch = cfg.get(Configuration.CATEGORY_GENERAL, "drumpitch", defaultPitch, "Drum Pitch").getInt();
        
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
        }
        
        proxy.registerRenderers();
    }

    @PostInit
    public void postInit(FMLPostInitializationEvent event) 
    {
        initNameColorMap();
        parseLiquidColors();
        initIdColorMap();
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
    
    public static Color getLiquidColor(String s)
    {
        if(NAME_COLOR_MAP.containsKey(s))
        {
            return NAME_COLOR_MAP.get(s);
        }
        
        return DEFAULT;
    }
    
    public static Color getLiquidColor(int id)
    {
        if(ID_COLOR_MAP.containsKey(id))
        {
            return ID_COLOR_MAP.get(id);
        }
        
        return DEFAULT;
    }
        
    private static void initNameColorMap()
    {
        NAME_COLOR_MAP.put("mead", MEAD);
        NAME_COLOR_MAP.put("coolant", COOLANT);
        NAME_COLOR_MAP.put("biomass", BIOMASS);
        NAME_COLOR_MAP.put("Steam", STEAM);
        NAME_COLOR_MAP.put("biofuel", BIOFUEL);
        NAME_COLOR_MAP.put("Water", WATER);
        NAME_COLOR_MAP.put("Creosote Oil", CREOSOTE);
        NAME_COLOR_MAP.put("honey", HONEY);
        NAME_COLOR_MAP.put("Oil", OIL);
        NAME_COLOR_MAP.put("ice", ICE);
        NAME_COLOR_MAP.put("milk", MILK);
        NAME_COLOR_MAP.put("seedoil", SEEDOIL);
        NAME_COLOR_MAP.put("juice", JUICE);
        NAME_COLOR_MAP.put("Lava", LAVA);
        NAME_COLOR_MAP.put("Fuel", FUEL);
        NAME_COLOR_MAP.put("Glass", GLASS);
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
                    ID_COLOR_MAP.put(liquid.itemID, DEFAULT);
                    NAME_COLOR_MAP.put(entry.getKey(), DEFAULT);
                }
            }
        }
    }
    
    private static boolean isValidRange(int r, int g, int b)
    {
        return r >= 0 && r <= 255 && g >= 0 && g <= 255 && b >= 0 && b <= 255;
    }
}
