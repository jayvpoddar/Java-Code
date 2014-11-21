
package sortingalgorithms;

import java.util.Random;

/**
 *
 * @author jayp
 */
public class Main 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    { 
       IArraySorter sort=new  InsertionSort();
        for (int i = 0; i < 6; i++) {
            testHarness(sort,i);

        }
        sort=new  MergeSort();
        for (int i = 0; i < 6; i++) {
            testHarness(sort,i);

        }
    }
    public static void testHarness(IArraySorter arraySorter,int size)
    {        

           System.out.println("for:"+Math.pow(10, size));
           
            int[] array= generateRandomArray((int)Math.pow(10, size));
            long begin=System.currentTimeMillis();
            arraySorter.sort(array);
 //           System.out.println(verifyArray(array));
           long end=System.currentTimeMillis();
           System.out.println("Time Taken:"+(end-begin));


    }
    public static Boolean verifyArray(int []array)
    {
        Boolean isSorted = true;
        for(int i=0; i<array.length - 1; ++i)
        {
            if(array[i+1] < array[i])
            {
                isSorted = false;
                break;
            }
        }
        return isSorted;
    }

    public static int [] generateRandomArray(int size)
    {
        int []array = new int[size];
        Random rand = new Random();
        for(int i=0; i<array.length; ++i)
        {
            array[i] = rand.nextInt(100);
        }
        return array;
    }


    
}
