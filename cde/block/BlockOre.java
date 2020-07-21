/**
 *
 * @author StormTiberius
 */

package cde.block;

import cde.api.Blocks;
import cde.api.Materials;
import cde.core.Defaults;
import cde.core.Info;
import cde.manager.ItemManager;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockOre extends Block
{
    public BlockOre(int id)
    {
        super(id, Material.rock);
    }
    
    @Override
    public int idDropped(int md, Random random, int fortune)
    {
        if(md == Blocks.oreApatite.getItemDamage())
        {
            return Materials.gemApatite.itemID;
        }
        else if(md > 3)
        {
            return ItemManager.materialItem.itemID;
        }
        else
        {
            return blockID;
        }
    }
    
    @Override
    public int damageDropped(int md)
    {
        switch(md)
        {
            case 4: return Materials.fuelUranium.getItemDamage();
            case 5: return Materials.dustSulfur.getItemDamage();
            case 6: return Materials.dustSaltpeter.getItemDamage();
            case 7: return Materials.gemQuartz.getItemDamage();
            case 8: return Materials.gemApatite.getItemDamage();
            case 9: return Materials.gemRuby.getItemDamage();
            case 10: return Materials.gemJade.getItemDamage();
            case 11: return Materials.gemSapphire.getItemDamage();
            default: return md;
        }
    }
    
    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
    }
    
    @Override
    public int quantityDropped(int md, int fortune, Random random)
    {
        switch(md)
        {
            case 5: return 2 + random.nextInt(4) + random.nextInt(fortune + 1);
            case 6: return 1 + random.nextInt(2) + random.nextInt(fortune + 1);
            case 7: return 1 + Math.abs(random.nextInt()) % (1 + fortune);
            
            case 8:
                int fortmod = random.nextInt(fortune + 2) - 1;
                
                if(fortmod < 0)
                {
                    fortmod = 0;
                }
                
                int amount = (1 + random.nextInt(5)) * (fortmod + 1);
                
                if(amount > 0)
                {
                    return amount;
                }
            
            case 9:
            case 10:
            
            case 11:
                int i = random.nextInt(fortune + 2) - 1;
                
                if(i < 0)
                {
                    i = 0;
                }
                
                return i + 1;
            
            default: return quantityDroppedWithBonus(fortune, random);
        }
    }
    
    @Override
    public int quantityDropped(Random random)
    {
        return 1;
    }
    
    @Override
    public int quantityDroppedWithBonus(int fortune, Random random)
    {
        if(fortune > 0 && blockID != idDropped(0, random, fortune))
        {
            int i = random.nextInt(fortune + 2) - 1;
            
            if(i < 0)
            {
                i = 0;
            }
            
            return quantityDropped(random) * (i + 1);
        }
        else
        {
            return quantityDropped(random);
        }
    }
    
    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int md, int fortune)
    {
        ArrayList<ItemStack> ret = super.getBlockDropped(world, x, y, z, md, fortune);
        
        if(md == Blocks.oreQuartz.getItemDamage())
        {
            int amount = Math.abs(world.rand.nextInt()) % (3 + fortune);
            
            if(amount > 0)
            {
                ret.add(new ItemStack(Materials.dustQuartz.itemID, amount, Materials.dustQuartz.getItemDamage()));
            }
        }
        
        return ret;
    }
    
    @Override
    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int md, float chance, int fortune)
    {
        super.dropBlockAsItemWithChance(world, x, y, z, md, chance, fortune);
        
        if(md > 4)
        {
            int exp = 0;
            
            switch(md)
            {
                case 5:
                case 6: exp = MathHelper.getRandomIntegerInRange(world.rand, 2, 5); break; // Sulfur & Saltpeter
                case 7: exp = MathHelper.getRandomIntegerInRange(world.rand, 1, 5); break; // Quartz
                case 8: exp = MathHelper.getRandomIntegerInRange(world.rand, 1, 4); break; // Apatite
                case 9:
                case 10:
                case 11: exp = MathHelper.getRandomIntegerInRange(world.rand, 3, 7); break; // Gems // Nikolite 1 to 5
            }
            
            dropXpOnBlockBreak(world, x, y, z, exp);
        }
    }
    
    @Override
    public int getBlockTextureFromSideAndMetadata(int side, int md)
    {
        switch(md)
        {
            case 0: return Defaults.TEXTURE_ORE_COPPER;
            case 1: return Defaults.TEXTURE_ORE_TIN;
            case 2: return Defaults.TEXTURE_ORE_SILVER;
            case 3: return Defaults.TEXTURE_ORE_LEAD;
            case 4: return Defaults.TEXTURE_ORE_URANIUM;
            case 5: return Defaults.TEXTURE_ORE_SULFUR;
            case 6: return Defaults.TEXTURE_ORE_SALTPETER;
            case 7: return Defaults.TEXTURE_ORE_QUARTZ;
            case 8: return Defaults.TEXTURE_ORE_APATITE;
            case 9: return Defaults.TEXTURE_ORE_RUBY;
            case 10: return Defaults.TEXTURE_ORE_JADE;
            case 11: return Defaults.TEXTURE_ORE_SAPPHIRE;
            default: return 0;
        }
    }
    
    @Override
    public String getTextureFile()
    {
        return Info.CDE_BLOCKS;
    }
}
