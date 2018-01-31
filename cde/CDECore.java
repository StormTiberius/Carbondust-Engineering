/**
 *
 * @author StormTiberius
 */

package cde;

import cde.core.CommonProxy;
import cde.core.CreativeTabCDE;
import cde.core.Namings;
import cde.core.block.BlockOre;
import cde.core.block.BlockStorage;
import cde.core.item.ItemOre;
import cde.core.item.ItemStorage;
import cde.core.network.PacketHandler;
import cde.core.sound.SoundTickHandler;
import cde.core.speaker.SpeakerModule;
import cde.core.worldgen.WorldGenModule;
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
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import static net.minecraft.block.Block.soundMetalFootstep;
import static net.minecraft.block.Block.soundStoneFootstep;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.oredict.OreDictionary;

@Mod(modid="CDE|Core", name="Core", version="1.0", dependencies = "required-after:Forge@[6.6.2.534,);required-after:IC2;required-after:BuildCraft|Core;required-after:Forestry;required-after:Railcraft")
@NetworkMod(channels = { "CDE" }, packetHandler = PacketHandler.class, clientSideRequired=true, serverSideRequired=true)
public class CDECore
{
    @SidedProxy(clientSide = "cde.core.ClientProxy", serverSide = "cde.core.CommonProxy")
    
    public static CommonProxy proxy;
    public static final String CDE_BLOCKS = "/cde/blocks.png";
    public static final CreativeTabs TAB_CDE = new CreativeTabCDE("carbondustengineering");
    public static Block oreBlock,storageBlock;
    private static Configuration cfg;
    private static int networkUpdateRate,oreBlockId,storageBlockId;
    private static boolean sounds,altRainSounds;
     
    @PreInit
    public void preInit(FMLPreInitializationEvent event) 
    {
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/core.cfg"));
        cfg.load();
        
        networkUpdateRate = cfg.get(Configuration.CATEGORY_GENERAL, "networkupdaterate", 40, "Network Update Rate").getInt();
        sounds = cfg.get(Configuration.CATEGORY_GENERAL, "sounds", true, "Enable/Disable CDE Sounds").getBoolean(false);
        altRainSounds = cfg.get(Configuration.CATEGORY_GENERAL, "rain", true, "MC 1.9 Rain Sounds").getBoolean(false);
        
        oreBlockId = cfg.get(Configuration.CATEGORY_BLOCK, "oreblockid", 180).getInt();
        storageBlockId = cfg.get(Configuration.CATEGORY_BLOCK, "storageblockid", 181).getInt();
        
        cfg.save();
        
        initBlocks();
        
        if(playSounds())
        {
            proxy.setupSound();
            TickRegistry.registerTickHandler(new SoundTickHandler(), Side.CLIENT);
        }
        
        SpeakerModule.preInit(event);
        WorldGenModule.preInit(event);
    }
    
    @Init
    public void init(FMLInitializationEvent event) 
    {   
        proxy.preloadTextures();
        
        SpeakerModule.init(event);
        WorldGenModule.init(event);
    }

    @PostInit
    public void postInit(FMLPostInitializationEvent event) 
    {

    }

    @ServerStarting
    public void serverStarting(FMLServerStartingEvent event)
    {
        
    }
    
    private static void initBlocks()
    {
        if(oreBlockId > 0)
        {
            oreBlock = (new BlockOre(oreBlockId)).setHardness(3.0F).setResistance(5.0F).setStepSound(soundStoneFootstep).setBlockName("cdeOreBlock").setCreativeTab(TAB_CDE);
            
            GameRegistry.registerBlock(oreBlock, ItemOre.class, "cdeOreBlock");
            
            for(int i = 0; i < Namings.EXTERNAL_ORE_BLOCK_NAMES.length; i++)
            {              
                ItemStack is = new ItemStack(oreBlock, 1, i);
                LanguageRegistry.addName(is, Namings.EXTERNAL_ORE_BLOCK_NAMES[i]);
                OreDictionary.registerOre(is.getItemName(), is);
            }
        }
        
        if(storageBlockId > 0)
        {
            storageBlock = (new BlockStorage(storageBlockId)).setHardness(3.0F).setResistance(10.0F).setStepSound(soundMetalFootstep).setBlockName("cdeStorageBlock").setCreativeTab(TAB_CDE);
            
            GameRegistry.registerBlock(storageBlock, ItemStorage.class, "cdeStorageBlock");
            
            for(int i = 0; i < Namings.EXTERNAL_STORAGE_BLOCK_NAMES.length; i++)
            {                
                ItemStack is = new ItemStack(storageBlock, 1, i);
                LanguageRegistry.addName(is, Namings.EXTERNAL_STORAGE_BLOCK_NAMES[i]);
                OreDictionary.registerOre(is.getItemName(), is);
            }
        }
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
