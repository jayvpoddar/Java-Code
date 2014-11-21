package huffingpuffing;

import squint.*;

// This class defines a template intended to be extended by other
// classes that define image filters.  The code in this class defines
// the "null" filter (i.e., it returns an unchanged copy of an image).
//
// In typical uses of this class, one of the methods defined below will
// be overridden while the other methods are either inherited or unused.
// That is, you might override layerFilter (in which case the pixelFilter
// methods would probably be unsued), or you might override pixelFilter
// (which would then be invoked by the inherited versions of the remaining
// methods).
public class ImageFilter {
    
    // Create a filter
    public ImageFilter( ) {
    }
    // Given an image, produce a copy
     public SImage filter( SImage original ) {
        return new SImage( layerFilter( original, SImage.RED ),
                           layerFilter( original, SImage.GREEN ),
                           layerFilter( original, SImage.BLUE )
                           );
    }
     
    // Extract the specified color pixel array
    public int [][] layerFilter( SImage orig, int layer ) {
        return pixelFilter( orig.getPixelArray( layer ) );
    }
    
    // Adjust the number of distinct brightness levels used in a pixel array
    public int [][] pixelFilter( int [][] shades ) {
        return shades;
    }

}