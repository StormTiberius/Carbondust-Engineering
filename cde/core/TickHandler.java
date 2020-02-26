/**
 *
 * @author StormTiberius
 */

package cde.core;

import cde.core.sound.SoundWave;
import java.util.EnumSet;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class TickHandler implements ITickHandler
{
    private final long timeUntilUpdate = 1000;
    private long previousUpdateTime;
    
    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData)
    {
        if(System.currentTimeMillis() - previousUpdateTime > timeUntilUpdate)
        {
            SoundWave.updateSources();
            previousUpdateTime = System.currentTimeMillis();
        }
    }
    
    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData)
    {
        
    }
    
    @Override
    public EnumSet<TickType> ticks()
    {
        return EnumSet.of(TickType.SERVER);
    }
    
    @Override
    public String getLabel()
    {
        return "CDE Core";
    }
}
