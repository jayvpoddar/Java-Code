/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dualimageviewer;

import squint.SImage;

/**
 *
 * @author jayp
 */
public class Differencer extends ImageFilter
{
    SImage _image;

    /**
     * takes a image which is to be compared with another later
     * @param image
     */
    public Differencer(SImage image)
    {
        _image = image;
    }


    /**
     * finds wheter original has the same dimensions as the _image
     * if it does it calls layerfilter for all the colours
     * @param original
     * @return image containing difference of original and _image
     */
    @Override
    public SImage filter(SImage original)
    {
        if (original.getWidth()==_image.getWidth()&&original.getHeight()==_image.getHeight())
        {
            return new SImage( layerFilter( original, SImage.RED),
                           layerFilter( original, SImage.GREEN),
                           layerFilter( original, SImage.BLUE)
                           );
        } 
        else
        {
            return null;
        }
        
    }
    /**
     * takes the colour constant and compares the int[][] of brightness value of original and _image
     * it creates a int[][] of size of the original int[][],in which for every int [x][y] contains the absolute
     * value of the  difference of the brightness value of the int[x][y] of original and _image
     * @param image
     * @param layer
     * @return int[][] differences of brightness value of original and _image
     */
    @Override
    public int[][] layerFilter(SImage original, int layer)
    {
        int [][] pixelArray=new int[original.getWidth()][original.getHeight()];
        int [][] imgPixelArray=_image.getPixelArray(layer);
        int [][] origPixelarray=original.getPixelArray(layer);
        for (int x = 0; x < original.getWidth(); x++)
        {
            for (int y = 0; y < original.getHeight(); y++)
            {
                 pixelArray[x][y]=(int)Math.abs(imgPixelArray[x][y]-origPixelarray[x][y]);
            }
        }
        return pixelArray;
    }

}
