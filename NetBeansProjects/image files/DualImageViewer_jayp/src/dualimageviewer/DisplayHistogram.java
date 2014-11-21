/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dualimageviewer;
import javax.swing.JLabel;
import squint.GUIManager;
import squint.SImage;

public final class DisplayHistogram extends GUIManager
{
    private final int HISTOGRAMHEIGHT=220;
    private final int HISTOGRAMWIDTH=256;
    /**
     * creates a histogram image
     * @param hgram
     */
    public DisplayHistogram(Histogram hgram)
    {
        createWindow(350, 300);
        this.contentPane.add(new JLabel(generateImage(hgram)));
    }
    /**
     * takes a histogram and makes a image of it
     * @param hgram
     * @return the image of histogram
     */
    private SImage generateImage(Histogram hgram)
    {
        SImage image = new SImage(HISTOGRAMWIDTH, HISTOGRAMHEIGHT, IImageConstants.MAXBRIGTHNESS);
        int[][] pixelArray=image.getPixelArray();
        int[] lengths= getLineLength(hgram);
        for (int x = 0; x < image.getWidth(); x++)
        {
            for (int y = HISTOGRAMHEIGHT-1; y >(HISTOGRAMHEIGHT-1-lengths[x]); y--)
            {
                pixelArray[x][y]=0;
            }
        }
        return new SImage(pixelArray);
    }
    /**
     * scale the value of brightnesses if the most occuring one overshoots HISTOGRAMHEIGHT
     * @param hgram
     * @return int[] length which has scaled value of all brightnesses
     */

    private int[] getLineLength(Histogram hgram)
    {
        int highestCount=hgram.getHighest();
        int[] lengths=new int[IImageConstants.MAXBRIGTHNESS+1];
        int scale=(int)Math.ceil((double)highestCount/(HISTOGRAMHEIGHT));
        for (int i = 0; i < lengths.length; i++)
        {
            lengths[i] =(hgram.getCount(i)/scale);
        }
        return lengths;
    }
}