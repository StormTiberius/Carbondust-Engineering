/**
 *
 * @author StormTiberius
 */

package cde.core.resource;

import cde.CDECore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockOreGeoStone extends Block
{
    public BlockOreGeoStone(int par1, int par2)
    {
        super(par1, par2, Material.rock);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }
    
    @Override
    public String getTextureFile()
    {             
        return CDECore.CDE_BLOCKS;
    }
}
