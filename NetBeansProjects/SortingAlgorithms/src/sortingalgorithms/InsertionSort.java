
package sortingalgorithms;

/**
 *
 * @author jayp
 */
public class InsertionSort implements IArraySorter
{

    public String getName()
    {
       return "Insertion Sort";
    }

    public void sort(int[] array)
    {
        for (int i = 0; i < array.length; i++) 
        {
            
            shiftElement(array, getInsertIndex(array, i), i);
            
        }
    }
    private void shiftElement(int[] array,int i,int last)
    {
        int temp=array[last];
        for (int index=last-1;index >(i-1); index--)
        {
            array[index+1]=array[index];
        }
        array[i]=temp;
    }
   private int getInsertIndex(int [] array,int last)
   {
       int i;
       for (i = 0; i < last; i++)
       {
           if(array[i]>array[last])
           {
               break;
           }
       }
       return i;
   }


}
