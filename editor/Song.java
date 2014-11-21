class Song{
public static void song(int x){
if(x==0)
{
 System.out.println("No bottles of beer on the wall,");      
 System.out.println("no bottles of beer,");  
 System.out.println(" ya’ can’t take one down,");  
 System.out.println("ya’ can’t pass it around,");  
 System.out.println("’cause there are no more bottles of beer on the wall!");  
System.out.println("");  
}
else
{
 System.out.println(x+" bottles of beer on the wall,");  
 System.out.println(x+" bottles of beer,");  
 System.out.println("ya’ take one down,");  
 System.out.println(" ya’ pass it around,");  
 System.out.println("");  
 song(x-1);
}
}
public static void main(String[]args){
song(99);
}
}


 
 
