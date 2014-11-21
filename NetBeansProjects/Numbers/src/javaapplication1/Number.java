/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication1;

/**
 *
 * @author jayp
 */
public class Number
{
    private int _number;
    private int[] _digits;
    private Number[] _factors;
    private int _noOfDigits;
    private int _noOfFactors;

    public Number(int number)
    {
        _number=number;
        _noOfDigits=findLength();
        storeDigits();
    }
    private int findLength()
    {
//        this method finds the noofDigits of the number and returns it
        int i=0;
        while(true)
        {
            if(Math.pow(10, i+1)>_number&& Math.pow(10, i)<=_number)
            {
                return i+1;
            }
            i++;
        }          
    }
    
    private void storeDigits()
    {
//        this method stores all the digits of the number in _digits
        _digits=new int[_noOfDigits];
        
        int prevMod=0;
        
        int currentMod=0;
        
        int digit;
        for(int i=1;i<_noOfDigits+1;i++)
        {
            currentMod=(int) (_number % (Math.pow(10, i)));
            
            digit=(int)((currentMod-prevMod)/Math.pow(10, i-1));
            
            _digits[i-1]=digit;
            
            prevMod=currentMod;
        }
    }
    public void findPrimeFactors()
    {
//        this method finds and puts all the prime factors of the number into _factors
        int number=_number;
       
        _factors =new Number[number/2];
        
        int index =0;
        
        for(int i=2;i<_number;i++)
        {
            if(isPrime(i))
            {
               if(number%i==0)
               {
                   while(true)
                   {
                       if(number%i==0)
                       {
                           _factors[index++]=new Number(i);
                           number=number/i;
                       }
                       else
                       {
                           break;
                       }
                   }
               }
            }
        }
        _noOfFactors=index;    
    }
    
    private boolean isPrime(int number)
    {
        for(int i=2;i<number;i++)
        {
            if(number%i==0)
            {                
                return false;
            }
        }
        return true;
    }

    public int sumOfDigits()
    {
       int total=0;

       for(int i=0;i<_noOfDigits;i++)
       {
         total+=_digits[i];
       }
       return total;
    }
    public int sumOfDigitsOfFactors()
    {
       int sum=0;

       for(int i=0;i<_noOfFactors;i++)
       {
         sum+=_factors[i].sumOfDigits();
       }
       return sum;
    }

}
