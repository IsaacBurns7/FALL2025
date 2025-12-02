
/** CSCE 314 Programming Languages 
    Written by Hyunyoung Lee for CSCE 314 Students
    Example for 
    (1) overriding Object.equals() and Object.hashCode()
    (2) and using Point.equals() to check the value equality
    (3) substitutability by subtyping losing the type information, 
        thus type cast needed
    (4) generic method move() that solves the problem in (3)
*/

import static java.lang.System.out;

class Point { 
  public int x; 
  public int y; 
  public static <T extends Point> T move(T a, int dx, int dy) {
  //public static <T> T move(T a, int dx, int dy) {
    a.x += dx; 
    a.y += dy; 
    return a; 
  }
  @Override
  public boolean equals(Object o) {
    return this.x == ((Point)o).x && this.y == ((Point)o).y;
  }
  @Override
  public int hashCode() {
    return x+y; // bad hash code, but serves the purpose in this example
  }
  @Override
  public String toString() {
    return ("(" + x + ", " + y + ")");
  }
}

class ColorPoint extends Point { 
  public int color; 
  @Override
  public String toString() {
    return ("(" + x + ", " + y + ", color= " + color + ")");
  }  
}

class Main {
  public static void main(String[] args)
  {
    Point p = new Point();
    p.x = 5; p.y = 5;
    Point t = Point.move(p, 3, 3);
    out.println("t = " + t);
    Point p1 = new Point();
    p1.x = 8; p1.y = 8;
    out.println("p1 = " + p1);
    out.println("t == p1 ? " + (t == p1)); // reference comparison
    out.println("t.equals(p1) ? " + t.equals(p1)); // value comparison

    Point cp = new ColorPoint();
    cp.x = 1; cp.y = 1;
    // cp.color = 0; // compile error because cp's type is Point
    ColorPoint cp1 = (ColorPoint) cp; // type cast needed
    cp1.color = 42;  // works
    cp1 = Point.move(cp1, 6, 6); // now this works! 
    // cp1 = (ColorPoint) Point.move(cp, 6, 6); // type cast needed
    out.println("cp = " + cp);
    out.println("cp1 = " + cp1);
  }
}
