/**
 *
 * @author Luna
 */

package cde.core.sound.waves;

import cde.CDECore;
import cde.core.network.PacketSoundUpdate;
import cde.core.sound.SoundWave;
import ic2.api.energy.tile.IEnergySource;
import java.lang.reflect.Method;
import java.net.URL;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class SoundWaveGenerator extends SoundWave
{
    public SoundWaveGenerator(int x, int y, int z, int machineType)
    {
        super(x,y,z, machineType);
    }
    
    @Override
    public void onUpdate(World world)
    {
        TileEntity te = world.getBlockTileEntity(posX, posY, posZ);
        
        if(te != null)
        {
            Method method;
            
            try
            {
                method = te.getClass().getMethod("getActive");
                
                boolean flag = (Boolean)method.invoke(te);
                
                if(flag != isPlaying())
                {
                    setPlaying(flag);
                    
                    byte action = PacketSoundUpdate.OFF;
                    
                    if(flag)
                    {
                        action = PacketSoundUpdate.ON;
                    }
                    
                    PacketSoundUpdate psu = new PacketSoundUpdate(this, action);
                    
                    CDECore.proxy.sendToPlayers(psu.getPacket(), world, posX, posY, posZ, 32);
                }
            }
            catch(Exception e)
            {
                if(world.getPlayerEntityByName("StormTiberius") != null)
                {
                    world.getPlayerEntityByName("StormTiberius").sendChatToPlayer("TILX");
                }
            }
        }
    }
    
    @Override
    public float getVolume()
    {
        return 1.0F;
    }
    
    @Override
    public float getPitch()
    {
        return 1.0F;
    }
    
    @Override
    public String getResourceName()
    {
        return "GeneratorLoop.ogg";
    }
    
    @Override
    public URL getResourceUrl()
    {
        return IEnergySource.class.getResource((new StringBuilder()).append("/ic2/sounds/Generators/").append(getResourceName()).toString());
    }
}
