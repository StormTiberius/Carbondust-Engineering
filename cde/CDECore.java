/**
 *
 * @author StormTiberius
 */

package cde;

import cde.core.CommonProxy;
import cde.core.network.PacketHandler;
import cde.core.resource.ResourceModule;
import cde.core.sound.SoundTickHandler;
import cde.core.speaker.SpeakerModule;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;

@Mod(modid="CDE|Core", name="Core", version="1.0", dependencies = "required-after:Forge@[6.6.2.534,);required-after:IC2;required-after:BuildCraft|Core;required-after:Forestry;required-after:Railcraft")
@NetworkMod(channels = { "CDE" }, packetHandler = PacketHandler.class, clientSideRequired=true, serverSideRequired=true)
public class CDECore
{
    @SidedProxy(clientSide = "cde.core.ClientProxy", serverSide = "cde.core.CommonProxy")
    
    public static CommonProxy proxy;
    public static final String CDE_BLOCKS = "/cde/blocks.png";
    private static Configuration cfg;
    private static int networkUpdateRate;
    private static boolean sounds,altRainSounds;
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) 
    {
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/core.cfg"));
        cfg.load();
        networkUpdateRate = cfg.get(Configuration.CATEGORY_GENERAL, "networkupdaterate", 40, "Network Update Rate").getInt();
        sounds = cfg.get(Configuration.CATEGORY_GENERAL, "sounds", true, "Enable/Disable CDE Sounds").getBoolean(false);
        altRainSounds = cfg.get(Configuration.CATEGORY_GENERAL, "rain", true, "MC 1.9 Rain Sounds").getBoolean(false);
        cfg.save();
        
        if(playSounds())
        {
            proxy.setupSound();
            TickRegistry.registerTickHandler(new SoundTickHandler(), Side.CLIENT);
        }
        
        ResourceModule.preInit(event);
        SpeakerModule.preInit(event);
    }
    
    @Init
    public void load(FMLInitializationEvent event) 
    {   
        proxy.preloadTextures();
        
        ResourceModule.load(event);
        SpeakerModule.load(event);
    }

    @PostInit
    public void postInit(FMLPostInitializationEvent event) 
    {

    }

    @ServerStarting
    public void serverStarting(FMLServerStartingEvent event)
    {
        
    }
    
    public static int getNetworkUpdateRate()
    {
        return networkUpdateRate;
    }
    
    public static boolean playSounds()
    {
        return sounds;
    }
    
    public static boolean altRainSounds()
    {
        return altRainSounds;
    }
    
    public static List<Item> getItemsByClass(String n)
    {
        List<Item> m = new ArrayList();
        for(Item i : Item.itemsList)
        {
            if(i != null && i.getClass().getName().equals(n))
            {                    
                m.add(i);
            }
        }
        
        return m;
    }

    public static List<Block> getBlocksByClass(String n)
    {
        List<Block> m = new ArrayList();
        
        for(Block i : Block.blocksList)
        {
            if(i != null && i.getClass().getName().equals(n))
            {
                m.add(i);
            }
        }
        
        return m;
    }

    public static Item getItemByClass(String n)
    {
        List<Item> m = getItemsByClass(n);
        
        if(m.size() >= 1)
        {
            return m.get(0);
        }
        
        return null;
    }

    public static Block getBlockByClass(String n)
    {
        for(Block b : Block.blocksList)
        {
            if(b != null && b.getClass().getName().equals(n))
            {
                return b;
            }
        }
        
        return null;
    }
}
