/**
 *
 * @author StormTiberius
 */

package cde.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketSound extends PacketCde
{
    public boolean playerChangedDimension;
    
    public PacketSound()
    {
        this.playerChangedDimension = true;
    }
    
    @Override
    public byte getID()
    {
        return PacketIds.SOUND;
    }
    
    @Override
    public void writeData(DataOutputStream data) throws IOException
    {
        data.writeBoolean(playerChangedDimension);
    }
    
    @Override
    public void readData(DataInputStream data) throws IOException
    {
        playerChangedDimension = data.readBoolean();
    }
}
