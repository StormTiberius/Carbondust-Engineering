/**
 *
 * @author StormTiberius
 */

package cde.core;

import net.minecraft.tileentity.TileEntity;

public abstract class TileEntityCde extends TileEntity
{
    protected final int[] COUNTER;
    
    public TileEntityCde()
    {
        this.COUNTER = new int[4];
    }
}
