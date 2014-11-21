class Power{
public static double power(double a,int n){
 if(n==0){
  return 1;
 }
 double y=a;
 while(n>1){
  y= y*a;
  n=n-1;
 }
 return y; 
}
public static void main(String[]args){
 System.out .println(power(4,4));
}
}
