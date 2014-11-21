package binaryTrees;
/**
 *
 * @author jayp
 */

public class TwoFourTrees
{
	 Node _root;
    int _size;

    private class Node
    {
        Node[] _children = new Node[4];
        Node _parentNode;
        int[] _values=new int[3];

        public Node(Node parentNode)
        {
            _parentNode = parentNode;
        }

        public boolean isFull()
        {
            return (_children[0]!=null && _children[1]!=null && _children[2]!=null);
        }

        public boolean isEmpty()
        {
            return (_children[0] == null);
        }

        public void putValue (int value)
        {
            if (!isFull())
            {
                if (isEmpty())
                {
                    _values[0] = value;
                    return;
                }

                putInSorted(value);
            }
            else
            {
                splitNode(value);
            }
        }

        private void splitNode(int value)
        {

//            Node secondNode = new Node(_parentNode);
//
//            secondNode._children[0] = _children[2];
//            _children[2] = null;
//            secondNode.putValue(value);
//
//            Element toBePushedUp = secondNode._children[0];
//            secondNode._children[0] = secondNode._children[1];
//            secondNode._children[1] = null;
//
//            toBePushedUp._leftNode = this;
//            toBePushedUp._rightNode = secondNode;
//
//            if (_parentNode == null)
//            {
//                Node newRootNode = new Node(null);
//                newRootNode._value[0] = toBePushedUp;
//                _root = newRootNode;
//                _parentNode = _root;
//                secondNode._parentNode = _root;
//            }
//            else if (_parentNode.isFull())
//            {
//                _parentNode.splitNode(toBePushedUp._value);
//            }
//            else
//            {
//                int nullIndex = (_parentNode._children[1] == null)? 1:2;
//                _parentNode._children[nullIndex] = toBePushedUp;
//            }
        }

        private void putInSorted(int value)
        {
            int index = (_children[1] == null) ? 1 : 2;
            _values[index] = value;
            for (int i = index; i > 0; i--)
            {
                if (_values[i-1]> _values[i])
                {
                    int temp =_values[index];
                    _values[index] =_values[index-1];
                    _values[index-1] = temp;
                }
            }
        }
    }

    public void put (int value) 
    {
        _size++;
        if (_root == null)
        {
            _root = new Node(null);
            _root.putValue(value);
            return;
        }

        Node temp = _root;
        while(true)
        {
            if(temp._children== null)
            {
                temp.putValue(value);
                break;
            }
            else
            {
                if (value < temp._values[0])
                {
                    temp = temp._children[0];
                }
                else if (temp._children[1] == null)
                {
                    temp = temp._children[0];
                }
                else if (value < temp._values[1])
                {
                    temp = temp._children[1];
                }
                else if (temp._children[2] == null)
                {
                    temp = temp._children[1];
                }
                else if (value < temp._values[2])
                {
                    temp = temp._children[2];
                }
                else
                {
                    temp = temp._children[2]._rightNode;
                }
            }
        }
    }


}
