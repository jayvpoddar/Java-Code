
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
class HuffModel implements IHuffModel {

    private HuffViewer _huffViewer;
    private ICharCounter _charCounter;
    private ITreeMaker _treeMaker;
    private IHuffEncoder _huffEncoder;
    private IHuffHeader _huffHeader;
    private boolean _isCompressedFileSmaller;

    public HuffModel() {
    }

    public void showCodings()
    {
        ArrayList<String> strings = new ArrayList<String>();

        for(int i = 0;i <IHuffConstants.ALPH_SIZE;i++)
        {
            String code = _huffEncoder.getCode(i);

            if(code != null)
            {
                strings.add(((char)i) + " - " + i + " - " + code);
            }
        }
        strings.add("e-o-f" + "  -  " + (IHuffConstants.ALPH_SIZE+1) + " - " + _huffEncoder.getCode(ALPH_SIZE));

        
        _huffViewer.update(strings);
    }

    public void showCounts()
    {
        ArrayList<String> strings = new ArrayList<String>();

        for(int i = 0;i <IHuffConstants.ALPH_SIZE;i++)
        {
            if(_charCounter.getCount(i) != 0)
            {
                strings.add(((char)i) + " - " + i + " - " + _charCounter.getCount(i));
            }
        }

        _huffViewer.update(strings);
    }

    public void initialize(InputStream stream) 
    {
        _charCounter = new CharCounter();
        int actualBitSize = 0;
        try
        {
            actualBitSize = _charCounter.countAll(stream)*8;
        }
        catch (IOException ex)
        {

        }

        _treeMaker = new TreeMaker(_charCounter);
        _huffEncoder = new HuffEncoder();
        _huffEncoder.makeTable(_treeMaker);
        
        _huffHeader = new HuffHeader(_charCounter);

        if (actualBitSize > calculateSpaceDueToCompression())
        {
            _isCompressedFileSmaller = true;
        }
    }

    private int calculateSpaceDueToCompression()
    {
        int space = _huffHeader.headerSize();

        CodeConverter cc = new CodeConverter(_huffEncoder);
        CharCode code;
        int lengthOfCode,frequency;

        for (int i = 0; i < IHuffConstants.ALPH_SIZE+1; i++)
        {
            if ((frequency = _charCounter.getCount(i)) == 0)
            {
                continue;
            }
            code = cc.getCode(i);
            lengthOfCode = (code.codeArray.length-1)*32 + code.howManyBits;
            
            space += lengthOfCode*frequency;
        }
        
        return space;
    }

    public void write(InputStream stream, File file, boolean force)
    {
        if (force == false)
        {
            if (!_isCompressedFileSmaller)
            {
                System.err.println("Compressed File Is Bigger!");
                return;
            }
        }

        CodeConverter cc = new CodeConverter(_huffEncoder);
        BitOutputStream bos = new BitOutputStream(file.getAbsolutePath());

        _huffHeader.writeHeader(bos);

        int ch;

        CharCode code;
        try
        {
            while ((ch = stream.read()) != -1)
            {
                code = cc.getCode(ch);
                bos.write(code.howManyBits, code.codeArray[0]);
            }

            code = cc.getCode(IHuffConstants.PSEUDO_EOF);

            bos.write(code.howManyBits,code.codeArray[0]);
        }
        catch (IOException ex)
        {
            Logger.getLogger(HuffModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            bos.close();
        }
    }

    public void setViewer(HuffViewer viewer)
    {
        _huffViewer = viewer;
    }

    public void uncompress(InputStream in, OutputStream out)
    {
        _huffHeader = new HuffHeader();
        try
        {
            BitInputStream bitIn = new BitInputStream(in);
            BitOutputStream bitOut = new BitOutputStream(out);
            _treeMaker = _huffHeader.readHeader(bitIn);
                
            IHuffDecoder huffDecoder = new HuffDecoder();

            huffDecoder.initialize(_treeMaker);
            huffDecoder.doDecode(bitIn, bitOut);
        }
        catch (IOException ex)
        {
            Logger.getLogger(HuffModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
