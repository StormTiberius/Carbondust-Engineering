package cde.core.block;

import cde.CDECore;
import cde.api.Blocks;
import cde.api.Materials;
import cde.core.Defaults;
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
        super(id,Material.rock);
    }
    
    @Override
    public int idDropped(int md, Random random, int fortune)
    {
        if(md == Blocks.oreApatite.getItemDamage())
        {
            return CDECore.apatiteId;
        }
        else if(md > 5)
        {
            return CDECore.materialsItem.itemID;
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
            case 6: return Materials.clodBitumen.getItemDamage();
            case 7: return Materials.dustSulfur.getItemDamage();
            case 8: return Materials.dustSaltpeter.getItemDamage();
            case 9: return CDECore.apatiteMeta;
            case 10: return Materials.gemRuby.getItemDamage();
            case 11: return Materials.gemJade.getItemDamage();
            case 12: return Materials.gemSapphire.getItemDamage();
            case 13: return Materials.gemOnyx.getItemDamage();
            case 14: return Materials.gemPhoenixite.getItemDamage();
            case 15: return Materials.gemQuartz.getItemDamage();
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
            case 7: return 2 + random.nextInt(4) + random.nextInt(fortune + 1);
            case 8: return 1 + random.nextInt(2) + random.nextInt(fortune + 1);
            case 9: return 1 + Math.abs(random.nextInt()) % (1 + fortune);
            
            case 10:
            case 11:
            case 12:
                int i = random.nextInt(fortune + 2) - 1;
                
                if(i < 0)
                {
                    i = 0;
                }
                
                return i + 1;
            
            case 15:
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
        if(fortune > 0 && this.blockID != this.idDropped(0, random, fortune))
        {
            int var3 = random.nextInt(fortune + 2) - 1;
            
            if(var3 < 0)
            {
                var3 = 0;
            }
            
            return this.quantityDropped(random) * (var3 + 1);
        }
        else
        {
            return this.quantityDropped(random);
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
    public void dropBlockAsItemWithChance(World world, int par2, int par3, int par4, int md, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(world, par2, par3, par4, md, par6, par7);
        
        if(md > 5)
        {
            int var8 = 0;
            
            switch(md)
            {
                case 7:
                case 8: var8 = MathHelper.getRandomIntegerInRange(world.rand, 2, 5); break; // Sulfur & Saltpeter
                case 9: var8 = MathHelper.getRandomIntegerInRange(world.rand, 1, 4); break; // Apatite
                case 10:
                case 11:
                case 12: var8 = MathHelper.getRandomIntegerInRange(world.rand, 3, 7); break; // Gems // Nikolite 1 to 5
                case 15: var8 = MathHelper.getRandomIntegerInRange(world.rand, 1, 5); break; // Quartz
            }
            
            this.dropXpOnBlockBreak(world, par2, par3, par4, var8);
        }
    }
    
    @Override
    public int getBlockTextureFromSideAndMetadata(int side, int md)
    {
        switch(md)
        {
            case 0: return Defaults.TEXTURE_BLOCK_ORE_COPPER;
            case 1: return Defaults.TEXTURE_BLOCK_ORE_TIN;
            case 2: return Defaults.TEXTURE_BLOCK_ORE_SILVER;
            case 3: return Defaults.TEXTURE_BLOCK_ORE_LEAD;
            case 4: return Defaults.TEXTURE_BLOCK_ORE_ZINC;
            case 5: return Defaults.TEXTURE_BLOCK_ORE_URANIUM;
            case 6: return Defaults.TEXTURE_BLOCK_ORE_BITUMEN;
            case 7: return Defaults.TEXTURE_BLOCK_ORE_SULFUR;
            case 8: return Defaults.TEXTURE_BLOCK_ORE_SALTPETER;
            case 9: return Defaults.TEXTURE_BLOCK_ORE_APATITE;
            case 10: return Defaults.TEXTURE_BLOCK_ORE_RUBY;
            case 11: return Defaults.TEXTURE_BLOCK_ORE_JADE;
            case 12: return Defaults.TEXTURE_BLOCK_ORE_SAPPHIRE;
            case 13: return Defaults.TEXTURE_BLOCK_ORE_ONYX;
            case 14: return Defaults.TEXTURE_BLOCK_ORE_PHOENIXITE;
            case 15: return Defaults.TEXTURE_BLOCK_ORE_QUARTZ;
            default: return 0;
        }
    }
    
    @Override
    public String getTextureFile()
    {
        return CDECore.CDE_BLOCKS;
    }
}
