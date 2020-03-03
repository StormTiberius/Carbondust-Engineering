/**
 *
 * @author StormTiberius
 */

package cde.industry.misc;

import cde.api.SoundSourceEvent;
import cde.core.sound.TileEntityWithSound;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraftforge.common.MinecraftForge;

public class TileEntitySpeaker extends TileEntityWithSound
{
    private int index;
    
    protected String getSound()
    {
        return index + 1 + ". " + MiscModule.sounds[index]; 
    }
    
    protected String setSound()
    {
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        
        return index + 1 + ". " + MiscModule.sounds[index];
    }
    
    protected String nextSound()
    {
        if(index >= MiscModule.sounds.length - 1 || index >= MiscModule.volumes.length - 1 || index >= MiscModule.pitchs.length - 1)
        {
            index = 0;
        }
        else
        {
            index++;
        }

        return index + 1 + ". " + MiscModule.sounds[index];
    }
    
    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
        if(worldObj.isRemote)
        {
            int i = index;
            
            readFromNBT(pkt.customParam1);
            
            if(i != index)
            {
                MinecraftForge.EVENT_BUS.post((new SoundSourceEvent(worldObj, this, SoundSourceEvent.UNLOAD)));
                MinecraftForge.EVENT_BUS.post((new SoundSourceEvent(worldObj, this, SoundSourceEvent.LOAD)));
            }
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
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        
        int i = par1NBTTagCompound.getInteger("index");
        
        if(i >= MiscModule.sounds.length || i >= MiscModule.volumes.length || i >= MiscModule.pitchs.length)
        {
            index = 0;
        }
        else
        {
            index = i;
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("index", index);
    }
    
    // TileEntityWithSound
    @Override
    public boolean getEmitSound()
    {
        return worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
    }
    
    // ISoundSource
    @Override
    public String getResourceName()
    {   
        return MiscModule.sounds[index];
    }
    
    @Override
    public float getVolume()
    {   
        return 0.01F * MiscModule.volumes[index];
    }
    
    @Override
    public float getPitch()
    {        
        return 0.01F * MiscModule.pitchs[index];
    }
}
