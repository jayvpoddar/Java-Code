package kaprekar;

import java.io.*;
/**
 *
 * @author jayp
 */
public class KaprekarNum
{
    int p;
    int q;
    int count;
     void main() throws IOException
     {
         
         InputStreamReader in=new InputStreamReader(System.in);
         BufferedReader br = new BufferedReader(in);
         System.out.print("p =");
         p=Integer.parseInt(br.readLine());
         System.out.print("q =");
         q=Integer.parseInt(br.readLine());
         count=0;
         System.out.println("THE KAPREKAR NUMBERS ARE:");
         long startTime=System.currentTimeMillis();
         for (int n = p; n < q+1; n++)
         {
             int noOfDigits=findNoOfDigits(n);
             int rightHand=(n*n)%((int)(Math.pow(10, noOfDigits)));
             int leftHand=(n*n)/((int)(Math.pow(10, noOfDigits)));
             if((leftHand+rightHand)==n)
             {
                 System.out.print(n+",");
                 count++;
             }
         }
         System.out.println("\n");
         
         System.out.println("FREQUENCY OF KAPREKAR NUMBERS IS:"+count);
         long endTime=System.currentTimeMillis();
         System.out.println(endTime-startTime);
     }
     private int findNoOfDigits(int n)
     {
        int power=1;
        while(true)
        {
            if(((int)Math.pow(10, power))>n)
            {
                return power;
            }
            power++;
        }
    }
     public static void main(String[] args) throws IOException
     {

         KaprekarNum kn = new KaprekarNum();
         kn.main();
     }

}
