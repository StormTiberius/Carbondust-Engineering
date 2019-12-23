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
    
    private static final String[] LIQUID_COLOR_DEFAULTS = {
        "mead.16777215",
        "coolant.16777215",
        "biomass.16777215",
        "Steam.16777215",
        "biofuel.6079922",
        "Water.16777215",
        "Creosote Oil.16777215",
        "honey.16777215",
        "Oil.16777215",
        "ice.16777215",
        "milk.16777215",
        "seedoil.16777215",
        "juice.16777215",
        "Lava.16777215",
        "Fuel.16777215"};
    
    private static String[] liquidColors = LIQUID_COLOR_DEFAULTS;
    
    private static final Map<String, Integer> NAME_COLOR_MAP = new HashMap<String, Integer>();
    private static final Map<Integer, Integer> ID_COLOR_MAP = new HashMap<Integer, Integer>();
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) 
    {
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/industry.cfg"));
        cfg.load();
        
        liquidColors = cfg.get(Configuration.CATEGORY_GENERAL, "liquidcolors", LIQUID_COLOR_DEFAULTS, "Liquid Color List").valueList;
        
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
        parseLiquidColors();
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
    
    public static int getLiquidColor(String s)
    {
        if(NAME_COLOR_MAP.containsKey(s))
        {
            return NAME_COLOR_MAP.get(s);
        }
        
        return 0xffffff;
    }
    
    public static int getLiquidColor(int id)
    {
        if(ID_COLOR_MAP.containsKey(id))
        {
            return ID_COLOR_MAP.get(id);
        }
        
        return 0xffffff;
    }
    
    private void parseLiquidColors()
    {
        for(String s : liquidColors)
        {
            try
            {
                String[] splits = s.split(".");
                
                int i = Integer.parseInt(splits[1]);
                
                NAME_COLOR_MAP.put(splits[0], i);
            }
            catch(Exception e)
            {
                System.out.println("IndustryCore parse int error");
            }
        }
        
        for(Map.Entry<String, LiquidStack> id : LiquidDictionary.getLiquids().entrySet())
        {
            for(Map.Entry<String, Integer> color : NAME_COLOR_MAP.entrySet())
            {
                if(id.getKey().contentEquals(color.getKey()))
                {
                    ID_COLOR_MAP.put(id.getValue().itemID, color.getValue());
                }
            }
        }
    }
}
