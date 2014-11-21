/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fb;

/**
 *
 * @author jaypoddar
 */


/*
 * There are K pegs. Each peg can hold discs in decreasing order of 
 * radius when looked from bottom to top of the peg. There are N discs which have radius 1 to N; 
 * Given the initial configuration of the pegs and the final configuration of the 
 * pegs, output the moves required to transform from the initial to final configuration. 
 * You are required to do the transformations in minimal number of moves.
 * A move consists of picking the topmost disc of any one of the pegs and placing it on top of anyother peg.
 * At anypoint of time, the decreasing radius property of all the pegs must be maintained.
 * 
 * 
 * Constraints:
 * 1<= N<=8
 * 3<= K<=5
 * 
 * 
 * Input Format:
 * N K
 * 2nd line contains N integers.
 * Each integer in the second line is in the range 1 to K where the i-th integer denotes the peg to which disc of
 * radius i is present in the initial configuration.
 * 3rd line denotes the final configuration in a format similar to the initial configuration.
 * 
 * Output Format:
 * The first line contains M - The minimal number of moves required to complete the transformation.
 * The following M lines describe a move, by a peg number to pick from and a peg number to place on.
 * If there are more than one solutions, it's sufficient to output any one of them. 
 * You can assume, there is always a solution with less than 7 moves and the initial confirguration will not be same as the final one.
 */
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Solution 
{
    int n;
    int k;
    String moves;
    int[] intPositions;
    int[] finPositions;
    public Solution()throws Exception
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer setup=new StringTokenizer(br.readLine());
        n=Integer.parseInt(setup.nextToken());
        k=Integer.parseInt(setup.nextToken());
        intPositions=new int[n];
        StringTokenizer initial=new StringTokenizer(br.readLine());
        int index=0;
        while (initial.hasMoreTokens())
        {
            intPositions[index]=Integer.parseInt(initial.nextToken());
            index++;
        }
        StringTokenizer fin=new StringTokenizer(br.readLine());
        index=0;
        while (fin.hasMoreTokens())
        {
            finPositions[index]=Integer.parseInt(fin.nextToken());
            index++;
        }
    }
    public void solve()
    {
        int k1=1;
        int k2=2;
        moves="";
        int movLen=0;
        if(swapDisks(k1,k2))
        {
            moves+=k1+" "+k2;
            k2++;
            movLen++;
        }
        else
        {
            moves=moves.substring(0, moves.length()-3);
        }
        while(Arrays.equals(intPositions, finPositions))
        {
            if(swapDisks(k1,k2))
            {
                moves+='\n'+k1+" "+k2;
                movLen++;
                k2++;
                if(k2>=k)
                {
                    
                }
            }
            if(movLen>=7)
            {
                moves=moves.substring(n, k1)
            }
        }
        
    }
    private boolean pegHasSpace(int pegNum,int radius)
    {
        for(int i=0;i<radius;i++)
        {
            if(intPositions[i]==pegNum)
            {
                return false;
            }
            
        }
        return true;
    }
    private boolean swapDisks(int peg1,int peg2)
    {
        int radius=findPeg(peg1);
        if(pegHasSpace(peg2, radius))
        {
            intPositions[radius]=peg2;
            return true;
        }
        return false;
    }
    private int findPeg(int peg)
    {
        int index=0;
        for(int val:intPositions)
        {
            if (val==peg)
            {
                return index;
            }
            index++;
        }
        return -1;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
}
