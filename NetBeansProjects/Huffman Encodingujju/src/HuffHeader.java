
import java.io.IOException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author niting
 */
public class HuffHeader implements IHuffHeader
{
    private ICharCounter _cc;

    public HuffHeader(ICharCounter cc)
    {
        _cc = cc;
    }

    public HuffHeader()
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

        //writes charcounts
        for(int i = 0; i < IHuffConstants.ALPH_SIZE+1; i++)
        {
            out.write(IHuffConstants.BITS_PER_INT, _cc.getCount(i));
        }
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
