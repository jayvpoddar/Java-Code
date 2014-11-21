class Fermat
{
public static void main(String []args)
{
 checkFermat(2,3,4,5);
}

public static void checkFermat(int a,int b ,int c ,int n) 
{
  if (n <= 2)  
  {
    System.out.println ("Exponents greater than two only");
    return;
  }
 else
 {
  int x = (int)Math.pow(a,n);
  int y = (int)Math.pow(b,n);
  int z = (int)Math.pow(c,n);
  if(x+y==z)
  {
    System.out.println("Holy smokes,Fermat was wrong."); 
  }
  else
  {
   System.out.println("No,this doesn't work."); 
  }
 }
}
}
