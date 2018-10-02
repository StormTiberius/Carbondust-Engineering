/**
 *
 * @author StormTiberius
 */

package cde;

import cde.core.Version;
import cde.world.ember.BiomeGenEmber;
import cde.world.ember.EmberEventManager;
import cde.world.ember.WorldGenOil;
import cde.world.ember.WorldProviderEmber;
import cde.world.tropics.BiomeGenTropicsBeach;
import cde.world.tropics.BiomeGenTropicsIsland;
import cde.world.tropics.BiomeGenTropicsOcean;
import cde.world.tropics.EventManagerTropics;
import cde.world.tropics.WorldChunkManagerTropics;
import cde.world.tropics.WorldProviderTropics;
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
import cpw.mods.fml.common.registry.GameRegistry;
import java.io.File;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

@Mod(modid="CDE|World", name="World", version=Version.VERSION, dependencies = "required-after:Forge@[6.6.2.534,);required-after:CDE|Core")
@NetworkMod(clientSideRequired=true, serverSideRequired=true)
public class WorldCore
{
    private static Configuration cfg;
    private static boolean tropicsDimension,emberDimension,civSpawn;
    private static int islandBiomeId,beachBiomeId,oceanBiomeId,emberBiomeId,tropicsDimensionId,emberDimensionId,islandSize,islandScarcity,liquidId,indigoFlowerId;
    
    private static final int[] WEATHER_DURATIONS = {12000, 3600, 168000, 12000, 12000, 12000, 168000, 12000, 0, 0};
    private static int[] weatherDurations = WEATHER_DURATIONS;
    
    private static int dayCycleDurationMultiplier = 1;
    
    public static BiomeGenBase island,beach,ocean,ember;
    
    public static final String EMBER_SPAWN_LOCATION_KEYWORD = "EmberSpawnLocation";
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) 
    {
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/world.cfg"));
        
        cfg.load();
        
        tropicsDimension = cfg.get(Configuration.CATEGORY_GENERAL, "tropicsDimension", true, "Enable/Disable Tropics Dimension").getBoolean(false);
        emberDimension = cfg.get(Configuration.CATEGORY_GENERAL, "emberDimension", true, "Enable/Disable Ember Dimension").getBoolean(false);
        
        islandBiomeId = cfg.get(Configuration.CATEGORY_GENERAL, "islandBiomeId", 23, "Island Biome Id").getInt();
        beachBiomeId = cfg.get(Configuration.CATEGORY_GENERAL, "beachBiomeId", 24, "Beach Biome Id").getInt();
        oceanBiomeId = cfg.get(Configuration.CATEGORY_GENERAL, "oceanBiomeId", 25, "Ocean Biome Id").getInt();
        emberBiomeId = cfg.get(Configuration.CATEGORY_GENERAL, "emberBiomeId", 26, "Ember Biome Id").getInt();
        
        tropicsDimensionId = cfg.get(Configuration.CATEGORY_GENERAL, "tropicsDimensionId", 2, "Tropics Dimension Id").getInt();
        emberDimensionId = cfg.get(Configuration.CATEGORY_GENERAL, "emberDimensionId", 3, "Ember dimension Id").getInt();
        
        islandSize = cfg.get(Configuration.CATEGORY_GENERAL, "islandSize", 4, "Island Size, 4-6 Recommended").getInt();
        islandScarcity = cfg.get(Configuration.CATEGORY_GENERAL, "islandScarcity", 100, "Island Scarcity, 100 Default").getInt();

        weatherDurations = cfg.get(Configuration.CATEGORY_GENERAL, "weatherDurations", WEATHER_DURATIONS, "Weather Durations").getIntList();
        dayCycleDurationMultiplier = cfg.get(Configuration.CATEGORY_GENERAL, "dayCycleDurationMultiplier", 1, "Day Cycle Duration Multiplier").getInt();
        
        civSpawn = cfg.get(Configuration.CATEGORY_GENERAL, "civSpawn", true, "Custom Spawn Rules").getBoolean(false);
        
        cfg.save();

        if(tropicsDimension)
        {
            island = (new BiomeGenTropicsIsland(islandBiomeId)).setColor(16440917).setBiomeName("Tropics").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.0F, 0.1F);
            beach = (new BiomeGenTropicsBeach(beachBiomeId)).setColor(16440917).setBiomeName("Tropics").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.0F, 0.1F);
            ocean = (new BiomeGenTropicsOcean(oceanBiomeId)).setColor(16440917).setBiomeName("Tropics").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(-1.0F, 0.1F);

            WorldChunkManagerTropics.allowedBiomes.clear();
            WorldChunkManagerTropics.allowedBiomes.add(island);
        }
        
        if(emberDimension)
        {
            ember = (new BiomeGenEmber(emberBiomeId)).setColor(112).setBiomeName("Ember");
        }
    }

    @Init
    public void init(FMLInitializationEvent event) 
    {   
        if(tropicsDimension)
        {
            if(tropicsDimensionId == 0 || tropicsDimensionId == -1 || tropicsDimensionId == 1)
            {
                DimensionManager.unregisterProviderType(tropicsDimensionId);
                DimensionManager.registerProviderType(tropicsDimensionId, WorldProviderTropics.class, true);
            }
            else
            {
                DimensionManager.registerProviderType(tropicsDimensionId, WorldProviderTropics.class, true);
                DimensionManager.registerDimension(tropicsDimensionId, tropicsDimensionId);
            }
                    
            BiomeManager.addStrongholdBiome(island);
            BiomeManager.addStrongholdBiome(beach);
            BiomeManager.addStrongholdBiome(ocean);

            BiomeManager.addVillageBiome(island, true);
            BiomeManager.addVillageBiome(beach, true);

            if(civSpawn)
            {
                MinecraftForge.EVENT_BUS.register(new EventManagerTropics(getTropicsDimensionId()));
            }
        }
        
        if(emberDimension)
        {
            DimensionManager.registerProviderType(emberDimensionId, WorldProviderEmber.class, true);
            DimensionManager.registerDimension(emberDimensionId, emberDimensionId);
            
            GameRegistry.registerWorldGenerator(new WorldGenOil(emberDimensionId));
            
            MinecraftForge.EVENT_BUS.register(new EmberEventManager(emberDimensionId));
        }
    }

    @PostInit
    public void postInit(FMLPostInitializationEvent event) 
    {
        Block block = CDECore.getBlockByClass("com.eloraam.redpower.world.BlockCustomFlower");
        
        if(block != null)
        {
            indigoFlowerId = block.blockID;
        }
        
        LiquidStack oil = LiquidDictionary.getLiquid("Oil", LiquidContainerRegistry.BUCKET_VOLUME);

        if(oil != null)
        {
            liquidId = oil.itemID;
        }
        else
        {
            liquidId = Block.lavaStill.blockID;
        }
    }

    @ServerStarting
    public void serverStarting(FMLServerStartingEvent event)
    {
        
    }
        
    public static int getFlowerId()
    {
        return indigoFlowerId;
    }
        
    public static int getLiquidId()
    {
        return liquidId;
    }
        
    public static int getTropicsDimensionId()
    {
        return tropicsDimensionId;
    }
    
    public static int getEmberDimensionId()
    {
        return emberDimensionId;
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
}
