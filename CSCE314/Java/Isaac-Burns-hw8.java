
/* Skeleton provided by Hyunyoung Lee
   For CSCE 314 [Sections 200, 502] Fall 2025, Assignment 8
   Due: Friday, 12/5/2025, 11:59 p.m.
   Class contained: OrderManager

   Student Name: Isaac Burns
   Student UIN: 935007106
   Acknowledgements: Oracle Java Documentation
*/

import java.util.Date;
import java.util.Objects;
import java.util.Queue;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

class OrderManager implements Runnable {
  // Shared fields among all threads
  static final int MAX_ORDER = 20;
  private static int totalOrder = 0; // sequential number for all orders
  private static Queue<String> orders = new LinkedList<String>();
  private static Lock queueAccessLock = new ReentrantLock();
  private static Condition newOrder = queueAccessLock.newCondition();

  // Instance members for each thread
  private String title; // manager or client
  private int id;
  private volatile boolean stop = false;
  private int orderNo = 0; // seqential no. for orders per thread

  public OrderManager(String t, int i) {
  // constructor: t is for title, i for id
    title = t;
    id = i;
  }

  public void takeOrder() {
  // First, prepare a string in the format shown in the pdf.
  // Then, acquire the queueAccessLock, and within the try block,
  // add the order string to the orders queue,
  // increment the total no. of orders, and 
  // invoke signalAll() on the condition object.
  // And then, in the finally block, release the lock.
      String s = "Preparing meal: " + title + " order no. " + id + "." + (orderNo+1);
      queueAccessLock.lock();
      try {
          orders.add(s);
          totalOrder++;
          this.orderNo++;
          newOrder.signalAll();
      } catch (Exception e){
        throw new RuntimeException(e);
      } finally {
          queueAccessLock.unlock();
      }
  }

  private void sendOrderToKitchen() {
  // First, acquire the queueAccessLock, and within the try block:
  // first check if the orders queue has at least one element
  // if so, then remove one order (at the head of the queue) and
  //        invoke prepareMeal() with the removed order string;
  // if not, invoke await() on the condition object, which
  // must be enclosed in a try-catch block that catches
  // InterruptedException
  // And then, in the finally block, release the lock.
//      System.out.println("Sending order to....");
      queueAccessLock.lock();

//      System.out.println("Thread '" + Thread.currentThread().getName() + "' sending order to kitchen...");
      try {
          while(orders.isEmpty()){
              try {
                  newOrder.await();
              } catch (InterruptedException e){
                  Thread.currentThread().interrupt();
                  return;
              }
          }
          String order = orders.remove();
          prepareMeal(order);

      } catch (Exception e) {
          throw new RuntimeException(e);
      } finally {
          queueAccessLock.unlock();
      }

  }

  private void prepareMeal(String s) {
  // this method simply outputs the string s
      System.out.println(s);
  }

  public void stop() { 
  // make it so that this Runnable will stop
      stop = true;
  }

  public void run() {
  // Within a while loop (if the condition !stop is satisfied),
  // first check if the title is "manager",
  //             then invoke sendOrderToKitchen() method in which
  //                  manager removes orders from the queue
  //             else invoke takeOrder() method, in which
  //                  the non-manager (client) adds orders to the queue.
  // At the end of each iteration of the loop, let this thread
  // sleep for 10000/id milliseconds before continuing on the
  // next iteration.
  // When the while loop exits, output the following string
  // "Thread for "+Thread.currentThread().getName()+" is ending..."
      while(!stop){
          if(this.title.equals("manager")){
              sendOrderToKitchen();
          } else {
              takeOrder();
          }
          try {
              Thread.sleep(((long) 10000 / id));
          } catch (InterruptedException e){
              Thread.currentThread().interrupt();
          }
      }
      System.out.println("Thread for " + Thread.currentThread().getName() + " is ending...");

  } // end run()


  public static void main(String[] args) {
  // Expand this main with at least two more client threads,
  // one for "Take out" (to distinguish from "To go") and
  // another for "Third party" (delivery) with different id values.
  //
  // Try different sets of id values for those client threads,
  // and write a paragraph about what you notice in the outputs
  // with those different id values. Explain the reason.
  /*****  (Your paragraph goes within this block comment.)
    I know manager is NOT A CLIENT THREAD, but note if you make the manager extremely slow
    by decreasing its id to say 1, then almost no orders are fulfilled, or are fulfilled very slowly.
    If, for any of the client threads, you make its id much lower, it will also become slower,
   and take less of the orders. If you give it a very high id, it will take orders much faster,
   and take more of them as a result.
   If you give it an extremely high ID, like 10000, it will take a lot of orders, way more than the
   20 its supposed to. This is because the while (OrderManager.totalOrder < OrderManager.MAX_ORDER)
   check is not locked, and only happens once every second. With extremely high ID,
   and therefore an extremely fast thread, it will complete many hundreds of orders in that one second.
   This happens because clients continue to add orders even after totalOrder surpasses MAX_ORDER,
   and only the main thread stops them, not internal client logic.
   *****/
    
    OrderManager m0 = new OrderManager("manager", 100);
    OrderManager c1 = new OrderManager("To go", 50);
    OrderManager c2 = new OrderManager("Eat in", 40);
    OrderManager c3 = new OrderManager("Delivery", 25);
      OrderManager c4 = new OrderManager("Take out", 15);
      OrderManager c5 = new OrderManager("Third party", 5);
    Date open = new Date();
    System.out.println("Welcome to Restaurant 314 !! "+open);
    System.out.println("We are accepting orders...");
    
    Thread tm1 = new Thread(m0, "manager");
    Thread tc1 = new Thread(c1, "To go");
    Thread tc2 = new Thread(c2, "Eat in");
    Thread tc3 = new Thread(c3, "Delivery");
    Thread tc4 = new Thread(c4, "Take out");
    Thread tc5 = new Thread(c5, "Third party");

    tm1.start();
    tc1.start();
    tc2.start();
    tc3.start();
    tc4.start();
    tc5.start();
    
    while (OrderManager.totalOrder < OrderManager.MAX_ORDER) {
    try {
	Thread.sleep(1000);
    } catch (InterruptedException exception) {
	System.out.println("main interrupted");
    }
    } // end while
    
    OrderManager[] servers = {m0, c1, c2, c3, c4, c5};
    for (OrderManager server : servers) { server.stop(); }

    // main thread will wait here until every child thread finishes
    try {
    tm1.join();
    tc1.join();
    tc2.join();
    tc3.join();
    tc4.join();
    tc5.join();
    } catch (InterruptedException exception) {}

    System.out.println("We have served a total of " + OrderManager.totalOrder + " orders today.");

    // main thread now outputs bye-bye message
    System.out.println("We're sold out of ingredients OR it's 8:45 PM!\n"
		     + "Restaurant 314 closing, until tomorrow... Bye!");
    
  } // end main()
}  // end class OrderManager
