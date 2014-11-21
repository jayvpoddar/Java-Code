/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package huffingpuffing;


import java.util.Stack;

import squint.SImage;

/**
 *
 * @author jayp
 */
public class RecursiveWaveletSimplifier  extends ImageSimplifier
{
    private ImageSimplifier waveletSimplifier=new WaveletSimplifier();
    
   

    private SImage getImagePart(SImage original,int width)
    {
        SImage image=new SImage(width, original.getHeight(), 0);
        return new Paster(original).filter(image);
    }
     /**
     * decodes a image using the RecursiveWavelet simplifier method
     * @param shades
     * @return the decoded image
     */
    @Override
    public SImage decode(SImage original)
    {
        Stack<Integer> stack= new Stack<Integer>();
        for (int i =original.getWidth(); i >1;)
        {
            stack.add(i);
            i=(i+1)/2;
        }
        
        while(!stack.empty())
        {
            SImage image=getImagePart(original, stack.pop());
            image=waveletSimplifier.decode(image);
            original=new Paster(image).filter(original);
        }        
        return original;
    }
     /**
     * encodes a image using the RecursiveWavelet simplifier method
     * @param shades
     * @return the decoded image
     */
    @Override
    public SImage encode(SImage original)
    {
        return encodeHelper(original);
    }
   //helps in enoding the image
    public SImage encodeHelper(SImage original)
    {
        if(original.getWidth()!=1)
        {
            original= waveletSimplifier.encode(original);
            SImage leftHalf=encodeHelper(getImagePart(original,(original.getWidth()+1)/2));
            original=new Paster(leftHalf).filter(original);

        }
        return original;
    }
}
