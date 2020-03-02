/**
 *
 * @author StormTiberius
 */

package cde.industry.machine;

import buildcraft.api.tools.IToolWrench;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public abstract class BlockMachine extends BlockContainer
{
    public BlockMachine(int id)
    {
        super(id, Material.iron);
    }
    
    @Override
    public int damageDropped(int md)
    {
        return md;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return null;
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int par6, float par7, float par8, float par9)
    {
        if(entityplayer.isSneaking())
        {
            return false;
        }
        
        TileEntity te = world.getBlockTileEntity(x, y, z);
        
        if(entityplayer.getCurrentEquippedItem() != null && entityplayer.getCurrentEquippedItem().getItem() instanceof IToolWrench && te instanceof TileEntityMachine)
        {
            TileEntityMachine tem = (TileEntityMachine)te;
            
            IToolWrench tool = (IToolWrench)entityplayer.getCurrentEquippedItem().getItem();
            
            if(tool.canWrench(entityplayer, x, y, z))
            {
                if(!world.isRemote)
                {
                    if(tem.isActive())
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
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getBlockTexture(IBlockAccess iba, int x, int y, int z, int side)
    {
        int md = iba.getBlockMetadata(x, y, z);
        TileEntity te = iba.getBlockTileEntity(x, y, z);
        
        if(te instanceof TileEntityMachine)
        {
            TileEntityMachine tem = (TileEntityMachine)te;
            
            if(tem.isConnected(ForgeDirection.getOrientation(side)))
            {
                return 255;
            }
            
            if(tem.isActive())
            {
                return 16 * (side + 6) + md;
            }
        }
        
        return getBlockTextureFromSideAndMetadata(side, md);
    }
    
    @Override
    public int getBlockTextureFromSideAndMetadata(int side, int md)
    {
        return 16 * side + md;
    }
}
