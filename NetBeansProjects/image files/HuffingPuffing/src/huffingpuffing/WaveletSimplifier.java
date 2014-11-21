/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package huffingpuffing;



/**
 *
 * @author jayp
 */
public class WaveletSimplifier extends ImageSimplifier
{
     /**
     * decodes a image using the waterfall simplifier method
     * @param shades
     * @return the decoded image
     */
    @Override
    public int[][] decodePixels(int[][] shades) 
    {
        //result stores the resultant image
        int[][] temp=new int[shades.length][shades[0].length];
        int x1=0;
        int x2=(shades.length+1)/2;
        //maitains the index at which the next brightness vvalue has to be put
        int index=0;
        int a;
        int b;
        for (int y = 0; y < shades[0].length; y++)
        {
            for (; x2 < shades.length; x1++,x2++)
            {
                a=shades[x1][y]+shades[x2][y];
                b=shades[x1][y]-shades[x2][y];
                temp[index++][y]=a;
                temp[index++][y]=b;
            }
            x2=(shades.length+1)/2;
            //check whtere there is a unparied element and if there is it is put at the end of the row y
            if(x1<x2)
            {
                temp[index][y]=shades[x1][y];
            }
            x1=0;
            index=0;
        }
        return temp;
    }
    /**
     * encodes a image using the wavelet simplifier method
     * @param shades
     * @return the encoded image
     */
    @Override
    public int[][] encodePixels(int[][] shades)
    {
        //rightHalf stores the right half of the encoded image for a particular y.
        int[] rightHalf=new int[shades.length/2];
        int a;
        int b;
        int index=0;

        for (int y = 0; y < shades[0].length; y++)
        {
            int x;
            for ( x = 0; x < shades.length-1; x+=2)
            {
                a=(shades[x][y]+shades[x+1][y])/2;
                b=(shades[x][y]-shades[x+1][y])/2;
                shades[index][y]=a;
                rightHalf[index++]=b;
            }
            //puts the unpaired element at the middle index of the array
            if(x==shades.length-1)
            {
                shades[index][y]=shades[x][y];
            }
            index=0;
             //the elements of rightHalf are copied to the right half of the original array for  y
            for (x =(int)(Math.ceil(((double)shades.length/2))); x < shades.length; x++)
            {
                shades[x][y]=rightHalf[index++];
            }
            index=0;
        }
        return shades;
    }

}
