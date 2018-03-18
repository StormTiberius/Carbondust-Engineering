/**
 *
 * @author StormTiberius
 */

package cde.core.resource;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraftforge.common.Configuration;

public class ResourceManager
{
    private static final Random RANDOM = new Random();
    private static String[] resources;
    private static WeightedObject[] resourceArray;
    private static final String[] INITIAL_RESOURCES = 
    {
        "3.0.578.Dirt",
        "13.0.294.Gravel",
        "12.0.292.Sand",
        "49.0.166.Obsidian",
        "82.0.10.Clay",
        "16.0.105.Coal",
        "15.0.60.Iron",
        "14.0.8.Gold",
        "21.0.4.Lapis",
        "73.0.24.Redstone",
        "56.0.3.Diamond",
        "129.0.5.Emerald",
        "180.0.72.Copper",
        "180.1.75.Tin",
        "180.4.5.Uranium",
        "180.2.17.Silver",
        "180.3.17.Lead",
        "254.7.26.Nikolite",
        "254.6.2.Tungsten",
        "254.0.7.Ruby",
        "254.1.7.GreenSapphire",
        "254.2.7.BlueSapphire",
        "255.0.218.Marble",
        "255.1.10.Basalt",
        "180.5.6.Sulfur",
        "180.6.6.Saltpeter",
        "180.8.6.Apatite",
        "180.7.6.Quartz",
        "87.0.30.Netherrack",
        "88.0.10.Soulsand",
        "89.0.8.Glowstone",
        "1.0.9967.Stone"
    };
    
    public static WeightedObject getResource()
    {
        return WeightedRandom.getRandomObject(RANDOM, resourceArray);
    }
    
    public static void cfgInit(Configuration cfg)
    {
        resources = cfg.get(Configuration.CATEGORY_GENERAL, "resources", INITIAL_RESOURCES, "BlockID.MetaData.OrePerChunk.Description").valueList;
        resourceArray = new WeightedObject[resources.length];
        setupResourceArray();
    }
    
    private static void setupResourceArray()
    {
        int index = 0;
        int id,meta,weight;
        
        for(String resource : resources)
        {
            String[] splits = resource.split("\\.", 4);
            
            try
            {
                id = Integer.parseInt(splits[0]);
                meta = Integer.parseInt(splits[1]);
                weight = Integer.parseInt(splits[2]);
            }
            catch(Exception e)
            {
                id = Block.stone.blockID;
                meta = 0;
                weight = 1;
                
                System.out.println("CDE: Error parsing resource.cfg");
            }
            
            resourceArray[index] = new WeightedObject(id,meta,weight);
            index++;
        }  
    }
}
