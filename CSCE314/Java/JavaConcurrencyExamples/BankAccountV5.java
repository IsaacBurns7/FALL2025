
/*  Written by Hyunyoung Lee for CSCE 314 students
 *  This BankAccountV5 uses synchronized methods (instead of locks) together 
 *    with Object.wait() and Object.notifyAll() (see below)
 *    to prevent deadlock as well as overdraft.
 *  It could use a lock together with a condition object 
 *    (as commented out below).
 *  If you change the amount to be withdrawn to be larger than the amount
 *    deposited, you will notice that the withdrawing threads are stuck 
 *    because the threads will repeat only ten times, but the amounts  
 *    deposited are not sufficient for the withdrawing.
 *    Note that this situation is not a deadlock. 
 */ 

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class BankAccountV5 {
  public BankAccountV5() { 
    balance = 0; 
    //balanceChangeLock = new ReentrantLock(); 
    //sufficientFundsCondition = balanceChangeLock.newCondition();
  }

  //public void deposit(double amount) {
  public synchronized void deposit(double amount) { // if using synchronized
  //  balanceChangeLock.lock();
  //  try {
      System.out.println("Depositing " + amount);
      double nb = balance + amount;
      System.out.println("New balance is " + nb);
      balance = nb;
  //  sufficientFundsCondition.signalAll(); // if using synchronized,
                                            // then use notifyAll() instead
      notifyAll();
  //  } finally {
  //    balanceChangeLock.unlock();
  //  } 
  }

  //public void withdraw(double amount)
  public synchronized void withdraw(double amount) // if using synchronized
    throws InterruptedException // needed because await puts thread to sleep
  {
  //  balanceChangeLock.lock();
  //  try {
      System.out.println("BEFORE Withdrawing " + amount);
      while (balance < amount) {
        System.out.println("need to wait .....");
  //    sufficientFundsCondition.await(); // if using synchronized,
      wait();	                          // then use wait() instead
      }
      System.out.println("Withdrawing " + amount);
      double nb = balance - amount;
      System.out.println("New balance is " + nb);
      balance = nb;
  //  } finally {
  //    balanceChangeLock.unlock();
  //  } 
  }
  public double getBalance() { return balance; }
  private double balance;
  private Lock balanceChangeLock;
  private Condition sufficientFundsCondition;

  public static void main(String args[]) {
    BankAccountV5 account = new BankAccountV5();
    final double AMOUNT = 100; // try various amounts to deposit/withdraw
    final int REPETITIONS = 10;

    DepositRunnable d1 = new DepositRunnable(account, 100, REPETITIONS);
    WithdrawRunnable w1 = new WithdrawRunnable(account, 100, REPETITIONS);
    //DepositRunnable d2 = new DepositRunnable(account, 10, REPETITIONS);
    //WithdrawRunnable w2 = new WithdrawRunnable(account, 50, REPETITIONS);
    Thread t1 = new Thread(d1);
    Thread t2 = new Thread(w1);
    //Thread t3 = new Thread(d2);
    //Thread t4 = new Thread(w2);

    t2.start();
    t1.start();
    //t3.start();
    //t4.start();
  } // end of main()
} // end of class BankAccountV5

class WithdrawRunnable implements Runnable {
  public WithdrawRunnable(BankAccountV5 acc, double anAmount, int aCount) {
         account = acc; amount = anAmount; count = aCount;
  }
  public void run() {
  try {
    for (int i=0; i<count; i++) {
        account.withdraw(amount);
        Thread.sleep(DELAY);
    }
  } catch (InterruptedException exception) {}
  }
  private static final int DELAY = 1;
  private BankAccountV5 account;
  private double amount;
  private int count;
}

class DepositRunnable implements Runnable {
  public DepositRunnable(BankAccountV5 acc, double anAmount, int aCount) {
         account = acc; amount = anAmount; count = aCount;
  }
  public void run() {
  try {
    for (int i=0; i<count; i++) {
        account.deposit(amount);
        Thread.sleep(DELAY);
    }
  } catch (InterruptedException exception) {}
  }
  private static final int DELAY = 1;
  private BankAccountV5 account;
  private double amount;
  private int count;
}
