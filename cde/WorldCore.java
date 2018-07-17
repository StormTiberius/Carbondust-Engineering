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
    private static boolean tropics,cave,laputa;
    private static int islandBiomeId,beachBiomeId,oceanBiomeId,caveBiomeId,laputaBiomeId,tropicsDimensionId,caveDimensionId,laputaDimensionId,liquidId;
    public static BiomeGenBase ember;
    public static final String EMBER_SPAWN_LOCATION_KEYWORD = "EmberSpawnLocation";
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) 
    {
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/world.cfg"));
        cfg.load();
        
        tropics = cfg.get(Configuration.CATEGORY_GENERAL, "tropics", false, "Enable/Disable Tropics Dimension").getBoolean(false);
        cave = cfg.get(Configuration.CATEGORY_GENERAL, "cave", false, "Enable/Disable Cave Dimension").getBoolean(false);
        laputa = cfg.get(Configuration.CATEGORY_GENERAL, "laputa", false, "Enable/Disable Laputa Dimension").getBoolean(false);
        
        tropicsDimensionId = cfg.get(Configuration.CATEGORY_GENERAL, "tropicsDimensionId", 2, "Tropics Dimension Id").getInt();
        caveDimensionId = cfg.get(Configuration.CATEGORY_GENERAL, "caveDimensionId", 3, "Cave Dimension Id").getInt();
        laputaDimensionId = cfg.get(Configuration.CATEGORY_GENERAL, "laputaDimensionId", 4, "Laputa Dimension Id").getInt();
        
        caveBiomeId = cfg.get(Configuration.CATEGORY_GENERAL, "caveBiomeId", 26, "Cave Biome Id").getInt();
        laputaBiomeId = cfg.get(Configuration.CATEGORY_GENERAL, "laputaBiomeId", 27, "Laputa Biome Id").getInt();
        
        cfg.save();
        
        if(cave)
        {
            ember = (new BiomeGenEmber(caveBiomeId)).setColor(112).setBiomeName("Ember");
        }
      
    }

    @Init
    public void init(FMLInitializationEvent event) 
    {
        if(tropics)
        {
            if(tropicsDimensionId == 0 || tropicsDimensionId == -1 || tropicsDimensionId == 1)
            {
                DimensionManager.unregisterProviderType(tropicsDimensionId);
                DimensionManager.registerProviderType(tropicsDimensionId, WorldProviderEmber.class, true);
            }
            else
            {
                DimensionManager.registerProviderType(tropicsDimensionId, WorldProviderEmber.class, true);
                DimensionManager.registerDimension(tropicsDimensionId, tropicsDimensionId);
            }
            
            GameRegistry.registerWorldGenerator(new WorldGenOil(tropicsDimensionId));
            
            MinecraftForge.EVENT_BUS.register(new EmberEventManager(tropicsDimensionId));
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
    
    public static int getDimensionId()
    {
        return caveDimensionId;
    }
}
