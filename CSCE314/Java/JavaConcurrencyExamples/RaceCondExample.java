/* Written by Hyunyoung Lee for CSCE 314 Students
*/

import static java.lang.System.out;

class IncOrDec {
  private int val = 0; // will be shared by multiple threads

  public void increment() { 
    out.print("Incrementing... ");
    int v = val; 
    v = v + 1;
    out.println(v);
    val = v; 
    //out.println(getVal()); // printout statement here will reduce
    //  the chance of race condition
  }
  public void decrement() { 
    out.print("Decrementing... ");
    int v = val; 
    v = v - 1;
    val = v; 
    out.println(getVal());
  }
  public int getVal() { return val; }

  public static void main(String args[]) {
    IncOrDec counter = new IncOrDec();
    final int REPEAT = 100; // increment or decrement 100 times each
    final int DELAY = 1; // 1 millisec

    IncrementRunnable ir1 = new IncrementRunnable(counter, DELAY, REPEAT);
    DecrementRunnable dr1 = new DecrementRunnable(counter, DELAY/1000, REPEAT*10);
    IncrementRunnable ir2 = new IncrementRunnable(counter, DELAY, REPEAT);
    IncrementRunnable ir3 = new IncrementRunnable(counter, DELAY, REPEAT);
    IncrementRunnable ir4 = new IncrementRunnable(counter, DELAY, REPEAT);
  
    Thread ti1 = new Thread(ir1);
    Thread ti2 = new Thread(ir2);
    Thread ti3 = new Thread(ir3);
    Thread ti4 = new Thread(ir4);
    Thread ti5 = new Thread(dr1);
    
    // every statement that "happens-before" the following Thread.start()
    // invocations also has a happens-before relationship with every 
    // statement executed by those threads. 
    ti1.start();
    ti2.start();
    ti3.start();
    ti4.start();
    ti5.start();

    try {
      ti1.join();
      ti2.join();
      ti3.join();
      ti4.join();
      ti5.join();
    } catch (InterruptedException exception) {}
    // all the statements executed by the terminated and "joined" thread 
    // have a happens-before relationship with all the statements 
    // following the successful join.

    out.println("Final value = " + counter.getVal());

/* output of an execution: 
   If everything goes as planned, Final value should be 400, 
   but due to race condition, sometimes some increments get lost (overwritten) 
   and Final value is less than what is expected, for example:
  . . .
Incrementing... 144
Incrementing... 145
Incrementing... 146
Incrementing... 147
Final value = 147
*/    
  }  
}

class IncrementRunnable implements Runnable {
  private IncOrDec counter;
  private int repeat = 1;
  private int delay = 1; // 1 millisec
  public IncrementRunnable(IncOrDec v, int d, int r) { 
    counter = v; delay = d; repeat = r; } 
  public void run() {
    //try {
      for (int i = 0; i < repeat; i++) {
        counter.increment();
        //Thread.sleep(delay);
      }
    //} catch (InterruptedException exception) {}
  }
}


class DecrementRunnable implements Runnable {
  private IncOrDec counter;
  private int repeat = 1;
  private int delay = 1; // 1 millisec
  public DecrementRunnable(IncOrDec v, int d, int r) { 
    counter = v; delay = d; repeat = r; } 
  public void run() {
   // try {
      for (int i = 0; i < repeat; i++) {
        counter.decrement();
        //Thread.sleep(delay);
      }
   // } catch (InterruptedException exception) {}
  }
}

