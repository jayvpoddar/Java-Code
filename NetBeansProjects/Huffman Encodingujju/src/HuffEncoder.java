
import java.util.HashMap;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author niting
 */
public class HuffEncoder implements IHuffEncoder
{
    HashMap<Integer, String> _codings = new HashMap<Integer, String>();

    public void makeTable(ITreeMaker treeMaker) 
    {
        traversal(treeMaker.makeRoot(), "");
    }

    private void traversal(TreeNode current, String code)
    {
        if (current.myLeft == null && current.myRight == null)
        {
            _codings.put(current.myValue, code);
            return;
        }

        traversal(current.myLeft, code+"0");
        traversal(current.myRight, code+"1");
    }

    public String getCode(int i)
    {
        return _codings.get(i);
    }

}
