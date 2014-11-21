/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package huffingpuffing;



/**
 *
 * @author jayp
 */
public class WaterfallSimplifier extends ImageSimplifier
{
     /**
     * decodes a image using the waterfall simplifier method
     * @param shades
     * @return the decoded image
     */
    @Override
    public int[][] decodePixels(int[][] shades)
    {
        for (int x = 0; x < shades.length; x++)
        {
          for (int y = 0; y < shades[0].length-1; y++)
            {
               shades[x][y+1]+=shades[x][y];
            }
        }
        return shades;
    }
    /**
     * encodes a image using the waterfall simplifier method
     * @param shades
     * @return the encoded image
     */
    @Override
    public int[][] encodePixels(int[][] shades)
    {
        for (int x = 0; x < shades.length; x++)
        {
            for (int y = shades[0].length-1; y > 0; y--)
            {
               shades[x][y]-=shades[x][y-1];
            }
        }
        return shades;
    }

}
