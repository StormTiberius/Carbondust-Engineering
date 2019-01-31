package cde.ember;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;

public class WorldGenSpawn
{   
    public void generate(World world, Random random, int chunkX, int chunkZ)
    {
        int xPos,yPos,zPos;
        
        for(int y = 5; y >= 0; y--)
        {
            for(int x = 4; x < 13; x++)
            {
                for(int z = 4; z < 13; z++)
                {
                    xPos = chunkX * 16 + x;
                    yPos = 19 + y;
                    zPos = chunkZ * 16 + z;
                    
                    if(x == 4 || x == 12 || y == 5 || z == 4 || z == 12)
                    {
                        if(!world.isAirBlock(xPos, yPos, zPos))
                        {
                            world.setBlock(xPos, yPos, zPos, Block.cobblestone.blockID);
                        }
                    }
                    else if(y == 0)
                    {
                        if(!world.isAirBlock(xPos, yPos, zPos))
                        {
                            world.setBlock(xPos, yPos, zPos, Block.grass.blockID);
                        }
                    }
                    else
                    {
                        world.setBlock(xPos, yPos, zPos, 0);
                    }
                }
            }
        }
        
        xPos = chunkX * 16 + 8;
        yPos = 20;
        zPos = chunkZ * 16 + 8;
        
        world.setBlockWithNotify(xPos + 1, yPos, zPos, Block.torchWood.blockID);
        world.setBlockWithNotify(xPos, yPos, zPos + 1, Block.torchWood.blockID);
        world.setBlockWithNotify(xPos - 1, yPos, zPos, Block.torchWood.blockID);
        world.setBlockWithNotify(xPos, yPos, zPos - 1, Block.torchWood.blockID);
        
        switch(random.nextInt(4))
        {
            case 0: xPos -= 3; zPos -= 3; xPos += 1 + random.nextInt(5); break;
            case 1: xPos -= 3; zPos -= 3; zPos += 1 + random.nextInt(5); break;
            case 2: xPos += 3; zPos += 3; xPos -= 1 + random.nextInt(5); break;
            case 3: xPos += 3; zPos += 3; zPos -= 1 + random.nextInt(5); break;
        }
        
        if(world.getBlockMaterial(xPos, yPos - 1, zPos).isSolid() && world.isAirBlock(xPos, yPos, zPos))
        {
            world.setBlockWithNotify(xPos, yPos, zPos, Block.chest.blockID);
            
            TileEntityChest tec = (TileEntityChest)world.getBlockTileEntity(xPos, yPos, zPos);

            if (tec != null)
            {
                ChestGenHooks info = ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST);
                WeightedRandomChestContent.generateChestContents(random, info.getItems(random), tec, info.getCount(random));
            }
        }
    }
    
    private ChunkCoordinates getChestOffset(int spot, int chunkX, int ChunkZ)
    {
        ChunkCoordinates cc = new ChunkCoordinates();
        
        switch(spot)
        {
            case 0: cc.set(6,0,5); return cc;
            case 1: cc.set(7,0,5); return cc;
            case 2: cc.set(8,0,5); return cc;
            case 3: cc.set(9,0,5); return cc;
            case 4: cc.set(10,0,5); return cc;
            case 5: cc.set(11,0,6); return cc;
            case 6: cc.set(11,0,7); return cc;
            case 7: cc.set(11,0,8); return cc;
            case 8: cc.set(11,0,9); return cc;
            case 9: cc.set(11,0,10); return cc;
            case 10: cc.set(10,0,11); return cc;
            case 11: cc.set(9,0,11); return cc;
            case 12: cc.set(8,0,11); return cc;
            case 13: cc.set(7,0,11); return cc;
            case 14: cc.set(6,0,11); return cc;
            case 15: cc.set(5,0,10); return cc;
            case 16: cc.set(5,0,9); return cc;
            case 17: cc.set(5,0,8); return cc;
            case 18: cc.set(5,0,7); return cc;
            case 19: cc.set(5,0,6); return cc;
            default: return cc;
        }
    }
}
