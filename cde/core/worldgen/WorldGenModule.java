/**
 *
 * @author StormTiberius
 */

package cde.core.worldgen;

import cde.CDECore;
import cde.core.Defaults;
import cde.core.Namings;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import java.io.File;
import net.minecraftforge.common.Configuration;

public class WorldGenModule
{
    private static final int[][] CONFIG = new int[Namings.INTERNAL_ORE_BLOCK_NAMES.length][];
    private static final String DESCRIPTION = "Enable, size, amount, minY, maxY";
    
    private static boolean worldGen;
    private static Configuration cfg;
    private static File modConfigDir;
    
    public static void preInit(FMLPreInitializationEvent event)
    {
        modConfigDir = event.getModConfigurationDirectory();
    }
    
    public static void addWorldGenConfig(String dimensionName)
    {
        cfg = new Configuration(new File(modConfigDir, "cde/worldgen_" + dimensionName.toLowerCase() + ".cfg"));
        cfg.load();
        
        worldGen = cfg.get(Configuration.CATEGORY_GENERAL, "worldGen", true, "Enable/Disable " + dimensionName + " WorldGen").getBoolean(true);
        
        for(int i = 0; i < CONFIG.length; i++)
        {
            CONFIG[i] = cfg.get(Configuration.CATEGORY_GENERAL, Namings.INTERNAL_ORE_BLOCK_NAMES[i], Defaults.ORE_GEN_DEFAULTS[i], DESCRIPTION).getIntList();
        }
        
        cfg.save();
        
        if(worldGen)
        {
            CDECore.logInfo("Adding worldgen for " + dimensionName);
            GameRegistry.registerWorldGenerator(new WorldGenOres(dimensionName, CONFIG, CDECore.oreBlock.blockID));
        }
    }
}
