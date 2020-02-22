/**
 *
 * @author StormTiberius
 */

package cde;

import cde.core.Version;
import cde.locomotive.CommonProxy;
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
import ic2.api.Items;
import java.io.File;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.oredict.ShapedOreRecipe;
import railcraft.common.api.carts.CartTools;
import railcraft.common.api.core.items.ItemRegistry;

@Mod(modid="CDE|Locomotive", name="Locomotive", version=Version.VERSION, dependencies = "required-after:Forge@[6.6.2.534,);required-after:CDE|Core")
@NetworkMod(clientSideRequired=true, serverSideRequired=true)
public class LocomotiveCore
{
    @SidedProxy(clientSide = "cde.locomotive.ClientProxy", serverSide = "cde.locomotive.CommonProxy")
    public static CommonProxy proxy;
    
    private static int idLocomotiveSteam,idLocomotiveDiesel,idLocomotiveElectric;
    public static Item locomotiveSteam,locomotiveDiesel,locomotiveElectric;
    private static Configuration cfg;
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event)
    {
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/locomotive.cfg"));
        cfg.load();
        idLocomotiveSteam = cfg.get(Configuration.CATEGORY_ITEM, "steam", 503).getInt() + CDECore.ID_SHIFT;
        idLocomotiveDiesel = cfg.get(Configuration.CATEGORY_ITEM, "diesel", 504).getInt() + CDECore.ID_SHIFT;
        idLocomotiveElectric = cfg.get(Configuration.CATEGORY_ITEM, "electric", 505).getInt() + CDECore.ID_SHIFT;
        cfg.save();
    }
    
    @Init
    public void init(FMLInitializationEvent event)
    {
        if(idLocomotiveSteam > 0)
        {
            CartTools.registerMinecart(this, EntityLocomotiveSteam.class, "CDE_LOCOMOTIVE_STEAM", 0);
            locomotiveSteam = new ItemLocomotive(idLocomotiveSteam, 0).setItemName("locomotiveSteam").setCreativeTab(CDECore.TAB_CDE);
            GameRegistry.registerItem(locomotiveSteam, "locomotiveSteam");
            LanguageRegistry.addName(locomotiveSteam, "Steam Locomotive");
        }
        
        if(idLocomotiveDiesel > 0)
        {
            CartTools.registerMinecart(this, EntityLocomotiveDiesel.class, "CDE_LOCOMOTIVE_DIESEL", 1);
            locomotiveDiesel = new ItemLocomotive(idLocomotiveDiesel, 1).setItemName("locomotiveDiesel").setCreativeTab(CDECore.TAB_CDE);
            GameRegistry.registerItem(locomotiveDiesel, "locomotiveDiesel");
            LanguageRegistry.addName(locomotiveDiesel, "Diesel Locomotive");
        }
        
        if(idLocomotiveElectric > 0)
        {
            CartTools.registerMinecart(this, EntityLocomotiveElectric.class, "CDE_LOCOMOTIVE_ELECTRIC", 2);
            locomotiveElectric = new ItemLocomotive(idLocomotiveElectric, 2).setItemName("locomotiveElectric").setCreativeTab(CDECore.TAB_CDE);
            GameRegistry.registerItem(locomotiveElectric, "locomotiveElectric");
            LanguageRegistry.addName(locomotiveElectric, "Electric Locomotive");
        }
        
        proxy.registerRenderers();
    }
    
    @PostInit
    public void postInit(FMLPostInitializationEvent event)
    {
        ItemStack lowPressureBoiler = ItemRegistry.getItem("machine.beta.boiler.tank.pressure.low", 1);
        ItemStack highPressureBoiler = ItemRegistry.getItem("machine.beta.boiler.tank.pressure.high", 1);
        ItemStack fireBoxSolid = ItemRegistry.getItem("machine.beta.boiler.firebox.solid", 1);
        ItemStack fireBoxLiquid = ItemRegistry.getItem("machine.beta.boiler.firebox.liquid", 1);
        ItemStack steelGear = ItemRegistry.getItem("part.gear.steel", 1);
        
        ItemStack transformer = Items.getItem("mvTransformer");
        
        ItemStack minecart = new ItemStack(Item.minecartEmpty.itemID, 1, 0);
        ItemStack fence = new ItemStack(Block.fenceIron.blockID, 1, 0);
        ItemStack lamp = new ItemStack(Item.minecartEmpty.itemID, 1, 0);
        
        if(idLocomotiveSteam > 0 && lowPressureBoiler != null && fireBoxSolid != null)
        {
            GameRegistry.addRecipe(new ItemStack(locomotiveSteam.itemID, 1, 0),
            "aab",
            "aab",
            "dcc",
            'a', lowPressureBoiler,
            'b', fireBoxSolid,
            'c', minecart,
            'd', fence);
        }
        
        if(idLocomotiveDiesel > 0 && highPressureBoiler != null && fireBoxLiquid != null)
        {
            GameRegistry.addRecipe(new ItemStack(locomotiveDiesel.itemID, 1, 0),
            "aab",
            "aab",
            "dcc",
            'a', highPressureBoiler,
            'b', fireBoxLiquid,
            'c', minecart,
            'd', fence);
        }
        
        if(idLocomotiveElectric > 0 && steelGear != null && transformer != null)
        {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(locomotiveElectric.itemID, 1, 0),
            "de ",
            "eae",
            "bcb",
            'a', transformer,
            'b', steelGear,
            'c', minecart,
            'd', lamp,
            'e', "plateSteel"));
        }
    }
    
    @ServerStarting
    public void serverStarting(FMLServerStartingEvent event)
    {
        
    }
}
