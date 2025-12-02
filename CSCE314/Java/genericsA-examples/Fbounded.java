
/** CSCE 314 Programming Languages 
 *  Written by Hyunyoung Lee for CSCE 314 Students
 *  Example for 
 *    plain bounded quantification losing the specific type information, 
 *    thus motivating F-bounded quantification 
 */

import static java.lang.System.out;

/* Example 1: Movable
   The code below (from here..to here) will cause the following compile error: 

   incompatible types: Movable cannot be converted to T
     { return m.move(1,1); }
                 ^
     where T is a type-variable:
       T extends Movable declared in method <T>translate(T)

 * A corrected version using F-bound quantification is shown afterward.

** from here **
interface Movable { Movable move(int x, int y); }
 
class Point implements Movable { 
  public int x = 0, y = 0; 

  @Override
  public Movable move(int x, int y) {
     this.x += x;
     this.y += y;
     return this;
  }
  <T extends Movable> T translate(T m) 
  { return m.move(1,1); }
** to here **
*/

// make Movable itself parametrized
interface Movable<T> { T move(int x, int y); }
 
// F-bounded quantification
class Point implements Movable<Point> { 
  public int x = 0, y = 0; 

  @Override
  public Point move(int x, int y) {
     this.x += x;
     this.y += y;
     return this;
  }

  // F-bounded quantification
  <T extends Movable<T>> T translate(T m) 
  { return m.move(1,1); }

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


/* Example 2: EqComparable - only the correct code is shown here
 */
interface EqComparable<T> { boolean eq(T o); }

class MyInt implements EqComparable<MyInt> {
  private int i = 0;
  public void setVal(int v) { i = v; }
  public int getVal() { return i; }

  public boolean eq(MyInt n) { return i == n.getVal(); }
  
  static <T extends EqComparable<T>> boolean noteq(T t, T u)
  { return !t.eq(u); }
}

class Main {
  public static void main(String[] args)
  { // test for interface Movable
    Point mp = new Point();
    mp = mp.translate(mp);
    out.println("After translating, mp = " + mp);
    mp = mp.translate(mp);
    out.println("After translating, mp = " + mp);

    // test for interface EqComparable
    MyInt mi = new MyInt();
    mi.setVal(3);
    MyInt mj = new MyInt();
    mj.setVal(3);
    out.println("mi.eq(mj)? " + mi.eq(mj));
    out.println("mi.noteq(mj)? " + MyInt.noteq(mi, mj));
  }
}

