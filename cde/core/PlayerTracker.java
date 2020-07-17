/**
 *
 * @author StormTiberius
 */

package cde.core;

import cde.Carbon;
import cde.sound.PacketTileSound;
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
        Carbon.proxy.sendNetworkPacketToPlayer(player, new PacketTileSound().getPacket());
    }
    
    @Override
    public void onPlayerRespawn(EntityPlayer player)
    {
        
    }
}
