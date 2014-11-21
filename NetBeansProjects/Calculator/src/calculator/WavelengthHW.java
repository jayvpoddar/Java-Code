/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author jaypoddar
 */
public class WavelengthHW {

    /**
     * @param args the command line arguments
     */
    final static double eMass=9e-31;
    final static double c=3e8;
    final static double h=6.63e-34;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        double input=0;
        while(input!=-1)
        {
            System.out.println("Enter the KE:");
            input=Double.parseDouble(br.readLine());
            findAns(input);
        }
        
    }
    public static void findAns(double ke)
    {
        ke*=1.602e-19;
        double p=Math.sqrt((ke*ke)+2*ke*eMass*c*c);
        p/=c;
        double wavLen=h/p;
        
        System.out.println("The momentum of the particle is: "+p);
        System.out.println("The wavelength of the particle is: "+wavLen);
    }
}
