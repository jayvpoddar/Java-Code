
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author skapoor
 */
class HuffModel implements IHuffModel
{
    private ICharCounter _charCounter=new CharCounter() ;
    private HuffViewer _viewer;
    private HuffEncoder _encoder = new HuffEncoder();
    public HuffModel() {
    }

    public void showCodings()
    {
        ArrayList<String> strings=new ArrayList<String>();

        for (int i = 0; i <= IHuffConstants.ALPH_SIZE; i++)
        {
            if(_charCounter.getCount(i)!=0)
            {
                strings.add(_encoder.getCode(i));
            }
        }
        _viewer.update(strings);
    }

    public void showCounts()
    {
        ArrayList<String> strings=new ArrayList<String>();

        for (int i = 0; i <= IHuffConstants.ALPH_SIZE; i++)
        {
            if(_charCounter.getCount(i)!=0)
            {
                strings.add(convertToStringPattern(_charCounter.getCount(i),i));
            }
        }
        _viewer.update(strings);
    }


    public void initialize(InputStream stream)
    {
        try
        {
            _charCounter.countAll(stream);
            TreeMaker tm=new TreeMaker(_charCounter);
            _encoder.makeTable(tm);
            
        }
        catch (IOException ex)
        {
            Logger.getLogger(HuffModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void write(InputStream stream, File file, boolean force) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setViewer(HuffViewer viewer) {
        _viewer=viewer;
    }

    public void uncompress(InputStream in, OutputStream out) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private String convertToStringPattern(int count, int ch)
    {
       return String.format("%3d - '%2c' - %-5d",ch,((char)ch),count);
    }

}
