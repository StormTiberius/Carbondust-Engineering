/**
 *
 * @author StormTiberius
 */

package cde.industry.machine;

import cde.core.sound.TileEntityWithSound;
import ic2.api.IWrenchable;
import ic2.api.Items;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;

public abstract class TileEntityRotateable extends TileEntityWithSound implements IWrenchable
{
    private short facing;
    
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        facing = tag.getShort("facing");
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        tag.setShort("facing", facing);
    }
    
    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
        if(worldObj.isRemote)
        {
            readFromNBT(pkt.customParam1);
        }
    }
    
    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound tag = new NBTTagCompound();
        writeToNBT(tag);
        return new Packet132TileEntityData(xCoord, yCoord, zCoord, 4, tag);
    }
    
    @Override
    public boolean wrenchCanSetFacing(EntityPlayer entityPlayer, int side)
    {
        return true;
    }
    
    @Override
    public short getFacing()
    {
        return facing;
    }
    
    @Override
    public void setFacing(short facing)
    {
        this.facing = facing;
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }
    
    @Override
    public boolean wrenchCanRemove(EntityPlayer entityPlayer)
    {
        return false;
    }
    
    @Override
    public float getWrenchDropRate()
    {
        return 1.0F;
    }
    
    @Override
    public ItemStack getWrenchDrop(EntityPlayer entityPlayer)
    {
        if(entityPlayer.worldObj.rand.nextFloat() > getWrenchDropRate())
        {
            return new ItemStack(Items.getItem("machine").itemID, 1, 0);
        }
        
        return new ItemStack(worldObj.getBlockId(xCoord, yCoord, zCoord), 1, worldObj.getBlockMetadata(xCoord, yCoord, zCoord));
    }
}
