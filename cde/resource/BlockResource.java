/**
 *
 * @author StormTiberius
 */

package cde.resource;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;

public class BlockResource extends Block
{   
    public BlockResource(int id)
    {
        super(id, 17, Material.rock);
        setBlockUnbreakable();
        setResistance(6000000.0F);
        setStepSound(soundStoneFootstep);
        disableStats();
        setTickRandomly(true);
        setCreativeTab(CreativeTabs.tabBlock);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random)
    {
        setResource(world, x, y, z);
        world.scheduleBlockUpdate(x, y, z, this.blockID, 5);
    }
    
    @Override
    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);
        world.scheduleBlockUpdate(x, y, z, this.blockID, 5);
    }

    private void setResource(World world, int x, int y, int z)
    {
        if(world.isAirBlock(x, y + 1, z))
        {
            WeightedObject wo = ResourceManager.getResource();
            
            world.setBlockAndMetadataWithNotify(x, y + 1, z, wo.objectId, wo.objectMeta);
        }
    }
}
