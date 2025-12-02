/** Example motivating generics: 
   written by Hyunyoung Lee for CSCE 314 Students 
*/

import java.util.*;

class GenMotiv {
  public static void main(String[] args) {
/* Part 1.  
 */
    List l = new LinkedList();    // line 1
    l.add(Integer.valueOf(7));    // line 2
    Integer x = (Integer) l.get(0);         // line 3
    String s = (String) l.get(0); // line 4
    System.out.println("x = " + x + ", s = " + s); // line 5

/* Part 2.

    List<Integer> l = new LinkedList<Integer>(); // line 6
    l.add(Integer.valueOf(7)); // line 7
    Integer x = l.get(0);      // line 8 
    String s = l.get(0);       // line 9
*/
  }
}

