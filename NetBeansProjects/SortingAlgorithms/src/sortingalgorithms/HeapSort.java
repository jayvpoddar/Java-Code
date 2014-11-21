
package sortingalgorithms;

/**
 *
 * @author jayp
 */
public class HeapSort implements IArraySorter
{
    private int[] _heap;
    public String getName()
    {
       return "Heap Sort";
    }
    private int getParent(int index)
    {
        return ((index+1)/2)-1;
    }
    private int getLeftChild(int index)
    {
        return ((index+1)*2)-1;
    }
    private int getRightChild(int index)
    {
        return ((index+1)*2);
    }
    private boolean hasChildInRange(int index,int n)
    {
        return (getLeftChild(index)<=n);
    }
    public void sort(int[] array)
    {
        _heap=array;
        for (int i = 0; i < array.length; i++)
        {
            siftUp(i);
        }
        for(int i=_heap.length-1;i>0;i--)
        {
            swap(0,i);
            siftdown(i-1);
        }
    }
    private void siftUp(int n)
    {
        int index=n;        
        while(index>0)
        {
            int parent=getParent(index);
            if(_heap[parent]<_heap[index])
            {
                swap(parent,index);
                index=parent;
            }
            else
            {
                break;
            }
        }
    }
    private void siftdown(int n)
    {
        int index=0;
        while(hasChildInRange(index, n))
        {
            int child=getGreaterChild(index,n);
            if(_heap[index]<_heap[child])
            {
                swap(index,child);
                index=child;
            }
            else
            {
                break;
            }
        }
    }
    private int getGreaterChild(int index,int n)
    {
        if(getRightChild(index)>n)
        {
            return getLeftChild(index);
        }
        if (_heap[getLeftChild(index)]<_heap[getRightChild(index)])
        {
            return getRightChild(index);
        }
        else
        {
            return  getLeftChild(index);
        }
    }

    private void swap(int i, int i2) {
        int temp=_heap[i];
        _heap[i]=_heap[i2];
        _heap[i2]=temp;
    }
}
