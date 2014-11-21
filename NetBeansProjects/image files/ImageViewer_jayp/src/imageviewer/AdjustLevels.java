/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jayp
 */

package imageviewer;

import java.awt.*;
import squint.*;
import javax.swing.*;

public final class AdjustLevels extends GUIManager
{
  private JLabel _lblImage = new JLabel("", 0);
  private JSlider _sliderRed = new JSlider(0, IImageConstants.MAXBRIGHTNESS, IImageConstants.MAXBRIGHTNESS);
  private JSlider _sliderGreen = new JSlider(0, IImageConstants.MAXBRIGHTNESS, IImageConstants.MAXBRIGHTNESS);
  private JSlider _sliderBlue = new JSlider(0, IImageConstants.MAXBRIGHTNESS, IImageConstants.MAXBRIGHTNESS);
  private SImage _image;

  public AdjustLevels(SImage image)
  {
    createWindow(500, 400);
    _image = image;
    contentPane.setLayout(new BorderLayout());
    contentPane.add(new JScrollPane(_lblImage), BorderLayout.CENTER);
    
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.add(createPanel(_sliderRed, "RED"), BorderLayout.NORTH);
    panel.add(createPanel(_sliderGreen, "GREEN"), BorderLayout.CENTER);
    panel.add(createPanel(_sliderBlue, "BLUE"), BorderLayout.SOUTH);
    contentPane.add(panel, BorderLayout.SOUTH);
    
    sliderChanged();
  }

  private static JPanel createPanel(JSlider slider, String sliderText)
  {
      JPanel panel = new JPanel();
      panel.add(new JLabel(sliderText + ":   " + slider.getMinimum()));
      panel.add(slider);
      panel.add(new JLabel("" + slider.getMaximum()));
      return panel;
  }

  @Override
  public final void sliderChanged()
  {
      _lblImage.setText(_sliderRed.getValue() + "," + 
                        _sliderGreen.getValue() + "," + 
                        _sliderBlue.getValue());
      int[][] redPixelArray=_image.getPixelArray(SImage.RED);
      int[][] greenPixelArray=_image.getPixelArray(SImage.GREEN);
      int[][] bluePixelArray=_image.getPixelArray(SImage.BLUE);
      adjustPixelArray(redPixelArray,_sliderRed.getValue());
      adjustPixelArray(greenPixelArray,_sliderGreen.getValue());
      adjustPixelArray(bluePixelArray,_sliderBlue.getValue());

     _lblImage.setIcon(new SImage(redPixelArray, greenPixelArray, bluePixelArray));
  }
  //  takes the brightness values of the various colurs and makes image by changing
//  the brightness values of the pixels appropriately
  private void adjustPixelArray(int [][]pixelArray,int brightNess)
  {
      for (int x = 0; x < _image.getWidth(); x++)
      {
          for (int y = 0; y < _image.getHeight(); y++)
          {
               pixelArray[x][y] =(pixelArray[x][y]*brightNess)/IImageConstants.MAXBRIGHTNESS;
          }
      }  
  }

}