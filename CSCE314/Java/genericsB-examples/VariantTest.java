/* Test for covariance and contravariance. 
   Written by Hyunyoung Lee for CSCE 314 Students
*/

import java.util.*;
import static java.lang.System.out;

public class VariantTest {

  public static void main(String[] args) {
    List<String> ls = new ArrayList<String>(); // ok
    // List<Object> lo = ls; // would cause Compile Error: incompatible types 
    List<Object> lo = new ArrayList<Object>();                                  

//    ls.add(new Object());
    lo.add(new Object()); // ok
    //lo.add(new String()); // ok
    //String s = lo.get(0); // incompatible types: 
                          //   Object cannot be converted to String
    //String s = (String) lo.get(0);  // runtime exception:
                                      // Object cannot be cast to String
//     ls.add(new Object()); // Compile Error: cannot find "add" method that
                       // accepts Object since the element is of type String 

    // test for Java arrays
    String[] strA = new String[1]; // strA is array of String with one element
    strA[0] = "Hi";
 
    Object[] objA = strA; // objA is array of Object
 
//    objA[0] = 1; // Attemp to assign an Integer to Object (so, compiles ok),
                 // but, the Object is indeed a String, so will throw 
                 // java.lang.ArrayStoreException at runtime
    /*
    Object[] objB = new Object[3];
    objB[0] = objA[0];
    objB[1] = 5;
    objB[2] = Math.PI;
    */
    Object[] objB = new Object[]{"Hi", 5, Math.PI};
    for (Object o : objB) out.print(o + " ");
    out.println();
    Number[] objN = new Number[]{(float)(4.0/3), 5, Math.PI};
    for (Object o : objN) out.print(o + " ");
    out.println();
    double s = 0.0;
    for (Number o : objN) s += o.doubleValue();
    out.println("sum = " + s);
  }
}
