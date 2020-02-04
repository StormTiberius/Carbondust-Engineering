/**
 *
 * @author StormTiberius
 */

package cde.terrene;

import cde.TerreneCore;
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
            
            if(!player.worldObj.isRemote)
            {
                String name = player.worldObj.provider.getDimensionName();
                int destination = 0;

                if(name.contentEquals("Tropics"))
                {
                    destination = TerreneCore.getDimensionId(1);
                }
                else if(name.contentEquals("Ember"))
                {
                    destination = TerreneCore.getDimensionId(0);
                }

                if(player.dimension != destination)
                {
                    TeleporterTropics.setDepartureCoords(player);
                    player.mcServer.getConfigurationManager().transferPlayerToDimension(player, destination, new TeleporterTropics((WorldServer)player.worldObj));
                }
            }
        }
    }
}
