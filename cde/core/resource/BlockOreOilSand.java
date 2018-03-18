/**
 *
 * @author StormTiberius
 */

package cde.core.resource;

import cde.CDECore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockOreOilSand extends Block
{
    public BlockOreOilSand(int par1, int par2)
    {
        super(par1, par2, Material.sand);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }
    
    @Override
    public String getTextureFile()
    {             
        return CDECore.CDE_BLOCKS;
    }
}
