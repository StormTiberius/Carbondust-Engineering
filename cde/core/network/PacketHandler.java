/**
 *
 * @author StormTiberius
 */

package cde.core.network;

import cde.api.INetwork;
import cde.core.sound.SoundHelper;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;

public class PacketHandler implements IPacketHandler
{
    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload message, Player player)
    {
        DataInputStream data = new DataInputStream(new ByteArrayInputStream(message.data));
        
        try
        {
            EntityPlayer ep = (EntityPlayer)player;
            
            int packetId = data.read();
            PacketCDE packet = getPacketById(packetId);
            packet.readData(data);
            
            switch(packetId)
            {
                case PacketIds.SOUND:
				sendPacketToSoundHelper((PacketSound)packet, ep);
				break;
                case PacketIds.ENTITY:
                                sendPacketToEntity((PacketEntity)packet, ep);
                                break;
                case PacketIds.TILE:
                case PacketIds.TILE_PARTICLE:
                                sendPacketToTileEntity((PacketTile)packet, ep);
                                break;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private PacketCDE getPacketById(int id)
    {
        switch(id)
        {
            case PacketIds.SOUND: return new PacketSound();
            case PacketIds.ENTITY: return new PacketEntity();
            case PacketIds.TILE: return new PacketTile();
            case PacketIds.TILE_PARTICLE: return new PacketTileParticle();
            default: return null;
        }
    }
    
    private void sendPacketToSoundHelper(PacketSound packet, EntityPlayer player)
    {
        SoundHelper.receivePacket(packet, player);
    }
    
    private void sendPacketToEntity(PacketEntity packet, EntityPlayer player)
    {
        Entity entity = player.worldObj.getEntityByID(packet.entityId);
        
        if(entity instanceof INetwork)
        {
            ((INetwork)entity).receivePacket(packet, player);
        }
    }
    
    private void sendPacketToTileEntity(PacketTile packet, EntityPlayer player)
    {
        TileEntity te = player.worldObj.getBlockTileEntity(packet.xCoord, packet.yCoord, packet.zCoord);
        
        if(te instanceof INetwork)
        {
            ((INetwork)te).receivePacket(packet, player);
        }
    }
}
