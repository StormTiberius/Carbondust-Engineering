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
    public float volume,pitch;
    public boolean updateVolume,updatePitch,playerChangedDimension;
    
    public PacketTileSound()
    {
        this.playerChangedDimension = true;
    }
    
    public PacketTileSound(TileEntityWithSound te, boolean updateVolume, boolean updatePitch)
    {
        super(te);
        
        this.volume = te.getVolume();
        this.pitch = te.getPitch();
        this.updateVolume = updateVolume;
        this.updatePitch = updatePitch;
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
        
        data.writeBoolean(updateVolume);
        data.writeBoolean(updatePitch);
        
        data.writeBoolean(playerChangedDimension);
    }

    @Override
    public void readData(DataInputStream data) throws IOException
    {
        super.readData(data);
        
        volume = data.readFloat();
        pitch = data.readFloat();
        
        updateVolume = data.readBoolean();
        updatePitch = data.readBoolean();
        
        playerChangedDimension = data.readBoolean();
    }
}
