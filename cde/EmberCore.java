/**
 *
 * @author StormTiberius
 */

package cde;

import cde.core.Version;
import cde.ember.BiomeGenEmber;
import cde.ember.EmberEventManager;
import cde.ember.EntityBatEmber;
import cde.ember.EntitySquidEmber;
import cde.ember.WorldProviderEmber;
import cde.ember.WorldTypeEmber;
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
import cpw.mods.fml.common.registry.LanguageRegistry;
import java.io.File;
import net.minecraft.block.Block;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

@Mod(modid="CDE|Ember", name="Ember", version=Version.VERSION, dependencies = "required-after:Forge@[6.6.2.534,);required-after:CDE|Core")
@NetworkMod(clientSideRequired=true, serverSideRequired=true)
public class EmberCore
{
    public static final String EMBER_SPAWN_LOCATION_KEYWORD = "EmberSpawnLocation";
    
    private static Configuration cfg;
    private static boolean enabled,mobSpawnRules;
    private static int biomeId,worldTypeId,liquidId;
    
    public static BiomeGenBase biome;
    public static WorldType ember;
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) 
    {
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/ember.cfg"));
        cfg.load();
        
        enabled = cfg.get(Configuration.CATEGORY_GENERAL, "enabled", false, "Enable/Disable Ember").getBoolean(false);
        mobSpawnRules = cfg.get(Configuration.CATEGORY_GENERAL, "mobSpawnRules", true, "Mob Spawn Rules").getBoolean(true);
        
        biomeId = cfg.get(Configuration.CATEGORY_GENERAL, "emberBiomeId", 23, "Ember Biome Id").getInt();
        worldTypeId = cfg.get(Configuration.CATEGORY_GENERAL, "worldTypeId", 9, "World Type Id").getInt();
        
        cfg.save();
        
        if(enabled)
        {
            biome = (new BiomeGenEmber(biomeId)).setColor(16440917).setBiomeName("Ember").setDisableRain().setTemperatureRainfall(0.8F, 0.4F);
            ember = new WorldTypeEmber(worldTypeId, "ember", 0);
            
            LanguageRegistry.instance().addStringLocalization("generator.ember", "en_US", "Ember");
            
            FMLInterModComms.sendMessage("CDE|Core", "add-oregen-for-world", "Ember");
        }
    }

    @Init
    public void init(FMLInitializationEvent event) 
    {   
        if(enabled)
        {
            DimensionManager.unregisterProviderType(0);
            DimensionManager.registerProviderType(0, WorldProviderEmber.class, true);
        
            EntityRegistry.registerModEntity(EntitySquidEmber.class, "EmberSquid", 0, this, 64, 3, true);
            EntityRegistry.registerModEntity(EntityBatEmber.class, "EmberBat", 1, this, 80, 3, false);
            
            MinecraftForge.EVENT_BUS.register(new EmberEventManager(mobSpawnRules));
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
}
