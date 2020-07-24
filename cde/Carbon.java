/**
 *
 * @author StormTiberius
 */

package cde;

import cde.api.Blocks;
import cde.api.Items;
import cde.api.Materials;
import cde.command.CommandCarbon;
import cde.core.ClientTickHandler;
import cde.network.PacketHandler;
import cde.core.CommonProxy;
import cde.core.Config;
import cde.manager.EventManager;
import cde.manager.FuelManager;
import cde.core.Info;
import cde.core.PlayerTracker;
import cde.manager.BlockManager;
import cde.manager.ItemManager;
import cde.manager.RecipeManager;
import cde.tab.CreativeTabResources;
import cde.tab.CreativeTabDrums;
import cde.tab.CreativeTabMachines;
import cde.tab.CreativeTabTools;
import com.google.common.collect.ImmutableList;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLInterModComms.IMCMessage;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import java.util.logging.Logger;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Info.MOD_ID, name = Info.NAME, version = Info.VERSION, dependencies = Info.DEPENDENCIES)
@NetworkMod(channels = { Info.MOD_ID }, packetHandler = PacketHandler.class, clientSideRequired=true, serverSideRequired=true)
public class Carbon
{
    @Instance(Info.MOD_ID)
    public static Carbon instance;
    
    @SidedProxy(clientSide = "cde.core.ClientProxy", serverSide = "cde.core.CommonProxy")
    public static CommonProxy proxy;
    
    private static final Logger LOGGER = Logger.getLogger(Info.MOD_ID);
    
    public static final CreativeTabs TAB_RESOURCES = new CreativeTabResources("cdeTabResource");
    public static final CreativeTabs TAB_DRUMS = new CreativeTabDrums("cdeTabDrum");
    public static final CreativeTabs TAB_MACHINES = new CreativeTabMachines("cdeTabMachine");
    public static final CreativeTabs TAB_TOOLS = new CreativeTabTools("cdeTabTools");
    
    public static Block blockOres,blockResources;
    public static Item itemMaterials;
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event)
    {
        LOGGER.setParent(FMLLog.getLogger());
        LOGGER.info("Starting Carbondust Engineering " + Info.VERSION);
        LOGGER.info("Copyright (c) StormTiberius, 2018");
        LOGGER.info("http://www.github.com/StormTiberius/Carbondust-Engineering");
        
        Config.init(event.getModConfigurationDirectory());
        
        BlockManager.init();
        ItemManager.init();
        
        if(Config.isAudioEnabled())
        {
            proxy.registerSoundEventManager();
            TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
            GameRegistry.registerPlayerTracker(new PlayerTracker());
        }
    }
    
    @Init
    public void init(FMLInitializationEvent event)
    {
        proxy.preloadTextures();
        GameRegistry.registerFuelHandler(new FuelManager());
        MinecraftForge.EVENT_BUS.register(new EventManager());
    }
    
    @PostInit
    public void postInit(FMLPostInitializationEvent event)
    {
        // Remove these when no longer null
        Blocks.drumIron = Blocks.oreApatite;
        Blocks.machineGenerator = Blocks.oreCopper;
        Items.equipmentUtilityHeadGoggles = Materials.dustBrass;
        
        RecipeManager.init();
        
        Config.addWorldGen("Overworld");
        processIMCMessages(FMLInterModComms.fetchRuntimeMessages(this));
    }
    
    @ServerStarting
    public void serverStarting(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandCarbon());
    }
    
    private static void processIMCMessages(ImmutableList<FMLInterModComms.IMCMessage> messages)
    {
        for(IMCMessage message : messages)
        {
            if(message.isStringMessage())
            {
                if(message.key.contains("add-oregen-for-world"))
                {
                    Config.addWorldGen(message.getStringValue());
                }
            }
        }
    }
    
    public static void logInfo(String msg)
    {
        LOGGER.info(msg);
    }
    
    public static void logWarning(String msg)
    {
        LOGGER.warning(msg);
    }
}
