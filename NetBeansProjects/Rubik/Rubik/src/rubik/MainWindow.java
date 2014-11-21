/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rubik;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;


// Rubik cube colors
//
// Up - White
// Right - Blue
// Front - Red
// Down - Yellow
// Left - Green
// Back - Orange

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// A simple GUI example to demonstrate how to drive the Rubik code

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation, company or business for any purpose whatever) then
 * you should purchase a license for each developer using Jigloo. Please visit www.cloudgarden.com for details. Use of
 * Jigloo implies acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR THIS MACHINE, SO
 * JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class MainWindow extends javax.swing.JFrame
{

	// +++++++++++++These variables used only in the GUI-interface+++++++++++++++++++++++++++++++++++++++++++++++++++++++
	private static final long serialVersionUID = 1L;
	private JButton[][] facelet = new JButton[6][9];
	private final JButton[] colorSel = new JButton[6];
	private final int FSIZE = 45;
	private final int[] XOFF = { 3, 6, 3, 3, 0, 9 };// Offsets for facelet display
	private final int[] YOFF = { 0, 3, 3, 6, 3, 3 };
        // The color are listed in order:    UP         RIGHT     FRONT         DOWN         LEFT          BACK
        private String[] faceNames = { "Up", "Right", "Front", "Down", "Left", "Back" };
	private final Color[] COLORS = { Color.white,Color.green, Color.red, Color.yellow, Color.blue , Color.orange };
	private JButton buttonRandom;
    private JButton reset;
	private JButton Solve;
    private JButton inputCube;
    private JButton saveCube;
    private JFileChooser chooser = new JFileChooser( new File(System.getProperty("user.dir")) );

	private Color curCol = COLORS[0];
	boolean useSeparator = true;
	boolean showString = false;
        private CubieCube currentCube;

        private JButton[] buttonsClockWise;
        private JButton[] buttonsAntiClockWise;

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainWindow inst = new MainWindow();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public MainWindow()
    {
		super();
		initGUI();
	}
    public void updateCubeDisplay()
    {
        String strRep = currentCube.toFaceCube().toString();
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 9; j++) {
                switch (strRep.charAt(9 * i + j)) {
                case 'U':
                    facelet[i][j].setBackground(COLORS[0]);
                    break;
                case 'R':
                    facelet[i][j].setBackground(COLORS[1]);
                    break;
                case 'F':
                    facelet[i][j].setBackground(COLORS[2]);
                    break;
                case 'D':
                    facelet[i][j].setBackground(COLORS[3]);
                    break;
                case 'L':
                    facelet[i][j].setBackground(COLORS[4]);
                    break;
                case 'B':
                    facelet[i][j].setBackground(COLORS[5]);
                    break;
                }
            }

    }

    private void setupOutputSave()
    {
        saveCube=new JButton("Save Cube");
        getContentPane().add(saveCube);
        saveCube.setBounds(422, 64, 114, 22);
        saveCube.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                try {
                    saveCubeInFile();
                } catch (IOException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }


        });
    }
    private void saveCubeInFile() throws IOException 
    {
        FileWriter fw = new FileWriter(getChosenFile());
        fw.write(getCube());
        fw.close();
    }

    private void setupDefaultCube()
    {
        reset = new JButton();
        getContentPane().add(reset);
        reset.setBounds(552, 17, 114, 22);
        reset.setText("Reset Cube");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt)
            {
                currentCube = Tools.resetCube();
                updateCubeDisplay();
            }
        });
    }

    private void setupCubeInput()
    {
        inputCube = new JButton("Input Cube");
        getContentPane().add(inputCube);
        inputCube.setBounds(552, 64, 114, 22);
        inputCube.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                try
                {
                    setCube();
                }
                catch (Exception ex)
                {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
}
    void setCube() throws Exception
    {
       BufferedReader br=new BufferedReader(new FileReader(getChosenFile()));
       currentCube= new FaceCube(br.readLine()).toCubieCube();
       updateCubeDisplay();
    }
    private File getChosenFile()
    {
        if ( chooser.showOpenDialog(this) ==JFileChooser.APPROVE_OPTION)
        {
          return chooser.getSelectedFile();
        }
        return null;
    }

    private void setupRandomCube()
    {
        buttonRandom = new JButton();
        getContentPane().add(buttonRandom);
        buttonRandom.setBounds(422, 17, 114, 22);
        buttonRandom.setText("Scramble");
        buttonRandom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt)
            {
                currentCube = Tools.randomCube();
                updateCubeDisplay();
            }
        });
    }

    private void setupFacelets()
    {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {
                facelet[i][j] = new JButton();
                getContentPane().add(facelet[i][j]);

                // Set the background color of the facelet
                if(j == 4)
                {
                    facelet[i][j].setBackground(COLORS[i]);
                // Write out the face name on each facelet in the center
                    facelet[i][j].setText(faceNames[i].charAt(0) + "");
                }
                else {
                    facelet[i][j].setBackground(Color.gray);
                }

                facelet[i][j].setRolloverEnabled(false);
                facelet[i][j].setOpaque(true);
                facelet[i][j].setBounds(FSIZE * XOFF[i] + FSIZE * (j % 3), FSIZE * YOFF[i] + FSIZE * (j / 3), FSIZE, FSIZE);
                facelet[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                            ((JButton) evt.getSource()).setBackground(curCol);
                    }
                });
            }
        }
    }

    private void setupColorSelection() {
        // Setup color selectors
        for (int i = 0; i < 6; i++) {
            colorSel[i] = new JButton();
            getContentPane().add(colorSel[i]);
            colorSel[i].setText(faceNames[i].charAt(0) + "");
            colorSel[i].setBackground(COLORS[i]);
            colorSel[i].setOpaque(true);
            colorSel[i].setBounds(FSIZE * (XOFF[1] + 1) + FSIZE  * i, FSIZE * (YOFF[3] + 1), FSIZE,
                            FSIZE);
            colorSel[i].setName("" + i);
            colorSel[i].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                            curCol = COLORS[Integer.parseInt(((JButton) evt.getSource()).getName())];
                    }
            });
        }
    }

    public void rotationPerformed(ActionEvent evt) {
        if(currentCube == null)
            return;

        JButton btnClicked = (JButton)evt.getSource();
        currentCube.doMoves(btnClicked.getText());


        updateCubeDisplay();
    }
    private String getCube()
    {
        StringBuffer s = new StringBuffer(54);
        for (int i = 0; i < 54; i++)
        s.insert(i, 'B');// default initialization
        for (int i = 0; i < 6; i++)
        {
            // read the 54 facelets
            for (int j = 0; j < 9; j++)
            {
                if (facelet[i][j].getBackground() == facelet[0][4].getBackground())
                    s.setCharAt(9 * i + j, 'U');
                if (facelet[i][j].getBackground() == facelet[1][4].getBackground())
                    s.setCharAt(9 * i + j, 'R');
                if (facelet[i][j].getBackground() == facelet[2][4].getBackground())
                    s.setCharAt(9 * i + j, 'F');
                if (facelet[i][j].getBackground() == facelet[3][4].getBackground())
                    s.setCharAt(9 * i + j, 'D');
                if (facelet[i][j].getBackground() == facelet[4][4].getBackground())
                    s.setCharAt(9 * i + j, 'L');
                if (facelet[i][j].getBackground() == facelet[5][4].getBackground())
                    s.setCharAt(9 * i + j, 'B');
            }
        }
        return s.toString();
    }


    private void setupButtons()
    {
        buttonsClockWise = new JButton[6];
        buttonsAntiClockWise = new JButton[6];

        int width = 50;
        int height = 30;
        int startX = 10;
        int offsetX = 5;
        int startY = 450;

        // Setup buttons for clock wise rotation
        for(int i=0; i<6; ++i) {
            JButton button = new JButton();
            buttonsClockWise[i] = button;
            button.setText(faceNames[i].charAt(0) + "");
            button.setBounds(startX + (width + offsetX) * i, startY, width, height);
            button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                rotationPerformed(ae);
            }
            });
            getContentPane().add(button);
        }

        // Change the start Y location
        startY += 40;

        // Setup buttons for anti-clock wise rotation
        for(int i=0; i<6; ++i) {
            JButton button = new JButton();
            buttonsAntiClockWise[i] = button;
            button.setText((faceNames[i].charAt(0)+"").toLowerCase());
            button.setBounds(startX + (width + offsetX) * i, startY, width, height);
            button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                rotationPerformed(ae);
            }
            });
            getContentPane().add(button);
        }

    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private void initGUI() {

        getContentPane().setLayout(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("Rubik Solver");

        // ++++++++++++++++++++++++++++++++++ Set up Solve Cube Button ++++++++++++++++++++++++++++++++++++++++++++++++++++
        Solve = new JButton("Solve Cube");
        getContentPane().add(Solve);
        Solve.setBounds(392, 460, 114, 48);
        Solve.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                solveCube(evt);
            }
        });
        //Set up Default Cube
                setupDefaultCube();
        // Set up Random Button
                setupRandomCube();

        // Set up editable facelets
                setupFacelets();

        // Setup the color selectors
                setupColorSelection();

       // Setup the move buttons
                setupButtons();
       // Setup a buttton for getting file cube input
                setupCubeInput();
       // Saves Output to file
                setupOutputSave();

        pack();
        this.setMinimumSize(new Dimension(700, 600));
    }


    // ++++++++++++++++++++++++++++++++++++ End initGUI +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    // +++++++++++++++++++++++++++++++ Generate cube from GUI-Input and solve it ++++++++++++++++++++++++++++++++++++++++
    private void solveCube(ActionEvent evt)
    {
        String cubeString = getCube();
        currentCube=new FaceCube(cubeString).toCubieCube();


        Solver solver=new Solver(currentCube);
        
        solver.solveCube();
        updateCubeDisplay();
        System.out.println(solver.getSolution());
    }
    
}