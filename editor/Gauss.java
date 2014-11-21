class Gauss{
public static double gauss(double x, int n){
 if(x==0){
  return 1;
 }
 double i = 1;
 double res = 1;
 double cof = 1;
 double fac = 1;
 double sign= -1;
 while(n >= 1){
  cof = 1+i;
  res = res + cof * x/fac*sign;
  i = i+1;
  n=n-1;
  x=x*x;
  fac=fac*i; 
  sign = sign * -1;
 }
 return res;
}

public static void main(String[]args){
 System.out.println(gauss(1.0,1000));
 System.out.println(Math.exp(-1.0));
}
}
/*e−x = 1 − 2x + 3x2 /2! − 4x3 /3! + 5x4 /4! − 
(−1)i (i + 1)xi /i!*/












/*public static void check(double x){
 double a = Math.exp(x);
 double b = gauss(x,1000);
 System.out. println(x +""+ ""+ a +""+""+ b);
}*/
