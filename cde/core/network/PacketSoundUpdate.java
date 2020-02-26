/**
 *
 * @author StormTiberius
 */

package cde.core.network;

import cde.api.ISoundSource;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketSoundUpdate extends PacketSound
{
    public static final byte OFF = 0;
    public static final byte ON = 1;
    public static final byte TOGGLE = 2;
    
    public byte action = Byte.MIN_VALUE;
    public float volume,pitch;
    public boolean updateVolume,updatePitch;
    
    public PacketSoundUpdate(){}
    
    public PacketSoundUpdate(ISoundSource iss, byte action)
    {
        super(iss);
        
        this.action = action;
    }
    
    public PacketSoundUpdate(ISoundSource iss, boolean updateVolume, boolean updatePitch)
    {
        super(iss);
        
        this.volume = iss.getVolume();
        this.pitch = iss.getPitch();
        this.updateVolume = updateVolume;
        this.updatePitch = updatePitch;
    }
    
    public PacketSoundUpdate(ISoundSource iss, boolean updateVolume, boolean updatePitch, byte action)
    {
        super(iss);
        
        this.action = action;
        this.volume = iss.getVolume();
        this.pitch = iss.getPitch();
        this.updateVolume = updateVolume;
        this.updatePitch = updatePitch;
    }
    
    @Override
    public byte getID()
    {
        return PacketIds.SOUND_UPDATE;
    }
    
    @Override
    public void writeData(DataOutputStream data) throws IOException
    {
        super.writeData(data);
        
        data.writeByte(action);
        
        data.writeFloat(volume);
        data.writeFloat(pitch);
        
        data.writeBoolean(updateVolume);
        data.writeBoolean(updatePitch);
    }
    
    @Override
    public void readData(DataInputStream data) throws IOException
    {
        super.readData(data);
        
        action = data.readByte();
        
        volume = data.readFloat();
        pitch = data.readFloat();
        
        updateVolume = data.readBoolean();
        updatePitch = data.readBoolean();
    }
}
