
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jaypoddar
 */
public class SortCompare
{

    public static void main(String[] args)
    {
        System.out.println("Recursive Sort\n");

        for (int i = 1; i < 10; i++)
        {
            System.out.println("for:" + (i));

            int[] array = getRandom(3000 * i);
            double begin = System.currentTimeMillis();
            mySort(array, 0);
            double end = System.currentTimeMillis();
            System.out.println("Time Taken:" + (end - begin) + "\n");

        }

        System.out.println("Merge Sort\n");

        for (int i = 1; i < 10; i++)
        {
            System.out.println("for:" + 3000 * i);

            int[] array = getRandom(3000 * i);
            long begin = System.currentTimeMillis();
            mergeSort(array);
            long end = System.currentTimeMillis();
            System.out.println("Time Taken:" + (end - begin) + "\n");


        }

    }

    

    private static void mySort(int[] toSort, int startIndex)
    {


        if (startIndex == toSort.length - 1)
        {
            return;
        }
        mySort(toSort, startIndex + 1);
        int ind = getIndex(toSort, startIndex);
        insertValue(toSort, ind, startIndex);
        //printArray(toSort);

    }

    private static void insertValue(int[] arr, int i, int start)
    {
        int temp = arr[start];
        for (int index = start + 1; index < (i + 1); index++)
        {
            arr[index - 1] = arr[index];
        }
        arr[i] = temp;
    }

    private static int getIndex(int[] arr, int start)
    {
        int i;
        for (i = arr.length - 1; i > start; i--)
        {
            if (arr[i] < arr[start])
            {
                break;
            }
        }

        return i;
    }

    
    static int[] myTemp;

    public static void mergeSort(int[] arr)
    {
        myTemp = new int[arr.length];
        sortHelper(arr, 0, arr.length - 1);

    }

    private static void sortHelper(int[] arr, int low, int high)
    {
        if (low >= high)
        {
            return;
        }
        int mid = (high + low) / 2;
        sortHelper(arr, low, mid);
        sortHelper(arr, mid + 1, high);
        merge(arr, low, mid, high);
    }

    private static void merge(int[] arr, int low, int middle, int high)
    {
        int d = low;
        int i = low;
        int j = middle + 1;
        while (i <= middle && j <= high)
        {
            if (arr[i] < arr[j])
            {
                myTemp[d++] = arr[i++];
            }
            else
            {
                myTemp[d++] = arr[j++];
            }
        }
        while (i <= middle)
        {
            myTemp[d++] = arr[i++];
        }
        while (j <= high)
        {
            myTemp[d++] = arr[j++];
        }
        for (int k = low; k < high + 1; k++)
        {
            arr[k] = myTemp[k];

        }
    }
    
    public static int[] getRandom(int size)
    {
        int[] arr = new int[size];
        Random rand = new Random();
        for (int i = 0; i < arr.length; ++i)
        {
            arr[i] = (rand.nextInt(200) - 100);
        }
        return arr;
    }
    
    private static void printArray(int[] array)
    {
        System.out.print("[ ");
        for (int i = 0; i < array.length; i++)
        {
            System.out.print(array[i] + " ");

        }
        System.out.println("]\n");
    }
}
