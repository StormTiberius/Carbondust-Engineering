/**
 *
 * @author StormTiberius
 */

package cde.resource;

public class WeightedObject
{
    public int objectId;
    public int objectMeta;
    public int objectWeight;

    public WeightedObject(int objectId, int objectMeta, int objectWeight)
    {
        this.objectId = objectId;
        this.objectMeta = objectMeta;
        this.objectWeight = objectWeight;
    }
}
