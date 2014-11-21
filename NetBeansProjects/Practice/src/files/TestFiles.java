/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package files;

import java.io.*;
/**
 *
 * @author jayp
 */
public class TestFiles
{
    public static void main(String[] args) throws IOException
    {
        testFiles();
    }
    public static void testFiles() throws IOException
    {
        File f =new File("asah the folder");        
//        System.out.println(f.mkdir());
        File f2 =new File("asah the folder/asah.txt");        
////        f2.createNewFile();
//        FileWriter fw=new FileWriter(f2);
//        fw.write("Dinesh is sitting next to me");
//        fw.close();
        FileReader fr =new FileReader(f2);
        BufferedReader br =new BufferedReader(fr);        
        StringBuffer sb=new StringBuffer("");
        while(br.ready())
        {
            sb.append(br.readLine());
        }
        System.out.println(sb);
        br.close();
    }
    
    public static void testConsole() throws IOException
    {
        Console console=System.console();
        if (console==null)
        {
            System.out.println("There is no console");
        }
        else
        {
            System.out.println("Write Password");
            char[] s=console.readPassword();
            for (int i = 0; i < s.length; i++) {
                System.out.print( s[i]);

            }
        }
        
    }

}
