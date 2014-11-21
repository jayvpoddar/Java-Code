/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dualimageviewer;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import squint.GUIManager;

public class DualImageViewer extends GUIManager
{
  private ImageViewer leftImageViewer = new ImageViewer();
  private ImageViewer rightImageViewer = new ImageViewer();
  private JButton btnDifference = new JButton("Show difference");
  private JButton btnMove = new JButton("Move Left -> Right");
  private JButton btnInsert = new JButton("Insert Left -> Right");

  public DualImageViewer()
  {
    createWindow(750, 500);
    contentPane.setLayout(new BorderLayout());
    JPanel localJPanel;
    (localJPanel = new JPanel()).setLayout(new GridLayout(1, 2));
    localJPanel.add(leftImageViewer, "North");
    localJPanel.add(rightImageViewer, "South");
    contentPane.add(localJPanel, "Center");
    (localJPanel = new JPanel()).add(btnDifference);
    localJPanel.add(btnMove);
    localJPanel.add(btnInsert);
    contentPane.add(localJPanel, "South");
  }

    @Override
  public void buttonClicked(JButton btnPressed)
  {
    if (btnPressed == btnDifference)
    {       
        ImageViewer diffViewer = new ImageViewer();
        diffViewer.createWindow(600, 500);
        diffViewer.setImage(new Differencer(rightImageViewer.getImage()).filter(leftImageViewer.getImage()));
    }
    if ((btnPressed == btnMove) && (leftImageViewer.getImage() != null))
    {
      rightImageViewer.setImage(leftImageViewer.getImage());
      return;
    }
    if ((btnPressed == btnInsert) &&
            (leftImageViewer.getImage() != null) &&
            (rightImageViewer.getImage() != null))
    {
       rightImageViewer.setImage(new Paster(leftImageViewer.getImage()).filter(rightImageViewer.getImage()));
    }
  }

  public static void main(String[] paramArrayOfString)
  {
    new DualImageViewer();
  }
}