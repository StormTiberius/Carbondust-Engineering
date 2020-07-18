/**
 *
 * @author StormTiberius
 */

package cde.network;

import cde.api.INetwork;
import cde.sound.SoundHelper;
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
            int packetId = data.read();
            
            EntityPlayer ep = (EntityPlayer)player;
            
            switch(packetId)
            {
                case PacketIds.ENTITY:
                                PacketEntity entity = new PacketEntity();
                                entity.readData(data);
                                sendPacketToEntity(entity, ep);
                                break;
                case PacketIds.TILE:
                case PacketIds.TILE_PARTICLE:
                                PacketTile tile = getPacket(packetId);
                                tile.readData(data);
                                sendPacketToTileEntity(tile, ep);
                                break;
                case PacketIds.SOUND:
                                PacketTileSound sound = new PacketTileSound();
				sound.readData(data);
				sendPacketToSoundHelper(sound, ep);
				break;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private PacketTile getPacket(int id)
    {
        switch(id)
        {
            case PacketIds.TILE: return new PacketTile();
            case PacketIds.TILE_PARTICLE: return new PacketTileParticle();
            default: return new PacketTile();
        }
    }
    
    private void sendPacketToEntity(PacketEntity packet, EntityPlayer player)
    {
        Entity entity = player.worldObj.getEntityByID(packet.entityId);
        
        if(entity instanceof INetwork)
        {
            ((INetwork)entity).onNetworkPacketArrival(packet, player);
        }
    }
    
    private void sendPacketToTileEntity(PacketTile packet, EntityPlayer player)
    {
        TileEntity te = player.worldObj.getBlockTileEntity(packet.xCoord, packet.yCoord, packet.zCoord);
        
        if(te instanceof INetwork)
        {
            ((INetwork)te).onNetworkPacketArrival(packet, player);
        }
    }
    
    private void sendPacketToSoundHelper(PacketTileSound packet, EntityPlayer player)
    {
        SoundHelper.onNetworkPacketArrival(packet, player);
    }
}
