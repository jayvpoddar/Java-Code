
package linkedList;


import interfaces.IList;

/**
 *
 * @author jayp
 */
public class DoubleLinkedList implements IList
{
    private Node _root;
    private Node _end;
    private int _size;

    private class Node
    {
        int _value;
        Node _previous;
        Node _next;

        public Node(int value, Node previous)
        {
            _value = value;
            _previous = previous;
        }

        public void reverse()
        {
            Node temp = _previous;
            _previous = _next;
            _next = temp;
        }
    }

    public DoubleLinkedList()
    {
        _root = _end;
        _size = 0;
    }

    private Node getNode (int index)
    {
        assert index < _size && index >= 0:"INDEX OUT OF BOUNDS";
        Node temp;
        if (index <= _size/2)
        {
            temp = _root;
            for (int i = 0; i < index; i++)
            {
                temp = temp._next;
            }            
        }
        else
        {
            temp = _end;
            for (int i = _size-1; i > index; i--)
            {
                temp = temp._previous;
            }
        }
        return temp;
    }

    public void put (int value)
    {
        if ( _root == null)
        {
            _root = new Node (value, null);
            _end = _root;
        }
        else
        {
            _end._next  = new Node (value, _end);
            _end = _end._next;
        }
        _size++;
    }

    public int get (int index)
    {
        assert _size!=0:"List Empty";
        assert index < _size && index >=0:"INDEX OUT OF BOUNDS";
        return getNode(index)._value;
    }

    public void insertAt (int index, int value) 
    {
        if (index > _size || index < 0)
        {
//           throw exception
        }

        if (index == _size)
        {
            put(value);
            return;
        }
        else if (index == 0)
        {
            Node temp = new Node (value, null);
            temp._next = _root;
            _root._previous = temp;
            _root = temp;
        }
        else
        {
            Node atIndex = getNode(index);
            Node valueNode = new Node(value, atIndex._previous);

            atIndex._previous._next = valueNode;
            
            valueNode._next = atIndex;
            atIndex._previous = valueNode;
        }
        _size++;
    }

    public void removeAt (int index)
    {
        if (index >= _size || index < 0)
        {
            //throws exception
            return;
        }

        if (index == 0)
        {
            if (_size == 1)
            {
                clear();
                return;
            }
            else
            {
                _root = _root._next;
                _root._previous = null;
            }
        }
        else if (index == _size-1)
        {
            _end = _end._previous;
            _end._next = null;
        }
        else
        {
            Node atIndexMinus1 = getNode (index-1);
            Node atIndexPlus1 = getNode (index+1);

            atIndexMinus1._next = atIndexPlus1;
            atIndexPlus1._previous = atIndexMinus1;
        }
        _size--;
    }

    public void clear ()
    {
        _root = null;
        _end = null;
        _size = 0;
    }

    public int[] toArray ()
    {
        int[] temp = new int[_size];

        for (int i = 0; i < _size; i++)
        {
            temp[i] = getNode(i)._value;
        }
        return temp;
    }

    public int size()
    {
        return _size;
    }

    public int[] reverseValues ()
    {
        Node temp = _end;
        int[] reversedArr = new int[_size];

        for (int i = 0; i < _size; i++)
        {
            reversedArr[i] = temp._value;
            temp = temp._previous;
        }
        return reversedArr;
    }

    public void reverse ()
    {
        Node temp = _root;

        for (int i = 0; i < _size; i++)
        {
            temp.reverse();
            temp = temp._previous;
        }
        Node temp2 = _end;
        _end = _root;
        _root = temp2;
    }
}
