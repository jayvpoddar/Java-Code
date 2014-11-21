/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imageviewer;

import java.awt.*;
import java.io.File;
import javax.swing.*;
import squint.*;

/**
 *
 * @author saukap
 */

public class ImageViewer extends GUIManager {

    private JButton _btnLoadImage = new JButton("Load Image");
    private JButton _btnAdjustLevels = new JButton("Adjust Levels");
    private JButton _btnRequantize = new JButton("Requantize");
    private JButton _btnExamples = new JButton("Examples");
    private JLabel _lblImage = new JLabel("", SwingConstants.CENTER);
    private JFileChooser _fileChooser = new JFileChooser(new File(System.getProperty("user.dir")));
    private SImage _image;    

    public ImageViewer()
    {
        createWindow(500, 400);
        contentPane.setLayout(new BorderLayout());
        
        contentPane.add(new JScrollPane(_lblImage), BorderLayout.CENTER);
        
        JPanel panel = new JPanel();
        
        // Add the buttons to the panel
        panel.add(_btnLoadImage);
        
        _btnAdjustLevels.setEnabled(false);
        panel.add(_btnAdjustLevels);
        
        _btnRequantize.setEnabled(false);
        panel.add(_btnRequantize);
        
        _btnExamples.setEnabled(true);
        panel.add(_btnExamples);
        
        contentPane.add(panel, BorderLayout.SOUTH);
    }

    @Override
    public void buttonClicked(JButton jButton)
    {
        if(jButton == _btnLoadImage)
        {
            if(_fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            {
                _image = new SImage(_fileChooser.getSelectedFile().getAbsolutePath());
                _lblImage.setIcon(_image);
                _btnAdjustLevels.setEnabled(true);
                _btnRequantize.setEnabled(true);
                return;
            }
        }
        else
        {
          if(jButton == _btnAdjustLevels)
          {
              new AdjustLevels(_image);
              return;
          }
          if(jButton == _btnRequantize)
          {
              new Quantizer(_image);
              return;
          }
          if(jButton == _btnExamples)
          {
              new Examples();
              return;
          }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new ImageViewer();
    }
    
    
}
