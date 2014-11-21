package linkedList;

import interfaces.IList;

/**
 *
 * @author jayp
 */

public class LinkedList implements IList
{
    private Node _root;
    private Node _end;
    private int _size;

    public LinkedList()
    {
        _root = _end;
        _size = 0;
    }

    private class Node
    {
        int _value;
        Node _next;


        public Node(int _value)
        {
            this._value = _value;
        }

    }

    private Node getNode(int index)
    {
        Node temp = _root;
        for(int i = 0; i < index; i++)
        {
            temp = temp._next;
        }
        return temp;
    }  
    public void insertAt (int index, int value) 
    {
        Node data = new Node (value);

        if (index > _size)
        {
            //  throw exception
        }
        else if ( _root == null)
        {
            _root = new Node (value);
            _end = _root;
        }
        else if (index == 0)
        {
            Node temp = _root;
            
            _root = data;
            _root._next = temp;
        }
        else if (index == _size)
        {
            _end._next  = data;
            _end = data;
        }        
        else 
        {
            Node temp = _root;

            int i;
            for (i = 0; i < index-1; i++)
            {
                temp = temp._next;
            }

            data._next = temp._next;
            temp._next  = data;
                   
        }
        _size++;
    }

    public void insertSorted(int newVal)
    {
        if (_root==null)
        {
         _root=new Node(newVal);
         return;
        }
        if (newVal<_root._value)
        {
            Node temp = new Node(newVal);
            temp._next=_root;
            _root=temp;
            return;
        }
        insertHelper(newVal,_root);
    }
    private void insertHelper(int val, Node current)
    {
        if(current._next==null)
        {
            current._next=new Node(val);
            return;
        }
        if(current._next._value>=val)
        {
             Node temp = new Node(val);
             temp._next=current._next;
             current._next=temp;
        }
        else
        {
            insertHelper(val, current._next);
        }
    }
    public void removeAt (int index)
    {
        Node tempNode = _root;

        if (index >= _size)
        {
            //throws exception
            return;
        }
        else if (index == 0)
        {
            _root = _root._next;
            if (_size == 1)
            {
                _end = null;
            }
        }        
        else if (index == _size-1)
        {
            for (int i = 0; i < index - 1; i++)
            {
                tempNode = tempNode._next;
            }
            _end = tempNode;
            _end._next = null;
        }
        else
        {
            for (int i = 0; i < index - 1; i++)
            {
                tempNode = tempNode._next;
            }

            tempNode._next = tempNode._next._next;
        }
        _size--;
    }

    public void clear ()
    {
        _root = null;
        _end = null;
        _size = 0;
    }
   
    public void put (int value) 
    {
        insertAt (_size, value);
    }

    public int[] toArray ()
    {
        int[] temp = new int[_size];
        Node tempNode = _root;

        for (int i = 0; i < _size; i++)
        {
            temp[i] = tempNode._value;
            tempNode = tempNode._next;
        }
        return temp;
    }
    
    public int size() {
        return _size;
    }

    public int get (int index)
    {
        if (index > _size)
        {
            //throws exception
        }
        
        Node tempNode = _root;

        for (int i = 0; i <index; i++)
        {
            tempNode = tempNode._next;
        }
        return tempNode._value;
    }

    public int[] reverseValues ()
    {
        return reverseValuesUsingRecursion();
    }

    private int[] reverseValuesUsingIteration ()
    {
        int[] temp = new int[_size];

        Node tempNode = _root;
        for (int i = _size-1; i >= 0; i--)
        {
            temp[i] = tempNode._value;
            tempNode = tempNode._next;
        }
        return temp;
    }

    private int[] returnArray (int[] temp, int i, Node element)
    {
        if (i < 0)
        {
            return temp;
        }

        temp = returnArray (temp, i-1, element._next);
        temp[i] = element._value;
        return temp;
    }

    private int[] reverseValuesUsingRecursion ()
    {
        return returnArray(new int[_size], _size-1, _root);
    }

    private void returnList (Node element)
    {
        if (element._next == null)
        {
            return;
        }

        returnList (element._next);
        element._next._next = element;
    }

    private void reverseUsingRecursion ()
    {
        returnList(_root);
    }
    
    private void reverseUsingIteration ()
    {      
        for (int i = _size-1; i > 0; i--)
        {
            Node temp = getNode(i-1);
            temp._next._next = temp;
        }
        _root._next = null;
    }
    
    public void reverse ()
    {
        reverseUsingIteration();
        
        Node temp = _end;
        _end = _root;
        _root = temp;
    }
}
