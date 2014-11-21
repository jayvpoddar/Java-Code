
package maps;

import interfaces.IMap;
import interfaces.IList;
import linkedList.*;

/**
 * @author jayp
 */
public class HashMap implements IMap
{
    
    private class Node
    {
        Node _next;
        int _key;
        String _name;

        private Node(int key, String name)
        {
            _key = key;
            _name = name;
        }

    }
    
    private Node[] _roots;
    private int _noOfElements;

    public HashMap(int roughSize)
    {
        _roots = new Node[roughSize];
    }  

    public void put(int key,String name)
    {
        int index=hashFunction(key);
        if(_roots[index]==null)
        {
            _roots[index]=new Node(key,name);
        }
        else
        {
            Node temp = _roots[index];
            while(temp._next!=null)
            {
                if(temp._key==key)
                {
                    temp._name=name;
                    return;
                }
                temp=temp._next;
            }
            if(temp._key==key)
            {
                temp._name=name;
                return;
            }
            temp._next=new Node(key, name);
        }
        _noOfElements++;
    }

    private int hashFunction(int key)
    {
        return (key%_roots.length);
    }


    public String get(int key)
    {
        assert !isEmpty():"The array is empty";

        int index=key%_roots.length;

        Node temp=_roots[index];

        for(;temp!=null;)
        {
            if(temp._key==key)
            {
                return temp._name;
            }
            temp=temp._next;
        }
        return null;
    }

    public int size()
    {
        return _noOfElements;
    }

    public boolean isEmpty()
    {
        return(_noOfElements==0);
    }

    public boolean remove(int key)
    {
        int index=key%_roots.length;

        assert !isEmpty():"The Array is Empty";

        assert _roots[index]==null:"The key does not exist";

        Node temp=_roots[index];

        for(;temp._next!=null;)
        {
            if(temp._next._key==key)
            {
                temp._next=temp._next._next;
                return true;
            }
            temp=temp._next;
        }
        return false;
    }

    public IList keySet()
    {
        IList keys=new DoubleLinkedList();
        for(int i=0;i<_roots.length;i++)
        {
            if(_roots[i]!=null)
            {
                Node temp=_roots[i];
                for(;temp!=null;)
                {
                    keys.put(temp._key);
                    temp=temp._next;
                }
            }
        }
        return keys;
    }

    public String[] valueSet()
    {
        String[] keys=new String[_noOfElements];
        int j=0;
        for(int i=0;i<_roots.length;i++)
        {
            if(_roots[i]!=null)
            {
                Node temp=_roots[i];
                for(;temp!=null;j++)
                {
                    keys[j]=temp._name;
                    temp=temp._next;
                }
            }
        }
        return keys;
    }

}
