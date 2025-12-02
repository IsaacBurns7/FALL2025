
/*  Written by Hyunyoung Lee for CSCE 314 students 
 *  BankAccountV2 uses a lock as a synchronization mechanism between 
 *  the depositing thread and the withdrawing thread, i.e., the 
 *  thread that wants to execute its task (either deposit or withdraw),
 *  first needs to acquire the lock, executes its task (the code
 *  within the try block) in full (without being interleaved by other 
 *  thread(s)), and then releases the lock (by calling unlock()), so
 *  now another thread can acquire the lock, and executes its task, 
 *  and so on.
 *
 *  But this BankAccountV2 code still does not prevent overdraft.
 *
 *  The BankAccountV3 code prevents overdraft by letting the withdrawing
 *  thread check whether there are enough funds before withdrawing
 *  (the while condition). However, this can lead to a deadlock.  
 */ 

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//import java.util.concurrent.locks.Condition;

public class BankAccountV2 {
  public BankAccountV2() { 
    balance = 0; 
    balanceChangeLock = new ReentrantLock(); 
  }
  public void deposit(double amount) {
    balanceChangeLock.lock();
    try {
      System.out.println("Depositing " + amount);
      double nb = balance + amount;
      System.out.println("New balance is " + nb);
      balance = nb;
    } finally {
      balanceChangeLock.unlock();
    }
  }
  public void withdraw(double amount) 
    throws InterruptedException // needed because await puts thread to sleep
  {
    balanceChangeLock.lock();
    try {
      System.out.println("Withdrawing " + amount);
      double nb = balance - amount;
      System.out.println("New balance is " + nb);
      balance = nb;
    } finally {
      balanceChangeLock.unlock();
    }
  }
  public double getBalance() { return balance; }
  private double balance;
  private Lock balanceChangeLock;

  public static void main(String args[]) {
    BankAccountV2 account = new BankAccountV2();
    final double AMOUNT = 100;
    final int REPETITIONS = 10000;

    DepositRunnable d1 = new DepositRunnable(account, AMOUNT, REPETITIONS);
    WithdrawRunnable w1 = new WithdrawRunnable(account, AMOUNT, REPETITIONS);
    DepositRunnable d2 = new DepositRunnable(account, AMOUNT, REPETITIONS);
    WithdrawRunnable w2 = new WithdrawRunnable(account, AMOUNT, REPETITIONS);
    Thread t1 = new Thread(d1);
    Thread t2 = new Thread(w1);
    Thread t3 = new Thread(d2);
    Thread t4 = new Thread(w2);

    t1.start();
    t2.start();
    t3.start();
    t4.start();
  } 
}

class DepositRunnable implements Runnable {
  public DepositRunnable(BankAccountV2 acc, double anAmount, int aCount) {
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
  private static final int DELAY = 0;
  private BankAccountV2 account;
  private double amount;
  private int count;
}

class WithdrawRunnable implements Runnable {
  public WithdrawRunnable(BankAccountV2 acc, double anAmount, int aCount) {
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
  private static final int DELAY = 0;
  private BankAccountV2 account;
  private double amount;
  private int count;
}


