package huffingpuffing;

// multiplying them by any rational number.
public class Scaler extends ImageFilter {

    // The numerator and denominator of the scaling factor
    private int mult, div;

    // Create a filter that will scale an images pixel values by
    // multiplying them by the quotient of its parameters (amult/adiv).
    public Scaler( int amult, int adiv ) {
        mult = amult;
        div = adiv;
    }
    
    // Modify the values in a pixel array by multiplying them by the
    // rational number mult/div (rounding correctly rather than truncating)
    @Override
    public int [][] pixelFilter( int [][] shades ) { 
        int round = (div - 1)/2;
        for ( int x = 0; x < shades.length; ++x ) {
            for ( int y = 0; y < shades[0].length; ++y ) {
                shades[x][y] = (mult*shades[x][y] + round)/div;
            }
        }
        return shades;
    }
}