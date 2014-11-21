
 /**
 *
 * @author jayp
 */
public class Vertex implements Comparable<Vertex>
{
    char _value;
    Vertex _parent;
    boolean _isWall=false;
    int _bestEstimatedDistance;
    int _x;
    int _y;
    @Override
    public String toString()
    {
        return ""+_value;
    }

    public Vertex(char value,int x,int y)
    {
        _value = value;
        _bestEstimatedDistance=Integer.MAX_VALUE;
        _x=x;
        _y=y;
    }
    public int compareTo(Vertex o)
    {
        return _bestEstimatedDistance-o._bestEstimatedDistance;
    }
}
