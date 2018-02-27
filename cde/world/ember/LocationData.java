/**
 *
 * @author StormTiberius
 */

package cde.world.ember;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.WorldSavedData;

public class LocationData extends WorldSavedData
{
    private final ChunkCoordinates c;
    
    public LocationData(String s)
    {
        super(s);
        c = new ChunkCoordinates(0, 0, 0);
    }
    
    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        c.set(par1NBTTagCompound.getInteger("xPos"), par1NBTTagCompound.getInteger("yPos"), par1NBTTagCompound.getInteger("zPos"));
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        par1NBTTagCompound.setInteger("xPos", c.posX);
        par1NBTTagCompound.setInteger("yPos", c.posY);
        par1NBTTagCompound.setInteger("zPos", c.posZ);
    }
    
    public ChunkCoordinates getSpawn()
    {
        return c;
    }
    
    public void setSpawn(int x, int y, int z)
    {
        c.set(x, y, z);
        markDirty();
    }
    
    public boolean isValid()
    {
        return !(c.posX == 0 && c.posY == 0 && c.posZ == 0);
    }
}
