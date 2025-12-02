
/*  Written by Hyunyoung Lee for CSCE 314 students 
 *  This BankAccountV3 prevents overdraft by checking if there are enough
 *  funds before withdrawing (in the withdraw() method) but can lead to
 *  a deadlock.
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//import java.util.concurrent.locks.Condition;

public class BankAccountV3 {
  public BankAccountV3() { 
    balance = 0; 
    balanceChangeLock = new ReentrantLock(); 
  }
  public void deposit(double amount) {
    balanceChangeLock.lock();
    try {
      System.out.println("Depositing " + amount);
      balance = balance + amount;
      System.out.println("New balance is " + balance);
    } finally {
      balanceChangeLock.unlock();
    }
  }
  public void withdraw(double amount) {
    balanceChangeLock.lock();
    try {
      // check if there are enough funds to avoid overdraft
      while (balance < amount) {} // if not, go into a busy loop,
                                  // possibly deadlock
      System.out.println("Withdrawing " + amount);
      double nb = balance - amount;
      //balance = balance - amount;
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
    BankAccountV3 account = new BankAccountV3();
    final double AMOUNT = 100;
    final int REPETITIONS = 10;

    DepositRunnable d1 = new DepositRunnable(account, AMOUNT, REPETITIONS);
    WithdrawRunnable w1= new WithdrawRunnable(account, AMOUNT, REPETITIONS);
    DepositRunnable d2 = new DepositRunnable(account, AMOUNT, REPETITIONS);
    WithdrawRunnable w2= new WithdrawRunnable(account, AMOUNT, REPETITIONS);
    Thread t1 = new Thread(d1);
    Thread t2 = new Thread(w1);
    Thread t3 = new Thread(d2);
    Thread t4 = new Thread(w2);
    System.out.println("Threads are created.");

    t1.start();
    t2.start();
    t3.start();
    t4.start();
  }
}

class WithdrawRunnable implements Runnable {
  public WithdrawRunnable(BankAccountV3 acc, double anAmount, int aCount) {
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
  private BankAccountV3 account;
  private double amount;
  private int count;
}

class DepositRunnable implements Runnable {
  public DepositRunnable(BankAccountV3 acc, double anAmount, int aCount) {
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
  private BankAccountV3 account;
  private double amount;
  private int count;
}

