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
import net.minecraftforge.common.DimensionManager;

public class CommandTPX extends CommandBase
{
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
                int destination = TerreneCore.getDimensionId(TerreneCore.TROPICS);
                
                if(player.dimension == destination)
                {
                    destination = TerreneCore.getDimensionId(TerreneCore.EMBER);
                }
                
                if(DimensionManager.getWorld(destination) != null)
                {
                    TeleporterTropics.setDepartureCoords(player);
                    player.mcServer.getConfigurationManager().transferPlayerToDimension(player, destination, new TeleporterTropics((WorldServer)player.worldObj, false));
                }
            }
        }
    }
}
