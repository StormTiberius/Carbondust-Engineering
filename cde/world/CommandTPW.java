package cde.world;

import cde.WorldCore;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.Teleporter;

public class CommandTPW extends CommandBase
{   
    @Override
    public boolean canCommandSenderUseCommand(ICommandSender par1ICommandSender)
    {
        return par1ICommandSender instanceof EntityPlayerMP;
    }

    @Override
    public String getCommandName()
    {
        return "tpw";
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 0;
    }

    @Override
    public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
    {
        if(par1ICommandSender instanceof EntityPlayerMP)
        {
            EntityPlayerMP player = (EntityPlayerMP)par1ICommandSender;
            
            if(player.worldObj.provider.dimensionId != WorldCore.getTropicsDimensionId())
            {
                travelToDimension(WorldCore.getTropicsDimensionId(), player);
            }
            else
            {
                travelToDimension(WorldCore.getEmberDimensionId(), player);
            }
        }
    }
    
    private void travelToDimension(int dimension, EntityPlayerMP player)
    {
        Teleporter tp = new WorldTeleporter(player.getServerForPlayer());
        
        player.mcServer.getConfigurationManager().transferPlayerToDimension(player, dimension, tp);
    }
}
