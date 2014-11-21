package interfaces;
/**
 *
 * @author jayp
 */
public interface IList
{
    /**
     * takes the value for a new node and adds it in the end of the list
     * @param value
     */
    void put (int value);
    /**
     * take the index of a node and returns the value of the element at the node
     * @param index
     * @return ind
     */
    int get (int index);
    /**
     * takes a value and a index and inserts the new element at the given index
     * @param index
     * @param value
     */
    void insertAt (int index, int value);
    /**
     *takes the index and deletes the node at its place
     * @param index
     */
    void removeAt (int index);
    /**
     *clears the whole list
     */
    void clear ();
    /**
     *converts the list into an array
     * @return array form of the list
     */
    int[] toArray ();
    /**
     * @return the no of elements in the list
     */
    int size();
}
