/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author saukap
 */
public class CharCode 
{
    String strCode;
    int[] codeArray;
    int howManyBits;
    
    public CharCode(String code)
    {
        strCode = code;
        generateCodeArray();
    }
    
    private void generateCodeArray()
    {
        if(strCode.length() < 1)
        {
            throw new IllegalArgumentException("code should be of length >= 1");
        }
        int size = ((strCode.length() - 1) / IHuffConstants.BITS_PER_INT) + 1;
        howManyBits = ((strCode.length()-1) % IHuffConstants.BITS_PER_INT) + 1;
        codeArray = new int[size];
        
        int arrayInd = 0;
        for(int i=0; i<strCode.length(); ++i)
        {
            if(i !=0 && i % IHuffConstants.BITS_PER_INT == 0)
            {
                arrayInd++;
            }
            if(strCode.charAt(i) == '0')
            {
                codeArray[arrayInd] = codeArray[arrayInd] <<  1;
            }
            else
            {
                codeArray[arrayInd] = (codeArray[arrayInd] << 1 ) + 1;
            }
        }
    }
}