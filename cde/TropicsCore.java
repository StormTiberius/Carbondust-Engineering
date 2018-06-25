/**
 *
 * @author StormTiberius
 */

package cde;

import cde.core.Version;
import cde.tropics.BiomeGenTropicsBeach;
import cde.tropics.BiomeGenTropicsIsland;
import cde.tropics.BiomeGenTropicsOcean;
import cde.tropics.EntityBatty;
import cde.tropics.EntitySquiddy;
import cde.tropics.WorldChunkManagerTropics;
import cde.tropics.WorldProviderTropics;
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
import java.io.File;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

@Mod(modid="CDE|Tropics", name="Tropics", version=Version.VERSION, dependencies = "required-after:Forge@[6.6.2.534,);required-after:CDE|Core")
@NetworkMod(clientSideRequired=true, serverSideRequired=true)
public class TropicsCore
{
    private static Configuration cfg;
    private static boolean enabled;
    private static int islandId,beachId,oceanId,dimensionId,islandSize,islandScarcity,liquidId;
    public static BiomeGenBase island,beach,ocean;
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) 
    {
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/tropics.cfg"));
        
        cfg.load();
        
        enabled = cfg.get(Configuration.CATEGORY_GENERAL, "enabled", false, "Enable/Disable Tropics").getBoolean(false);
        
        islandId = cfg.get(Configuration.CATEGORY_GENERAL, "islandid", 23, "Island biome id").getInt();
        beachId = cfg.get(Configuration.CATEGORY_GENERAL, "beachid", 24, "Beach biome id").getInt();
        oceanId = cfg.get(Configuration.CATEGORY_GENERAL, "oceanid", 25, "Ocean biome id").getInt();
        
        dimensionId = cfg.get(Configuration.CATEGORY_GENERAL, "dimensionid", 2, "Tropics dimension id").getInt();
        
        islandSize = cfg.get(Configuration.CATEGORY_GENERAL, "islandsize", 4, "Island size, 4-6 recommended").getInt();
        islandScarcity = cfg.get(Configuration.CATEGORY_GENERAL, "islandscarcity", 100, "Island scarcity, 100 default").getInt();

        cfg.save();
      
        if(enabled)
        {
            island = (new BiomeGenTropicsIsland(islandId)).setColor(16440917).setBiomeName("Tropics").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.0F, 0.1F);
            beach = (new BiomeGenTropicsBeach(beachId)).setColor(16440917).setBiomeName("Tropics").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.0F, 0.1F);
            ocean = (new BiomeGenTropicsOcean(oceanId)).setColor(16440917).setBiomeName("Tropics").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(-1.0F, 0.1F);
            
            WorldChunkManagerTropics.allowedBiomes.clear();
            WorldChunkManagerTropics.allowedBiomes.add(island);
            WorldChunkManagerTropics.allowedBiomes.add(beach);
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
            
            EntityRegistry.registerModEntity(EntitySquiddy.class, "Squiddy", 0, this, 50, 1, true);
            EntityRegistry.registerModEntity(EntityBatty.class, "Batty", 1, this, 50, 1, true);
        }
        
        BiomeManager.addVillageBiome(island, enabled);
        BiomeManager.addVillageBiome(beach, enabled);
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
    
    public static int getDimensionId()
    {
        return dimensionId;
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
