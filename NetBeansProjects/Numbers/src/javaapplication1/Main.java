/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication1;

import java.io.*;

/**
 *
 * @author jayp
 */
public class Main
{    
    public static void main(String[] args) throws IOException
    {
       InputStreamReader in =new InputStreamReader(System.in);
       BufferedReader br = new BufferedReader(in);
       System.out.println("Enter a number.");
       int num =Integer.parseInt(br.readLine());
       Number number = new Number(num);
       number.findPrimeFactors();
       
       if(number.sumOfDigits()==number.sumOfDigitsOfFactors())
       {
           System.out.println("Smith Number");
       }
       else
       {
           System.out.println("Not Smith Number");
       }
    }

}
