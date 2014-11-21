class Distance{
public static void distance(double x1,double y1,double x2,double y2){ 
 double a =x2-x1;
 double b =y2-y1;
 double c =sumSquare(a,b);
System.out.println(Math.sqrt(c));
}
public static double sumSquare( double a,double b){
 double x = Math.pow(a,2);
 double y = Math.pow(b,2);
 return x+y;
}
public static void main(String[]args){
distance(3.0,6.0,7.0,3.0);
}
}
