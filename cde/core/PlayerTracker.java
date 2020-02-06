/**
 *
 * @author StormTiberius
 */

package cde.core;

import cde.CDECore;
import cde.core.sound.PacketTileSound;
import cpw.mods.fml.common.IPlayerTracker;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerTracker implements IPlayerTracker
{
    @Override
    public void onPlayerLogin(EntityPlayer player)
    {
        
    }
    
    @Override
    public void onPlayerLogout(EntityPlayer player)
    {
        
    }
    
    @Override
    public void onPlayerChangedDimension(EntityPlayer player)
    {
        CDECore.proxy.sendToPlayer(player, new PacketTileSound(null, false, false, true).getPacket());
    }
    
    @Override
    public void onPlayerRespawn(EntityPlayer player)
    {
        
    }
}
