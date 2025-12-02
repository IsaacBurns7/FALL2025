/* Written by Hyunyoung Lee for CSCE 314 Students
 * To test wildcards.
 *    and helper method to capture the wildcard.
 */
import java.util.*;
import static java.lang.System.out;

class Wildcards {

  static void fun(List<?> i) {
  //  i.set(0, i.get(0)); // compile error
    out.println("In fun: i = " + i.getClass());
    out.println("In fun: i.get(0) = " + (i.get(0)).getClass());
    funHelper( i );
  }
    
  // Helper method will have the wildcard be captured
  // through type inference.
  static private <T> void funHelper(List<T> l) {
    out.println("In funHelper: l = " + l.getClass());
    out.println("In funHelper: l.get(0) = " + (l.get(0)).getClass());
    printAll(l);
    T j = l.get(1); // this and the next line work
    out.println("T j = l.get(1); " + j);
//    int k = l.get(1); // but not this
    l.set(0, l.get(1));
    l.add(l.get(2));
//    l.add(5); // compile error
    printAll(l);
  }

  static private <T> void addHelper(List<T> l) {
    l.set(0, l.get(0));
  }

  // a method that accepts any collection l as long as it is a subtype of List
  static void printAll (List<?> l) {
   for (Object o : l) out.print(o + " "); // such simple task works
   out.println();
  }

  // a method that accepts List of any type as long as that type is Number
  // or a subtype of Number
  static double sum(List<? extends Number> list) {
    double sum = 0.0;
    for (Number n : list) sum += n.doubleValue();
    return sum;
  }

  public static <T> List<T> foo(List<T> list) {
    printAll(list); // works ok
    //list.add("Here!"); // compile error: 
              // argument mismatch; String cannot be converted to T
    return list;
  }

  public static void main(String[] args) {
    List<Integer> li = new ArrayList<Integer>();
    List<String>  ls = new ArrayList<String>(); 
    li.add(8);
    li.add(42);
    li.add(15);
    ls.add("Howdy!");
    ls.add("Y'all!");
    printAll(ls);

    List<?> aList = ls;
//    aList.add("Hi");  // compile error
    List<?> ell = foo(aList); // ok

    out.print("Before fun(li): ");
    printAll(li);
    out.println();
    out.print("Sum li = " + sum(li));
    out.println();
    fun(li);
    out.print("After fun(li): ");
    for (Integer e : li) out.print(" " + e);
    out.println();

    List<Integer> lt = new ArrayList<>();
    lt.add(5);
    List<? extends Number> ln = lt;
    out.println("lt:" + lt.getClass());
    out.println("ln:" + ln.getClass());
    //ln.add(5);  // compile-time error
    addHelper(ln);
  }
}

