class Date{
public static void printAmerican(String day,String month,int date,int year ){
System.out.print(day) ;
System.out.print(",");
System.out.print(month);
System.out.print(" ");
System.out.print(date);
System.out.print(",");
System.out.println(year);
}
public static void main(String[]args){
System.out.println("American Format:");
 printAmerican("Tuesday","December",30,2008 );
System.out.println("European Format:");
 printEuropean("Tuesday","December",30,2008 );
}
public static void printEuropean(String day,String month,int date,int year ){
System.out.print(day) ;
System.out.print(",");
System.out.print(date);
System.out.print(" ");
System.out.print(month);
System.out.print(",");
System.out.println(year);
}
}

