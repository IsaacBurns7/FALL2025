
// CSCE 314 Programming Languages Java Example Code 
// (Based on the Oracle tutorial) Modified by Hyunyoung Lee 
// This example is for
// (1) nested interface
// (2) local inner class 
// (3) anonymous inner class

import static java.lang.System.out;

public class Greetings {

  interface Greet { // nested interface is by default public static
    public void greet();
    public void greetSomeone(String name);
  }
 
  public void hi() {
    class HelloGreeting implements Greet { // local inner class
      String name = "everyone";
      public void greet() {
          greetSomeone("everyone");
      }
      public void greetSomeone(String someone) {
          name = someone;
          out.println("Hello, " + name);
      }
    }
    Greet hello = new HelloGreeting();

    Greet aggieGreeting = new Greet() {   // anonymous inner class
      String name = "all";
      public void greet() {
          greetSomeone("y'all");
      }
      public void greetSomeone(String someone) {
          name = someone;
          out.println("Howdy, " + name);
      }
    };

    hello.greet();
    aggieGreeting.greet();
  }

  public static void main(String args[]) {
    Greetings myGreetings = new Greetings();
    myGreetings.hi();
  }
};


