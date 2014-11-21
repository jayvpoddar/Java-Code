package dualimageviewer;



public class Quantizer extends ImageFilter {
    // Number of brightness levels desired
    private int _levels;
 
    // Create a filter that will quantize to a specific number of brightness levels
    public Quantizer( int theLevels ) {
        _levels = theLevels;
    }
    // Adjust the number of distinct brightness levels used in a pixel array
    @Override
    public int [][] pixelFilter( int [][] shades ) {
        int bandWidth = (int)Math.ceil((double)(IImageConstants.MAXBRIGTHNESS+1)/_levels);
        int round = (bandWidth-1)/2;
        for ( int x = 0; x < shades.length; ++x ) {
            for ( int y = 0; y < shades[0].length; ++y ) {
                shades[x][y] = shades[x][y]/bandWidth*bandWidth + round;
            }
        }
        return shades;

    }
 }