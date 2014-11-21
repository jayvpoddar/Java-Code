import java.util.Scanner;

public class LineFilter 
{
        public static void main(String[] args) 
        {

            Scanner myScan = new Scanner(System.in);
            String pattern;
            
            if (args.length < 1) 
            {
                pattern = "";
            } 
            else 
            {
                pattern = args[0];
                pattern = pattern.replace("\"", "");
            }
            
            while (myScan.hasNext()) 
            {
                String nextLine = myScan.nextLine();
                if (nextLine.contains(pattern)) {
                    System.out.println(nextLine);
                }
            }
        }
    }
