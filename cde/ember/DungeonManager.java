/**
 *
 * @author StormTiberius
 */

package cde.ember;

import cde.ember.dungeons.VillageDungeon;
import cde.ember.dungeons.VanillaDungeon;
import cde.ember.dungeons.JungleDungeon;
import cde.ember.dungeons.StarterDungeon;
import cde.ember.dungeons.NetherDungeon;
import cde.ember.dungeons.DesertDungeon;
import java.util.Random;
import net.minecraft.world.World;

public class DungeonManager
{
    public static boolean starter;
    
    public static boolean generate(World world, Random random, int x, int y, int z)
    {
        if(starter)
        {
            return (new StarterDungeon()).generate(world, random, x, y, z);
        }
        else
        {
            switch(random.nextInt(5))
            {
                case 0: return ((new VanillaDungeon()).generate(world, random, x, y, z));
                case 1: return ((new VillageDungeon()).generate(world, random, x, y, z));
                case 2: return ((new DesertDungeon()).generate(world, random, x, y, z));
                case 3: return ((new JungleDungeon()).generate(world, random, x, y, z));
                case 4: return ((new NetherDungeon()).generate(world, random, x, y, z));
                default: return false;
            }
        }
    }
}
