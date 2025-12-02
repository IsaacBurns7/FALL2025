/* Example for type erasure: 
   Written by Hyunyoung Lee for CSCE 314 Students
*/

import java.util.*;
import static java.lang.System.out;

class TypeErasureTest {
  private List<String> ls = new ArrayList<String>(); 
  private List<Integer> li = new ArrayList<Integer>();
  
  boolean checkObjectType() {
    return ls.getClass() == li.getClass();
  }

  public static void main(String[] args) {
    out.println((new TypeErasureTest()).checkObjectType()); // true

    TypeErasureTest et = new TypeErasureTest();
    out.println(et.ls.getClass());
    out.println(et.li.getClass());
  }
}

/*
class Foo<T> {
  public void bar(T x) {
     T t = new T();                       // error
     if(x instanceof T) {}                // error
  }
  public static void static_bar(T t) {}   // error
  public static List<T> l;                // error
}
*/

