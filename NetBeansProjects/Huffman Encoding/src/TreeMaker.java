
import java.util.PriorityQueue;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jayp
 */
public class TreeMaker implements  ITreeMaker
{
    private TreeNode _root;

    public TreeMaker(ICharCounter cc)
    {
        buildTree(cc);
    }

    public TreeNode makeRoot()
    {
       return  _root;
    }

    private void buildTree(ICharCounter cc) {
        PriorityQueue<TreeNode> queue = new PriorityQueue<TreeNode>();
        for (int i = 0; i <IHuffConstants.ALPH_SIZE+1; i++)
        {
            if(cc.getCount(i)!=0)
            {
                TreeNode node = new TreeNode(i, cc.getCount(i));
                queue.add(node);
            }
        }         
        while(queue.size()>1)
        {
           TreeNode node = new TreeNode();
            node.myLeft=queue.remove();
            node.myRight=queue.remove();
            node.myValue=-1;
            node.myWeight=node.myLeft.myWeight+node.myRight.myWeight;
            queue.add(node);
        }
        _root=queue.remove();
    }

}
