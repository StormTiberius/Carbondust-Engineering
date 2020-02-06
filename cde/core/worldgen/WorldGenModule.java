/**
 *
 * @author StormTiberius
 */

package cde.core.worldgen;

import cde.CDECore;
import cde.core.Defaults;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLInterModComms.IMCMessage;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import java.io.File;
import net.minecraftforge.common.Configuration;

public class WorldGenModule
{
    private static Configuration cfg;
    private static final int ORE_ENTRIES = 12;
    private static int[][] ARRAY;
    private static final String DESCRIPTION = "Enable,size,amount,minY,maxY";
    private static boolean enabled;
    
    private static File modConfigDir;
    
    public static void preInit(FMLPreInitializationEvent event)
    {
        modConfigDir = event.getModConfigurationDirectory();
    }
    
    private static void addWorldGenConfig(String dimensionName)
    {
        cfg = new Configuration(new File(modConfigDir, "cde/worldgen_" + dimensionName.toLowerCase() + ".cfg"));
        cfg.load();
        
        enabled = cfg.get(Configuration.CATEGORY_GENERAL, "worldgen", true, "Enable/Disable Worldgen").getBoolean(true);
        
        ARRAY = new int[ORE_ENTRIES][5];
        
        ARRAY[0] = cfg.get(Configuration.CATEGORY_GENERAL, "copper", Defaults.ORE_GEN_DEFAULTS[0], DESCRIPTION).getIntList();
        ARRAY[1] = cfg.get(Configuration.CATEGORY_GENERAL, "tin", Defaults.ORE_GEN_DEFAULTS[1], DESCRIPTION).getIntList();
        ARRAY[2] = cfg.get(Configuration.CATEGORY_GENERAL, "silver", Defaults.ORE_GEN_DEFAULTS[2], DESCRIPTION).getIntList();
        ARRAY[3] = cfg.get(Configuration.CATEGORY_GENERAL, "lead", Defaults.ORE_GEN_DEFAULTS[3], DESCRIPTION).getIntList();
        ARRAY[4] = cfg.get(Configuration.CATEGORY_GENERAL, "uranium", Defaults.ORE_GEN_DEFAULTS[4], DESCRIPTION).getIntList();
        ARRAY[5] = cfg.get(Configuration.CATEGORY_GENERAL, "sulfur", Defaults.ORE_GEN_DEFAULTS[5], DESCRIPTION).getIntList();
        ARRAY[6] = cfg.get(Configuration.CATEGORY_GENERAL, "saltpeter", Defaults.ORE_GEN_DEFAULTS[6], DESCRIPTION).getIntList();
        ARRAY[7] = cfg.get(Configuration.CATEGORY_GENERAL, "quartz", Defaults.ORE_GEN_DEFAULTS[7], DESCRIPTION).getIntList();
        ARRAY[8] = cfg.get(Configuration.CATEGORY_GENERAL, "apatite", Defaults.ORE_GEN_DEFAULTS[8], DESCRIPTION).getIntList();
        ARRAY[9] = cfg.get(Configuration.CATEGORY_GENERAL, "ruby", Defaults.ORE_GEN_DEFAULTS[9], DESCRIPTION).getIntList();
        ARRAY[10] = cfg.get(Configuration.CATEGORY_GENERAL, "jade", Defaults.ORE_GEN_DEFAULTS[10], DESCRIPTION).getIntList();
        ARRAY[11] = cfg.get(Configuration.CATEGORY_GENERAL, "sapphire", Defaults.ORE_GEN_DEFAULTS[11], DESCRIPTION).getIntList();
        
        cfg.save();
        
        if(enabled)
        {
            System.out.println("Adding worldgen for " + dimensionName);
            GameRegistry.registerWorldGenerator(new WorldGenManager(dimensionName, CDECore.oreBlock.blockID, ARRAY));
        }
    }
    
    public static void init(FMLInitializationEvent event) 
    {
        addWorldGenConfig("Overworld");
        
        for(IMCMessage message : FMLInterModComms.fetchRuntimeMessages(CDECore.instance))
        {
            if(message.key.contains("add-oregen-for-world") && message.isStringMessage())
            {
                addWorldGenConfig(message.getStringValue());
            }
        }
    }
}
