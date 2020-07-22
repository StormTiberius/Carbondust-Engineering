/**
 *
 * @author StormTiberius
 */

package cde.core;

import cde.Carbon;
import cde.manager.BlockManager;
import cpw.mods.fml.common.registry.GameRegistry;
import java.io.File;
import java.util.HashMap;
import net.minecraftforge.common.Configuration;

public class Config
{
    private static final String DESCRIPTION = "Enable, size, amount, minY, maxY";
    
    private static final int ITEM_ID_SHIFT = -256;
    
    private static final HashMap<String, Integer> BLOCK_IDS = new HashMap<String, Integer>();
    private static final HashMap<String, Integer> ITEM_IDS = new HashMap<String, Integer>();
    
    private static File modConfigDir;
    
    public static void initConfig(File file)
    {
        modConfigDir = file;
        
        // BASE
        Configuration cfg = new Configuration(new File(file, "cde/base.cfg"));
        cfg.load();
        BLOCK_IDS.put("Ores", cfg.get(Configuration.CATEGORY_BLOCK, "Ores", Defaults.BLOCK_ID_ORES).getInt());
        BLOCK_IDS.put("Resources", cfg.get(Configuration.CATEGORY_BLOCK, "Resources", Defaults.BLOCK_ID_RESOURCES).getInt());
        ITEM_IDS.put("Materials", cfg.get(Configuration.CATEGORY_ITEM, "Materials", Defaults.ITEM_ID_MATERIALS).getInt());
        cfg.save();
        
        // TERRENE
        cfg = new Configuration(new File(file, "cde/terrene.cfg"));
        cfg.load();
        cfg.save();
    }
    
    public static void addWorldGen(String dimensionName)
    {
        Configuration cfg = new Configuration(new File(modConfigDir, "cde/worldgen_" + dimensionName.toLowerCase() + ".cfg"));
        cfg.load();
        
        boolean worldGen = cfg.get(Configuration.CATEGORY_GENERAL, "worldGen", true, "Enable/Disable " + dimensionName + " WorldGen").getBoolean(true);
        
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
            Carbon.logInfo("Adding worldgen for " + dimensionName);
            GameRegistry.registerWorldGenerator(new WorldGenOres(dimensionName, config, BlockManager.oreBlock.blockID));
        }
    }
    
    public static int getBlockId(String key)
    {
        if(BLOCK_IDS.containsKey(key))
        {
            return BLOCK_IDS.get(key);
        }
        
        return 0;
    }
    
    public static int getItemId(String key)
    {
        if(ITEM_IDS.containsKey(key))
        {
            return ITEM_IDS.get(key) + ITEM_ID_SHIFT;
        }
        
        return 0;
    }
    
    public static boolean isAudioEnabled()
    {
        return true;
    }
    
    public static boolean isAlternateRainSoundsEnabled()
    {
        return true;
    }
    
    public static boolean isAlternateExplosionSoundsEnabled()
    {
        return true;
    }
}
