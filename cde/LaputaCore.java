/**
 *
 * @author StormTiberius
 */

package cde;

import cde.core.Version;
import cde.laputa.BiomeGenLaputa;
import cde.laputa.WorldProviderLaputa;
import cde.laputa.gog.ClientTickHandler;
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
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
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
    
    private static final int[] WEATHER_DURATIONS = {12000, 3600, 168000, 12000, 12000, 12000, 168000, 12000, 0, 0};
    private static int[] weatherDurations = WEATHER_DURATIONS;
    
    private static int dayCycleDurationMultiplier = 1;
    
    public static BiomeGenBase laputa;
    
    @PreInit
    public static void preInit(FMLPreInitializationEvent event) 
    {
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/laputa.cfg"));
        cfg.load();
        
        enabled = cfg.get(Configuration.CATEGORY_GENERAL, "enabled", false, "Enable/Disable Laputa Dimension").getBoolean(false);
        biomeId = cfg.get(Configuration.CATEGORY_GENERAL, "biomeId", 23, "Laputa Biome Id").getInt();
        dimensionId = cfg.get(Configuration.CATEGORY_GENERAL, "dimensionId", 2, "Laputa Dimension Id").getInt();
        
        weatherDurations = cfg.get(Configuration.CATEGORY_GENERAL, "weatherDurations", WEATHER_DURATIONS, "Weather Durations").getIntList();
        
        dayCycleDurationMultiplier = cfg.get(Configuration.CATEGORY_GENERAL, "dayCycleDurationMultiplier", 1, "Day Cycle Multiplier").getInt();
                
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
            
            TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
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
    
    public static int getWeatherDuration(int index)
    {
        switch(index)
        {
            case 3:return weatherDurations[index] + weatherDurations[9];
            case 7: return weatherDurations[index] + weatherDurations[8];
            default: return weatherDurations[index];
        }
    }
    
    public static int getDayCycleDurationMultiplier()
    {
        return dayCycleDurationMultiplier;
    }
}
