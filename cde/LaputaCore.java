/**
 *
 * @author StormTiberius
 */

package cde;

import cde.core.Version;
import cde.laputa.BiomeGenLaputa;
import cde.laputa.WorldProviderLaputa;
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
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DimensionManager;

@Mod(modid="CDE|Laputa", name="Laputa", version=Version.VERSION, dependencies = "required-after:Forge@[6.6.2.534,);required-after:CDE|Core")
@NetworkMod(clientSideRequired=true, serverSideRequired=true)
public class LaputaCore
{
    private static Configuration cfg;
    
    private static boolean enabled;
    private static int biomeId,dimensionId;
    
    public static BiomeGenBase laputa;
    
    @PreInit
    public static void preInit(FMLPreInitializationEvent event) 
    {
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/laputa.cfg"));
        cfg.load();
        
        enabled = cfg.get(Configuration.CATEGORY_GENERAL, "enabled", false, "Enable/Disable Laputa Dimension").getBoolean(false);
        biomeId = cfg.get(Configuration.CATEGORY_GENERAL, "biomeId", 23, "Laputa Biome Id").getInt();
        dimensionId = cfg.get(Configuration.CATEGORY_GENERAL, "dimensionId", 2, "Laputa Dimension Id").getInt();
        
        cfg.save();
        
        if(enabled)
        {
            laputa = new BiomeGenLaputa(biomeId).setColor(16440917).setBiomeName("Laputa").setTemperatureRainfall(0.8F, 0.4F);
        }
    }

    @Init
    public static void init(FMLInitializationEvent event) 
    {
        if(enabled)
        {
            if(dimensionId == 0 || dimensionId == -1 || dimensionId == 1)
            {
                DimensionManager.unregisterProviderType(dimensionId);
                DimensionManager.registerProviderType(dimensionId, WorldProviderLaputa.class, true);
            }
            else
            {
                DimensionManager.registerProviderType(dimensionId, WorldProviderLaputa.class, true);
                DimensionManager.registerDimension(dimensionId, dimensionId);
            }
        }
    }
    
    @PostInit
    public void postInit(FMLPostInitializationEvent event) 
    {
        
    }

    @ServerStarting
    public void serverStarting(FMLServerStartingEvent event)
    {
        
    }
    
    public static int getDimensionId()
    {
        return dimensionId;
    }
}
