/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 *
 * @author jayp
 */
public class StringProgramWithUtilMap
{
    private HashMap<String, Integer> mapWordsToFrequency;
    private OrderedTree _wordListByFrequency;
    private Word[] _words;
    private int _noOfWords;

    public StringProgramWithUtilMap()
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
        mapWordsToFrequency =new HashMap<String, Integer>();
         for (int i = 0; i < _noOfWords; i++)
         {
            String word =str.nextToken();
            if(mapWordsToFrequency.containsKey(word))
            {
                Integer frequency = mapWordsToFrequency.get(word);
                ++frequency;
                mapWordsToFrequency.put(word, frequency);
            }
            else
            {
                mapWordsToFrequency.put(word, 1);
            }

        }
        _wordListByFrequency=new OrderedTree();
        Set<Map.Entry<String, Integer>> wordSet = mapWordsToFrequency.entrySet();
        for(Map.Entry<String, Integer> entry : wordSet)
        {
            _wordListByFrequency.putByFrequency(entry.getValue(), entry.getKey());
        }
        _words=_wordListByFrequency.inOrderTraversal();

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
        StringProgramWithTwoTrees str=new StringProgramWithTwoTrees();
        str.getInputFromFile();
//        System.out.println("Total no. of Words:"+str._noOfWords);
        long timeTaken=System.currentTimeMillis()-beginTime;
//        str.printList();

        System.out.println("This Program uses Hash Maps");
        return timeTaken;

    }
    private void printList()
    {
        StringBuffer output=new StringBuffer(String.format("%-20s","Word"));
        output.append("Frequncy\n");
        for (int i = 0; i < _words.length; i++)
        {
            output.append(String.format("%-20s",_words[i]._word));
            output.append( _words[i]._frequency+"\n");
        }
        System.out.println(output);
    }
    
}
