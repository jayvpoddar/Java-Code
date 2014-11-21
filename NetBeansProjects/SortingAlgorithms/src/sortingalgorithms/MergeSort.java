
package sortingalgorithms;

/**
 *
 * @author jayp
 */
public class MergeSort implements IArraySorter
{
    private int [] _temp;

    public String getName()
    {
        return "Merge Sort";
    }

    public void sort(int[] array)
    {
        _temp=new int[array.length];
        sortHelper(array,0,array.length-1);
    }
    
    private void sortHelper(int[] array, int low, int high)
    {
        if (low>=high)
        {
            return;
        }
        int mid=(high+low)/2;
        sortHelper(array, low, mid);
        sortHelper(array, mid+1,high);
        merge1(array,low,mid,high);
    }
    private void merge1(int[] array, int low, int mid, int high)
    {
        int d=low;
        int i = low;
        int j = mid + 1;
        while(i<=mid&&j<=high)
        {
           if(array[i]<array[j])
           {
               _temp[d++]=array[i++];
           }
           else
           {
                _temp[d++]=array[j++];
           }
        }
        while(i<=mid)
        {
             _temp[d++]=array[i++];
        }
        while(j<=high)
        {
            _temp[d++]=array[j++];
        }
        for (int k = low; k <high+1; k++)
        {
            array[k] =_temp[k];

        }
    }
    private void merge2(int[] array, int low, int mid, int high)
    {        
        int n;
        int k;
        for (k = low,n=0; k <mid+1; k++,n++)
        {
           _temp[n] =array[k];

        }
        int i=0;
        int j=mid+1;
        int d=low;
        while(i<_temp.length&&j<=high)
        {
           if(_temp[i]<array[j])
           {
               array[d++]=_temp[i++];
           }
           else
           {
               array[d++]=array[j++];
           }
        }
        while(i<_temp.length)
        {
            array[d++]=_temp[i++];
        }
    }
    private void merge3(int[] array, int low, int mid, int high)
    {
        int i=low;
        int j=mid+1;
        while(i<=mid&&j<=high)
        {
           if(array[i]>array[j])
           {
               shiftElement(array,j,i);
               mid++;
               j++;
           }
           i++;
            
        }
    }
    private void shiftElement(int[] array, int j, int i)
    {
        int temp=array[j];
        for (int index=j-1;index>(i-1); index--)
        {
            array[index+1]=array[index];
        }
        array[i]=temp;
    }
}
