/**
 *
 * @author StormTiberius
 */

package cde.laputa.gog;

import java.util.EnumSet;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

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
        if(type.contains(TickType.RENDER))
        {
            partialTicks = (Float)tickData[0];
        }
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData)
    {
        if(type.contains(TickType.RENDER))
        {
            calcDelta();
        }
        
        if(type.contains(TickType.CLIENT))
        {
            GuiScreen gui = Minecraft.getMinecraft().currentScreen;
	
            if(gui == null || !gui.doesGuiPauseGame())
            {
                ticksInGame++;
                partialTicks = 0;
            }

            calcDelta();
        }
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
