package huffingpuffing;

// A class that provides method to simplify/unsimply images by
// compressing/expanding the range of brightness values used to encode
// the image
//
// This class is provided as an example of how an image simplifier can
// be defined by extending ImageSimplifier and overriding the encodePixels
// and decodePixels methods.
public class RangeSimplifier1 extends ImageSimplifier {

    // The width of the bands in the original image that
    // should be compressed to a single brightness level
    private int widthOfBands;
    
    // Create a simplifier that will compress the range of brightnesses
    // used to the range provided as a parameter
    public RangeSimplifier1( int theRange ) {
        widthOfBands = 256/theRange;
    }

    // Compress the range of pixel values used in a pixel array
    @Override
    public int [][] encodePixels( int [][] shades ) {
        for ( int x = 0; x < shades.length; ++x ) {
            for ( int y = 0; y < shades[0].length; ++y ) {
                shades[x][y] = shades[x][y]/widthOfBands;
            }
        }
        return shades;
    }

    // Stretch the range of pixel values used in a pixel array
    @Override
    public int [][] decodePixels( int [][] shades ) {
        // The amount to add to get to the middle of each band
        int round = widthOfBands/2;

        for ( int x = 0; x < shades.length; ++x ) {
            for ( int y = 0; y < shades[0].length; ++y ) {
                shades[x][y] = shades[x][y]*widthOfBands + round;
            }
        }
        return shades;
    }


}