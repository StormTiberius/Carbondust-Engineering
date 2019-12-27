package cde.core.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.tileentity.TileEntity;

public class PacketTile extends PacketCDE
{
    public int xCoord,yCoord,zCoord;
    
    public PacketTile(){}
    
    public PacketTile(TileEntity te)
    {
        this.xCoord = te.xCoord;
        this.yCoord = te.yCoord;
        this.zCoord = te.zCoord;
    }

    @Override
    public byte getID()
    {
        return PacketIds.TILE;
    }
    
    @Override
    public void writeData(DataOutputStream data) throws IOException
    {
        data.writeInt(xCoord);
        data.writeInt(yCoord);
        data.writeInt(zCoord);
    }

    @Override
    public void readData(DataInputStream data) throws IOException
    {
        xCoord = data.readInt();
        yCoord = data.readInt();
        zCoord = data.readInt();
    }
}
