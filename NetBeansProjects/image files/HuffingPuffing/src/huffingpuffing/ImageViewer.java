package huffingpuffing;

import java.awt.*;
import squint.*;
import javax.swing.*;

/*
 * ImageViewer --- a GUIManager to display a single image and provide the
 *                 ability to expand the range of that image and to
 *                 see the image's color histograms.
 */
public class ImageViewer extends GUIManager {
    // The image to display
    private SImage picture;
    
    // Label used to display the image
    private JLabel displayImage = new JLabel( "", SwingConstants.CENTER );
   
    // Filter used to implement the "Expand" button
    private Expander expandRange = new Expander( 0, 255);
   
    // Control buttons
    private JButton showHist = new JButton( "Show Histogram" );
    private JButton expand = new JButton( "Expand Range" );
                  
    // Place all the GUI components in the GUIManager's pane
    public ImageViewer() {
      contentPane.setLayout( new BorderLayout() );
      contentPane.add( new JScrollPane( displayImage ), BorderLayout.CENTER );
      
      JPanel controlPane = new JPanel();
      controlPane.setLayout( new GridLayout( 2, 2 ) );
      controlPane.add( showHist );
      controlPane.add( expand );
      
      contentPane.add( controlPane, BorderLayout.SOUTH );
    }
    
    // Display histograms or extend range as requested
    @Override
    public void buttonClicked( JButton which ) {
        if ( which == showHist ) {
            Histogram [] histogram = new Histogram[3];
    
            for ( int c = SImage.RED; c <= SImage.BLUE; ++c ) {
                histogram[c] = new Histogram( picture.getPixelArray( c ) );
            }
            new DisplayHistograms( histogram );
            
        } else if ( which == expand ) {
            setPic( expandRange.filter(picture) );
            
        } 
    }
    
    // Set the image displayed within the viewer
    public void setPic( SImage newPic ) {
        picture = newPic;
        displayImage.setIcon( picture );
    }
    
    // Get the image displayed within the viewer
    public SImage getPic( ) {
        return picture;
    }
    


}



