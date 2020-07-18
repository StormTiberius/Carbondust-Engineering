/**
 *
 * @author StormTiberius
 */

package cde.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketEntity extends PacketCde
{
    public int entityId;
    
    public PacketEntity(){}
    
    public PacketEntity(int entityId)
    {
        this.entityId = entityId;
    }
    
    @Override
    public byte getID()
    {
        return PacketIds.ENTITY;
    }
    
    @Override
    public void writeData(DataOutputStream data) throws IOException
    {
        data.writeInt(entityId);
    }
    
    @Override
    public void readData(DataInputStream data) throws IOException
    {
        entityId = data.readInt();
    }
}
