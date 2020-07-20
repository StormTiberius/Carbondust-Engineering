/**
 *
 * @author StormTiberius
 */

package cde;

import cde.network.PacketHandler;
import cde.core.CommonProxy;
import cde.core.Config;
import cde.core.Info;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid="CDE", name="CarbonDustEngineering", version=Info.VERSION, dependencies = "required-after:Forge@[6.6.2.534,);required-after:IC2;required-after:BuildCraft|Core;required-after:Forestry;required-after:Railcraft;required-after:RedPowerCore;required-after:AppliedEnergistics")
@NetworkMod(channels = { "CDE" }, packetHandler = PacketHandler.class, clientSideRequired=true, serverSideRequired=true)
public class Carbon
{
    @Instance("CDE")
    public static Carbon instance;
    
    @SidedProxy(clientSide = "cde.core.ClientProxy", serverSide = "cde.core.CommonProxy")
    public static CommonProxy proxy;
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event)
    {
        Config.initConfig(event.getModConfigurationDirectory());
    }
    
    @Init
    public void init(FMLInitializationEvent event)
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
