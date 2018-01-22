/**
 *
 * @author StormTiberius
 */

package cde.core.speaker;

import cde.CDECore;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSpeaker extends BlockContainer
{   
    public BlockSpeaker(int par1)
    {
        super(par1, Material.wood);
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1)
    {
        return new TileEntitySpeaker();
    }

    @Override
    public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        if(!par1World.isRemote)
        {
            TileEntity te = par1World.getBlockTileEntity(x, y, z);
            
            if(te != null)
            {
                TileEntitySpeaker tes = (TileEntitySpeaker)te;

                if(!tes.isWorking())
                {
                    if(par5EntityPlayer.isSneaking())
                    {
                        par5EntityPlayer.sendChatToPlayer("New sound: " + tes.setSound() + " set");
                    }
                    else
                    {
                        par5EntityPlayer.sendChatToPlayer(tes.nextSound());
                    }
                }
                else
                {
                    par5EntityPlayer.sendChatToPlayer(tes.getSound());
                }
            }
        }
        
        return true;
    }

    @Override
    public String getTextureFile()
    {             
        return CDECore.CDE_BLOCKS;
    }
    
    @Override
    public int getBlockTextureFromSide(int side)
    {
        switch(side)
        {
            case 0: return 25;
            case 1: return 25;
            case 2: return 26;
            case 3: return 26;
            case 4: return 26;
            case 5: return 26;
            default: return 26;
        }
    }
}
