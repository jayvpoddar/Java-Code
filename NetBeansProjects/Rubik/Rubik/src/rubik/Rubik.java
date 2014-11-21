/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rubik;

/**
 *
 * @author saukap
 */
public class Rubik {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
        String testCube= "URFDLBUDLBURFDLBURFDLBURFDLBRFDLBURFDLBURFURFDLBURFDLB";
        FaceCube test= new FaceCube(testCube);
        System.out.println(testCube.equals(test.toString()));
    }
}
