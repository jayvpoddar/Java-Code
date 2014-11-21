
import java.util.PriorityQueue;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author niting
 */
public class TreeMaker implements ITreeMaker
{
    private TreeNode _root;

    public TreeMaker(ICharCounter cc)
    {
        PriorityQueue<TreeNode> q = new PriorityQueue<TreeNode>();

        for (int i = 0; i < IHuffConstants.ALPH_SIZE+1; i++)
        {
            if (cc.getCount(i) != 0)
            {
                q.add(new TreeNode(i, cc.getCount(i)));
            }
        }

        while(q.size() > 1)
        {
            TreeNode one = q.remove();
            TreeNode two = q.remove();

            int weight = one.myWeight + two.myWeight;

            q.add(new TreeNode(-1, weight, one, two));
        }

        _root = q.remove();
    }

    public TreeNode makeRoot()
    {
        return _root;
    }

}
