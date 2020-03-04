/**
 *
 * @author StormTiberius
 */

package cde.core.network;

import cde.api.ISoundSource;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketSound extends PacketTile
{
    public String sourceName;
    
    public PacketSound()
    {
        this.sourceName = "";
    }
    
    public PacketSound(ISoundSource iss)
    {
        super(iss.getSourceX(), iss.getSourceY(), iss.getSourceZ());
        
        this.sourceName = iss.getSourceName();
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
    }
    
    @Override
    public void readData(DataInputStream data) throws IOException
    {
        super.readData(data);
        
        sourceName = data.readUTF();
    }
}
