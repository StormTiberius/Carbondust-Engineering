/**
 *
 * @author StormTiberius
 */

package cde;

import cde.locomotive.CommonProxy;
import cde.core.Version;
import cde.locomotive.EntityLocomotiveDiesel;
import cde.locomotive.EntityLocomotiveElectric;
import cde.locomotive.EntityLocomotiveSteam;
import cde.locomotive.ItemLocomotive;
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
import java.io.File;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import railcraft.common.api.carts.CartTools;

@Mod(modid="CDE|Locomotive", name="Locomotive", version=Version.VERSION, dependencies = "required-after:Forge@[6.6.2.534,);required-after:CDE|Core")
@NetworkMod(clientSideRequired=true, serverSideRequired=true)
public class LocomotiveCore
{
    @SidedProxy(clientSide = "cde.locomotive.ClientProxy", serverSide = "cde.locomotive.CommonProxy")
    
    public static CommonProxy proxy;
    
    private static Configuration cfg;
    private static int locomotiveSteamId,locomotiveDieselId,locomotiveElectricId;
    
    public static Item locomotiveSteam,locomotiveDiesel,locomotiveElectric;
    
    public static enum LocomotiveType
    {
        STEAM,
        DIESEL,
        ELECTRIC
    }
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) 
    {
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/locomotive.cfg"));
        cfg.load();

        locomotiveSteamId = cfg.get(Configuration.CATEGORY_ITEM, "steam", 502).getInt() + CDECore.ID_SHIFT;
        locomotiveDieselId = cfg.get(Configuration.CATEGORY_ITEM, "diesel", 503).getInt() + CDECore.ID_SHIFT;
        locomotiveElectricId = cfg.get(Configuration.CATEGORY_ITEM, "electric", 504).getInt() + CDECore.ID_SHIFT;
        
        cfg.save();
    }

    @Init
    public void init(FMLInitializationEvent event) 
    {
        CartTools.registerMinecart(this, EntityLocomotiveSteam.class, LocomotiveType.STEAM.toString(), 0);
        CartTools.registerMinecart(this, EntityLocomotiveDiesel.class, LocomotiveType.DIESEL.toString(), 1);
        CartTools.registerMinecart(this, EntityLocomotiveElectric.class, LocomotiveType.ELECTRIC.toString(), 2);
        
        locomotiveSteam = new ItemLocomotive(locomotiveSteamId, 0).setItemName("locomotiveSteam").setCreativeTab(CDECore.TAB_CDE);
        locomotiveDiesel = new ItemLocomotive(locomotiveDieselId, 1).setItemName("locomotiveDiesel").setCreativeTab(CDECore.TAB_CDE);
        locomotiveElectric = new ItemLocomotive(locomotiveElectricId, 2).setItemName("locomotiveElectric").setCreativeTab(CDECore.TAB_CDE);
        
        GameRegistry.registerItem(locomotiveSteam, "locomotiveSteam");
        GameRegistry.registerItem(locomotiveDiesel, "locomotiveDiesel");
        GameRegistry.registerItem(locomotiveElectric, "locomotiveElectric");
        
        LanguageRegistry.addName(locomotiveSteam, "Steam Locomotive");
        LanguageRegistry.addName(locomotiveDiesel, "Diesel Locomotive");
        LanguageRegistry.addName(locomotiveElectric, "Electric Locomotive");
        
        proxy.registerRenderers();
    }

    @PostInit
    public void postInit(FMLPostInitializationEvent event) 
    {   
        
    }

    @ServerStarting
    public void serverStarting(FMLServerStartingEvent event)
    {
        
    }
}
