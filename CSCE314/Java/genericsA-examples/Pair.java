/** Example code for:
    generics: parametric polymorphism
    Written by Hyunyoung Lee for CSCE 314 Students
*/

public class Pair<T, U> { 
  private T a; private U b; 
  public Pair(T t, U u) { a = t; b = u; } 
  public T getFirst()  { return a; } 
  public U getSecond() { return b; }

  @Override
  public String toString() { // String representation of Pair
    return "(" + a + ", " + b + ")";
  }

  public static void main(String args[]) {
    // use command line arguments
    if (args.length < 2) { // check if two arguments are provided
      System.out.println("Usage: java Pair <int> <float>");
      System.out.println("E.g. java Pair 2 3.1");
    } else { // at least two command line arguments are provided 
      int i   = Integer.parseInt(args[0]);
      float f = Float.parseFloat(args[1]);
      Pair<Integer, String> p1 = new Pair<>(i, "Two"); // line 1
      Pair<Integer, Float>  p2 = new Pair<Integer, Float>(i, f); // line 2
      Pair<Integer, String> p3 = new Pair<Integer, String>(i, "hi"); // line 3
      Pair<Integer, Float>  p4 = new Pair(i, f);  // line 4
      //Pair<Integer, Float>  p4 = new Pair<>(i, "Hi");  // line 4 corrected
                                             //will cause compile-time error
      System.out.println(p1);
      System.out.println(p2);
      System.out.println(p3);
      System.out.println(p4);
      //Float f1=p4.getSecond();
    }
  }
} 

