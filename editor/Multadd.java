class Multadd{
public static double multadd(double a,double b,double c ){
return(a*b)+c;
}
public  static void main(String[] args){
double test = multadd(1,2,3);
double trig = multadd(Math.cos(Math.PI/4.0),1.0/2.0,Math.sin(Math.PI/4.0));
double log = multadd(1.0,Math.log(10),Math.log(20));
System.out.println(yikes(2));
System.out.println(test+","+trig+","+log);
}
public static double yikes(double x){
double a = x;
double b = Math.exp(-x);
double c = Math.sqrt(1-Math.exp(-x));
return multadd(a,b,c);
}
}
