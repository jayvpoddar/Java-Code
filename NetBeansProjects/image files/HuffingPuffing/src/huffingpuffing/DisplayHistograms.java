package huffingpuffing;

import java.awt.*;
import squint.*;
import javax.swing.*;

/*
 * DisplayHistograms -- Display a set of histograms in a new window
 */
public class DisplayHistograms extends GUIManager {
    // Window dimensions
    private final int MAX_WINDOW_WIDTH = 1000, WINDOW_HEIGHT = 300;
    
    // Space desired between histogram images
    private final int PADDING = 2;
    
    // How tall a histogram image will fit in the window
    private final int HIST_HEIGHT = WINDOW_HEIGHT - 40 - 2*PADDING;
    
    // Values that represent colors used to draw histograms
    private final int GRAY = 200;
    private final int BLACK = 0;
    private final int WHITE = 255;
    
    // Create a window containing drawings of the histograms in the parameter array
    public DisplayHistograms( Histogram [] histogram ) {
      contentPane.setLayout( new BorderLayout() );
      
      // A pane to hold the histogram images
      JPanel graphs = new JPanel();
      graphs.setBackground( Color.YELLOW );
      graphs.setLayout( new GridLayout(1, histogram.length, PADDING, 0 ) );
      contentPane.add( graphs, BorderLayout.CENTER );
      
      // A pane to hold the bit/pixel information for each histogram
      JPanel huffmanPane = new JPanel();
      huffmanPane.setLayout( new GridLayout(1, histogram.length, PADDING, 0 ) );     
      contentPane.add( huffmanPane, BorderLayout.SOUTH );
      
      // Construct histogram images and bit/pixel information for each histogram
      int windowWidth = PADDING;
      for ( int c = 0; c < histogram.length; c++ ) {
          SImage histImage = drawHistogram( histogram[c] );
          graphs.add( new JLabel( histImage ) );
          windowWidth = histImage.getWidth() + windowWidth + PADDING + 1;
          
          huffmanPane.add( new JLabel( "Bits per pixel = " +  histogram[c].huffmanSize() ) );
      }
      
      // Display the completed window
      this.createWindow( Math.min( windowWidth, MAX_WINDOW_WIDTH), WINDOW_HEIGHT );
    }
    
    
    // Create an image containing a drawing of the histogram parameter
    private SImage drawHistogram( Histogram histogram ) {
        
        // Create an initial white background
        int [][] result = new int[histogram.range()][HIST_HEIGHT];
        fillRectangle( 0, 0, histogram.range(), HIST_HEIGHT, result, WHITE );
        
        int maxFreq = histogram.maxFreq();
        int minBrightness = histogram.min();
        
        // Draw a linen for each brightness value in the histogram
        for ( int b = minBrightness; b <= histogram.max(); b++ ) {
            if ( b % 10 == 0 ) {  // Add graph paper lines every 10 positions
                drawLine( b - minBrightness, HIST_HEIGHT, result, GRAY );
            }
            drawLine( b - minBrightness, HIST_HEIGHT*histogram.frequency(b)/maxFreq, result, BLACK );
        }
        
        return new SImage( result );
    }
    
    // Fill a rectangular region in a pixel array with brightness value for a given shade
    private void fillRectangle( int left, int top, int width, int height, int[][] pixels, int shade ) {
        for ( int x = left; x < left + width; ++x ) {
            for ( int y = top; y < top + height; ++y ) {
                pixels[x][y] = shade;
            }
        }
    }
    
    // Set the brightness values in a pixel array that correspond to a vertical line 
    // of a specified height at a particular horizontal position to a given shade
    private void drawLine( int x, int height, int[][] pixels, int shade ) {
        for( int y = pixels[0].length - height; y < pixels[0].length; y++ ) {
            pixels[x][y] = shade;
        }
    }
   

}



