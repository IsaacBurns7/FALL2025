/* Written by Hyunyoung Lee for CSCE 314 Students 
   Example for obtaining a class object,
      accessing superclass, methods, and constructors
*/

import java.lang.reflect.*;
import static java.lang.System.out;
import static java.lang.System.err;

public class SimpleClassDesc {
  public static void main(String[] args) {
    Class<?> type = null;
    try {
      type = Class.forName(args[0]);
    } catch (ClassNotFoundException e) {
      err.println(e);
      return;
    }
   
    out.println(type.toString());
    StringBuilder sb = new StringBuilder();
    sb.append("abc");
    String s = new String(sb);
    out.println(s);

    out.print("class " + type.getName());
    Class superclass = type.getSuperclass();
    if (superclass != null)
        out.println(" <: (extends) " + 
                    //superclass.getSimpleName());
                    superclass.getCanonicalName());
    else
        out.println();

    out.println("Methods:");
    Method[] methods = type.getDeclaredMethods();
    for (Method m : methods)
//        if (Modifier.isPublic(m.getModifiers()))
        out.println("  " + m.toString());

    out.println("Constructors:");
    Constructor[] constructors = type.getDeclaredConstructors();
    for (Constructor c : constructors)
           out.println("  " + c);
  }
}

