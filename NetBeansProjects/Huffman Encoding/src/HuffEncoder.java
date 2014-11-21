
import java.util.HashMap;
import java.util.Map;



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jayp
 */
public class HuffEncoder implements IHuffEncoder
{
    private  Map<Integer,Code> _table=new HashMap<Integer, Code>();

    public void makeTable(ITreeMaker treeMaker)
    {
        for(int i=0;i<IHuffConstants.ALPH_SIZE+1;i++)
        {           
            TreeNode node =treeMaker.makeRoot();
            inOrderHelp(node,"");
        }

    }
    private void inOrderHelp(TreeNode current,String code)
    {
        if(current ==null)
        {
            return;
        }
        inOrderHelp(current.myLeft,code+"0");
        visit(current,code);
        inOrderHelp(current.myRight,code+"1");
        return;
    }
    private void visit(TreeNode current,String code)
    {
        if(current.myValue!=-1)
        {
            if(code.length())
//            _table.put(current.myValue,);
        }

    }

    public String getCode(int i)
    {
        return _table.get(i);
    }

}
