/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Harsha Nori
 */


public class ImageProcessor
{
    Pic original;
    Pic toModify;
    public ImageProcessor(Pic input)
    {
        original=input;
        toModify=input.deepCopy();
    }
    public static void main(String[] args)
    {
        Pic myPic = new Pic(args[0]);
        ImageProcessor theImage = new ImageProcessor(myPic);
        theImage.greyscale();
        theImage.toModify.show();
        theImage.toModify = theImage.original.deepCopy();
        theImage.invert();
        theImage.toModify.show();
        theImage.toModify = theImage.original.deepCopy();
        theImage.noRed();
        theImage.toModify.show();
        theImage.toModify = theImage.original.deepCopy();
        theImage.noBlue();
        theImage.toModify.show();
        theImage.toModify = theImage.original.deepCopy();
        theImage.noGreen();
        theImage.toModify.show();
        theImage.toModify = theImage.original.deepCopy();
        theImage.maximize();
        theImage.toModify.show();
        theImage.toModify = theImage.original.deepCopy();
        if(args.length > 1){
            Pic mySecondPic = new Pic(args[1]);
            theImage.overlay(mySecondPic);
            theImage.toModify.show();
            theImage.toModify = theImage.original.deepCopy();
        }
             
    }
    
    public void greyscale()
    {
        for (int i = 0; i < toModify.getHeight(); i++)
        {
            for (int j = 0; j < toModify.getWidth(); j++)
            {
                Pixel myPixel = toModify.getPixel(i, j);
                int redVal = myPixel.getRed();
                int greenVal = myPixel.getGreen();
                int blueVal = myPixel.getBlue();
                int average; 
                average = (redVal + greenVal + blueVal)/3;
                myPixel.setRed(average);
                myPixel.setGreen(average);
                myPixel.setBlue(average);
            }
        }
    }
    
    public void invert()
    {
        for (int i = 0; i < toModify.getHeight(); i++)
        {
            for (int j = 0; j < toModify.getWidth(); j++)
            {
                Pixel myPixel = toModify.getPixel(i, j);
                int redVal = 255 - myPixel.getRed();
                int greenVal = 255 - myPixel.getGreen();
                int blueVal = 255 - myPixel.getBlue();
                myPixel.setBlue(blueVal);
                myPixel.setRed(redVal);
                myPixel.setGreen(greenVal);
            }
        }
    }
    
    public void noRed()
    {
        for (int i = 0; i < toModify.getHeight(); i++)
        {
            for (int j = 0; j < toModify.getWidth(); j++)
            {
                Pixel myPixel = toModify.getPixel(i, j);
                myPixel.setRed(0);
            }
        }
    }
    
    public void noBlue()
    {
        for (int i = 0; i < toModify.getHeight(); i++)
        {
            for (int j = 0; j < toModify.getWidth(); j++)
            {
                Pixel myPixel = toModify.getPixel(i, j);
                myPixel.setBlue(0);
            }
        }
    }
    
    public void noGreen()
    {
        for (int i = 0; i < toModify.getHeight(); i++)
        {
            for (int j = 0; j < toModify.getWidth(); j++)
            {
                Pixel myPixel = toModify.getPixel(i, j);
                myPixel.setGreen(0);
            }
        }
    }
    

    
    public void maximize()
    {
        for (int i = 0; i < toModify.getHeight(); i++)
        {
            for (int j = 0; j < toModify.getWidth(); j++)
            {
                Pixel myPixel = toModify.getPixel(i, j);
                int redVal = myPixel.getRed();
                int greenVal = myPixel.getGreen();
                int blueVal = myPixel.getBlue();
               
                int max1 = (redVal>greenVal)?redVal:greenVal;
                int max2 = (blueVal>max1)?blueVal:max1;
               
                if(redVal != max2)
                {
                    myPixel.setRed(0);
                }
                
                if(blueVal != max2)
                {
                    myPixel.setBlue(0);
                }
                
                if(greenVal != max2)
                {
                    myPixel.setGreen(0);
                }
            }
        }
    }
    
    public void overlay(Pic other)
    {
        int lengthUsed = (toModify.getHeight()<other.getHeight())?toModify.getHeight():other.getHeight();
        int widthUsed = (toModify.getWidth()<other.getWidth())?toModify.getWidth():other.getWidth();
        for (int i = 0; i < lengthUsed; i++)
        {
            for (int j = 0; j < widthUsed; j++)
            {
                Pixel myPixel = toModify.getPixel(i, j);
                Pixel myOtherPixel = other.getPixel(i, j);
                int redVal = myOtherPixel.getRed();
                int greenVal = myOtherPixel.getGreen();
                int blueVal = myOtherPixel.getBlue();
                myPixel.setRed((redVal + myPixel.getRed())/2);
                myPixel.setGreen((greenVal + myPixel.getGreen())/2);
                myPixel.setBlue((blueVal + myPixel.getBlue())/2);
            }
        }
    }
}
