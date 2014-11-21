
package sortingalgorithms;

/**
 *
 * @author jayp
 */
public interface IArraySorter
{
    /**
     * Returns the name of the sorting algorithm
     * @return
     */
    public String getName();
    /**
     * Sorts the given array in ascending order
     * @param array
     */
    public void sort(int[] array);
}
