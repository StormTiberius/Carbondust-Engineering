/**
 *
 * @author StormTiberius
 */

package cde.world;

import cde.world.ember.BiomeGenEmber;
import cde.world.ember.WorldGenOil;
import cde.world.ember.WorldProviderEmber;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import java.io.File;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

public class EmberModule
{   
    private static Configuration cfg;
    private static boolean enabled;
    private static int emberId,liquidId;
    public static BiomeGenBase ember;
    
    public static void preInit(FMLPreInitializationEvent event) 
    {
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/ember.cfg"));
        cfg.load();
        
        enabled = cfg.get(Configuration.CATEGORY_GENERAL, "enabled", false, "Enable/Disable Ember").getBoolean(false);
        emberId = cfg.get(Configuration.CATEGORY_GENERAL, "emberid", 23, "Ember biome id").getInt();
        
        cfg.save();
        
        if(enabled)
        {
            ember = (new BiomeGenEmber(emberId)).setColor(112).setBiomeName("Ember");
        }
      
    }

    public static void init(FMLInitializationEvent event) 
    {
        if(enabled)
        {
            BiomeManager.addStrongholdBiome(ember);
            
            DimensionManager.unregisterProviderType(0);
            DimensionManager.registerProviderType(0, WorldProviderEmber.class, true);
            
            DungeonHooks.addDungeonMob("Creeper", 150);
            DungeonHooks.addDungeonMob("Enderman", 150);
            
            GameRegistry.registerWorldGenerator(new WorldGenOil());
        }
    }

    public static void postInit(FMLPostInitializationEvent event) 
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

    public static void serverStarting(FMLServerStartingEvent event)
    {
        
    }
    
    public static int getLiquidId()
    {
        return liquidId;
    }
}
