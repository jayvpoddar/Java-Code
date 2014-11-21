

package stack;

import linkedList.*;
/**
 *
 * @author jayp
 */

import interfaces.IStack;

public class Stack implements IStack
{
    private DoubleLinkedList _data;

    public Stack()
    {
        _data=new DoubleLinkedList();
    }
    public void push(int value)
    {
        _data.put(value);
    }
    public int pop()
    {
        assert !isEmpty():"The Stack is empty";
        int value=_data.get(_data.size()-1);
        _data.removeAt(_data.size()-1);
        return value;
    }
    public int peek(int index)
    {
        assert !isEmpty():"The Stack is empty";
        return _data.get(index);
    }
    public boolean isEmpty()
    {
        if(_data.size()==0)
        {
            return true;
        }
        return false;
    }
    public boolean isFull()
    {
        return false;
    }

    public int size()
    {
        return _data.size();
    }
}