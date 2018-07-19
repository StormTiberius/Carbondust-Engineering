/**
 *
 * @author StormTiberius
 */

package cde.laputa.gog;

import java.util.EnumSet;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ClientTickHandler implements ITickHandler
{   
    public static int ticksInGame = 0;
    public static float partialTicks = 0;
    
    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData)
    {
        
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData)
    {

    }

    @Override
    public EnumSet<TickType> ticks()
    {
        return EnumSet.of(TickType.CLIENT);
    }

    @Override
    public String getLabel()
    {
        return "CDE Laputa";
    }
}
