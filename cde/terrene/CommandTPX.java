/**
 *
 * @author StormTiberius
 */

package cde.terrene;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

public class CommandTPX extends CommandBase
{
    private final int OVERWORLD_ID,TROPICS_ID,EMBER_ID;
    private final boolean TROPICS_ENABLED,EMBER_ENABLED;
    
    public CommandTPX(int tropicsId, int emberId, boolean tropicsEnabled, boolean emberEnabled)
    {
        OVERWORLD_ID = 0;
        TROPICS_ID = tropicsId;
        EMBER_ID = emberId;
        TROPICS_ENABLED = tropicsEnabled;
        EMBER_ENABLED = emberEnabled;
    }
    
    @Override
    public boolean canCommandSenderUseCommand(ICommandSender par1ICommandSender)
    {
        return MinecraftServer.getServer().isSinglePlayer() || super.canCommandSenderUseCommand(par1ICommandSender);
    }
    
    @Override
    public String getCommandName()
    {
        return "tpx";
    }
    
    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }
    
    @Override
    public void processCommand(ICommandSender ics, String[] strings)
    {
        if(ics instanceof EntityPlayerMP)
        {
            EntityPlayerMP player = (EntityPlayerMP)ics;
            
            if(!player.worldObj.isRemote)
            {
                int destination = OVERWORLD_ID;
                
                String origin = player.worldObj.provider.getDimensionName();
                
                if(origin.contentEquals("Tropics") && EMBER_ENABLED)
                {
                    destination = EMBER_ID;
                }
                else if(origin.contentEquals("Ember") && TROPICS_ENABLED)
                {
                    destination = TROPICS_ID;
                }
                else if(origin.contentEquals("Overworld"))
                {
                    if(TROPICS_ENABLED)
                    {
                        destination = TROPICS_ID;
                    }
                    else if(EMBER_ENABLED)
                    {
                        destination = EMBER_ID;
                    }
                }
                
                if(player.dimension != destination && DimensionManager.getWorld(destination) != null)
                {
                    TeleporterTerrene.setDepartureCoords(player);
                    player.mcServer.getConfigurationManager().transferPlayerToDimension(player, destination, new TeleporterTerrene((WorldServer)player.worldObj, false));
                }
            }
        }
    }
}
