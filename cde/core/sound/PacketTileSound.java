/**
 *
 * @author StormTiberius
 */

package cde.core.sound;

import cde.core.network.PacketIds;
import cde.core.network.PacketTile;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketTileSound extends PacketTile
{
    private float volume,pitch;
    
    public PacketTileSound(TileEntityWithSound te)
    {
        this.xCoord = te.xCoord;
        this.yCoord = te.yCoord;
        this.zCoord = te.zCoord;
        
        this.volume = te.getVolume();
        this.pitch = te.getPitch();
    }
    
    @Override
    public byte getID()
    {
        return PacketIds.SOUND;
    }
    
    @Override
    public void writeData(DataOutputStream data) throws IOException
    {
        super.writeData(data);
        
        data.writeFloat(volume);
        data.writeFloat(pitch);
    }

    @Override
    public void readData(DataInputStream data) throws IOException
    {
        super.readData(data);
        
        volume = data.readFloat();
        pitch = data.readFloat();
    }
}
