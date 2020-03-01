/**
 *
 * @author StormTiberius
 */

package cde;

import cde.core.Version;
import cde.industry.CommonProxy;
import cde.industry.drum.DrumModule;
import cde.industry.machines.ModuleMachine;
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
import java.io.File;
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
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event)
    {
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/industry.cfg"));
        cfg.load();
        
        drumModuleEnabled = cfg.get(Configuration.CATEGORY_GENERAL, "drumModuleEnabled", true, "Enable/Disable Drum Module").getBoolean(true);
        machineModuleEnabled = cfg.get(Configuration.CATEGORY_GENERAL, "machineModuleEnabled", true, "Enable/Disable Machine Module").getBoolean(true);
        
        cfg.save();
        
        if(drumModuleEnabled)
        {
            DrumModule.preInit(event);
        }
        
        if(machineModuleEnabled)
        {
            ModuleMachine.preInit(event);
        }
    }
    
    @Init
    public void init(FMLInitializationEvent event)
    {
        proxy.registerRenderers();
        
        if(drumModuleEnabled)
        {
            DrumModule.init(event);
        }
        
        if(machineModuleEnabled)
        {
            ModuleMachine.init(event);
        }
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
            ModuleMachine.postInit(event);
        }
    }
}
