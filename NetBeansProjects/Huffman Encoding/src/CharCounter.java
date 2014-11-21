
import java.io.IOException;
import java.io.InputStream;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jayp
 */
public class CharCounter implements ICharCounter
{
    private int[] _charCounts=new int[IHuffConstants.ALPH_SIZE+1];
    int temp;
    
    public int getCount(int ch)
    {
        return _charCounts[ch];
    }

    public int countAll(InputStream stream) throws IOException
    {
        int noOfchunks=0;
        int ch=stream.read();
        while(ch!=-1)
        {
            noOfchunks++;
            _charCounts[ch]++;
            ch=stream.read();
        }
        _charCounts[IHuffConstants.PSEUDO_EOF]=1;
        temp=noOfchunks;
        return temp;
    }


    public void add(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void set(int i, int value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void clear() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
