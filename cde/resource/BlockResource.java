/**
 *
 * @author StormTiberius
 */

package cde.resource;

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
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
        
        if(!par1World.isRemote)
        {
            tryPlaceResource(par1World, par2, par3, par4);
        }
    }

    private void tryPlaceResource(World world, int x, int y, int z)
    {
        if(world.isAirBlock(x, y + 1, z))
        {
            WeightedObject wo = ResourceManager.getResource();

            world.setBlockAndMetadataWithNotify(x, y + 1, z, wo.objectId, wo.objectMeta);
        }
    }
}
