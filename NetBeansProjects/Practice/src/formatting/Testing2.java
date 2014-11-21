/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package formatting;

/**
 *
 * @author jayp
 */
public class Testing2
{
    public static void main(String[] args)
    {
         int size = 1000000;
         int [] array =new int[size];
         long beginTime=System.currentTimeMillis();
         for(int i:array)
         {
             System.out.println(i);
         }
//        for(Data i:data)
//        {
//            System.out.printf("%-10s",i.no);
//            System.out.printf("%-15s",i.name);
//            System.out.printf("%-1s\n",i.marks);
//        }
         long time1=System.currentTimeMillis()-beginTime;
         String output="";
         beginTime=System.currentTimeMillis();
//        for(Data i:data)
//        {
//            output+=String.format("%-10s",i.no);
//            output+=String.format("%-15s",i.name);
//            output+=String.format("%-1s\n",i.marks);
//        }

         for(int i:array)
         {
             output+=i+"\n";
         }
         System.out.println(output);
         long time2=System.currentTimeMillis()-beginTime;

         StringBuffer out = new StringBuffer();

         beginTime=System.currentTimeMillis();

//        for(Data i:data)
//        {
//            out.append(String.format("%-10s",i.no));
//            out.append(String.format("%-15s",i.name));
//            out.append(String.format("%-1s\n",i.marks));
//        }
         for(int i:array)
         {
             out.append(i+"\n");
         }
         System.out.println(out);
         long time3=System.currentTimeMillis()-beginTime;

         StringBuilder outp = new StringBuilder();

         beginTime=System.currentTimeMillis();

//        for(Data i:data)
//        {
//            out.append(String.format("%-10s",i.no));
//            out.append(String.format("%-15s",i.name));
//            out.append(String.format("%-1s\n",i.marks));
//        }
         for(int i:array)
         {
             outp.append(i+"\n");
         }
         System.out.println(outp);
         long time4=System.currentTimeMillis()-beginTime;

         System.out.println("Time for print statements:"+time1);
         System.out.println("Time for string way:"+time2);
         System.out.println("Time for stringBuffer way:"+time3);
         System.out.println("Time for stringBuilder way:"+time4);
     }

}
