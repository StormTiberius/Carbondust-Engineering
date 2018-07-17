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

@Mod(modid="CDE|Ember", name="Ember", version=Version.VERSION, dependencies = "required-after:Forge@[6.6.2.534,);required-after:CDE|Core")
@NetworkMod(clientSideRequired=true, serverSideRequired=true)
public class WorldCore
{   
    private static Configuration cfg;
    private static boolean enabled;
    private static int biomeId,dimensionId,liquidId;
    public static BiomeGenBase ember;
    public static final String EMBER_SPAWN_LOCATION_KEYWORD = "EmberSpawnLocation";
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) 
    {
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/ember.cfg"));
        cfg.load();
        
        enabled = cfg.get(Configuration.CATEGORY_GENERAL, "enabled", false, "Enable/Disable Ember").getBoolean(false);
        biomeId = cfg.get(Configuration.CATEGORY_GENERAL, "biomeid", 23, "Ember biome id").getInt();
        dimensionId = cfg.get(Configuration.CATEGORY_GENERAL, "dimensionid", 2, "Ember dimension id").getInt();
        
        cfg.save();
        
        if(enabled)
        {
            ember = (new BiomeGenEmber(biomeId)).setColor(112).setBiomeName("Ember");
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
                DimensionManager.registerProviderType(dimensionId, WorldProviderEmber.class, true);
            }
            else
            {
                DimensionManager.registerProviderType(dimensionId, WorldProviderEmber.class, true);
                DimensionManager.registerDimension(dimensionId, dimensionId);
            }
            
            GameRegistry.registerWorldGenerator(new WorldGenOil(dimensionId));
            
            MinecraftForge.EVENT_BUS.register(new EmberEventManager(dimensionId));
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
        return dimensionId;
    }
}
