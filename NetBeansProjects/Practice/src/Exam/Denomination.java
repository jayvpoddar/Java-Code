/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author jayp
 */
public class Denomination
{
    int _noGiven;
    int _amountAvailable;
    Note[] _notes;
    int _indexOfHighestPresent;
    int _noOfDigits;



    class Note
    {
        int _value;
        int _noPresent;
        int _noUsedCurrently;

        public Note(int value, int noPresent)
        {
            _value = value;
            _noPresent = noPresent;
            _noUsedCurrently=0;
        }
    }

    public Denomination()
    {
        _notes=new Note[5];
        _notes[0]=new Note(1000, 30);
        _notes[1]=new Note(500, 100);
        _notes[2]=new Note(100, 1000);
        _notes[3]=new Note(50, 500);
        _notes[4]=new Note(10, 1000);
        _amountAvailable=(1000*30)+(500*100)+(100*1000)+(50*500)+(10*1000);
        _indexOfHighestPresent=0;
    }
    private void getInput() throws IOException
    {
        BufferedReader br= new BufferedReader(new InputStreamReader (System.in));
        _noGiven=Integer.parseInt(br.readLine());
        _noOfDigits=findNoOfDigtis();
    }
    private int findNoOfDigtis()
    {
        for (int i= 1; i < 6; i++)
        {
            if(_noGiven<Math.pow(10, i))
            {
                return i;
            }
        }
        return 6;
    }
    private void convertToWords()
    {
        int number=_noGiven;
        String[] digitInWords={"ZERO ","ONE ","TWO ","THREE ","FOUR ","FIVE ","SIX ","SEVEN ","EIGHT ","NINE "};
        for (int i = _noOfDigits-1; i < -1; i--)
        {
            int digit=number /(int) Math.pow(10, i);
            number=number%(int)Math.pow(10, i);
            System.out.print(digitInWords[digit]);
        }
        System.out.println("");
    }
     private void convertToDenomination()
     {
         int number =_noGiven;
         for (int i = 0; i < _notes.length; i++)
         {
             int numNeededToBeUsed = number/_notes[i]._value;

             if (numNeededToBeUsed<=_notes[i]._noPresent)
             {
                 _notes[i]._noUsedCurrently=numNeededToBeUsed;
             }
             else
             {
                 _notes[i]._noUsedCurrently=_notes[i]._noPresent;
             }
             _notes[i]._noPresent-=_notes[i]._noUsedCurrently;
             number-=(_notes[i]._value*_notes[i]._noUsedCurrently);
         }
         _amountAvailable-=_noGiven;
     }

     public static void main(String[] args) throws IOException
     {
         Denomination dn = new Denomination();
         while(true)
         {
             dn.getInput();
             if(dn.isInputValid())
             {
                 dn.convertToWords();
                 dn.convertToDenomination();
                 dn.printDenomination();
                 dn.resetNoUsedCurrently();
                 if(dn._amountAvailable==0)
                 {
                     System.out.println("OUT OF CASH");
                     break;
                 }
                 if(dn._notes[dn._indexOfHighestPresent]._noPresent==0)
                 {
                     System.out.println("OUT OF "+dn._notes[dn._indexOfHighestPresent]._value+" NOTES");
                     dn._indexOfHighestPresent++;
                 }
             }
         }
     }
     private void printDenomination()
     {
         int totalNotes=0;
         String output="DENOMINATION:     ";
         for(Note note:_notes)
         {
             if(note._noUsedCurrently>0)
             {
                 if(!output.equals("DENOMINATION:     "))
                 {
                     output+=String.format("%18s", "");
                 }
                 output+=String.format("%-6d%-5s%-6d%3s%7s",note._value,"x",note._noUsedCurrently,"=",(note._value*note._noUsedCurrently)+"\n");
                 totalNotes+=note._noUsedCurrently;
             }
         }
         output+=String.format("%34s%4s%-7d%1s","TOTAL","=",_noGiven,"\n");
         output+=String.format("%30s%3s%2s%-5d", "TOTAL NUMBER OF NOTES","=","",totalNotes);
         System.out.println(output);
         
     }
           

    private void resetNoUsedCurrently()
    {
        for(Note note:_notes)
        {
            note._noUsedCurrently=0;
        }
    }
    private  boolean isInputValid()
    {
        if (_noOfDigits>5)
        {
            System.out.println("Invalid Amount");
            return false;
        }
        else if(_noGiven>_amountAvailable)
        {
            System.out.println("Amount not Available");
            return false;
        }
        else if(_noGiven%10!=0)
        {
            System.out.println("Inavlid Amount");
            return  false;
        }
        return true;
    }

}
