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
    public static float delta = 0;
    public static float total = 0;
    
    private void calcDelta()
    {
        float oldTotal = total;
        total = ticksInGame + partialTicks;
        delta = total - oldTotal;
    }
    
    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData)
    {
        if(event.phase == Phase.START)
        {
            partialTicks = event.renderTickTime;
        }
        else
        {
            calcDelta();
        }
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData)
    {

    }

    @Override
    public EnumSet<TickType> ticks()
    {
        return EnumSet.of(TickType.CLIENT, TickType.RENDER);
    }

    @Override
    public String getLabel()
    {
        return "CDE Laputa";
    }
}
