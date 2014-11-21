
import java.io.IOException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ujwals
 */
public class HuffDecoder implements IHuffDecoder
{
    private ITreeMaker _tree;

    public void initialize(ITreeMaker treeMaker)
    {
        _tree = treeMaker;
    }

    public void doDecode(BitInputStream input, BitOutputStream output) throws IOException
    {
        boolean fileHasNotEnded = true;
        TreeNode temp = _tree.makeRoot();

        while(fileHasNotEnded)
        {
            

            if(bit == 1)
            {
                temp = temp.myRight;
            }
            else if(bit == 0)
            {
                temp = temp.myLeft;
            }

            if(temp.myLeft == null && temp.myRight == null)
            {
                if(temp.myValue == IHuffConstants.PSEUDO_EOF)
                {
                    fileHasNotEnded = false;
                }
                else
                {
                    output.write(temp.myValue);
                    temp = _tree.makeRoot();
                }
            }
        }
    }

}
