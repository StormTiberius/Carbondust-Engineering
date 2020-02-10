/**
 *
 * @author StormTiberius
 */

package cde.terrene;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityMob;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;

public class EventManagerTerrene
{
    private final int[] ALLOWED_BLOCK_IDS;
    
    public EventManagerTerrene()
    {
        ALLOWED_BLOCK_IDS = new int[]
        {    
            Block.stone.blockID, 
            Block.cobblestone.blockID, 
            Block.cobblestoneMossy.blockID, 
            Block.dirt.blockID, 
            Block.grass.blockID, 
            Block.sand.blockID, 
            Block.gravel.blockID, 
            Block.obsidian.blockID,
            Block.blockSnow.blockID,
            Block.bedrock.blockID
        };
    }
    
    @ForgeSubscribe
    public void lse(LivingSpawnEvent event)
    {
        if(event.entity.dimension == 0)
        {
            if(event.entity instanceof EntityMob)
            {
                int id = event.world.getBlockId((int)event.x, (int)event.y - 1, (int)event.z);
                
                boolean flag = false;
                
                for(int blockId : ALLOWED_BLOCK_IDS)
                {
                    if(blockId == id)
                    {
                        flag = true;
                        break;
                    }
                }
                
                if(!flag)
                {
                    event.setResult(Event.Result.DENY);
                }
            }
        }
    }
}
