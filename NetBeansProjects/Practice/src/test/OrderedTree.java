/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

/**
 *
 * @author jayp
 */
//import exceptions.IndexOutofBoundException;



/**
 *
 * @author jayp
 */
public class OrderedTree 
{
    private Node _root;
    private int _size;

    public class Node
    {
        Word _word;
        Node _left;
        Node _right;
        Node _parent;

        public Node(String value,int frequency,Node parent)
        {
            _word = new Word(value,frequency);
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
    public void putByFrequency(int frequency,String word)
    {        
        if(_root==null)
        {

            _root=new Node(word,frequency,null);
        }
        else
        {
            Node current=_root;
            while(true)
            {
                if(frequency<current._word._frequency)
                {
                    if(current._left== null)
                    {
                        current._left=new Node(word,frequency,current);
                        break;
                    }
                    current=current._left;

                }
                else
                {
                    if(current._right== null)
                    {
                        current._right=new Node(word,frequency,current);
                        break;
                    }
                    current=current._right;
                }
            }
        }
        _size++;
    }
    public void putByName(String word)
    {        
        if(_root==null)
        {
            _root=new Node(word,1,null);
        }
        else
        {
            Node current=_root;
            while(true)
            {
                if(word.equals(current._word._word))
                {
                    current._word._frequency++;
                    return;
                }
                else if(word.compareTo(current._word._word)<0)
                {
                    if(current._left== null)
                    {
                        current._left=new Node(word,1,current);
                        break;
                    }
                    current=current._left;

                }
                else
                {
                    if(current._right== null)
                    {
                        current._right=new Node(word,1,current);
                        break;
                    }
                    current=current._right;
                }
            }
        }
        _size++;
    }
    int index =0;
    public Word[] inOrderTraversal()/*throws IndexOutofBoundException*/
    {
        Word[] list=new Word[_size];
        inOrderHelp(_root,list);
        return list;
    }

    private void inOrderHelp(Node current,Word[] list)
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
    private void visit(Word[] list,Node current)
    {
        list[index++]=current._word;
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
    private Node getNode(String value)
    {
        Node current=_root;

        while(current!=null)
        {
            if(value.equals(current._word._word))
            {
                return current;
            }
            else if(value .compareTo(current._word._word)<0)
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
//    public void delete(String value)
//    {
//        deleteHelper(getNode(value));
//        _size--;
//    }
//
//    private void deleteHelper(Node toDelete)
//    {
//        if(toDelete._left==null&&toDelete._right==null)
//        {
//            deleteWithNoChild(toDelete);
//        }
//
//        else if(toDelete._left==null||toDelete._right==null)
//        {
//            deleteWithOneChild(toDelete);
//        }
//
//        else
//        {
//            deleteWithTwoChildren(toDelete);
//        }
//
//    }
//    private void deleteWithNoChild(Node toDelete)
//    {
//        if(toDelete._parent==null)
//        {
//            _root=null;
//            return;
//        }
//
//        if(toDelete.isLeft())
//        {
//            toDelete._parent._left=null;
//        }
//        else
//        {
//            toDelete._parent._right=null;
//        }
//    }
//    private void deleteWithOneChild(Node toDelete)
//    {
//        Node replaceWith=null;
//        if(toDelete._left==null&&toDelete._right!=null)
//        {
//            replaceWith=toDelete._right;
//            toDelete._right._parent=toDelete._parent;
//        }
//        else
//        {
//           replaceWith=toDelete._left;
//           toDelete._left._parent=toDelete._parent;
//        }
//        if(toDelete._parent!=null)
//        {
//            if(toDelete.isLeft())
//            {
//                toDelete._parent._left=replaceWith;
//            }
//            else
//            {
//                toDelete._parent._right=replaceWith;
//            }
//        }
//        else
//        {
//            _root=replaceWith;
//        }
//    }
//    private void deleteWithTwoChildren(Node toDelete)
//    {
//        Node replaceWith=getNode(successor(toDelete._word._word));
//
//        if(replaceWith.isLeft())
//        {
//            replaceWith._parent._left=replaceWith._right;
//        }
//        else
//        {
//            replaceWith._parent._right=replaceWith._right;
//        }
//        if(toDelete._parent!=null)
//        {
//            if(toDelete.isLeft())
//            {
//                toDelete._parent._left=replaceWith;
//            }
//            else
//            {
//                toDelete._parent._right=replaceWith;
//            }
//        }
//        else
//        {
//            _root=replaceWith;
//        }
//        toDelete._left._parent=replaceWith;
//        toDelete. _right._parent=replaceWith;
//        replaceWith._left=toDelete._left;
//        replaceWith._right=toDelete._right;
//        replaceWith._parent=toDelete._parent;
//    }
//    public String successor(String value)
//    {
//        Node succNode=successorHelp(value);
//        if(succNode==null)
//        {
//            return null;
//        }
//        return succNode._word._word;
//    }
//    private Node successorHelp(String value)
//    {
//        Node current=getNode(value);
//
//        assert current !=null:"Value not found";
//
//        if(current._right== null)
//        {
//            Node parent =current._parent;
//            while(parent !=null&& parent ._right==current)
//            {
//                current= parent ;
//                 parent =current._parent;
//            }
//            return parent;
//        }
//        return nodeMin(current._right);
//    }
//
//    public String predecessor(String value)
//    {
//        Node predeNode=predecessorHelp(value);
//        if(predeNode==null)
//        {
//            return null;
//        }
//        return predeNode._word._word;
//    }
//    private Node predecessorHelp(String value)
//    {
//        Node current=getNode(value);
//
//        assert current !=null:"Key not found";
//        if(current._left== null)
//        {
//            Node parent =current._parent;
//            while(parent !=null&& parent ._left==current)
//            {
//                current= parent;
//                parent =current._parent;
//            }
//            return parent;
//        }
//        return nodeMax(current._left);
//    }

    

//    public String max()
//    {
//        return nodeMax(_root)._word._word;
//    }
//    private Node nodeMax(Node current)
//    {
//        if(current._right==null)
//        {
//            return current;
//        }
//        Node max=nodeMax(current._right);
//        return max;
//    }
//
//    public String min()
//    {
//        return nodeMin(_root)._word._word;
//    }
//    private Node nodeMin(Node current)
//    {
//        if(current._left==null)
//        {
//            return current;
//        }
//        Node min=nodeMin(current._left);
//        return min;
//    }

}

