
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jaypoddar
 */
public class Hw2
{

    public static void main(String[] args)
    {
        
        System.out.println("Insertion Sort\n");

        for (int i = 1; i < 10; i++)
        {
            testInstertion(i);

        }

        System.out.println("Merge Sort\n");

        for (int i = 1; i < 10; i++)
        {
            testMerge(i);

        }
    }

    public static void testInstertion(int i)
    {

        System.out.println("for:" + (i));

        int[] array = generateRandomArray(i);
        double begin = System.currentTimeMillis();
        sort(array, 0);
        double end = System.currentTimeMillis();
        System.out.println("Time Taken:" + (end - begin) + "\n\n\n\n");


    }

    public static void testMerge(int i)
    {

        System.out.println("for:" + 3000 * i);

        int[] array = generateRandomArray(3000 * i);
        long begin = System.currentTimeMillis();
        mergeSort(array);
        long end = System.currentTimeMillis();
        System.out.println("Time Taken:" + (end - begin) + "\n");


    }

    public static int[] generateRandomArray(int size)
    {
        int[] array = new int[size];
        Random rand = new Random();
        for (int i = 0; i < array.length; ++i)
        {
            array[i] = (rand.nextInt(200) - 100);
        }
        return array;
    }

    private static void sort(int[] toSort, int startIndex)
    {


        if (startIndex == toSort.length - 1)
        {
            return;
        }
        sort(toSort, startIndex + 1);
        int index = getInsertIndex(toSort, startIndex);
        shiftElement(toSort, index, startIndex);
        printArray(toSort);

    }

    private static void shiftElement(int[] array, int i, int start)
    {
        int temp = array[start];
        for (int index = start + 1; index < (i + 1); index++)
        {
            array[index - 1] = array[index];
        }
        array[i] = temp;
    }

    private static int getInsertIndex(int[] array, int start)
    {
        int i;
        for (i = array.length - 1; i > start; i--)
        {
            if (array[i] < array[start])
            {
                break;
            }
        }

        return i;
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
    static int[] _temp;

    public static void mergeSort(int[] array)
    {
        _temp = new int[array.length];
        sortHelper(array, 0, array.length - 1);

    }

    private static void sortHelper(int[] array, int low, int high)
    {
        if (low >= high)
        {
            return;
        }
        int mid = (high + low) / 2;
        sortHelper(array, low, mid);
        sortHelper(array, mid + 1, high);
        merge(array, low, mid, high);
    }

    private static void merge(int[] array, int low, int mid, int high)
    {
        int d = low;
        int i = low;
        int j = mid + 1;
        while (i <= mid && j <= high)
        {
            if (array[i] < array[j])
            {
                _temp[d++] = array[i++];
            }
            else
            {
                _temp[d++] = array[j++];
            }
        }
        while (i <= mid)
        {
            _temp[d++] = array[i++];
        }
        while (j <= high)
        {
            _temp[d++] = array[j++];
        }
        for (int k = low; k < high + 1; k++)
        {
            array[k] = _temp[k];

        }
    }
}
