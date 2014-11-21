package huffingpuffing;

import java.awt.*;
import squint.*;
import javax.swing.*;
import java.io.File;

/*
 * DualImageViewer  --- Loads and displays images.  Allows user to apply 
 *                      a variety of simplification algorighms to the image
 *                      pixels and see both the simplified image and the result
 *                      of attempting to restore the original by "unsimplifying"
 */
public class DualImageViewer extends GUIManager {
    // Window dimensions
    private final int WINDOW_WIDTH  = 700, WINDOW_HEIGHT = 400;
   
    // Viewer panels for the original and unsimplified versions of an image
    private ImageViewer left = new ImageViewer();
    private ImageViewer right = new ImageViewer();
    
    // Buttons used to load, compress, and compare original and unsimplified images
    private JButton btnLoad = new JButton( "Load Image" );
    private JButton btnCompress = new JButton( "-> Simplify -> Unsimplify ->");
    private JButton btnDifference = new JButton( "Show difference" );

    // Menu used to select a simplification algorithm
    private JComboBox methods = new JComboBox();
    
    // List of simplifiers that have been placed in methods menu
    private SimplifierList simplifiers = new SimplifierList();
  
    // Dialog box used to select image files
    private JFileChooser chooser = new JFileChooser( new File( System.getProperty("user.dir")) );
    
    public DualImageViewer() {
      this.createWindow( WINDOW_WIDTH, WINDOW_HEIGHT );
      contentPane.setLayout( new BorderLayout() );
      
      // Place two imageviewers in a panel at CENTER of contentPane
      JPanel viewers = new JPanel();
      viewers.setLayout( new GridLayout(1,2) );
      viewers.add( left , BorderLayout.NORTH);
      viewers.add( right , BorderLayout.SOUTH);
      contentPane.add( viewers, BorderLayout.CENTER );
      
      
      // Add desired image simplifiers to menu of available algorithms
      addSimplifier( "Range Simplifier", new RangeSimplifier1( 32 ) );
      addSimplifier( "Alternate Range Simplifier", new RangeSimplifier2( 32 ) );
      addSimplifier("WaterFall", new WaterfallSimplifier());
      addSimplifier("Wavelet", new WaveletSimplifier());
      addSimplifier("Recursive Wavelet", new RecursiveWaveletSimplifier());
      addSimplifier("Kitchen Sink", new KitchenSink());
      //   TODO: ......   add lines to incorporate additional simplifiers here

      
      // Add control buttons and menu to SOUTH of contentPane
      JPanel controls = new JPanel();
      controls.add( btnLoad );
      controls.add( btnDifference );
      controls.add( btnCompress );
      controls.add( methods );
      contentPane.add( controls, BorderLayout.SOUTH );
    }
    
    
    // Respond to control buttons
    @Override
    public void buttonClicked( JButton btnClicked ) {
        
        if ( btnClicked == btnLoad ) {
            // Try to load a new image file
            if ( chooser.showOpenDialog( this ) == JFileChooser.APPROVE_OPTION ) {
                left.setPic( new SImage( chooser.getSelectedFile().getAbsolutePath() ) );
            }

        } else if ( btnClicked == btnDifference ) {
            SImage leftPic = left.getPic();
            SImage rightPic = right.getPic();
        
            // Compute and display the difference between and original and unsimplified image
            // (as long as they both exist and are of the same dimensions)
           if ( leftPic != null && rightPic != null &&
                 leftPic.getWidth() == rightPic.getWidth() &&
                 leftPic.getHeight() == rightPic.getHeight() )
           {
                ImageViewer newWin = new ImageViewer();
                newWin.setPic( new Differencer( leftPic).filter( rightPic ) );
                newWin.createWindow( WINDOW_WIDTH, WINDOW_HEIGHT );
            }
            
        } else if ( btnClicked == btnCompress && left.getPic() != null) {
            // Apply the simplification algorithm selected in the menu to the
            // image displayed in the left page.
            ImageViewer result = new ImageViewer( );
            result.createWindow( 300, 300 );

            ImageSimplifier comp = simplifiers.getSimplifier( methods.getSelectedItem().toString() );
            
            result.setPic( comp.encode( left.getPic()) );
            right.setPic( comp.decode( result.getPic() ) );
        }
      
    }
    
    // Add an ImageSimplifier to both the list of simplifiers installed and the JComboBox
    // used to select the desired algorithm
    public void addSimplifier( String label, ImageSimplifier simp ) {
        simplifiers = new SimplifierList( label, simp, simplifiers );
        methods.addItem( label );
    }

    public static void main(String[] paramArrayOfString)
    {
        new DualImageViewer();
    }
    
}



