package huffingpuffing;

import squint.*;

// This class defines a template intended to be extended by other
// classes that define actual image simplification algotithms.
// The code in this class performs the "null" simplification algorithm.
//
// In typical uses of this class, one pair of the "encode/decode" methods
// defined below will be overridden while the other methods are either
// inherited or unused.  That is, you might override encode and decode (in
// which case the remaining methods would probably be unsued), or you
// might override encodePixels and decodePixels (which would then be
// invoked by the inherited versions of the remaining methods).
public class ImageSimplifier {

    // Simplify the pixel array provided as a parameter
    public int [][] encodePixels( int [][] shades ) {
        return shades;
    }
    
    // Unsimplify the pixel array provided as a parameter
    public int [][] decodePixels( int [][] shades ) {
        return shades;
    }
    
    // Simplify the pixel array corresponding to the color layer
    // specified in the SImage provided as a parameter
    public int [][] encodeLayer( SImage orig, int layer ) {
        return encodePixels( orig.getPixelArray( layer ) );
    }
    
    // Unimplify the pixel array corresponding to the color layer
    // specified in the SImage provided as a parameter
    public int [][] decodeLayer( SImage orig, int layer ) {
        return decodePixels( orig.getPixelArray( layer ) );
    }
    
    // Simplify the SImage provided as a parameter
    public SImage encode( SImage original ) {
        return new SImage( encodeLayer( original, SImage.RED ),
                           encodeLayer( original, SImage.GREEN ),
                           encodeLayer( original, SImage.BLUE )
                           );
    }
    
    // Unsimplify the SImage provided as a parameter
    public SImage decode( SImage original ) {
        return new SImage( decodeLayer( original, SImage.RED ),
                           decodeLayer( original, SImage.GREEN ),
                           decodeLayer( original, SImage.BLUE )
                           );
    }
    
}