/**
 *
 * @author StormTiberius
 */

package cde.core.worldgen;

import cde.CDECore;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import java.io.File;
import net.minecraftforge.common.Configuration;

public class WorldGenModule
{
    private static Configuration cfg;
    private static final int ORE_ENTRIES = 12;
    private static final int[][] ARRAY = new int[ORE_ENTRIES][5];
    private static final String DESCRIPTION = "Enable,size,amount,minY,maxY";
    private static boolean enabled;
    
    public static void preInit(FMLPreInitializationEvent event)
    {
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/worldgen.cfg"));
        cfg.load();
        
        enabled = cfg.get(Configuration.CATEGORY_GENERAL, "worldgen", true, "Enable/Disable Worldgen").getBoolean(true);
        
        ARRAY[0] = cfg.get(Configuration.CATEGORY_GENERAL, "copper", new int[]{1,8,10,20,55}, DESCRIPTION).getIntList();
        ARRAY[1] = cfg.get(Configuration.CATEGORY_GENERAL, "tin", new int[]{1,8,7,20,55}, DESCRIPTION).getIntList();
        ARRAY[2] = cfg.get(Configuration.CATEGORY_GENERAL, "silver", new int[]{1,8,3,5,30}, DESCRIPTION).getIntList();
        ARRAY[3] = cfg.get(Configuration.CATEGORY_GENERAL, "lead", new int[]{1,8,4,10,35}, DESCRIPTION).getIntList();
        ARRAY[4] = cfg.get(Configuration.CATEGORY_GENERAL, "uranium", new int[]{1,3,20,0,64}, DESCRIPTION).getIntList();
        ARRAY[5] = cfg.get(Configuration.CATEGORY_GENERAL, "sulfur", new int[]{1,6,1,10,16}, DESCRIPTION).getIntList();
        ARRAY[6] = cfg.get(Configuration.CATEGORY_GENERAL, "saltpeter", new int[]{1,6,1,16,32}, DESCRIPTION).getIntList();
        ARRAY[7] = cfg.get(Configuration.CATEGORY_GENERAL, "quartz", new int[]{1,4,7,12,62}, DESCRIPTION).getIntList();
        ARRAY[8] = cfg.get(Configuration.CATEGORY_GENERAL, "ruby", new int[]{1,7,2,0,48}, DESCRIPTION).getIntList();
        ARRAY[9] = cfg.get(Configuration.CATEGORY_GENERAL, "jade", new int[]{1,7,2,0,48}, DESCRIPTION).getIntList();
        ARRAY[10] = cfg.get(Configuration.CATEGORY_GENERAL, "sapphire", new int[]{1,7,2,0,48}, DESCRIPTION).getIntList();
        ARRAY[11] = cfg.get(Configuration.CATEGORY_GENERAL, "apatite", new int[]{1,6,1,0,64}, DESCRIPTION).getIntList();
        
        cfg.save();
    }
    
    public static void init(FMLInitializationEvent event) 
    {
        if(enabled)
        {
            GameRegistry.registerWorldGenerator(new WorldGenManager(CDECore.oreBlock.blockID, ARRAY));
        }
    }
}
