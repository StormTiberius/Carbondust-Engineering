/**
 *
 * @author StormTiberius
 */

package cde.manager;

import cde.Carbon;
import cde.api.Materials;
import cde.core.Config;
import cde.core.Namings;
import cde.item.ItemMaterial;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ItemManager
{
    public static Item materialItem;
    
    public static void init()
    {
        int id = Config.getItemId("Materials");
        
        if(id > 0)
        {
            materialItem = (new ItemMaterial(id)).setItemName("cdeMaterialItem").setCreativeTab(Carbon.TAB_RESOURCES);
            
            GameRegistry.registerItem(materialItem, "cdeMaterialItem");
            
            for(int i = 0; i < Namings.EXTERNAL_PART_ITEM_NAMES.length; i++)
            {
                ItemStack is = new ItemStack(materialItem.itemID, 1, i);
                
                switch(i)
                {
                    case 0: Materials.ingotCopper = is; break;
                    case 1: Materials.ingotTin = is; break;
                    case 2: Materials.ingotSilver = is; break;
                    case 3: Materials.ingotLead = is; break;
                    case 4: Materials.ingotUranium = is; break;
                    case 5: Materials.ingotZinc = is; break;
                    case 6: Materials.ingotBronze = is; break;
                    case 7: Materials.ingotBrass = is; break;
                    case 8: Materials.ingotSteel = is; break;
                    
                    case 9: Materials.fuelPeat = is; break;
                    case 10: Materials.fuelBituminousPeat = is; break;
                    
                    case 11: Materials.dustCoal = is; break;
                    case 12: Materials.dustCharcoal = is; break;
                    case 13: Materials.dustIron = is; break;
                    case 14: Materials.dustGold = is; break;
                    case 15: Materials.dustCopper = is; break;
                    case 16: Materials.dustTin = is; break;
                    case 17: Materials.dustSilver = is; break;
                    case 18: Materials.dustLead = is; break;
                    case 19: Materials.dustZinc = is; break;
                    case 20: Materials.dustBronze = is; break;
                    case 21: Materials.dustBrass = is; break;
                    case 22: Materials.dustSteel = is; break;
                    case 23: Materials.dustSulfur = is; break;
                    case 24: Materials.dustSaltpeter = is; break;
                    case 25: Materials.dustQuartz = is; break;
                    case 26: Materials.dustApatite = is; break;
                    
                    case 27: Materials.nuggetIron = is; break;
                    case 28: Materials.nuggetCopper = is; break;
                    case 29: Materials.nuggetTin = is; break;
                    case 30: Materials.nuggetSilver = is; break;
                    case 31: Materials.nuggetLead = is; break;
                    case 32: Materials.nuggetZinc = is; break;
                    case 33: Materials.nuggetBronze = is; break;
                    case 34: Materials.nuggetBrass = is; break;
                    case 35: Materials.nuggetSteel = is; break;
                    
                    case 36: Materials.fuelCoke = is; break;
                    case 37: Materials.fuelUranium = is; break;
                    
                    case 38: Materials.gemQuartz = is; break;
                    case 39: Materials.gemApatite = is; break;
                    case 40: Materials.gemRuby = is; break;
                    case 41: Materials.gemJade = is; break;
                    case 42: Materials.gemSapphire = is; break;
                    
                    case 43: Materials.plateIron = is; break;
                    case 44: Materials.plateGold = is; break;
                    case 45: Materials.plateCopper = is; break;
                    case 46: Materials.plateTin = is; break;
                    case 47: Materials.plateBronze = is; break;
                    case 48: Materials.plateSteel = is; break;
                    
                    case 49: Materials.circuitBoardSingle = is; break;
                    case 50: Materials.circuitBoardDouble = is; break;
                    case 51: Materials.circuitBoardMulti = is; break;
                    case 52: Materials.electricMotor = is; break;
                    case 53: Materials.electricWire = is; break;
                }
                
                LanguageRegistry.addName(is, Namings.EXTERNAL_PART_ITEM_NAMES[i]);
                OreDictionary.registerOre(is.getItemName(), is);
            }
            
            OreDictionary.registerOre("ingotQuartz", Materials.gemQuartz);
            OreDictionary.registerOre("dropUranium", Materials.fuelUranium);
            OreDictionary.registerOre("brickPeat", Materials.fuelPeat);
        }
    }
}
