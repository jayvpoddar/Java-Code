package dualimageviewer;

import squint.*;

public class Paster extends ImageFilter {

    // The image to be pasted in when this filter is applied
    private SImage _imgToPaste;

   
    public Paster( SImage img )
    {
        _imgToPaste = img;
    }
    
    // Produces a pixel array describing the brightness values for the color layer
    // specified for an image formed by pasting "insert" into the image "target"
    @Override
    public int [][] layerFilter( SImage target, int layer ) {
        
        int [][] src = _imgToPaste.getPixelArray(layer);
        int [][] dest = target.getPixelArray(layer );
        int maxX=(dest.length<src.length)?dest.length:src.length;
        int maxY=(dest[0].length<src[0].length)?dest[0].length:src[0].length;

        for (int x = 0; x < maxX; x++)
        {
            for (int y = 0; y < maxY; y++)
            {
                dest[x][y] = src[x][y];
            }
        }
        return dest;
    }
}