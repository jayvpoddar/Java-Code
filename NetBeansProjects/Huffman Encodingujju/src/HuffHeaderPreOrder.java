
import java.io.IOException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ujwals
 */
public class HuffHeaderPreOrder implements IHuffHeader
{
    private ICharCounter _cc;

    public HuffHeaderPreOrder(ICharCounter cc)
    {
        _cc = cc;
    }

    public HuffHeaderPreOrder()
    {

    }

    public int headerSize()
    {
        return (IHuffConstants.BITS_PER_INT * (IHuffConstants.ALPH_SIZE+2));
    }

    public void writeHeader(BitOutputStream out)
    {
        //writes magic number
        out.write(IHuffConstants.BITS_PER_INT,IHuffConstants.MAGIC_NUMBER);

        //writes huffman tree

    }

    private void traversal(TreeNode current, String value)
    {
        if (current.myLeft == null && current.myRight == null)
        {
            return;
        }

        if(current.myValue == 0)
        {
            value += "0";
        }
        else
        {
            
            value += "1";
        }

        traversal(current.myLeft, value);
        traversal(current.myRight, value);
    }

    public ITreeMaker readHeader(BitInputStream in) throws IOException
    {
        _cc = new CharCounter();

        //read magic number
        if (in.read(IHuffConstants.BITS_PER_INT) != IHuffConstants.MAGIC_NUMBER)
        {
            throw new IOException("error not the right type");
        }

        //read and assign frequencies
        for (int i = 0; i < IHuffConstants.ALPH_SIZE+1; i++)
        {
            _cc.set(i, in.read(IHuffConstants.BITS_PER_INT));
        }

        //constructs the tree from the charcounts and returns it

        return new TreeMaker(_cc);
    }
}
