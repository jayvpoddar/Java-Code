package binaryTrees;


import interfaces.*;
import linkedList.*;

/**
 *
 * @author jayp
 */
public class OrderedTree implements ITree
{
    private Node _root;
    private int _size;

    public class Node
    {
        int _value;
        Node _left;
        Node _right;
        Node _parent;

        public Node(int value,Node parent)
        {
            _value = value;
            _parent=parent;
        }

        public boolean isLeft()
        {
            return(_parent._left==this);
        }

        public boolean isRight()
        {
            return(_parent._right==this);
        }

        public void replaceNodes(Node replaceWith)
        {
            _left._parent=replaceWith;
            _right._parent=replaceWith;
            replaceWith._left=_left;            
            replaceWith._right=_right;            
            replaceWith._parent=_parent;
        }
    }
    
    public void put(int value)
    {
        if(_root==null)
        {
            _root=new Node(value,null);
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
                    {
                        current._left=new Node(value,current);
                        break;
                    }
                    current=current._left;
                    
                }
                else
                {
                    if(current._right== null)
                    {
                        current._right=new Node(value,current);
                        break;
                    }
                    current=current._right;
                }
            }
        }        
        _size++;
    }
    
    public void delete(int value)
    {
        deleteHelper(getNode(value));
        _size--;
    }

    private void deleteHelper(Node toDelete)
    {
        // using the the node call the appropriate method to remove all
        //references to given node and appropriately connects the parent of
        //toDelete to tits children
        if(toDelete._left==null&&toDelete._right==null)
        {
            deleteWithNoChild(toDelete);
        }

        else if(toDelete._left==null||toDelete._right==null)
        {
            deleteWithOneChild(toDelete);
        }

        else
        {
            deleteWithTwoChildren(toDelete);
        }

    }
    private void deleteWithNoChild(Node toDelete)
    {
        if(toDelete._parent==null)
        {
            _root=null;
            return;
        }

        if(toDelete.isLeft())
        {
            toDelete._parent._left=null;
        }
        else
        {
            toDelete._parent._right=null;
        }
    }
    private void deleteWithOneChild(Node toDelete)
    {
        Node replaceWith=null;
        if(toDelete._left==null&&toDelete._right!=null)
        {
            replaceWith=toDelete._right;
            toDelete._right._parent=toDelete._parent;
        }
        else
        {
           replaceWith=toDelete._left;
           toDelete._left._parent=toDelete._parent;
        }
        if(toDelete._parent!=null)
        {
            if(toDelete.isLeft())
            {
                toDelete._parent._left=replaceWith;
            }
            else
            {
                toDelete._parent._right=replaceWith;
            }
        }
        else
        {
            _root=replaceWith;
        }
    }

    private void deleteWithTwoChildren(Node toDelete)
    {
        Node replaceWith=getNode(successor(toDelete._value));

        if(replaceWith.isLeft())
        {
            replaceWith._parent._left=replaceWith._right;
        }
        else
        {
            replaceWith._parent._right=replaceWith._right;
        }
        if(toDelete._parent!=null)
        {
            if(toDelete.isLeft())
            {
                toDelete._parent._left=replaceWith;
            }
            else
            {
                toDelete._parent._right=replaceWith;
            }
        }
        else
        {
            _root=replaceWith;
        }
        toDelete._left._parent=replaceWith;
        toDelete. _right._parent=replaceWith;
        replaceWith._left=toDelete._left;
        replaceWith._right=toDelete._right;
        replaceWith._parent=toDelete._parent;
    }
    
    public int successor(int value)
    {
        Node succNode=successorHelp(value);
        if(succNode==null)
        {
            return -1;
        }
        return succNode._value;
    }
    private Node successorHelp(int value)
    {
        Node current=getNode(value);

        assert current !=null:"Value not found";

        if(current._right== null)
        {
            Node parent =current._parent;
            while(parent !=null&& parent ._right==current)
            {
                current= parent ;
                 parent =current._parent;
            }
            return parent;
        }
        return nodeMin(current._right);
    }
    
    public int predecessor(int value)
    {
        Node predeNode=predecessorHelp(value);
        if(predeNode==null)
        {
            return -1;
        }
        return predeNode._value;
    }
    private Node predecessorHelp(int value)
    {
        Node current=getNode(value);

        assert current !=null:"Key not found";
        if(current._left== null)
        {
            Node parent =current._parent;
            while(parent !=null&& parent ._left==current)
            {
                current= parent;
                parent =current._parent;
            }
            return parent;
        }
        return nodeMax(current._left);
    }

    private Node getNode(int value)
    {
        Node current=_root;
        
        while(current!=null)
        {
            if(value==current._value)
            {
                return current;
            }
            else if(value < current._value)
            {
                current=current._left;
            }
            else
            {
                current=current._right;
            }
        }
        return null;
    }
    
    public int max()
    {
        return nodeMax(_root)._value;
    }
    private Node nodeMax(Node current)
    {
        if(current._right==null)
        {
            return current;
        }
        Node max=nodeMax(current._right);
        return max;
    }
    
    public int min()
    {
        return nodeMin(_root)._value;
    }
    private Node nodeMin(Node current)
    {
        if(current._left==null)
        {
            return current;
        }
        Node min=nodeMin(current._left);
        return min;
    }
    
    public IList inOrderTraversal()
    {
        IList list=new DoubleLinkedList();
        inOrderHelp(_root,list);
        return list;
    }

    private void inOrderHelp(Node current,IList list)
    {
        if(current ==null)
        {
            return;
        }
        inOrderHelp(current._left,list);
        visit(list,current);
        inOrderHelp(current._right,list);
        return;
    }
    private void visit(IList list,Node current)
    {
        list.put(current._value);
    }
    
    public int size()
    {
        return _size;
    }

    private int heightFinder(Node current)
    {
        if(current==null)
        {
             return -1;
        }
        int heightLeft=1+heightFinder(current._left);
        int heightRight=1+heightFinder(current._right);
        return (heightRight > heightLeft)?heightRight:heightLeft;
    }
    
    public int height()
    {
        if(_size==0)
        {
            return 0;
        }
        Node current=_root;
        return heightFinder(current);
    }
}
