/**
 *
 * @author StormTiberius
 */

package cde.core.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.tileentity.TileEntity;

public class PacketTileParticle extends PacketTile
{
    public String particle;
    public int count;
    
    public PacketTileParticle(TileEntity te, String particle, int count)
    {
        super(te);
        this.particle = particle;
        this.count = count;
    }
    
    @Override
    public void writeData(DataOutputStream data) throws IOException
    {
        super.writeData(data);
        
        data.writeUTF(particle);
        data.writeInt(count);
    }

    @Override
    public void readData(DataInputStream data) throws IOException
    {
        super.readData(data);
        
        particle = data.readUTF();
        count = data.readInt();
    }
}
