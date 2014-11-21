/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dualimageviewer;


/**
 *
 * @author jayp
 */
public class Histogram 
{
    private int[] _histogram=new int[IImageConstants.MAXBRIGTHNESS+1];
    private int _mostCount;
    /**
     * Constructor for the _histogram takes a pixel array and stores a histopgram of the brightness values in
     * int[] _histogram
     * @param pixelArray The array of pixels. width = pixelArray.length, 
     * height = pixelArray[0].length
     */
    public Histogram(int [][]pixelArray)
    {        
        
        for (int x = 0; x < pixelArray.length; x++)
        {
            for (int y = 0; y < pixelArray[0].length; y++)
            {
                _histogram[pixelArray[x][y]]++;
                if(_histogram[pixelArray[x][y]]>_mostCount)
                {
                    _mostCount=_histogram[pixelArray[x][y]];
                }
            }
        }
    }
    /**
     * @return the index of the most occuring brightness value
     */
    public int getHighest()
    {
        return _mostCount;
    }
    /**
     * @return the index of the least occuring brightness value
     */
    public int getCount(int brightness)
    {
        return _histogram[brightness];
    }
}
