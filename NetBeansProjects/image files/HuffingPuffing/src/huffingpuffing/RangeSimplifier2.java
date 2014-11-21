package huffingpuffing;

import squint.*;

// A class that provides methods to simplify/unsimply images by
// compressing/expanding the range of brightness values used to encode
// the image
//
// This class provides an example of how an image simplifier can
// be defined by extending ImageSimplifier and overriding the encode
// and decode methods.  This is a bit odd because it means that none
// of the methods inheritied are actually used.  By defining a class in
// this way, however, we enable Java to recognize that is is an
// ImageSimplifier and can validly be put in our SimplifierList.
public class RangeSimplifier2 extends ImageSimplifier {

    // Image filters that will scale the pixel values in an image
    // up or down to fit the range desired.
    private ImageFilter upscale;
    private ImageFilter downscale;
    
    // Create a simplifier that will compress the range of brightnesses
    // used to the range provided as a parameter
    public RangeSimplifier2( int theRange ) {
        // The width of the bands in the original image that
        // should be compressed to a single brightness level
        int widthOfBands = 256/theRange;
        
        upscale = new Scaler( widthOfBands, 1 );
        downscale = new Scaler( 1, widthOfBands );
    }

    // Compress the range of pixel values used in an image
    @Override
    public SImage encode( SImage original ) {
        return downscale.filter( original );
    }

    // Unompress the range of pixel values used in an image
    @Override
    public SImage decode( SImage simplified ) {
        return upscale.filter( simplified );
    }


}