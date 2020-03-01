/**
 *
 * @author StormTiberius
 */

package cde.industry.machine;

import buildcraft.api.tools.IToolWrench;
import cde.CDECore;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class BlockMachine extends BlockContainer
{
    public BlockMachine(int id)
    {
        super(id, Material.iron);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int par6, float par7, float par8, float par9)
    {
        if(entityplayer.isSneaking())
        {
            return false;
        }
        
        TileEntity te = world.getBlockTileEntity(x, y, z);
        
        if(entityplayer.getCurrentEquippedItem() != null && entityplayer.getCurrentEquippedItem().getItem() instanceof IToolWrench && te instanceof TileEntityEnergyBase)
        {
            TileEntityEnergyBase tem = (TileEntityEnergyBase)te;
            
            IToolWrench tool = (IToolWrench)entityplayer.getCurrentEquippedItem().getItem();
            
            if(tool.canWrench(entityplayer, x, y, z))
            {
                if(!world.isRemote)
                {
                    if(tem.isPowered())
                    {
                        entityplayer.sendChatToPlayer(tem.useWrench(false));
                    }
                    else
                    {
                        entityplayer.sendChatToPlayer(tem.useWrench(true));
                    }
                }
                
                tool.wrenchUsed(entityplayer, x, y, z);
                
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    public String getTextureFile()
    {
        return CDECore.CDE_BLOCKS;
    }
}
