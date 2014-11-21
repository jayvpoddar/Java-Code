/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package huffingpuffing;

import squint.SImage;

/**
 *
 * @author jayp
 */
public class KitchenSink extends ImageSimplifier
{
    ImageSimplifier recursiveWaveletSimplifier=new RecursiveWaveletSimplifier();
    ImageSimplifier waterFallSimplifier=new WaterfallSimplifier();

    /**
     * takes an encoded image and decodes it by first using waterfall decoder and
     * then doing recursive wavelet decoder on the resultant
     * @param original
     * @return the decoded image
     */
    @Override
    public SImage decode(SImage original)
    {
       return recursiveWaveletSimplifier.decode(waterFallSimplifier.decode(original));
    }

    /**
     * takes a image and encodes that using recursive wavelet
     * and then encoding the image encoded earlier using waterfall
     * @param original
     * @return encoded image
     */
    @Override
    public SImage encode(SImage original)
    {
        return waterFallSimplifier.encode(recursiveWaveletSimplifier.encode(original));
    }

}
