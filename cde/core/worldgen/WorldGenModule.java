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
        
        int[][] config = new int[Namings.INTERNAL_ORE_BLOCK_NAMES.length][];
        int[][] defaults = Defaults.ORE_GEN_DEFAULTS;
        
        if(dimensionName.contentEquals("Terrene"))
        {
            defaults = Defaults.ORE_GEN_DEFAULTS_TERRENE;
        }
        
        for(int i = 0; i < config.length; i++)
        {
            config[i] = cfg.get(Configuration.CATEGORY_GENERAL, Namings.INTERNAL_ORE_BLOCK_NAMES[i], defaults[i], DESCRIPTION).getIntList();
        }
        
        cfg.save();
        
        if(worldGen)
        {
            CDECore.logInfo("Adding worldgen for " + dimensionName);
            GameRegistry.registerWorldGenerator(new WorldGenOres(dimensionName, config, CDECore.oreBlock.blockID));
        }
    }
}
