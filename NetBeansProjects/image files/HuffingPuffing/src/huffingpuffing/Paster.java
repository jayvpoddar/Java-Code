package huffingpuffing;

import squint.*;

// An image filter that can be used to place a copy of one image into
// another.
public class Paster extends ImageFilter {

    // The image to be pasted in when this filter is applied
    private SImage insert;

    // Create a new Paste-er given an image to paste
    public Paster( SImage theInsert ) {
        insert = theInsert;
    }
    
    // Produce a pixel array describing the brightness values for the color layer
    // specified for an image formed by pasting "insert" into the image "target"
    @Override
    public int [][] layerFilter( SImage target, int layer ) {
        
        int [][] src = insert.getPixelArray( layer );
        int [][] dest = target.getPixelArray( layer );
        
        for ( int x = 0; x < dest.length && x < src.length; ++x ) {
            for ( int y = 0; y < dest[0].length && y < src[0].length; ++y ) {
                dest[x][y] = src[x][y];
            }
        }
        
        return dest;
    }
}