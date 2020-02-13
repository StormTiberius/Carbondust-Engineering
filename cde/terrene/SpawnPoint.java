/**
 *
 * @author StormTiberius
 */

package cde.terrene;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.WorldSavedData;

public class SpawnPoint extends WorldSavedData
{
    public static final int SPAWN_X = 264;
    public static final int SPAWN_Y = 128;
    public static final int SPAWN_Z = 264;
    
    private final ChunkCoordinates c;
    
    public SpawnPoint(String s)
    {
        super(s);
        c = new ChunkCoordinates(SPAWN_X, SPAWN_Y, SPAWN_Z);
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        c.set(tag.getInteger("xPos"), tag.getInteger("yPos"), tag.getInteger("zPos"));
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        tag.setInteger("xPos", c.posX);
        tag.setInteger("yPos", c.posY);
        tag.setInteger("zPos", c.posZ);
    }
    
    public ChunkCoordinates getSpawnPoint()
    {
        return c;
    }
    
    public void setSpawnPoint(int x, int y, int z)
    {
        c.set(x, y, z);
        markDirty();
    }
}
