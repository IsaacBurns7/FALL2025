
/*  Written by Hyunyoung Lee for CSCE 314 students */

import java.util.Date;

public class InterruptRunnable implements Runnable { 
  public InterruptRunnable(String aGreeting) {
    greeting = aGreeting;
  }

  public void run () {
    try {
      for (int i=0; i<REPETITIONS; i++) { 
         Date now = new Date();
         if (!Thread.interrupted()) { 
            System.out.println(now + " " + greeting);
         } else {
            System.out.println("Interrupted");
            // this occurs if interrupt notification received
            // while thread active
            throw new InterruptedException();
            // idiomatic: allows cleanup to be done in one place
         }
         Thread.sleep(DELAY);
      }
    } catch (InterruptedException exception) {
      System.out.println(Thread.currentThread().getName() + ":" + 
                         Thread.currentThread().getState());
      System.out.println("Interrupted and ousted");
      // this occurs if interrupt notification received while
      // thread sleeping
    }
  }
  private String greeting;
    
  private static final int REPETITIONS = 100;
  private static final int DELAY = 20;

  public static void main(String[] args) {
    InterruptRunnable r1 = new InterruptRunnable("Hi!");
    InterruptRunnable r2 = new InterruptRunnable("Bye!");
    Thread t0 = new Thread(r1);
    Thread t1 = new Thread(r2);
    t0.start();
    t1.start();	
    try {
        Thread.sleep(35);
    } 
    catch (InterruptedException exception) {}
    System.out.println("main: " + Thread.currentThread().getState());
    t0.interrupt();
    t1.interrupt();
    System.out.println("Is t0 alive? " + t0.isAlive());
    System.out.println("Is t1 alive? " + t1.isAlive());
    System.out.println("Is t0 alive? " + t0.isAlive());
    System.out.println("t0: " + t0.getState());
    System.out.println("t1: " + t1.getState());
    System.out.println("Is t0 alive? " + t0.isAlive());
    System.out.println("Is t1 alive? " + t1.isAlive());
    System.out.println("Is main thread alive? " + Thread.currentThread().isAlive());
  }
}


