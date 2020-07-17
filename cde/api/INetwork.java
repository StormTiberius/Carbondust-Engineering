/**
 *
 * @author StormTiberius
 */

package cde.api;

import net.minecraft.entity.player.EntityPlayer;

public interface INetwork
{
    public abstract void onNetworkPacketArrival(Object packet, EntityPlayer player);
}
