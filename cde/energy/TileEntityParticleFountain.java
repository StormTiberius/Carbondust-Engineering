/**
 *
 * @author StormTiberius
 */

package cde.energy;

import cde.EnergyCore;
import cde.core.resource.ResourceManager;
import cde.core.resource.WeightedObject;
import ic2.api.Direction;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergyTile;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityParticleFountain extends TileEntityMachine implements IEnergySink
{   
    private int euBuffer;
    private final int BUFFER_SIZE = 4000;
    private final int WORK_CYCLE_EU_COST = 500;
    private int counter;
    private TileEntity te;
    private IInventory ii;
    private ForgeDirection direction;
    private ArrayList<ItemStack> resources;
    
    public TileEntityParticleFountain()
    {

    }
        
    @Override
    protected void doWorkCycle()
    {
        if(counter >= 20)
        {
            counter = 0;
            
            if(hasOutput())
            {
                if(resources != null && !resources.isEmpty())
                {
                    addToInventory();
                }

                else if(euBuffer >= WORK_CYCLE_EU_COST)
                {
                    WeightedObject wo = ResourceManager.getResource();
                    Block block = Block.blocksList[wo.objectId];

                    if(block != null)
                    {
                        resources = block.getBlockDropped(worldObj, 0, 0, 0, wo.objectMeta, 0);

                        if(resources != null)
                        {
                            euBuffer -= WORK_CYCLE_EU_COST;
                            addToInventory();
                        }
                    }
                }
            }
        }
        
        counter++;
    }
    
    private void addToInventory()
    {
        boolean flag = false;
        
        for(ItemStack is : resources)
        {
            if(is != null)
            {
                int emptySlotId = -1;
                
                for(int i = 0; i < ii.getSizeInventory(); i++)
                {
                    ItemStack inInventory = ii.getStackInSlot(i);
                    
                    if(inInventory == null)
                    {
                        if(emptySlotId < 0)
                        {
                            emptySlotId = i;
                        }
                    }
                    
                    else if(inInventory.stackSize < inInventory.getMaxStackSize() && inInventory.itemID == is.itemID && inInventory.getItemDamage() == is.getItemDamage())
                    {
                        int amount = Math.min(inInventory.getMaxStackSize() - inInventory.stackSize, is.stackSize);

                        ItemStack tmp = is.splitStack(amount);

                        tmp.stackSize += inInventory.stackSize;

                        ii.setInventorySlotContents(i, tmp);
                        flag = true;    
                        
                        if(is.stackSize < 1)
                        {
                            break;
                        }
                    }
                }
                
                if(emptySlotId >= 0 && is.stackSize > 0)
                {
                    ii.setInventorySlotContents(emptySlotId, is);
                    flag = true;
                }
            }
        }
        
        if(flag)
        {
            resources.clear();
        }
    }
    
    private boolean hasOutput()
    {
        for(int i = 1; i < 6; i++)
        {
            direction = ForgeDirection.getOrientation(i);
            te = worldObj.getBlockTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);

            if(te instanceof IInventory)
            {
                ii = (IInventory)te;
                
                return ii.getSizeInventory() > 0;
            }
        }
        
        return false;
    }
    
    @Override
    public String useWrench(boolean flag)
    {
        return "Particle Fountain by CDE";
    }
    
    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        euBuffer = par1NBTTagCompound.getInteger("euBuffer");
        counter = par1NBTTagCompound.getInteger("counter");
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("euBuffer", euBuffer);
        par1NBTTagCompound.setInteger("counter", counter);
    }
    
    // IC2 Methods
    @Override
    public boolean acceptsEnergyFrom(TileEntity emitter, Direction direction)
    {
        return emitter instanceof IEnergyTile;
    }
    
    @Override
    public int demandsEnergy()
    {
        if(isRedstonePowered && euBuffer < BUFFER_SIZE)
        {
            return BUFFER_SIZE - euBuffer;
        }
        
        return 0;
    }
    
    @Override
    public int injectEnergy(Direction directionFrom, int amount)
    {
        int uncharged = BUFFER_SIZE - euBuffer;
        
        if(amount > uncharged)
        {
            int excess = amount - uncharged;
            
            euBuffer += amount - excess;
            
            return excess;
        }
        
        euBuffer += amount;
        
        return 0;
    }

    @Override
    public int getMaxSafeInput()
    {
        return 2048;
    }
        
    // Ambient Sounds
    @Override
    public boolean isWorking()
    {
        return isPowered();
    }
    
    @Override
    public String getSoundFileName()
    {
        return "electric-mining-drill.ogg";
    }
    
    @Override
    public float getVolume()
    {
        return 1.0F / 100 * EnergyCore.particleFountainVolume;
    }
    
    @Override
    public float getPitch()
    {
        return 1.0F / 100 * EnergyCore.particleFountainPitch;
    }
}
