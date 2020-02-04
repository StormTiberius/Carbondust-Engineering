/**
 *
 * @author StormTiberius
 */

package cde;

import cde.api.Blocks;
import cde.core.Defaults;
import cde.core.Version;
import cde.tropics.BiomeGenTropicsBeach;
import cde.tropics.BiomeGenTropicsIsland;
import cde.tropics.BiomeGenTropicsOcean;
import cde.tropics.EventManagerTropics;
import cde.tropics.WorldChunkManagerTropics;
import cde.tropics.WorldProviderTropics;
import cde.tropics.portal.BlockPortal;
import cde.tropics.portal.CommandTPD;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLInterModComms.IMCMessage;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
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

@Mod(modid="CDE|Tropics", name="Tropics", version=Version.VERSION, dependencies = "required-after:Forge@[6.6.2.534,);required-after:CDE|Core")
@NetworkMod(clientSideRequired=true, serverSideRequired=true)
public class TropicsCore
{
    private static Configuration cfg;
    private static boolean enabled,sisterIslands,civspawn;
    private static int islandId,beachId,oceanId,dimensionId,islandSize,islandScarcity,liquidId,indigoFlowerId,emberDimensionId,portalId;
    
    private static final int[] WEATHER_DURATIONS = {12000, 3600, 168000, 12000, 12000, 12000, 168000, 12000, 0, 0};
    private static int[] weatherDurations = WEATHER_DURATIONS;
    
    private static int dayCycleDurationMultiplier = 1;
    
    public static BiomeGenBase island,beach,ocean;
    public static Block portal;
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) 
    {
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/tropics.cfg"));
        
        cfg.load();
        
        enabled = cfg.get(Configuration.CATEGORY_GENERAL, "enabled", false, "Enable/Disable Tropics Dimension").getBoolean(false);
        sisterIslands = cfg.get(Configuration.CATEGORY_GENERAL, "sisterislands", true, "Sister Islands").getBoolean(false);
        civspawn = cfg.get(Configuration.CATEGORY_GENERAL, "civspawn", true, "Custom Spawn Rules").getBoolean(false);
        
        islandId = cfg.get(Configuration.CATEGORY_GENERAL, "islandBiomeId", 23, "Island Biome Id").getInt();
        beachId = cfg.get(Configuration.CATEGORY_GENERAL, "beachBiomeId", 24, "Beach Biome Id").getInt();
        oceanId = cfg.get(Configuration.CATEGORY_GENERAL, "oceanBiomeId", 25, "Ocean Biome Id").getInt();
        
        dimensionId = cfg.get(Configuration.CATEGORY_GENERAL, "dimensionId", 2, "Tropics Dimension Id").getInt();
        weatherDurations = cfg.get(Configuration.CATEGORY_GENERAL, "weatherDurations", WEATHER_DURATIONS, "Weather Durations").getIntList();
        dayCycleDurationMultiplier = cfg.get(Configuration.CATEGORY_GENERAL, "dayCycleDurationMultiplier", 1, "Day Cycle Duration Multiplier").getInt();
        
        islandSize = cfg.get(Configuration.CATEGORY_GENERAL, "islandSize", 4, "Island Size, 4-6 Recommended").getInt();
        islandScarcity = cfg.get(Configuration.CATEGORY_GENERAL, "islandScarcity", 100, "Island Scarcity, 100 Default").getInt();
        
        portalId = cfg.get(Configuration.CATEGORY_BLOCK, "portalId", Defaults.BLOCK_PORTAL_ID).getInt();
        
        cfg.save();

        if(enabled)
        {
            island = (new BiomeGenTropicsIsland(islandId)).setColor(16440917).setBiomeName("Tropics").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.0F, 0.1F);
            beach = (new BiomeGenTropicsBeach(beachId)).setColor(16440917).setBiomeName("Tropics").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.0F, 0.1F);
            ocean = (new BiomeGenTropicsOcean(oceanId)).setColor(16440917).setBiomeName("Tropics").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(-1.0F, 0.1F);

            WorldChunkManagerTropics.allowedBiomes.clear();
            WorldChunkManagerTropics.allowedBiomes.add(island);
            
            if(portalId > 0)
            {
                portal = new BlockPortal(portalId).setHardness(-1.0F).setStepSound(Block.soundGlassFootstep).setLightValue(0.75F).setBlockName("dimensionalportal");
                
                GameRegistry.registerBlock(portal, "dimensionalportal");
                LanguageRegistry.addName(portal, "Dimensional Portal");
                
                Blocks.blockPortal = new ItemStack(portal.blockID, 1, 0);
            }
        }
    }

    @Init
    public void init(FMLInitializationEvent event) 
    {   
        if(enabled)
        {
            if(dimensionId == 0 || dimensionId == -1 || dimensionId == 1)
            {
                DimensionManager.unregisterProviderType(dimensionId);
                DimensionManager.registerProviderType(dimensionId, WorldProviderTropics.class, true);
            }
            else
            {
                DimensionManager.registerProviderType(dimensionId, WorldProviderTropics.class, true);
                DimensionManager.registerDimension(dimensionId, dimensionId);
            }
                    
            BiomeManager.addStrongholdBiome(island);
            BiomeManager.addStrongholdBiome(beach);
            BiomeManager.addStrongholdBiome(ocean);

            BiomeManager.addVillageBiome(island, true);
            BiomeManager.addVillageBiome(beach, true);

            if(civspawn)
            {
                MinecraftForge.EVENT_BUS.register(new EventManagerTropics(getDimensionId()));
            }
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
        
        for(IMCMessage msg : FMLInterModComms.fetchRuntimeMessages(this))
        {
            if(msg.isStringMessage() && msg.key.contentEquals("ember-dimension-id"))
            {
                try
                {
                    emberDimensionId = Integer.parseInt(msg.getStringValue());
                }
                catch(Exception e)
                {
                    emberDimensionId = dimensionId;
                }
            }
        }
    }
    
    @ServerStarting
    public void serverStarting(FMLServerStartingEvent event)
    {
        if(enabled)
        {
            event.registerServerCommand(new CommandTPD());
        }
    }
    
    public static int getEmberDimensionId()
    {
        return emberDimensionId;
    }
    
    public static int getFlowerId()
    {
        return indigoFlowerId;
    }
        
    public static int getLiquidId()
    {
        return liquidId;
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
