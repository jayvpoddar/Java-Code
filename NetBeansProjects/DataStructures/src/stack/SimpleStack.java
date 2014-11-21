

package stack;


/**
 *
 * @author jayp
 */
import interfaces.IStack;
public class SimpleStack implements IStack
{
    private int [] _stack = null;
    private int _size = 0;
    private int _numberOfElementsPresent = 0;

    public SimpleStack(int size)
    {
        _size = size;
        _stack = new int[size];
    }

    public int size()
    {
        return _numberOfElementsPresent;
    }

    public void push(int value)
    {
        if(size() == _size)
        {
            System.out.println("stack is filled");
        }
        else
        {
            _stack[_numberOfElementsPresent++] = value;
        }
    }

    public int pop()
    {
        if(size() == 0)
        {
            System.out.println("stack is empty");
            return -9;
        }
        else
        {
            return _stack[--_numberOfElementsPresent];
        }
    }

    public int peek(int index)
    {
        if(index>=0 && index<size())
        {
            return _stack[index];
        }
        else
        {
            System.out.println("no element at that index");
            return -9;
        }
    }

    public boolean isEmpty()
    {
        if(size()==0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isFull()
    {
        if(size() == _size)
        {
            return true;
        }
        else
        {
            return false;
        }
    }   

}




