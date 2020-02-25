/**
 *
 * @author Luna
 */

package cde.core.sound.waves;

public class SoundWaveGeothermal extends SoundWaveGenerator
{
    public SoundWaveGeothermal(int x, int y, int z)
    {
        super(x,y,z);
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
        return "GeothermalLoop.ogg";
    }
}
