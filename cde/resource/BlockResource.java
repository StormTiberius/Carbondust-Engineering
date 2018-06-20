/**
 *
 * @author StormTiberius
 */

package cde.resource;

import cde.ResourceCore;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockResource extends Block
{   
    public BlockResource(int id)
    {
        super(id, 17, Material.rock);
        enableStats = false;
    }
        
    @Override
    public void updateTick(World world, int x, int y, int z, Random random)
    {
        if(!world.isRemote)
        {
            setResource(world, x, y, z);
            world.scheduleBlockUpdate(x, y, z, blockID, ResourceCore.resourceRate);
        }
    }
    
    @Override
    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);
        
        if(!world.isRemote)
        {
            world.scheduleBlockUpdate(x, y, z, blockID, ResourceCore.resourceRate);
        }
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
