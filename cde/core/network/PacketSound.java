/**
 *
 * @author StormTiberius
 */

package cde.core.network;

import cde.api.ISoundSource;
import cde.core.network.PacketCDE;
import cde.core.network.PacketIds;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketSound extends PacketCDE
{
    public String sourceName;
    public float volume,pitch;
    public boolean updateVolume,updatePitch;
    
    public PacketSound(){}
    
    public PacketSound(ISoundSource iss, boolean updateVolume, boolean updatePitch)
    {
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
        data.writeUTF(sourceName);
        
        data.writeFloat(volume);
        data.writeFloat(pitch);
        
        data.writeBoolean(updateVolume);
        data.writeBoolean(updatePitch);
    }
    
    @Override
    public void readData(DataInputStream data) throws IOException
    {
        sourceName = data.readUTF();
        
        volume = data.readFloat();
        pitch = data.readFloat();
        
        updateVolume = data.readBoolean();
        updatePitch = data.readBoolean();
    }
}
