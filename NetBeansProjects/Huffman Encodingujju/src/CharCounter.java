
import java.io.IOException;
import java.io.InputStream;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author niting
 */
public class CharCounter implements ICharCounter{
    
    private int[] frequency = new int[IHuffConstants.ALPH_SIZE+1];

    public int getCount(int ch)
    {
        return frequency[ch];
    }

    public int countAll(InputStream stream) throws IOException
    {
        int noOfBytes = 0;

        int val = 0;
        while((val = stream.read()) != -1)
        {
            frequency[val]++;
            noOfBytes++;
        }

        frequency[frequency.length- 1] = 1;
        return noOfBytes;
    }

    public void add(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void set(int i, int value)
    {
        frequency[i] = value;
    }

    public void clear() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
