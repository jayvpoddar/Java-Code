package dualimageviewer;

import squint.*;

public class ImageFilter {
    
    // Create a filter
    public ImageFilter( ) {
    }
    
    // Given an image, produce a copy
    public SImage filter( SImage original ) {
        return new SImage( layerFilter( original, SImage.RED),
                           layerFilter( original, SImage.GREEN),
                           layerFilter( original, SImage.BLUE)
                           );
    }
     
    // Extract the specified color pixel array
    public int [][] layerFilter( SImage orig, int layer ) {
        return pixelFilter( orig.getPixelArray( layer ) );
    }
    
    // Return the provided pixel array unchanged
    public int [][] pixelFilter( int [][] shades ) {
        return shades;
    }
 }