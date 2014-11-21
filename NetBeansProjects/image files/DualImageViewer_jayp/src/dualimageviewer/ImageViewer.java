package dualimageviewer;


import java.awt.*;
import squint.*;
import javax.swing.*;
import java.io.File;

/*
 * ImageViewer - Display an image with controls to modify resolution
 */
public class ImageViewer extends GUIManager {
    private final int WINDOW_WIDTH  = 500, WINDOW_HEIGHT = 600;
    
    // A slider to control the number of brightness levels used
    private JSlider sldLevels = new JSlider( 1, IImageConstants.MAXBRIGTHNESS+1, IImageConstants.MAXBRIGTHNESS+1 );
    private JSlider sldBlockSize = new JSlider(1, 16, 1);
    
    private JLabel lblBlockSize = new JLabel( "Block size: " + sldBlockSize.getValue() );
    private JLabel lblLevels = new JLabel( "Brightness levels: " + sldLevels.getValue());
    
    // Button to request loading a new image
    private JButton btnLoadImage = new JButton( "Load Image" );
    // Button to request saving of image
    private JButton btnSaveImage = new JButton( "Save Image" );
    // JPanel for showing the "Load Image" and "Save Image" buttons
    private JPanel paneLoadSave = new JPanel();
    
    // Dialog box used to select an image to load
    private JFileChooser chooser = new JFileChooser( new File( System.getProperty("user.dir")) );
  
    // Button to "Show Histogram"
    private JButton btnHistogram = new JButton( "Show Histogram" );
    // Button to "Expand Range"
    private JButton btnExpandRange = new JButton( "Expand Range" );
    // JPanel for showing the "Show Histogram" and "Expand Range" buttons
    private JPanel paneExtra = new JPanel();
    
    // Pane for displaying all the UI elements for ImageViewer
    JPanel panelControl = new JPanel();
    
    
    // The underlying picture to be displayed
    private SImage imgOriginal;
    
    // The image displayed after requested transformation of "picture"
    private SImage imgDisplayed;
    
    // Label used to display "displayed"
    private JLabel lblImage = new JLabel( "", SwingConstants.CENTER );

   
    public ImageViewer() {
      
      this.contentPane.setLayout( new BorderLayout() );
      this.contentPane.add( new JScrollPane( lblImage ), BorderLayout.CENTER );
      
      panelControl.setLayout( new GridLayout( 4, 1) );
      
      JPanel paneBlockSize = new JPanel();
      paneBlockSize.add(lblBlockSize);
      paneBlockSize.add(sldBlockSize);
      panelControl.add(paneBlockSize);
      
      JPanel paneLevels = new JPanel();
      paneLevels.add( lblLevels );
      paneLevels.add( sldLevels );
      panelControl.add( paneLevels );
      
      // Add the "Load Image" and "Save Image" buttons to the pane
      paneLoadSave.add( btnLoadImage );
      paneLoadSave.add( btnSaveImage );
      
      // Add the "Show Histogram" and "Expand Range" buttons to the panel
      paneExtra.add( btnHistogram);
      paneExtra.add( btnExpandRange);

      panelControl.add( paneLoadSave );
            
      this.contentPane.add( panelControl, BorderLayout.SOUTH );
    }
    
    // Adjust image display when slider is moved
    @Override
    public void sliderChanged(JSlider slider)
    {
        if(slider == sldBlockSize)
        {
            lblBlockSize.setText( "Block levels: " + sldBlockSize.getValue() );
            if ( imgOriginal != null )
            {
                imgDisplayed = adjust( imgOriginal );
                imgDisplayed = adjustBlocks( imgDisplayed );
                lblImage.setIcon( imgDisplayed );
            }
        }
        else if(slider == sldLevels)
        {
            lblLevels.setText( "Brightness levels: " + sldLevels.getValue() );
            if ( imgOriginal != null ) 
            {
                imgDisplayed = adjustBlocks( imgOriginal );
                imgDisplayed = adjust( imgDisplayed );
                lblImage.setIcon( imgDisplayed );
            }
        }
    }
        

    
    // Load an image when the button is clicked
    @Override
    public void buttonClicked( JButton which ) {
        if ( which == btnLoadImage ) 
        {
            if ( chooser.showOpenDialog( this ) == JFileChooser.APPROVE_OPTION ) {
                setImage( new SImage( chooser.getSelectedFile().getAbsolutePath() ) );
            }
        } 
        else if (which == btnHistogram)
        {            
            Histogram hist = new Histogram(imgDisplayed.getPixelArray());
            new DisplayHistogram(hist);
        }
        else if (which == btnExpandRange)
        {
            imgDisplayed = new Expander().filter(imgOriginal);
            lblImage.setIcon( imgDisplayed );
        }
        else if(which == btnSaveImage)
        {
          if (chooser.showSaveDialog(this) == 0)
          {
            imgDisplayed.saveAs(chooser.getSelectedFile().getAbsolutePath());
            return;
          }
        }

    }

    private SImage adjustBlocks(SImage img)
    {
        SImage blocked = new Blocker(sldBlockSize.getValue()) .filter(img );
        return blocked;
    }
    
    // Change the picture to be displayed
    private void setPic( SImage imgNew ) {
        imgOriginal = imgNew;
        imgDisplayed = adjust( imgOriginal );
        lblImage.setIcon( imgDisplayed );
    }
    
    public void setImage(SImage imgNew)
    {
        setPic(imgNew);
        if(paneExtra != null) 
        {
            panelControl.add(paneExtra);
        }
    }
    
    public SImage getImage()
    {
        return this.imgDisplayed;
    }
    
    // Apply desired transformation to image before displaying it
    private SImage adjust( SImage img )
    {
        SImage quantized = new Quantizer( sldLevels.getValue() ).filter(img );
        return quantized;
    }
}
