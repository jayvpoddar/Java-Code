/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imageviewer;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import squint.GUIManager;
import squint.SImage;

public final class Quantizer extends GUIManager
{
  private JLabel _lblImage = new JLabel("", 0);
  private JSlider _sliderQuanta = new JSlider(1, IImageConstants.MAXBRIGHTNESS, 1);
  private SImage _image;

  public Quantizer(SImage image)
  {
    createWindow(500, 400);
    _image = image;
    contentPane.setLayout(new BorderLayout());
    contentPane.add(new JScrollPane(_lblImage), BorderLayout.CENTER);
    _image.getRedPixelArray();
    
    JPanel panel = new JPanel();
    panel.add(new JLabel("" + _sliderQuanta.getMinimum()));
    panel.add(_sliderQuanta);
    panel.add(new JLabel("" + _sliderQuanta.getMaximum()));
    
    contentPane.add(panel, BorderLayout.SOUTH);
    sliderChanged();
  }

  @Override
  public final void sliderChanged()
  {
      int nQuanta = _sliderQuanta.getValue();
      _lblImage.setText("" + nQuanta);
      adjustPixelArray(nQuanta);      
  }
  //takes the desired number of shades to be used for the image  and alters the brightness values appropriately
  private void adjustPixelArray (int nQuanta)
  {
      SImage image=_image;
      int widthOfeachband=(int)Math.ceil((double)(IImageConstants.MAXBRIGHTNESS+1)/nQuanta);
      
      int[][] redPixels = image.getRedPixelArray();
      for (int x = 0; x < image.getWidth(); x++)
      {
          for (int y = 0; y < image.getHeight(); y++)
          {

              redPixels[x][y]=(widthOfeachband)*(redPixels[x][y]/(widthOfeachband));
          }
      }

      int[][] greenPixels = image.getGreenPixelArray() ;
      for (int x = 0; x < image.getWidth(); x++)
      {
          for (int y = 0; y < image.getHeight(); y++)
          {

              greenPixels[x][y]=(widthOfeachband)*(greenPixels[x][y]/(widthOfeachband));
          }
      }

      int[][] bluePixels = image.getBluePixelArray();
      for (int x = 0; x < image.getWidth(); x++)
      {
          for (int y = 0; y < image.getHeight(); y++)
          {

              bluePixels[x][y]=(widthOfeachband)*(bluePixels[x][y]/(widthOfeachband));
          }
      }

      _lblImage.setIcon(new SImage(redPixels,greenPixels,bluePixels));
  }

}