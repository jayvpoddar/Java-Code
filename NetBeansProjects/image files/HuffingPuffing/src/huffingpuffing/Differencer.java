package huffingpuffing;

import squint.*;

// An image filter that can be used to compute the difference between the pixel
// values of images of similar dimensions
public class Differencer extends ImageFilter {

    private SImage orig;

    // Create a differencer that will compute the difference between the image
    // provided as a parameter and other images later provided to its filter
    // method
    public Differencer( SImage theOrig ) {
        orig = theOrig;
    }
    
    // Compute the differences between corresponding pixel values in the pixel
    // arrays describing the4 given color layer of the images provided as
    // a parameter to this method and to the differencer constructor.
    @Override
    public int [][] layerFilter( SImage other, int layer ) {
        int [][] others = other.getPixelArray( layer );
        int [][] shades = orig.getPixelArray( layer );
                
        if ( others.length == shades.length && others[0].length == shades[0].length ) {
            for ( int x = 0; x < shades.length; ++x ) {
                for ( int y = 0; y < shades[0].length; ++y ) {
                    others[x][y] = Math.abs(shades[x][y] - others[x][y]);
                }
            }
            return others;
        } else {
            return null;
        }
    }
}