class Myexp{
public static double myexp(double x, int n){
 if(x==0){
  return 1;
 }
 double i=1;
 double res=1;
 double deno=1;
 double num=1;
 while(n >= 1){
  deno = deno *i;
  num = num * x;
  res = res + num/deno;
  i = i+1;
  n=n-1;
 }
 return res;
}
public static void check(double x){
 double a = Math.exp(x);
 double b = myexp(x,1000);
 System.out. println(x +""+ ","+ a +""+","+ b);
}
public static void main(String[]args){
 int n= -1;
 while(n < 3){
  double x = Math.pow(10,n);
  check(x);
  n=n+1;
 }
}
}
