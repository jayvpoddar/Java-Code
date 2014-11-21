/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package binaryTrees;

/**
 *
 * @author jayp
 */
public class CompleteTree
{

    private Node _root;    
    private int _size;

    private int height() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private class Node
    {
        int _value;
        Node _left;
        Node _right;

        public Node(int value)
        {
            _value = value;
        }
    }

    public void  put(int value)
    {
        if(_size<=2)
        {
            if(_root==null)
            {
            _root=new Node(value);
            }
            else if(_root._left==null)
            {
                _root._left=new Node(value);
            }
            else if(_root._right==null)
            {
                _root._right=new Node(value);
            }
        }
        else
        {
            int totalCapacity=(int)Math.pow(2,height())-1;

            int actualCapacity=_size;

            Node temp=_root;

            if(actualCapacity >(totalCapacity/2))
            {
                temp=_root._right;

                for(int i=2;i<height()-1;i++)
                {
                    int demo =(int)Math.pow(2, i);

                    if(actualCapacity >((demo-1)*totalCapacity/demo))
                    {
                        temp =temp._right;
                    }
                    else if(actualCapacity >((((demo)/2) +1)*totalCapacity/demo))
                    {
                        temp=temp._left;
                    }
                    temp._right=new Node(value);
                }
            }

            else
            {
                temp=_root._left;

                for(int i=2;i<height()-1;i++)
                {
                    int demo =(int)Math.pow(2, i);
                    if(actualCapacity >(totalCapacity/demo))
                    {
                        temp =temp._left;
                    }
                    else if(actualCapacity >((((demo)/2) -1)*totalCapacity/demo))
                    {
                        temp=temp._right;
                    }

                }
                temp._left=new Node(value);
            }            
        }
        _size++;
    }
    public int[] preOrderTraversal()
    {
        return null;
    }
    public int[] postOrderTraversal()
    {
        return null;
    }
    public int[] inOrderTraversal()
    {
        return null;
    }
    private void postOrderHelper(Node current,int index,int[] array)
    {
        if(current==null)
        {
            return null;
        }
        postOrderHelper(current._left,index+1,array);
        postOrderHelper(current._right,index+1,array);
        visit(current,array,index);
        return array;
    }
    public void visit(Node n,int[] array,int index)
    {
        array[index] =n._value;
    }
    public int height( Node current)
    {

       for(int i=0;;i++)
       {
           if(current._left==null)
           {
               return i;
           }
       }
    }

}

