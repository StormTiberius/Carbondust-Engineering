/**
 *
 * @author StormTiberius
 */

package cde.tropics.portal;

import cde.TropicsCore;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

public class CommandTPD extends CommandBase
{
    @Override
    public boolean canCommandSenderUseCommand(ICommandSender par1ICommandSender)
    {
        return MinecraftServer.getServer().isSinglePlayer() || super.canCommandSenderUseCommand(par1ICommandSender);
    }
    
    @Override
    public String getCommandName()
    {
        return "tpd";
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
            
            int destination = 0;
            
            if(player.worldObj.provider.getDimensionName().contentEquals("Tropics"))
            {
                destination = TropicsCore.getEmberDimensionId();
            }
            else if(player.worldObj.provider.getDimensionName().contentEquals("Ember"))
            {
                destination = TropicsCore.getDimensionId();
            }
            
            if(player.dimension != destination)
            {
                player.mcServer.getConfigurationManager().transferPlayerToDimension(player, destination, new TeleporterTropics((WorldServer)player.worldObj));
            }
        }
    }
}
