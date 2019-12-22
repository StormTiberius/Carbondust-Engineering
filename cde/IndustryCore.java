/**
 *
 * @author StormTiberius
 */

package cde;

import cde.core.Defaults;
import cde.core.Version;
import cde.industry.CommonProxy;
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
import java.io.File;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;

@Mod(modid="CDE|Industry", name="Industry", version=Version.VERSION, dependencies = "required-after:Forge@[6.6.2.534,);required-after:CDE|Core")
@NetworkMod(clientSideRequired=true, serverSideRequired=true)
public class IndustryCore
{
    @SidedProxy(clientSide = "cde.industry.ClientProxy", serverSide = "cde.industry.CommonProxy")
    
    public static CommonProxy proxy;
    
    private static Configuration cfg;
    private static int industryBlockId,industryItemId;
    public static int barrelVolume;
    public static int barrelPitch;
    public static int drumRenderId;
    public static Item industry_tool;
    public static Block industry;
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) 
    {
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/industry.cfg"));
        cfg.load();
        
        industryBlockId = cfg.get(Configuration.CATEGORY_BLOCK, "industryblockid", Defaults.BLOCK_INDUSTRY_ID).getInt();
        
        industryItemId = cfg.get(Configuration.CATEGORY_ITEM, "industryitemid", Defaults.ITEM_INDUSTRY_ID).getInt() + CDECore.ID_SHIFT;
        
        // Individual TileEntity Sound Settings.
        
        int defaultVolume = 20;
        int defaultPitch = 100;
        
        // Volume
        barrelVolume = cfg.get(Configuration.CATEGORY_GENERAL, "barrelvolume", defaultVolume, "Barrel Volume").getInt();
        
        // Pitch
        barrelPitch = cfg.get(Configuration.CATEGORY_GENERAL, "barrelpitch", defaultPitch, "Barrel Pitch").getInt();
        
        cfg.save();
    }

    @Init
    public void init(FMLInitializationEvent event) 
    {   
        proxy.registerRenderers();
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
