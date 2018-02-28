/**
 *
 * @author StormTiberius
 */

package cde;

import cde.world.EmberModule;
import cde.world.WorldEventManager;
import cde.world.atlantic.BiomeGenAtlantic;
import cde.world.atlantic.WorldProviderAtlantic;
import cde.world.pacific.BiomeGenPacificBeach;
import cde.world.pacific.BiomeGenPacificIsland;
import cde.world.pacific.BiomeGenPacificOcean;
import cde.world.pacific.WorldProviderPacific;
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
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid="CDE|World", name="World", version="1.0.3", dependencies = "required-after:Forge@[6.6.2.534,);required-after:CDE|Core")
@NetworkMod(clientSideRequired=true, serverSideRequired=true)
public class WorldCore
{
    private static Configuration cfg;
    private static int islandSize,islandScarcity,islandId,beachId,oceanId,atlanticId,pacificDimId,atlanticDimId;
    private static boolean pacificDimension,atlanticDimension,pacificDayMode,atlanticDayMode,pacificFeatures;
    
    public static BiomeGenBase island,beach,ocean,atlantic;
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) 
    {
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/world.cfg"));
        
        cfg.load();
        
        islandSize = cfg.get(Configuration.CATEGORY_GENERAL, "islandsize", 4, "Island size, 4-6 recommended").getInt();
        islandScarcity = cfg.get(Configuration.CATEGORY_GENERAL, "islandscarcity", 100, "Island scarcity, 100 default").getInt();
        
        islandId = cfg.get(Configuration.CATEGORY_GENERAL, "islandid", 23, "Pacific Island Biome ID").getInt();
        beachId = cfg.get(Configuration.CATEGORY_GENERAL, "beachid", 24, "Pacific Beach Biome ID").getInt();
        oceanId = cfg.get(Configuration.CATEGORY_GENERAL, "oceanid", 25, "Pacific Ocean Biome ID").getInt();
        atlanticId = cfg.get(Configuration.CATEGORY_GENERAL, "atlanticid", 26, "Atlantic Biome ID").getInt(); 
        
        pacificDimId = cfg.get(Configuration.CATEGORY_GENERAL, "pacificdimid", 0, "Pacific Dimension ID").getInt();
        atlanticDimId = cfg.get(Configuration.CATEGORY_GENERAL, "atlanticdimid", 2, "Atlantic Dimension ID").getInt();

        pacificDimension = cfg.get(Configuration.CATEGORY_GENERAL, "pacificdimension", true, "Pacific Dimension").getBoolean(true);
        atlanticDimension = cfg.get(Configuration.CATEGORY_GENERAL, "atlanticdimension", true, "Atlantic Dimension").getBoolean(true);
        
        pacificDayMode = cfg.get(Configuration.CATEGORY_GENERAL, "pacificdaymode", false, "Always day in Pacific").getBoolean(false);
        atlanticDayMode = cfg.get(Configuration.CATEGORY_GENERAL, "atlanticdaymode", false, "Always day in Atlantic").getBoolean(false);
        
        pacificFeatures = cfg.get(Configuration.CATEGORY_GENERAL, "pacificfeatures", true, "Prevent custom features from generating in the Pacific such as BC oil").getBoolean(true);
        
        cfg.save();
      
        island = (new BiomeGenPacificIsland(islandId)).setColor(16440917).setBiomeName("Pacific").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.0F, 0.1F);
        beach = (new BiomeGenPacificBeach(beachId)).setColor(16440917).setBiomeName("Pacific").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.0F, 0.1F);
        ocean = (new BiomeGenPacificOcean(oceanId)).setColor(16440917).setBiomeName("Pacific").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(-1.0F, 0.1F);   
        atlantic = (new BiomeGenAtlantic(atlanticId)).setColor(16440917).setBiomeName("Atlantic").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(-1.0F, 0.4F);
        
        EmberModule.preInit(event);
    }

    @Init
    public void init(FMLInitializationEvent event) 
    {   
        if(pacificFeatures)
        {
            MinecraftForge.TERRAIN_GEN_BUS.register(new WorldEventManager());
        }
        
        BiomeManager.addSpawnBiome(island);
        BiomeManager.addSpawnBiome(beach);
        BiomeManager.addStrongholdBiome(atlantic);
        
        if(pacificDimension)
        {
            if(pacificDimId == 0 || pacificDimId == 1 || pacificDimId == -1)
            {
                DimensionManager.unregisterProviderType(pacificDimId);
                DimensionManager.registerProviderType(pacificDimId, WorldProviderPacific.class, true);
            }
            else
            {
                DimensionManager.registerProviderType(pacificDimId, WorldProviderPacific.class, true);
                DimensionManager.registerDimension(pacificDimId, pacificDimId);
            }

        }
        
        if(atlanticDimension)
        {
            DimensionManager.registerProviderType(atlanticDimId, WorldProviderAtlantic.class, true);
            DimensionManager.registerDimension(atlanticDimId, atlanticDimId);
        }
        
        EmberModule.init(event);
    }

    @PostInit
    public void postInit(FMLPostInitializationEvent event) 
    {
        EmberModule.postInit(event);
    }

    @ServerStarting
    public void serverStarting(FMLServerStartingEvent event)
    {
        EmberModule.serverStarting(event);
    }
    
    public static byte getIslandSize()
    {
        return (byte)islandSize;
    }
    
    public static int getIslandScarcity()
    {
        return islandScarcity;
    }
    
    public static boolean isIsland(int id)
    {
        return id == island.biomeID;
    }
    
    public static boolean isBeach(int id)
    {
        return id == beach.biomeID;
    }

    public static boolean isOcean(int id)
    {
        return id == ocean.biomeID;
    }
    
    public static boolean isDayMode(int id)
    {
        if(id == pacificDimId)
        {
            return pacificDayMode;
        }
    
        if(id == atlanticDimId)
        {
            return atlanticDayMode;
        }
        
        return false;
    }
}
