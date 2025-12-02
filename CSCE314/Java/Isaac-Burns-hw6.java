
/* Skeleton provided by Hyunyoung Lee
   For CSCE 314 [Sections 200, 502] Fall 2025, Assignment 7
   Due: Friday, 11/21/2025, 11:59 p.m.
   Classes contained: Node, Node$NodeIterator, MyQueue
    
   Student Name: Isaac Burns
   UIN: 935007106
   Acknowledgements: Java Programming Language, Professor Lee's slides, Oracle docs
*/

import java.util.Iterator;
import java.util.Arrays;
import java.util.NoSuchElementException;
import static java.lang.System.out;

// Total 100 points

// class Node: 5 points
// give correct class header - given
final class Node<E> implements Iterable<E> {
  // private fields
  private E val;
  private Node<E> next;

  // (1 point) implement constructor
  public Node (E val, Node<E> node) { 
  // implement this constructor
    this.val = val;
    this.next = node;
  } 

  // (2 points) iterator() returns a NodeIterator object for this object
  @Override  
  public NodeIterator<E> iterator() {
  // implement this method and explain
    return new NodeIterator<>(this);
  }

  // (2 points) getter and setter methods for the private fields
  public E getVal() {
   // implement this method
      return this.val;
  }
  public void setVal(E v) {
   // implement this method
      this.val = v;
  } 
  public Node<E> getNext() {
   // implement this method
      return this.next;
  }
  public void setNext(Node<E> node) {
   // implement this method
      this.next = node;
  }

  //*** NodeIterator as an inner class: 10 points
  // Having NodeIterator as an inner class of Node makes sense
  // correct class header as given in the problem statement
  class NodeIterator<E> implements Iterator<E> {
    private Node<E> p;  // given

    // (1 points) constructor
    public NodeIterator (Node<E> n) { 
    // implement this constructor
        this.p = n;
    }

    // (9 points) methods to implement the Iterator interface
    //// (2 points) hasNext()
    @Override
    public boolean hasNext() {
    // implement this method and explain
        //hasNext has to check whether p is null, per java iterator specifications
        return p != null;
    }

    //// (7 points) next()
    @Override
    public E next() {
    // implement this method and explain
        //next must update p after returning the value
        if (p == null) throw new NoSuchElementException();
        E v = p.val;
        p = p.next;
        return v;
    }

  } // end of NodeIterator

  // Total 10 points for the two methods: mysum, print
    
  //// 7 points
  static double mysum (Iterable<? extends Number> n) {
    // implement this method and explain the use of the wildcard (?)
      //the wildcard states that an allowed type is-a number
      //in other words, the parameter n is covariant, which makes sense in this method
      //because we are reading, and not writing
      //you can read Numbers as Integers, but you cannot write Numbers as Integers
      double sum = 0;
      for(Number e: n){
          sum += e.doubleValue();
      }
      return sum;
  }

  //// 3 points
  static <T> void print (Iterable<T> n) {
    // implement this method and explain
      //because print is type-agnostic, <T> allows all types
      //Java infers type T from the argument, so method can print Iterable<Number>, Iterable<Integer>, etc
      for(T t: n){
          System.out.println(t);
      }
  }

  // Required: (20 points) Expand the main method (see the pdf for
  //   more details), but keep whatever provided as it is
  public static void main (String args[]) {
    Node<Double> doublelist = 
        new Node<Double>(21.5, 
          new Node<Double>(16.74, 
            new Node<Double>(2.189, 
              new Node<Double>(7.1, null))));

    System.out.println("===");
    print(doublelist);
    System.out.println("sum doublelist = " + mysum(doublelist));
    System.out.println("===");

    // EXPAND this main. Test every functionality including getter and
    // setter methods. See the hw7.pdf for more details.
      // 1. Integer list
      Node<Integer> intlist =
              new Node<>(5,
                      new Node<>(10,
                              new Node<>(15, null)));

      System.out.println("Integer list:");
      print(intlist);
      System.out.println("sum intlist = " + mysum(intlist));

      // 2. Float list
      Node<Float> floatlist =
              new Node<>(1.5f,
                      new Node<>(2.5f,
                              new Node<>(3.5f, null)));

      System.out.println("\nFloat list:");
      print(floatlist);
      System.out.println("sum floatlist = " + mysum(floatlist));

      // 3. null Double list
      Node<Double> doublelist2 = null;
      System.out.println("\nnull doublelist2:");
      System.out.println("print should do nothing:");
      if (doublelist2 == null)
          System.out.println("(doublelist2 is null)");

      // 4. Test getter & setter
      System.out.println("\nTesting getters and setters:");
      Node<Integer> single = new Node<>(99, null);

      System.out.println("Value before setVal: " + single.getVal());
      single.setVal(123);
      System.out.println("Value after setVal: " + single.getVal());

      System.out.println("Next before setNext: " + single.getNext());
      single.setNext(new Node<>(777, null));
      System.out.println("Next after setNext: " + single.getNext().getVal());

      // 5. Test iterator manually
      System.out.println("\nTesting iterator on floatlist:");
      Iterator<Float> it = floatlist.iterator();
      while (it.hasNext()) {
          System.out.println("iter: " + it.next());
      }

      // 6. Test print and mysum on every list
      System.out.println("\nTesting print() and mysum() on all lists:");
      print(intlist);
      print(floatlist);
      print(doublelist);

      System.out.println("mysum(intlist) = " + mysum(intlist));
      System.out.println("mysum(floatlist) = " + mysum(floatlist));
      System.out.println("mysum(doublelist) = " + mysum(doublelist));

      System.out.println("\n=== End Node tests ===\n");

    
  } // end of main
} // end of class Node



/* class MyQueue */

// Total 55 points for the MyQueue class

class MyQueue<E> implements Iterable<E>, Cloneable, Comparable<MyQueue<E>> {   
  protected Node<E> head;
  protected Node<E> tail;
  protected int length;

  @Override
  public Iterator<E> iterator() { return head.iterator(); }

  // helper method reverse() *** to be used in clone()
  public MyQueue<E> reverse() {
    Node<E> l = null;
    for (E e : head)  l = new Node<E>(e, l);
    return (new MyQueue<E>(l));
  }

  // Task 1: override Object.clone() (5 points)
  @Override
  public MyQueue<E> clone() {
      MyQueue<E> reversedClone = reverse();
      MyQueue<E> clone = reversedClone.reverse();
      return clone;
  }

  @Override
  public int compareTo(MyQueue<E> list) { 
    if (this.length < list.length) return -1;
    if (this.length == list.length) return 0;
    return 1;
  }

  // Task 2: override Object.equals() (5 points)
  @Override
  public boolean equals(Object o) { 
  // Implement this method and explain (read the equality criteria in the
  // hw7.pdf problem statement carefully!)
      if(this == o) return true; //same reference
      if(!(o instanceof MyQueue<?>)){ //if its not a queue, it can't be compared - so automatically false
          return false;
      }
      MyQueue<?> t = (MyQueue<?>) o; //cast to wildcard
      if(t.hashCode() != this.hashCode()) return false; //compare lengths
      Node<E> p1 = this.head;
      Node<?> p2 = t.head;

      while(p1 != null && p2 != null){
          if(!p1.getVal().equals(p2.getVal())){ //check whether they're equal based on their own methods
              return false;
          }
          p1 = p1.getNext();
          p2 = p2.getNext();
      }

      //both queues should terminate at the same time for equality
      return p1 == null && p2 == null;
  }

  @Override
  public int hashCode() {
    return length;
  }

  // two constructors (implementing the one-arg constructor 5 points)
  // no-arg constructor given
  public MyQueue() { head = null; tail = null; length = 0; }
    
  // Task 3: one-arg constructor (10 points)
  public MyQueue(Iterable<E> iterable) { 
  // implement this constructor and explain
      this.length = 0;
      this.head = null;
      this.tail = null;
      if(iterable == null) return; //handle null safety

      for(E e: iterable){
          Node<E> newNode = new Node<>(e, null); //create new node
          if(head == null){ //if head is null, then head should be the first element, and tail should also be the first element
              head = newNode;
              tail = newNode;
          }else{
              tail.setNext(newNode); //ensure tail always points to the last element
              tail = newNode;
          }
          length++; //keep track of length
      }
  }

				
  // Task 4: total 15 points for toString(), add() and remove()
  // toString() (5 points)
  @Override
  public String toString() {
  // implement this method and explain
      if(head == null) return "(empty queue)"; //nullish safety
      StringBuilder sb = new StringBuilder(); //use sb for better performance
      Node<E> dummy = head.getNext(); //skip head
      sb.append("(head: ") //treat head specially
              .append(head.getVal())
              .append(") -> ");
      //iterate through and add each to sb, until reaching tail
      while(dummy != tail){
          sb.append("(")
                  .append(dummy.getVal())
                  .append(") -> ");
          dummy = dummy.getNext();
      }
      //treat tail specially
      sb.append("(tail: ")
              .append(tail.getVal())
              .append(")");
      return sb.toString();
  }

  // add() (5 points)
  public void add(E item) { 
  // implement this method and explain
      Node<E> next = new Node<>(item, null); //create new node
      if(tail == null) {
          head = tail = next; //list is empty, effectively init
      } else {
          tail.setNext(next); //ensure next is linked
          tail = next; //ensure tail stays at end
      }
      length++; //ensure length stays consistent
  }

  // remove() (5 points)
  public E remove() { 
  // implement this method and explain
      if(head == null) throw new NoSuchElementException(); //null;ish safety
      E val = peek();//capture val
      head = head.getNext(); //ensure head still points to first in queue
      if(head == null) tail = null; //b/c queue is empty
      length--; //ensure length stays consistent
      return val;
  }

  // given
  public E peek() { return head.getVal(); }

  // given
  public int getLength() { return length; }

  // Required: (25 points) Expand the main method, but keep whatever 
  //   provided as it is
  public static void main (String args[]) {
    MyQueue<Integer> empty_queue = new MyQueue<Integer>();
    MyQueue<Integer> q  = new MyQueue<Integer>(Arrays.asList(10,2,3,4));
    MyQueue<Integer> q1 = new MyQueue<Integer>(Arrays.asList(2,4,3,10));
    MyQueue<Integer> q2 = new MyQueue<Integer>(q.reverse());

    out.println("sum of q = " + Node.mysum(q));
    out.println("sum of q1 = " + Node.mysum(q1));
    Node.print(q);
    Node.print(q1);
    
    out.println(q);
    out.println("q1 = " + q1);
    out.println("q2 = " + q2);
    out.println("q2.compareTo(q1) = " + q2.compareTo(q1));
    out.println("=== end of test");

    // EXPAND this main. Test every functionality you implemented above.
    // See the hw7.pdf for more details.

      // 1. Integer queue
      MyQueue<Integer> intqueue = new MyQueue<>(Arrays.asList(1, 2, 3, 4, 5));

      // 2. Double queue
      MyQueue<Double> doublequeue2 = new MyQueue<>(Arrays.asList(1.1, 2.2, 3.3));

      // 3. String queue
      MyQueue<String> stringqueue = new MyQueue<>(Arrays.asList("a", "b", "c"));

      // --- Test Node.mysum() (only numeric queues) ---
      System.out.println("mysum(intqueue) = " + Node.mysum(intqueue));
      System.out.println("mysum(doublequeue2) = " + Node.mysum(doublequeue2));
      // Node.mysum(stringqueue); // is invalid b/c strings are not Numbers

      // --- Test Node.print() ---
      System.out.println("\nPrinting intqueue:");
      Node.print(intqueue);
      System.out.println("\nPrinting doublequeue2:");
      Node.print(doublequeue2);
      System.out.println("\nPrinting stringqueue:");
      Node.print(stringqueue);

      // --- Test add() ---
      System.out.println("\nAdding elements to queues:");
      intqueue.add(6);
      doublequeue2.add(4.4);
      stringqueue.add("d");
      System.out.println(intqueue);
      System.out.println(doublequeue2);
      System.out.println(stringqueue);

      // --- Test remove() ---
      System.out.println("\nRemoving elements:");
      System.out.println("intqueue.remove() = " + intqueue.remove());
      System.out.println("doublequeue2.remove() = " + doublequeue2.remove());
      System.out.println("stringqueue.remove() = " + stringqueue.remove());

      // --- Test peek() ---
      System.out.println("\nPeeking at heads:");
      System.out.println("intqueue.peek() = " + intqueue.peek());
      System.out.println("doublequeue2.peek() = " + doublequeue2.peek());
      System.out.println("stringqueue.peek() = " + stringqueue.peek());

      // --- Test getLength() ---
      System.out.println("\nLengths after operations:");
      System.out.println("intqueue length = " + intqueue.getLength());
      System.out.println("doublequeue2 length = " + doublequeue2.getLength());
      System.out.println("stringqueue length = " + stringqueue.getLength());

      // --- Test clone() ---
      System.out.println("\nTesting clone():");
      MyQueue<Integer> intqueueClone = intqueue.clone();
      System.out.println("intqueue = " + intqueue);
      System.out.println("intqueueClone = " + intqueueClone);
      System.out.println("intqueue.equals(intqueueClone) = " + intqueue.equals(intqueueClone));

      // --- Test equals() ---
      System.out.println("\nTesting equals():");
      MyQueue<Integer> anotherIntQueue = new MyQueue<>(Arrays.asList(2,3,4,5,6));
      System.out.println("intqueue.equals(anotherIntQueue) = " + intqueue.equals(anotherIntQueue));

      // --- Test compareTo() ---
      System.out.println("\nTesting compareTo():");
      System.out.println("intqueue.compareTo(anotherIntQueue) = " + intqueue.compareTo(anotherIntQueue));
      System.out.println("intqueue.compareTo(intqueueClone) = " + intqueue.compareTo(intqueueClone));

      System.out.println("\n=== End of MyQueue Task 4 tests ===");

    
  } // end of main
} // end of class MyQueue



