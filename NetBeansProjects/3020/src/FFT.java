
/**
 *
 * @author harshanori
 */
public class FFT
{
    static Complex results[]=new Complex[8];
     
    public static void main(String[] args)
    {
        results=new Complex[8];
        Complex[] x = {new Complex(1, 0),new Complex(1, 0),new Complex(1, 0),new Complex(1, 0),new Complex(-1, 0),
            new Complex(-1, 0),new Complex(-1, 0),new Complex(-1, 0)};
        fastFourierTransform(x);
        arrayPrinter(x);
        
        results=new Complex[8];
        Complex[] y={new Complex(0, 0),new Complex(.5, 0),new Complex(1, 0),new Complex(.5, 0),new Complex(0, 0)
                ,new Complex(-.5, 0),new Complex(-1, 0),new Complex(-.5, 0)};
        fastFourierTransform(y);
        arrayPrinter(y);
    }
    
    private static void arrayPrinter(Complex[] array)
    {
        System.out.print("[ ");
        for (int i = 0; i < array.length; i++)
        {
            System.out.print(array[i]+" , ");

        }
        System.out.println("\b\b]\n");
    }
    
    public static void fastFourierTransform(Complex[] input)
    {
        int j=0;
        for(int i=0; i<3; i+=2,j+=2)   //butterfly stage 1, part 1
        {
          
            butterfly(input, 0, i, i+4,j);
                
        }
        for(int i=1;i<4;i+=2,j+=2)    //butterfly stage 1, part 2 
        {
           
            butterfly(input, 0, i, i+4,j);
        }
        for (int i = 0; i < input.length; i++)  //butterfly stage 1, part 3
        
        {
            input[i]=results[i];    //rewrite results to input
        }
        int k;
         
        for (j = 0,k=0; j < 6; j++,k+=2)    //butterfly stage 2
        {
            if(j!=2&&j!=3)
            {
              
               butterfly(input,k,j,j+2,-1);
            }
        }
        
        for (j = 0,k=0; j < 4;j++,k++)  //butterfly stage 3
        {
         
            butterfly(input,k,j,j+4,-1);
        }
        
    }
   protected static void butterfly(Complex[] input,double k,int element2,int element1,int stageCheck)
    {
        Complex wk=new Complex(0,(-2.0*Math.PI*k/8.0));
        wk=wk.exp();
        
        Complex first;
        first=input[element2].plus(wk.times(input[element1]));
        Complex second;
        second=input[element2].minus(wk.times(input[element1]));
        if(stageCheck>-1)
        {
            results[stageCheck]=first;
        results[stageCheck+1]=second;
        }
        else
        {
            input[element2]=first;
            input[element1]=second;
        }
        
    }
}
