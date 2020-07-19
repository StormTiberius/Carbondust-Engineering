/**
 *
 * @author Luna
 */

package cde.api;

import java.net.URL;

public interface ISoundSource
{
    public abstract boolean isPlaying();
    public abstract void setPlaying(boolean flag);
    public abstract boolean isPriority();
    public abstract String getSourceName();
    public abstract URL getResourceUrl();
    public abstract String getResourceName();
    public abstract boolean isLooping();
    public abstract float getOriginX();
    public abstract float getOriginY();
    public abstract float getOriginZ();
    public abstract int getAttModel();
    public abstract float getDistOrRoll();
    public abstract float getVolume();
    public abstract float getPitch();
}
