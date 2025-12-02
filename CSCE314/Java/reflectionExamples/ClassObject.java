
/* Written by Hyunyoung Lee for CSCE 314 Students
   Example for obtaining class object
*/

//import java.lang.reflect.*;
//import static java.lang.System.out;
//import static java.lang.System.err;

class yourClass {}

public class ClassObject {
  public static void main(String[] args) {
    yourClass mc = new yourClass();
    //Class cB = yourClass.getClass();  // error
    Class cA = mc.getClass();
    //Class cB = mc.class;  // error
    //Class cB = yourClass.class; // correct
    Class cC = null;
    try { cC = Class.forName("yourClass");
    } catch (ClassNotFoundException e) {}
    System.out.println(cA);
   // System.out.println(cB);
    System.out.println(cC);

    
    System.out.println("int.class1 = " + int.class);
    System.out.println("int.class2 = " + Integer.TYPE);
    try {
    System.out.println("int.class3 = " + Class.forName("java.lang.Integer"));
    } catch (ClassNotFoundException e) {
      System.out.println("No information by Class.forName(\"Integer\")");
    }
    
    System.out.println("String.class1 = " + String.class);
//    System.out.println("String.class2 = " + String.TYPE);
    try {
    System.out.println("String.class3 = " + Class.forName("java.lang.String"));
    } catch (ClassNotFoundException e) {
      System.out.println("No information by Class.forName(\"String\")");
    }
    String s1 = new String();
    System.out.println("StringObject.class4 = " + s1.getClass());

    System.out.println("Super class of String = " + s1.getClass().getSuperclass());
    System.out.println("Super class of Object = " + Object.class.getSuperclass());

  }
}

