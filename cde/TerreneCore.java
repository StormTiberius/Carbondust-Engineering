/**
 *
 * @author StormTiberius
 */

package cde;

import cde.api.Blocks;
import cde.core.Defaults;
import cde.core.Version;
import cde.ember.BiomeGenEmber;
import cde.ember.EmberEventManager;
import cde.ember.EntityBatEmber;
import cde.ember.EntitySquidEmber;
import cde.ember.WorldProviderEmber;
import cde.tropics.BiomeGenTropicsBeach;
import cde.tropics.BiomeGenTropicsIsland;
import cde.tropics.BiomeGenTropicsOcean;
import cde.tropics.EventManagerTropics;
import cde.tropics.WorldChunkManagerTropics;
import cde.tropics.WorldProviderTropics;
import cde.terrene.BlockPortal;
import cde.terrene.CommandTPD;
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
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import java.io.File;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

@Mod(modid="CDE|Terrene", name="Terrene", version=Version.VERSION, dependencies = "required-after:Forge@[6.6.2.534,);required-after:CDE|Core")
@NetworkMod(clientSideRequired=true, serverSideRequired=true)
public class TerreneCore
{
    private static final int TROPICS = 0;
    private static final int EMBER = 1;
    private static final int WORLDS = 2;
    private static final int[] DIMENSION_ID = new int[WORLDS];
    private static final boolean[] MOB_SPAWN_RULES = new boolean[WORLDS];
    private static final boolean[] ENABLED = new boolean[WORLDS];
    private static final int[] WEATHER_DURATIONS = {12000, 3600, 168000, 12000, 12000, 12000, 168000, 12000, 0, 0};
    
    private static int[] weatherDurations = WEATHER_DURATIONS;
    private static int islandSize,islandScarcity,islandId,beachId,oceanId,emberId,liquidId,indigoFlowerId,portalId;
    private static int dayCycleDurationMultiplier = 1;
    private static boolean sisterIslands; 
    private static Configuration cfg;
    
    public static BiomeGenBase island,beach,ocean,ember;
    public static Block portal;
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) 
    {
        // Terrene
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/terrene.cfg"));
        cfg.load();
        
        portalId = cfg.get(Configuration.CATEGORY_BLOCK, "portalId", Defaults.BLOCK_PORTAL_ID).getInt();
        
        cfg.save();
        
        // Tropics
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/tropics.cfg"));
        cfg.load();
        
        ENABLED[TROPICS] = cfg.get(Configuration.CATEGORY_GENERAL, "tropicsDimension", true, "Enable/Disable Tropics Dimension").getBoolean(true);
        sisterIslands = cfg.get(Configuration.CATEGORY_GENERAL, "sisterIslands", true, "Sister Islands").getBoolean(true);
        MOB_SPAWN_RULES[TROPICS] = cfg.get(Configuration.CATEGORY_GENERAL, "mobSpawnRules", true, "Mob Spawn Rules").getBoolean(true);
        
        islandId = cfg.get(Configuration.CATEGORY_GENERAL, "islandBiomeId", 23, "Island Biome Id").getInt();
        beachId = cfg.get(Configuration.CATEGORY_GENERAL, "beachBiomeId", 24, "Beach Biome Id").getInt();
        oceanId = cfg.get(Configuration.CATEGORY_GENERAL, "oceanBiomeId", 25, "Ocean Biome Id").getInt();
        
        DIMENSION_ID[TROPICS] = cfg.get(Configuration.CATEGORY_GENERAL, "dimensionId", 2, "Tropics Dimension Id").getInt();
        weatherDurations = cfg.get(Configuration.CATEGORY_GENERAL, "weatherDurations", WEATHER_DURATIONS, "Weather Durations").getIntList();
        dayCycleDurationMultiplier = cfg.get(Configuration.CATEGORY_GENERAL, "dayCycleDurationMultiplier", 1, "Day Cycle Duration Multiplier").getInt();
        
        islandSize = cfg.get(Configuration.CATEGORY_GENERAL, "islandSize", 4, "Island Size, 4-6 Recommended").getInt();
        islandScarcity = cfg.get(Configuration.CATEGORY_GENERAL, "islandScarcity", 100, "Island Scarcity, 100 Default").getInt();
        
        cfg.save();

        // Ember
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/ember.cfg"));
        cfg.load();
        
        ENABLED[EMBER] = cfg.get(Configuration.CATEGORY_GENERAL, "enabled", true, "Enable/Disable Ember Dimension").getBoolean(true);
        MOB_SPAWN_RULES[EMBER] = cfg.get(Configuration.CATEGORY_GENERAL, "mobSpawnRules", true, "Mob Spawn Rules").getBoolean(true);
        
        emberId = cfg.get(Configuration.CATEGORY_GENERAL, "emberBiomeId", 26, "Ember Biome Id").getInt();
        DIMENSION_ID[EMBER] = cfg.get(Configuration.CATEGORY_GENERAL, "dimensionId", 3, "Ember Dimension Id").getInt();
        
        cfg.save();
        
        if(portalId > 0)
        {
            portal = new BlockPortal(portalId).setHardness(-1.0F).setStepSound(Block.soundGlassFootstep).setLightValue(0.75F).setBlockName("dimensionalportal");

            GameRegistry.registerBlock(portal, "dimensionalportal");
            LanguageRegistry.addName(portal, "Dimensional Portal");

            Blocks.blockPortal = new ItemStack(portal.blockID, 1, 0);
        }
        
        if(ENABLED[TROPICS])
        {
            island = (new BiomeGenTropicsIsland(islandId)).setColor(16440917).setBiomeName("Tropics").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.0F, 0.1F);
            beach = (new BiomeGenTropicsBeach(beachId)).setColor(16440917).setBiomeName("Tropics").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.0F, 0.1F);
            ocean = (new BiomeGenTropicsOcean(oceanId)).setColor(16440917).setBiomeName("Tropics").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(-1.0F, 0.1F);

            WorldChunkManagerTropics.allowedBiomes.clear();
            WorldChunkManagerTropics.allowedBiomes.add(island);
        }
        
        if(ENABLED[EMBER])
        {
            ember = (new BiomeGenEmber(emberId)).setColor(16440917).setBiomeName("Ember").setDisableRain().setTemperatureRainfall(0.8F, 0.4F);
        }
    }

    @Init
    public void init(FMLInitializationEvent event) 
    {   
        if(ENABLED[TROPICS])
        {
            if(DIMENSION_ID[TROPICS] == 0 || DIMENSION_ID[TROPICS] == -1 || DIMENSION_ID[TROPICS] == 1)
            {
                DimensionManager.unregisterProviderType(DIMENSION_ID[TROPICS]);
                DimensionManager.registerProviderType(DIMENSION_ID[TROPICS], WorldProviderTropics.class, true);
            }
            else
            {
                DimensionManager.registerProviderType(DIMENSION_ID[TROPICS], WorldProviderTropics.class, true);
                DimensionManager.registerDimension(DIMENSION_ID[TROPICS], DIMENSION_ID[TROPICS]);
            }
                    
            BiomeManager.addStrongholdBiome(island);
            BiomeManager.addStrongholdBiome(beach);
            BiomeManager.addStrongholdBiome(ocean);

            BiomeManager.addVillageBiome(island, true);
            BiomeManager.addVillageBiome(beach, true);

            if(MOB_SPAWN_RULES[TROPICS])
            {
                MinecraftForge.EVENT_BUS.register(new EventManagerTropics(DIMENSION_ID[TROPICS]));
            }
        }
        
        if(ENABLED[EMBER])
        {
            if(DIMENSION_ID[EMBER] == 0 || DIMENSION_ID[EMBER] == -1 || DIMENSION_ID[EMBER] == 1)
            {
                DimensionManager.unregisterProviderType(DIMENSION_ID[EMBER]);
                DimensionManager.registerProviderType(DIMENSION_ID[EMBER], WorldProviderEmber.class, true);
            }
            else
            {
                DimensionManager.registerProviderType(DIMENSION_ID[EMBER], WorldProviderEmber.class, true);
                DimensionManager.registerDimension(DIMENSION_ID[EMBER], DIMENSION_ID[EMBER]);
            }
        
            EntityRegistry.registerModEntity(EntitySquidEmber.class, "EmberSquid", 0, this, 64, 3, true);
            EntityRegistry.registerModEntity(EntityBatEmber.class, "EmberBat", 1, this, 80, 3, false);
            
            MinecraftForge.EVENT_BUS.register(new EmberEventManager(MOB_SPAWN_RULES[EMBER]));
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
        if(ENABLED[TROPICS] && ENABLED[EMBER])
        {
            event.registerServerCommand(new CommandTPD());
        }
    }
    
    public static int getFlowerId()
    {
        return indigoFlowerId;
    }
        
    public static int getLiquidId()
    {
        return liquidId;
    }
        
    public static int getDimensionId(int i)
    {
        switch(i)
        {
            case TROPICS: return DIMENSION_ID[TROPICS];
            case EMBER: return DIMENSION_ID[EMBER];
            default: return 0;
        }
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
    
    public static boolean sisterIslands()
    {
        return sisterIslands;
    }
}
