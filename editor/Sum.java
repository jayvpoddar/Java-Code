class Sum{
public static int methOne (int m, int n) {
 System.out.println(m +","+ n);
 if (m == n) {
    return n;
  } else {
    return m + methOne (m+1, n);
  }
}
public static int methTwo (int m, int n) {
 System.out.println(m +","+ n);
 if (m == n) {
    return n;
  } else {
    return n * methTwo (m, n-1);
  }
}
public static void main (String[]args){
 methOne(1,2);
 methTwo(1,2);
}
