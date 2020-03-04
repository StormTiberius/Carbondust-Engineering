/**
 *
 * @author StormTiberius
 */

package cde.industry.misc;

import cde.CDECore;
import cde.core.Defaults;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMisc extends BlockContainer
{   
    public BlockMisc(int par1)
    {
        super(par1, Material.wood);
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
    public TileEntity createNewTileEntity(World world, int md)
    {
        switch(md)
        {
            case 0: return new TileEntitySpeaker();
            case 1: return new TileEntitySmokestack();
            default: return null;
        }
    }
    
    @Override
    public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        if(!par1World.isRemote)
        {
            TileEntity te = par1World.getBlockTileEntity(x, y, z);
            
            if(te instanceof TileEntitySpeaker)
            {
                TileEntitySpeaker tes = (TileEntitySpeaker)te;

                if(!tes.getEmitSound())
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
    public int getBlockTextureFromSideAndMetadata(int side, int md)
    {
        switch(md)
        {
            case 0: 
                switch(side)
                {
                    case 0: return Defaults.TEXTURE_MACHINE_SPEAKER_BOTTOM_TOP;
                    case 1: return Defaults.TEXTURE_MACHINE_SPEAKER_BOTTOM_TOP;
                    case 2: return Defaults.TEXTURE_MACHINE_SPEAKER_SIDE;
                    case 3: return Defaults.TEXTURE_MACHINE_SPEAKER_SIDE;
                    case 4: return Defaults.TEXTURE_MACHINE_SPEAKER_SIDE;
                    case 5: return Defaults.TEXTURE_MACHINE_SPEAKER_SIDE;
                    default: return 0;
                }
            
            case 1:
                switch(side)
                {
                    case 0: return Defaults.TEXTURE_MACHINE_SMOKESTACK_BOTTOM;
                    case 1: return Defaults.TEXTURE_MACHINE_SMOKESTACK_TOP;
                    case 2: return Defaults.TEXTURE_MACHINE_SMOKESTACK_SIDE;
                    case 3: return Defaults.TEXTURE_MACHINE_SMOKESTACK_SIDE;
                    case 4: return Defaults.TEXTURE_MACHINE_SMOKESTACK_SIDE;
                    case 5: return Defaults.TEXTURE_MACHINE_SMOKESTACK_SIDE;
                    default: return 0;
                }
                
            default: return 0;
        }
    }
    
    @Override
    public String getTextureFile()
    {             
        return CDECore.CDE_BLOCKS;
    }
}
