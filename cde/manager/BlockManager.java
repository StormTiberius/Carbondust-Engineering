/**
 *
 * @author StormTiberius
 */

package cde.manager;

import cde.Carbon;
import cde.api.Blocks;
import cde.core.Config;
import cde.core.Namings;
import cde.core.block.BlockOre;
import cde.core.block.BlockResource;
import cde.core.item.ItemOre;
import cde.core.item.ItemResource;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

public class BlockManager
{
    public static Block oreBlock,resourceBlock;
    
    public static void init()
    {
        int id = Config.getBlockId("Ores");
        
        if(id > 0)
        {
            oreBlock = (new BlockOre(id)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setBlockName("cdeOreBlock").setCreativeTab(Carbon.TAB_RESOURCES);
            
            GameRegistry.registerBlock(oreBlock, ItemOre.class, "cdeOreBlock");
            
            for(int i = 0; i < Namings.EXTERNAL_ORE_BLOCK_NAMES.length; i++)
            {
                ItemStack is = new ItemStack(oreBlock.blockID, 1, i);
                
                switch(i)
                {
                    case 0: Blocks.oreCopper = is; break;
                    case 1: Blocks.oreTin = is; break;
                    case 2: Blocks.oreSilver = is; break;
                    case 3: Blocks.oreLead = is; break;
                    case 4: Blocks.oreUranium = is; break;
                    case 5: Blocks.oreSulfur = is; break;
                    case 6: Blocks.oreSaltpeter = is; break;
                    case 7: Blocks.oreQuartz = is; break;
                    case 8: Blocks.oreApatite = is; break;
                    case 9: Blocks.oreRuby = is; break;
                    case 10: Blocks.oreJade = is; break;
                    case 11: Blocks.oreSapphire = is; break;
                }
                
                LanguageRegistry.addName(is, Namings.EXTERNAL_ORE_BLOCK_NAMES[i]);
                OreDictionary.registerOre(is.getItemName(), is);
            }
            
            MinecraftForge.setBlockHarvestLevel(oreBlock, 0, "pickaxe", 1); // Copper
            MinecraftForge.setBlockHarvestLevel(oreBlock, 1, "pickaxe", 1); // Tin
            MinecraftForge.setBlockHarvestLevel(oreBlock, 2, "pickaxe", 1); // Silver
            MinecraftForge.setBlockHarvestLevel(oreBlock, 3, "pickaxe", 2); // Lead
            MinecraftForge.setBlockHarvestLevel(oreBlock, 4, "pickaxe", 2); // Uranium
            MinecraftForge.setBlockHarvestLevel(oreBlock, 5, "pickaxe", 2); // Sulfur
            MinecraftForge.setBlockHarvestLevel(oreBlock, 6, "pickaxe", 2); // Saltpeter
            MinecraftForge.setBlockHarvestLevel(oreBlock, 7, "pickaxe", 0); // Quartz
            MinecraftForge.setBlockHarvestLevel(oreBlock, 8, "pickaxe", 1); // Apatite
            MinecraftForge.setBlockHarvestLevel(oreBlock, 9, "pickaxe", 2); // Ruby
            MinecraftForge.setBlockHarvestLevel(oreBlock, 10, "pickaxe", 2); // Jade
            MinecraftForge.setBlockHarvestLevel(oreBlock, 11, "pickaxe", 2); // Sapphire
        }
        
        id = Config.getBlockId("Resources");
        
        if(id > 0)
        {
            resourceBlock = (new BlockResource(id)).setHardness(3.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setBlockName("cdeResourceBlock").setCreativeTab(Carbon.TAB_RESOURCES);
            
            GameRegistry.registerBlock(resourceBlock, ItemResource.class, "cdeResourceBlock");
            
            MinecraftForge.setBlockHarvestLevel(resourceBlock, "pickaxe", 1);
            
            for(int i = 0; i < Namings.EXTERNAL_STORAGE_BLOCK_NAMES.length; i++)
            {
                ItemStack is = new ItemStack(resourceBlock.blockID, 1, i);
                
                switch(i)
                {
                    case 0: Blocks.blockCopper = is; break;
                    case 1: Blocks.blockTin = is; break;
                    case 2: Blocks.blockSilver = is; break;
                    case 3: Blocks.blockLead = is; break;
                    case 4: Blocks.blockUranium = is; break;
                    case 5: Blocks.blockZinc = is; break;
                    case 6: Blocks.blockBronze = is; break;
                    case 7: Blocks.blockBrass = is; break;
                    case 8: Blocks.blockSteel = is; break;
                    case 9: Blocks.blockRuby = is; break;
                    case 10: Blocks.blockJade = is; break;
                    case 11: Blocks.blockSapphire = is; break;
                }
                
                LanguageRegistry.addName(is, Namings.EXTERNAL_STORAGE_BLOCK_NAMES[i]);
                OreDictionary.registerOre(is.getItemName(), is);
            }
        }
    }
}
