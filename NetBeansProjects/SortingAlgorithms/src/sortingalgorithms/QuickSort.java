package sortingalgorithms;

/**
 *
 * @author jayp
 */
public class QuickSort implements IArraySorter
{

    public String getName()
    {
        return "Quick Sort";
    }

    public void sort(int[] array)
    {
        sortHelper(array, 0, array.length-1);
    }
    private void sortHelper(int[]array,int lowerBound,int upperBound)
    {
        if(lowerBound<upperBound)
        {
            int mid=partition(array, lowerBound, upperBound);
            sortHelper(array, lowerBound, mid-1);
            sortHelper(array, mid+1, upperBound);
        }
    }
    private int partition(int[] array,int lowerBound,int upperBound)
    {
        int mid=lowerBound;
        for (int i = lowerBound+1; i < upperBound+1; i++)
        {
            if(array[i]<array[lowerBound])
            {
                swap(array,++mid,i);
            }
        }
        swap(array,mid,lowerBound);
        return mid;
    }

    private void swap(int[] array, int i, int i2)
    {
        int temp=array[i];
        array[i]=array[i2];
        array[i2]=temp;
    }

}
