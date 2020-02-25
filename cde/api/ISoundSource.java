/**
 *
 * @author StormTiberius
 */

package cde.api;

import java.net.URL;

public interface ISoundSource
{
    public abstract boolean isPriority();
    public abstract boolean isLooping();
    public abstract boolean isPlaying();
    public abstract void setPlaying(boolean flag);
    public abstract int getAttModel();
    public abstract float getDistOrRoll();
    public abstract float getOriginX();
    public abstract float getOriginY();
    public abstract float getOriginZ();
    public abstract float getVolume();
    public abstract float getPitch();
    public abstract String getSourceName();
    public abstract String getResourceName();
    public abstract URL getResourceUrl();
}
