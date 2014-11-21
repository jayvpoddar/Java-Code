package huffingpuffing;

import squint.*;

// A filter that can be used to rescale the pixel values in an image so
// that they fill a specified range of values.
public class Expander extends ImageFilter {

    int low, newRange;

    // Create an expander given the upper and lower bounds of the
    // range of values desired in the results of a filter operation
    public Expander( int theLow, int high ) {
        low = theLow;
        newRange = high - low + 1;
    }
    
    // Rescale the values used in a pixel array so that they fall within
    // the desired range
    public int [][] pixelFilter( int [][] shades ) {
        int min = getMin( shades );
        int max = getMax( shades );
        int range = max - min + 1;
        
        for ( int x = 0; x < shades.length; ++x ) {
            for ( int y = 0; y < shades[0].length; ++y ) {
                shades[x][y] = (shades[x][y]-min)*newRange/range + low;
            }
        }
        return shades;
    }
        
    // Determine the smallest value used in a pixel array
    public int getMin( int [][] shades ) {
        int result = shades[0][0];
        
        for ( int x = 0; x < shades.length; ++x ) {
            for ( int y = 0; y < shades[0].length; ++y ) {
                if ( shades[x][y] < result ) {
                    result = shades[x][y];
                }
            }
        }
        return result;
                
    }
    
    // Determine the largest value used in a pixel array
    public int getMax( int [][] shades ) {
        int result = shades[0][0];
        
        for ( int x = 0; x < shades.length; ++x ) {
            for ( int y = 0; y < shades[0].length; ++y ) {
                if ( shades[x][y] > result ) {
                    result = shades[x][y];
                }
            }
        }
        return result;
                
    }
}