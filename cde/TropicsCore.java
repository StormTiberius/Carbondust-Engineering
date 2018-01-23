/**
 *
 * @author StormTiberius
 */

package cde;

import cde.tropics.BiomeGenTropicsBeach;
import cde.tropics.BiomeGenTropicsIsland;
import cde.tropics.BiomeGenTropicsOcean;
import cde.tropics.WorldTypeTropics;
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
import cpw.mods.fml.common.registry.LanguageRegistry;
import java.io.File;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.Configuration;

@Mod(modid="CDE|Tropics", name="Tropics", version="1.0", dependencies = "required-after:Forge@[6.6.2.534,);required-after:CDE|Core")
@NetworkMod(clientSideRequired=true, serverSideRequired=true)
public class TropicsCore
{
    private static Configuration cfg;
    private static int islandSize,islandScarcity,islandId,beachId,oceanId,tropicsId;
    
    public static BiomeGenBase island,beach,ocean;
    public static WorldType tropics;
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) 
    {
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/tropics.cfg"));
        
        cfg.load();
        
        islandSize = cfg.get(Configuration.CATEGORY_GENERAL, "islandsize", 4, "Island size, 4-6 recommended").getInt();
        islandScarcity = cfg.get(Configuration.CATEGORY_GENERAL, "islandscarcity", 100, "Island scarcity, 100 default").getInt();
        
        islandId = cfg.get(Configuration.CATEGORY_GENERAL, "islandid", 23, "Island Biome ID").getInt();
        beachId = cfg.get(Configuration.CATEGORY_GENERAL, "beachid", 24, "Beach Biome ID").getInt();
        oceanId = cfg.get(Configuration.CATEGORY_GENERAL, "oceanid", 25, "Ocean Biome ID").getInt();
        
        tropicsId = cfg.get(Configuration.CATEGORY_GENERAL, "tropicsid", 10, "Tropics ID").getInt();

        cfg.save();
      
        island = (new BiomeGenTropicsIsland(islandId)).setColor(16440917).setBiomeName("Tropics").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.0F, 0.1F);
        beach = (new BiomeGenTropicsBeach(beachId)).setColor(16440917).setBiomeName("Tropics").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.0F, 0.1F);
        ocean = (new BiomeGenTropicsOcean(oceanId)).setColor(16440917).setBiomeName("Tropics").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(-1.0F, 0.1F);
        
        tropics = new WorldTypeTropics(tropicsId, "TROPICS");
    }

    @Init
    public void load(FMLInitializationEvent event) 
    {   
        LanguageRegistry.instance().addStringLocalization("generator.TROPICS", "en_US", "Tropics");
        
        BiomeManager.addSpawnBiome(island);
        BiomeManager.addSpawnBiome(beach);
    }

    @PostInit
    public void postInit(FMLPostInitializationEvent event) 
    {
        
    }

    @ServerStarting
    public void serverStarting(FMLServerStartingEvent event)
    {
        
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
