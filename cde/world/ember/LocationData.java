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
    public static final int SPAWN_X = 264;
    public static final int SPAWN_Y = 128;
    public static final int SPAWN_Z = 264;
    
    private final ChunkCoordinates c;
    
    public LocationData(String s)
    {
        super(s);
        c = new ChunkCoordinates(SPAWN_X, SPAWN_Y, SPAWN_Z);
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
    
    public ChunkCoordinates getSpawnLocation()
    {
        return c;
    }
    
    public void setSpawnLocation(int x, int y, int z)
    {
        c.set(x, y, z);
        markDirty();
    }
}