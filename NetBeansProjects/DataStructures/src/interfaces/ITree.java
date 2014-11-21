
package interfaces;

/**
 *
 * @author jayp
 */
public interface ITree
{
   /**
     * takes a integer value and inserts a node of that value in the tree method
     * does not allow duplication
     * @param value- value for the new node
     */
    public void put(int value);
   /**
     * finds the value in the tree just greater than the given existing tree node value
     * @param value
     * @return value just greater than one given
     */
    public int successor(int value);
    /**
     * finds the value in the tree just smaller than given value
     * @param value
     * @return the value just smaller that given value
     */
    public int predecessor(int value);
    /**
     * finds and returns max value in tree
     * @return max value
     */
    public int max();
    /**
     * finds and returns the least value in tree
     * @return min value
     */
    public int min();
    /**
     * returns the in order traversal of the tree
     * @return a list containing elements in right order
     */
    public IList inOrderTraversal();
    /**
     * returns the size/number of elements present in tree
     * @return _size
     */
    public int size();
    /**
     * finds the height of the tree
     * @return height of tree
     */
    public int height();
    /**
     * takes a input of the value of the node to be deleted
     * @param value-value of the node
     */
    public void delete(int value);
}
