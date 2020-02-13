/**
 *
 * @author StormTiberius
 */

package cde;

import cde.core.Version;
import cde.terrene.EntityBatTerrene;
import cde.terrene.EntitySquidTerrene;
import cde.terrene.BiomeGenTropicsBeach;
import cde.terrene.BiomeGenTropicsIsland;
import cde.terrene.BiomeGenTropicsOcean;
import cde.terrene.EventManagerTerrene;
import cde.terrene.WorldChunkManagerTropics;
import cde.terrene.WorldProviderTerrene;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
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

@Mod(modid="CDE|Terrene", name="Terrene", version=Version.VERSION, dependencies = "required-after:Forge@[6.6.2.534,);required-after:CDE|Core")
@NetworkMod(clientSideRequired=true, serverSideRequired=true)
public class TerreneCore
{
    private static final int[] WEATHER_DURATIONS = {12000, 3600, 168000, 12000, 12000, 12000, 168000, 12000, 0, 0};
    
    private static int dayCycleDurationMultiplier = 1;
    private static int islandId,beachId,oceanId,islandSize,islandScarcity,liquidId,indigoFlowerId;
    private static int[] weatherDurations = WEATHER_DURATIONS;
    private static boolean enabled,sisterIslands,mobSpawnRules;
    private static Configuration cfg;
    
    public static BiomeGenBase island,beach,ocean;
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event)
    {
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/terrene.cfg"));
        cfg.load();
        
        enabled = cfg.get(Configuration.CATEGORY_GENERAL, "terrene", false, "Enable/Disable Terrene").getBoolean(false);
        sisterIslands = cfg.get(Configuration.CATEGORY_GENERAL, "sisterIslands", true, "Sister Islands").getBoolean(true);
        mobSpawnRules = cfg.get(Configuration.CATEGORY_GENERAL, "mobSpawnRules", true, "Mob Spawn Rules").getBoolean(true);
        
        islandId = cfg.get(Configuration.CATEGORY_GENERAL, "islandBiomeId", 23, "Island Biome Id").getInt();
        beachId = cfg.get(Configuration.CATEGORY_GENERAL, "beachBiomeId", 24, "Beach Biome Id").getInt();
        oceanId = cfg.get(Configuration.CATEGORY_GENERAL, "oceanBiomeId", 25, "Ocean Biome Id").getInt();
        
        weatherDurations = cfg.get(Configuration.CATEGORY_GENERAL, "weatherDurations", WEATHER_DURATIONS, "Weather Durations").getIntList();
        dayCycleDurationMultiplier = cfg.get(Configuration.CATEGORY_GENERAL, "dayCycleDurationMultiplier", 1, "Day Cycle Duration Multiplier").getInt();
        
        islandSize = cfg.get(Configuration.CATEGORY_GENERAL, "islandSize", 4, "Island Size, 4-6 Recommended").getInt();
        islandScarcity = cfg.get(Configuration.CATEGORY_GENERAL, "islandScarcity", 100, "Island Scarcity, 100 Default").getInt();
        
        cfg.save();
        
        if(enabled)
        {
            island = (new BiomeGenTropicsIsland(islandId)).setColor(16440917).setBiomeName("Tropics").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.0F, 0.1F);
            beach = (new BiomeGenTropicsBeach(beachId)).setColor(16440917).setBiomeName("Tropics").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.0F, 0.1F);
            ocean = (new BiomeGenTropicsOcean(oceanId)).setColor(16440917).setBiomeName("Tropics").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(-1.0F, 0.1F);
            
            WorldChunkManagerTropics.allowedBiomes.clear();
            WorldChunkManagerTropics.allowedBiomes.add(island);
            
            FMLInterModComms.sendMessage("CDE|Core", "add-oregen-for-world", "Terrene");
        }
    }
    
    @Init
    public void init(FMLInitializationEvent event)
    {
        if(enabled)
        {
            DimensionManager.unregisterProviderType(0);
            DimensionManager.registerProviderType(0, WorldProviderTerrene.class, true);
            
            BiomeManager.addVillageBiome(island, true);
            BiomeManager.addVillageBiome(beach, true);
            
            if(mobSpawnRules)
            {
                MinecraftForge.EVENT_BUS.register(new EventManagerTerrene());
            }
            
            EntityRegistry.registerModEntity(EntitySquidTerrene.class, "TerreneSquid", 0, this, 64, 3, true);
            EntityRegistry.registerModEntity(EntityBatTerrene.class, "TerreneBat", 1, this, 80, 3, false);
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
