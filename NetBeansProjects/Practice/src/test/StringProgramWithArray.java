/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

/**
 *
 * @author jayp
 */
import java.util.*;
import java.io.*;
public class StringProgramWithArray
{   
    private Word[] _words;
    private int _noOfWords;
    private int  _noOfDifferentWords=0;



    public StringProgramWithArray() throws Exception
    {

    }
    public void getInputFromFile() throws IOException
    {
        File f2 =new File("asah the folder/asah.txt");
        FileReader fr =new FileReader(f2);
        BufferedReader br =new BufferedReader(fr);
        StringBuffer sb=new StringBuffer("");
        while(br.ready())
        {
            sb.append(br.readLine());
        }
        intiallizeWords(sb.toString());
        br.close();
    }
    public void getInputFromUser() throws IOException
    {
        System.out.println("Enter no. of senteces");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int i=Integer.parseInt(br.readLine());
        if (i<1||i>3)
        {
            System.out.println("Invalid entry");
            System.exit(0);
        }
        System.out.println("Enter sentences.");
        intiallizeWords(br.readLine());
    }
    private void intiallizeWords(String given)
    {
        StringTokenizer str=new StringTokenizer(given, " .?");
        _noOfWords=str.countTokens();
        _words=new Word[_noOfWords];
        for (int i = 0; i < _words.length; i++)
        {
            String temp = str.nextToken();
            int index= findWord(temp);
            if (index==-1)
            {
                _words[_noOfDifferentWords++]=new Word(temp,1);
            }
            else
            {
                _words[index]._frequency++;
            }
        }       
        bubbleSort();
    }

    private int findWord(String temp)
    {
        for (int i = 0; i < _noOfDifferentWords-1; i++)
        {
            if(_words[i]._word.equals(temp))
            {
                return i;
            }
        }
        return -1;
    }

    private void bubbleSort()
    {
        for (int i = _noOfDifferentWords-1; i >-1 ; i--)
        {
            for (int j = 0; j < i; j++)
            {
                if (_words[j]._frequency>_words[j+1]._frequency)
                {
                    swapElements(j,j+1);
                }
            }
        }
    }

    private void swapElements(int a, int b)
    {
        Word temp =_words[a];
        _words[a]=_words[b];
        _words[b]=temp;
    }
    public static long main(String[] args) throws Exception
    {
       
        long beginTime=System.currentTimeMillis(); 
        StringProgramWithArray str=new StringProgramWithArray();
        str.getInputFromFile();
        System.out.println("Total no. of Words:"+str._noOfWords);
        long timeTaken=System.currentTimeMillis()-beginTime;        
//        str.printList();
        System.out.println("This Program uses Arrays");
        return timeTaken;
        
    }
    private void printList()
    {
        StringBuffer output=new StringBuffer(String.format("%-20s","Word"));
        output.append("Frequncy\n");
        for (int i = 0; i < _noOfDifferentWords; i++)
        {
            output.append(String.format("%-20s",_words[i]._word));
            output.append( _words[i]._frequency+"\n");
        }
        System.out.println(output);
    }



}
