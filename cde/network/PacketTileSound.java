/**
 *
 * @author StormTiberius
 */

package cde.network;

import cde.api.ISoundSource;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketTileSound extends PacketTile
{
    public String sourceName;
    public float volume,pitch;
    public boolean updateVolume,updatePitch,playerChangedDimension;
    
    public PacketTileSound()
    {
        this.playerChangedDimension = true;
    }
    
    public PacketTileSound(ISoundSource iss, boolean updateVolume, boolean updatePitch)
    {
        super(iss.getSourceX(), iss.getSourceY(), iss.getSourceZ());
        
        this.sourceName = iss.getSourceName();
        this.volume = iss.getVolume();
        this.pitch = iss.getPitch();
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
        
        data.writeUTF(sourceName);
        
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
        
        sourceName = data.readUTF();
        
        volume = data.readFloat();
        pitch = data.readFloat();
        
        updateVolume = data.readBoolean();
        updatePitch = data.readBoolean();
        
        playerChangedDimension = data.readBoolean();
    }
}
