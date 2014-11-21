/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author jayp
 */
public class BankDenomination
{
    private int _noGiven;
    private int _noOfDigits;
    private Note[] _notes={new Note(1000,0),new Note(500,0),new Note(100,0),new Note(50,0),new Note(20,0),new Note(10,0),new Note(5,0),new Note(2,0),new Note(1,0)};
    private int _indexOfHighestDenomination;

    class Note
    {
        int _value;
        int _noUsed;
        public Note(int denomination,int frequency)
        {
            _value=denomination;
            _noUsed=frequency;
        }
    }

    @SuppressWarnings("empty-statement")
    public BankDenomination()
    {
        _indexOfHighestDenomination=0;
    }
    public void getInput() throws IOException
    {
        InputStreamReader in =new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(in);
        _noGiven=Integer.parseInt(br.readLine());
        if (hasMoreThanDigits())
        {
            System.out.println("INVALID AMOUNT");
            System.exit(0);
        }
    }
    private boolean hasMoreThanDigits()
    {
        for (int i = 1; i < 6; i++)
        {
            if(_noGiven<Math.pow(10, i))
            {
                _noOfDigits = i;
                return false;
            }
         }
         return true;
    }
    private void convertToWords()
    {
        int number=_noGiven;
        for (int i =_noOfDigits-1; i>-1; i--)
        {
            int digit=number/(int)Math.pow(10, i);
            number%=(int)Math.pow(10,i);
            convertToDigits(digit);
        }
        System.out.println("");
    }
    private void convertToDigits(int digit)
    {
        switch(digit)
        {
            case 0:System.out.print("ZERO ");break;
            case 1:System.out.print("ONE ");break;
            case 2:System.out.print("TWO ");break;
            case 3:System.out.print("THREE ");break;
            case 4:System.out.print("FOUR ");break;
            case 5:System.out.print("FIVE ");break;
            case 6:System.out.print("SIX ");break;
            case 7:System.out.print("SEVEN ");break;
            case 8:System.out.print("EIGHT ");break;
            case 9:System.out.print("NINE ");break;

        }
    }
    private void convertToDenomination()
    {
        int number=_noGiven;
        if(_indexOfHighestDenomination>0)
        {
            _notes[_indexOfHighestDenomination-1]._noUsed=0;
        }
        for (int i = _indexOfHighestDenomination; i < _notes.length; i++)
        {
            _notes[i]._noUsed=number/_notes[i]._value;
            number-=(_notes[i]._value*_notes[i]._noUsed);
        }
    }
    public static void main(String[] args) throws IOException
    {
        BankDenomination bd=new BankDenomination();
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        bd.getInput();
        bd.convertToWords();
        while(bd._indexOfHighestDenomination!=9)
        {
            bd.convertToDenomination();
            bd.printDenomination();
            if(bd._indexOfHighestDenomination!=8)
            {
                System.out.println("Do you want lower Denomination?(Y/N)");
                String answer=br.readLine();
                if(answer.equals("N"))
                {
                    break;
                }
            }
            bd._indexOfHighestDenomination++;
        }
    }
    private void printDenomination()
    {
        int totalNotes=0;
        String output="DENOMINATION : ";
        for (int i =_indexOfHighestDenomination; i < _notes.length; i++)
        {
            if(_notes[i]._noUsed>0)
            {
                if((!output.equals("DENOMINATION : ")))
                {
                    output+=String.format("%15s", "");
                }
                output+=_notes[i]._value;
                output+=String.format("%6s","x");
                output+=String.format("%5s", _notes[i]._noUsed);
                output+=String.format("%3s","=");
                output+=String.format("%8s", (_notes[i]._value* _notes[i]._noUsed));
                output+="\n";
                totalNotes+=_notes[i]._noUsed;
            }
        }
        output+=String.format("%15s", "TOTAL");
        output+="="+_noGiven+"\n";
        output+="TOTAL NUMBER OF NOTES = "+totalNotes;
        System.out.println(output);
    }
}
