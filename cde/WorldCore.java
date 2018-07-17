/**
 *
 * @author StormTiberius
 */

package cde;

import cde.core.Version;
import cde.world.ember.EmberEventManager;
import cde.world.ember.BiomeGenEmber;
import cde.world.ember.WorldGenOil;
import cde.world.ember.WorldProviderEmber;
import cde.world.tropics.BiomeGenPacificBeach;
import cde.world.tropics.BiomeGenPacificIsland;
import cde.world.tropics.BiomeGenPacificOcean;
import cde.world.tropics.WorldChunkManagerPacific;
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
    
    private static boolean pacificDimension,atlanticDimension,emberDimension,laputaDimension;
    
    private static int oasisBiomeId,islandBiomeId,beachBiomeId,pacificBiomeId,atlanticBiomeId,emberBiomeId,laputaBiomeId;
    private static int pacificDimensionId,atlanticDimensionId,emberDimensionId,laputaDimensionId;
    private static int islandSize,islandScarcity,liquidId;
    
    public static BiomeGenBase oasis,island,beach,pacific,atlantic,ember,laputa;
    public static final String EMBER_SPAWN_LOCATION_KEYWORD = "EmberSpawnLocation";
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) 
    {
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/world.cfg"));
        cfg.load();
        
        pacificDimension = cfg.get(Configuration.CATEGORY_GENERAL, "pacificDimension", false, "Enable/Disable Pacific Dimension").getBoolean(false);
        atlanticDimension = cfg.get(Configuration.CATEGORY_GENERAL, "atlanticDimension", false, "Enable/Disable Atlantic Dimension").getBoolean(false);
        emberDimension = cfg.get(Configuration.CATEGORY_GENERAL, "emberDimension", false, "Enable/Disable Ember Dimension").getBoolean(false);
        laputaDimension = cfg.get(Configuration.CATEGORY_GENERAL, "laputaDimension", false, "Enable/Disable Laputa Dimension").getBoolean(false);
        
        pacificDimensionId = cfg.get(Configuration.CATEGORY_GENERAL, "pacificDimensionId", 2, "Pacific Dimension Id").getInt();
        atlanticDimensionId = cfg.get(Configuration.CATEGORY_GENERAL, "atlanticDimensionId", 3, "Atlantic Dimension Id").getInt();
        emberDimensionId = cfg.get(Configuration.CATEGORY_GENERAL, "emberDimensionId", 4, "Ember Dimension Id").getInt();
        laputaDimensionId = cfg.get(Configuration.CATEGORY_GENERAL, "laputaDimensionId", 5, "Laputa Dimension Id").getInt();
        
        oasisBiomeId = cfg.get(Configuration.CATEGORY_GENERAL, "oasisBiomeId", 23, "Oasis Biome Id").getInt();
        islandBiomeId = cfg.get(Configuration.CATEGORY_GENERAL, "islandBiomeId", 24, "Island Biome Id").getInt();
        beachBiomeId = cfg.get(Configuration.CATEGORY_GENERAL, "beachBiomeId", 25, "Beach Biome Id").getInt();
        pacificBiomeId = cfg.get(Configuration.CATEGORY_GENERAL, "pacificBiomeId", 26, "Pacific Biome Id").getInt();
        atlanticBiomeId = cfg.get(Configuration.CATEGORY_GENERAL, "atlanticBiomeId", 27, "Atlantic Biome Id").getInt();
        emberBiomeId = cfg.get(Configuration.CATEGORY_GENERAL, "emberBiomeId", 28, "Ember Biome Id").getInt();
        laputaBiomeId = cfg.get(Configuration.CATEGORY_GENERAL, "laputaBiomeId", 29, "Laputa Biome Id").getInt();
        
        islandSize = cfg.get(Configuration.CATEGORY_GENERAL, "islandsize", 4, "Island size, 4-6 recommended").getInt();
        islandScarcity = cfg.get(Configuration.CATEGORY_GENERAL, "islandscarcity", 100, "Island scarcity, 100 default").getInt();
        
        cfg.save();
        
        if(pacificDimension)
        {
            oasis = (new BiomeGenPacificIsland(oasisBiomeId)).setColor(16440917).setBiomeName("Pacific").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.0F, 0.1F);
            island = (new BiomeGenPacificIsland(islandBiomeId)).setColor(16440917).setBiomeName("Pacific").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.0F, 0.1F);
            beach = (new BiomeGenPacificBeach(beachBiomeId)).setColor(16440917).setBiomeName("Pacific").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.0F, 0.1F);
            pacific = (new BiomeGenPacificOcean(pacificBiomeId)).setColor(16440917).setBiomeName("Pacific").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(-1.0F, 0.1F);

            WorldChunkManagerPacific.allowedBiomes.clear();
            WorldChunkManagerPacific.allowedBiomes.add(oasis);
            WorldChunkManagerPacific.allowedBiomes.add(island);
            WorldChunkManagerPacific.allowedBiomes.add(beach);
        }
        
        if(atlanticDimension)
        {
            
        }
        
        if(emberDimension)
        {
            ember = (new BiomeGenEmber(emberBiomeId)).setColor(112).setBiomeName("Ember");
        }
        
        if(laputaDimension)
        {
            
        }
    }

    @Init
    public void init(FMLInitializationEvent event) 
    {
        if(pacificDimension)
        {
            if(emberDimensionId == 0 || emberDimensionId == -1 || emberDimensionId == 1)
            {
                DimensionManager.unregisterProviderType(emberDimensionId);
                DimensionManager.registerProviderType(emberDimensionId, WorldProviderEmber.class, true);
            }
            else
            {
                DimensionManager.registerProviderType(emberDimensionId, WorldProviderEmber.class, true);
                DimensionManager.registerDimension(emberDimensionId, emberDimensionId);
            }
                        
            BiomeManager.addStrongholdBiome(oasis);
            BiomeManager.addStrongholdBiome(island);
            BiomeManager.addStrongholdBiome(beach);
            BiomeManager.addStrongholdBiome(pacific);

            BiomeManager.addVillageBiome(oasis, true);
            BiomeManager.addVillageBiome(island, true);
            BiomeManager.addVillageBiome(beach, true);
        }
        
        if(atlanticDimension)
        {
            if(emberDimensionId == 0 || emberDimensionId == -1 || emberDimensionId == 1)
            {
                DimensionManager.unregisterProviderType(emberDimensionId);
                DimensionManager.registerProviderType(emberDimensionId, WorldProviderEmber.class, true);
            }
            else
            {
                DimensionManager.registerProviderType(emberDimensionId, WorldProviderEmber.class, true);
                DimensionManager.registerDimension(emberDimensionId, emberDimensionId);
            }
            
            BiomeManager.addSpawnBiome(pacific);
            BiomeManager.addStrongholdBiome(atlantic);
        }
        
        if(emberDimension)
        {
            if(emberDimensionId == 0 || emberDimensionId == -1 || emberDimensionId == 1)
            {
                DimensionManager.unregisterProviderType(emberDimensionId);
                DimensionManager.registerProviderType(emberDimensionId, WorldProviderEmber.class, true);
            }
            else
            {
                DimensionManager.registerProviderType(emberDimensionId, WorldProviderEmber.class, true);
                DimensionManager.registerDimension(emberDimensionId, emberDimensionId);
            }
            
            GameRegistry.registerWorldGenerator(new WorldGenOil(emberDimensionId));
            
            if(emberDimensionId == 0)
            {
                MinecraftForge.EVENT_BUS.register(new EmberEventManager(emberDimensionId));
            }
        }
        
        if(laputaDimension)
        {
            if(emberDimensionId == 0 || emberDimensionId == -1 || emberDimensionId == 1)
            {
                DimensionManager.unregisterProviderType(emberDimensionId);
                DimensionManager.registerProviderType(emberDimensionId, WorldProviderEmber.class, true);
            }
            else
            {
                DimensionManager.registerProviderType(emberDimensionId, WorldProviderEmber.class, true);
                DimensionManager.registerDimension(emberDimensionId, emberDimensionId);
            }
        }
    }

    @PostInit
    public void postInit(FMLPostInitializationEvent event) 
    {
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
    
    public static int getLiquidId()
    {
        return liquidId;
    }
    
    public static int getPacificDimensionId()
    {
        return pacificDimensionId;
    }
    
    public static int getAtlanticDimensionId()
    {
        return atlanticDimensionId;
    }
        
    public static int getEmberDimensionId()
    {
        return emberDimensionId;
    }
            
    public static int getLaputaDimensionId()
    {
        return laputaDimensionId;
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
        return id == island.biomeID || id == oasis.biomeID;
    }
    
    public static boolean isBeach(int id)
    {
        return id == beach.biomeID;
    }

    public static boolean isOcean(int id)
    {
        return id == pacific.biomeID;
    }
}
