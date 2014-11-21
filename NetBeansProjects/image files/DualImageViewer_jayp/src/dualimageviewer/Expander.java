/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dualimageviewer;



/**
 *
 * @author jayp
 */
public class Expander extends ImageFilter
{
    /**
     * takes a int[] [] representing a Image and expandes the range of its brigthness
     * values making the image more bright
     * @param shades
     * @return a int[][] represnting the new image
     */
    @Override
    public int[][] pixelFilter(int[][] shades)
    {
        int min=findMin(shades);
        int max=findMax(shades);
        int range=max-min+1;
        int width=(IImageConstants.MAXBRIGTHNESS+1)/range;
        for (int x = 0; x < shades.length; x++)
        {
            for (int y = 0; y < shades[0].length; y++)
            {
                 shades[x][y]=(shades[x][y]-min)*width;
            }
        }
        return shades;
    }
    /**
     * finds the highest brightness value in image
     * @param shades
     * @return max brightness value
     */
    private int findMax(int[][] shades)
    {
        int max=0;
        for (int x = 0; x < shades.length; x++)
        {
            for (int y = 0; y < shades[0].length; y++)
            {
                if(max<shades[x][y])
                {
                    max=shades[x][y];
                }

            }
        }
        return max;
    }
    /**
     * finds and return the lowest brightness value in image
     * @param shades
     * @return min brightness value
     */
    private int findMin(int[][] shades) {
         int min=IImageConstants.MAXBRIGTHNESS;
        for (int x = 0; x < shades.length; x++)
        {
            for (int y = 0; y < shades[0].length; y++)
            {
                if(min>shades[x][y])
                {
                    min=shades[x][y];
                }

            }
        }
        return min;
    }

}
