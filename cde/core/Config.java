/**
 *
 * @author StormTiberius
 */

package cde.core;

import java.io.File;
import java.util.HashMap;
import net.minecraftforge.common.Configuration;

public class Config
{
    private static final int ITEM_ID_SHIFT = -256;
    
    private static final HashMap<String, Integer> BLOCK_IDS = new HashMap<String, Integer>();
    private static final HashMap<String, Integer> ITEM_IDS = new HashMap<String, Integer>();
    
    private static Configuration cfg;
    
    public static void initConfig(File file)
    {
        // BASE
        cfg = new Configuration(new File(file, "cde/base.cfg"));
        cfg.load();
        BLOCK_IDS.put("Ores", cfg.get(Configuration.CATEGORY_BLOCK, "Ores", Defaults.BLOCK_ID_ORES).getInt());
        BLOCK_IDS.put("Resources", cfg.get(Configuration.CATEGORY_BLOCK, "Resources", Defaults.BLOCK_ID_RESOURCES).getInt());
        ITEM_IDS.put("Materials", cfg.get(Configuration.CATEGORY_ITEM, "Materials", Defaults.ITEM_ID_MATERIALS).getInt());
        cfg.save();
        
        // WORLDGEN
        cfg = new Configuration(new File(file, "cde/worldgen.cfg"));
        cfg.load();
        cfg.save();
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
