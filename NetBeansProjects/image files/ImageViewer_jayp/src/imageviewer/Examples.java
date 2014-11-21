/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imageviewer;

import java.awt.*;
import squint.*;
import javax.swing.*;

/**
 *
 * @author saukap
 */
public class Examples extends GUIManager
{
    
  private JLabel _lblImage = new JLabel("", 0);
  private JSlider _slider1 = new JSlider(0, 255, 180);
  private SImage _image;

  public Examples()
  {
    createWindow(500, 400);
    contentPane.setLayout(new BorderLayout());
    contentPane.add(new JScrollPane(_lblImage), BorderLayout.CENTER);
    
    JPanel panel = new JPanel();
    panel.add(new JLabel("" + _slider1.getMinimum()));
    panel.add(_slider1);
    panel.add(new JLabel("" + _slider1.getMaximum()));
    
    contentPane.add(panel, BorderLayout.SOUTH);   
    sliderChanged();
  }

  @Override
  public final void sliderChanged()
  {
      int sliderValue = _slider1.getValue();
      _lblImage.setText("" + sliderValue);
       drawGradient(sliderValue);
  }
  
  /**
   * Draw a rectangular block
   * 
   * @param brightNess : The brightness with which we should fill the block
   */
  private void drawBox(int brightNess)
  {
      SImage image = new SImage(256, 100, brightNess);
      int[][] pixels = image.getPixelArray();
      
      for (int x = 0; x < image.getWidth(); x++)
      {
          pixels[x][0] = 0;
          pixels[x][image.getHeight()]=0;
      }
      for (int y = 0; y < image.getHeight(); y++)
      {
          pixels[0][y] = 0;
          pixels[image.getWidth()][y]=0;

      }



      _lblImage.setIcon(new SImage(pixels));
  }
  private void drawGradient(int sliderValue)
  {
       SImage image = new SImage(256, 100,256);
      int[][] pixels = image.getPixelArray();

      for (int x = 0; x < image.getWidth(); x++)
      {
          for (int y = 0; y < image.getHeight(); y++)
          {
              pixels[x][y] =x*sliderValue/IImageConstants.MAXBRIGHTNESS;
             
          }
      }

      _lblImage.setIcon(new SImage(pixels));
  }


  
}
