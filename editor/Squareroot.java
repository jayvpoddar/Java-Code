class Squareroot{
public static double squareRoot(double a){
  double y= a/2;
 double x =( y + a/y)/2;
 double diff =Math.abs(x-y);
 while (diff >= 0.0000000001){
  y= x;
  x =( y + a/y)/2;
  diff=Math.abs(x-y);
 }
 return x;
}
public static void main(String[]args){
System.out.println(squareRoot(16));
}
} 
