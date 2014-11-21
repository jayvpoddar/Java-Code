class Triangle
{
  public static void isTriangle(int x,int y,int z)
  {
    if (x+y<=z)
     {
       System.out.println("False");
     }
    else if(y+z<=x)
     {
       System.out.println("False");
     } 
    else if(x+z<=y)
     {
       System.out.println("False");
     } 
    else
     {
       System.out.println("True");
     }
  }
  public static void main(String[]args)
  {
     isTriangle(3,4,5);
  } 
} 
