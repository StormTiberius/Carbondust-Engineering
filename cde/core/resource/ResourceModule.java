/**
 *
 * @author StormTiberius
 */

package cde.core.resource;

import cde.CDECore;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import java.io.File;
import net.minecraft.block.Block;
import net.minecraftforge.common.Configuration;

public class ResourceModule
{
    private static Configuration cfg;
    private static int resourceId;
    public static Block resource;
    
    public static void preInit(FMLPreInitializationEvent event) 
    {
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/resource.cfg"));
        cfg.load();
        resourceId = cfg.get(Configuration.CATEGORY_BLOCK, "resource", 183).getInt();
        ResourceManager.cfgInit(cfg);
        cfg.save(); 
    }

    public static void init(FMLInitializationEvent event) 
    {   
        if(resourceId > 0)
        {
            resource = new BlockResource(resourceId).setBlockName("resourceBlock").setBlockUnbreakable().setResistance(6000000.0F).setStepSound(Block.soundStoneFootstep).setTickRandomly(true).setCreativeTab(CDECore.TAB_CDE);
            GameRegistry.registerBlock(resource, "resourceBlock");
            LanguageRegistry.addName(resource, "Resource");
        }
    }
}
