class Time
{public static void main(String[]args){
double hour ,minute,second;
hour=12;
minute=15;
second=40;
System.out.println("Number of second since midnight:");
System.out.println(hour*60*60+ minute*60+ second);
System.out.println("Number of second remaining in the day:");
System.out.println(24*60*60-(hour*60*60+ minute*60+ second)); 
System.out.println("Percentage of day passed:");
System.out.println(( ((hour*60*60+ minute*60+ second) *100)) / (24*60*60)); 
}
}
