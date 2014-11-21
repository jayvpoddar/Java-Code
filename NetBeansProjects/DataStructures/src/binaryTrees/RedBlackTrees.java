/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package binaryTrees;

/**
 *
 * @author jayp
 */
public class RedBlackTrees
{

    private Node _root;
    private int _size;

    private class Node
    {
        int _value;
        boolean _colour;//true is black;false is red;
        Node _left,_right,_parent;

        public Node(int value, boolean colour, Node parent)
        {
            _value = value;
            _colour = colour;
            _parent = parent;
        }
        public boolean isLeft()
        {
            return(_parent._left==this);
        }

        public boolean isRight()
        {
            return(_parent._right==this);
        }
    }

    public void put(int value)
    {
        int abs = Math.abs(2);
        if(_root==null)
        {
            _root=new Node(value,true,null);
        }
        else
        {
            Node current=_root;
            while(true)
            {
                if(value==current._value)
                {
                    break;
                }
                else if(value< current._value)
                {
                    if(current._left== null)
                    {   current=current._left;
                        current=new Node(value,false,current);
                        break;
                    }
                    current=current._left;
                }
                else
                {
                    if(current._right== null)
                    {
                        current=current._right;
                        current=new Node(value,false,current);
                        break;
                    }
                    current=current._right;
                }
            }
            balance(current);
        }
        _size++;
    }
   private void balance(Node current)
    {
//       int blackCount= blackCount(current);
        if(current._parent._colour==false)
        {
            if(current.isRight())
            {
                if(current._parent.isRight())
                {
                    if(current._parent._parent._left._colour==true)
                    {
                        current._parent._colour=true;
                        current._parent._parent._colour=false;
                        rotateLeft(current._parent);
                    }
                }
            }
            else
            {
               if(current._parent.isLeft())
                {                    
                    if(current._parent._parent._right._colour==true)
                    {
                        current._parent._colour=true;
                        current._parent._parent._colour=false;
                        rotateRight(current._parent);                        
                    }
                } 
            }
        }
   }
//    gets the parent of the node as the left child of the node
    private void rotateLeft(Node toRotateAt)
    {
        if(toRotateAt._parent._parent!=null)
        {
            if(toRotateAt._parent.isLeft())
            {
                toRotateAt._parent._parent._left=toRotateAt;
            }
            else
            {
                toRotateAt._parent._parent._right=toRotateAt;
            }
        }
        else
        {
            _root=toRotateAt;
        }
        if(toRotateAt._left!=null)
        {
            toRotateAt._left._parent=toRotateAt._parent;
        }
        toRotateAt._parent._right=toRotateAt._left;
        toRotateAt._left=toRotateAt._parent;
        toRotateAt._parent=toRotateAt._parent._parent;
        toRotateAt._left._parent=toRotateAt;
    }
//    gets the parent of the node as the left child of the node
    private void rotateRight(Node toRotateAt)
    {

        if(toRotateAt._parent._parent!=null)
        {
            if(toRotateAt._parent.isLeft())
            {
                toRotateAt._parent._parent._left=toRotateAt;
            }

            else
            {
                toRotateAt._parent._parent._right=toRotateAt;
            }
        }
        else
        {
            _root=toRotateAt;
        }
        if(toRotateAt._right!=null)
        {
            toRotateAt._right._parent=toRotateAt._parent;
        }
        toRotateAt._parent._left=toRotateAt._right;
        toRotateAt._right=toRotateAt._parent;
        toRotateAt._parent=toRotateAt._parent._parent;
        toRotateAt._right._parent=toRotateAt;
    }

//    private int blackCount(Node current)
//    {
//        return -1;
//    }

}
