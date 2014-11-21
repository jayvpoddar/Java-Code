/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dualimageviewer;

/**
 *
 * @author jayp
 */
public class Blocker extends ImageFilter
{
    private int _blockSize;

    /**
     * takes in the desired block size
     * @param blockSize
     */
    public Blocker(int blockSize)
    {
        _blockSize = blockSize;
    }
    /**
     * finds the average of brightness values of the pixels in the block using blockAverage
     * and then changes the brightness value of the block to the average using changeBlockBrightness
     * @param shades
     * @return
     */
    @Override
    public int[][] pixelFilter(int[][] shades)
    {
        int average;        
        for (int x = 0; x < shades.length; x+=_blockSize)
        {
            for (int y = 0; y < shades[0].length;y+=_blockSize)
            {
                 average = blockAverage(shades,x,y);
                 changeBlockBrightness(average,shades,x,y);
            }
        }
        return shades;
    }
    /**
     * finds the block rightness average and takes into account the fact that the block maybe incomplete
     * @param pixelArray
     * @param x
     * @param y
     * @return average of brightness values of the block
     */
    private int blockAverage(int[][] pixelArray, int x, int y)
    {
        int sum=0;
        int count=0;
        for (int i = x; i < x+_blockSize&&i<pixelArray.length; i++)
        {
            for (int j = y; j < y+_blockSize&&j<pixelArray[0].length; j++)
            {
                sum+=pixelArray[i][j];
                count++;
            }

        }
        return (sum/count);
    }
//    takes a brightness value and sets that brightness value for all the pixles in the block
    private void changeBlockBrightness(int brightness, int[][] pixelArray, int x, int y)
    {
        for (int i = x; i < x+_blockSize&&i<pixelArray.length; i++)
        {
            for (int j = y; j < y+_blockSize&&j<pixelArray[0].length; j++)
            {
                pixelArray[i][j]=brightness;
            }
        }
    }

}
