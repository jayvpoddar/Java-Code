/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * A wrapper class around the CharCode class that returns the code corresponding
 * to each character
 * 
 * @author saukap
 */
public class CodeConverter {
    
    private CharCode[] charCodeArray = new CharCode[IHuffConstants.ALPH_SIZE+1];
    private IHuffEncoder _encoder;
    
    public CodeConverter(IHuffEncoder encoder)
    {
        _encoder = encoder;
    }
    
    public CharCode getCode(int ch)
    {
        if(charCodeArray[ch] == null)
        {
            charCodeArray[ch] = new CharCode(_encoder.getCode(ch));
        }
        return charCodeArray[ch];
    }
    
}