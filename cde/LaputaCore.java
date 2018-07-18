/**
 *
 * @author StormTiberius
 */

package cde;

import cde.core.Version;
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
import java.io.File;
import net.minecraftforge.common.Configuration;

@Mod(modid="CDE|Laputa", name="Laputa", version=Version.VERSION, dependencies = "required-after:Forge@[6.6.2.534,);required-after:CDE|Core")
@NetworkMod(clientSideRequired=true, serverSideRequired=true)
public class LaputaCore
{
    private static Configuration cfg;
    
    @PreInit
    public static void preInit(FMLPreInitializationEvent event) 
    {
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/laputa.cfg"));
        cfg.load();

        cfg.save();
    }

    @Init
    public static void init(FMLInitializationEvent event) 
    {

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
