
/*  Written by Hyunyoung Lee for CSCE 314 students
 *  This BankAccountV4 uses a lock together with a condition object and 
 *  now prevents race hazard, deadlock as well as overdraft.
 */ 

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class BankAccountV4 {
  public BankAccountV4() { 
    balance = 0; 
    balanceChangeLock = new ReentrantLock(); 
    sufficientFundsCondition = balanceChangeLock.newCondition();
  }
  public void deposit(double amount) {
    balanceChangeLock.lock();
    try {
      System.out.println("Depositing " + amount);
      double nb = balance + amount;
      System.out.println("New balance is " + nb);
      balance = nb;
      sufficientFundsCondition.signalAll();
    } finally {
      balanceChangeLock.unlock();
    }
  }
  public void withdraw(double amount) 
    throws InterruptedException // needed because await puts thread to sleep
  {
    balanceChangeLock.lock();
    try {
      while (balance < amount) 
        sufficientFundsCondition.await();
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
  private Condition sufficientFundsCondition;

  public static void main(String args[]) {
    BankAccountV4 account = new BankAccountV4();
    final double AMOUNT = 100;
    final int REPETITIONS = 10;

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

class WithdrawRunnable implements Runnable {
  public WithdrawRunnable(BankAccountV4 acc, double anAmount, int aCount) {
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
  private BankAccountV4 account;
  private double amount;
  private int count;
}

class DepositRunnable implements Runnable {
  public DepositRunnable(BankAccountV4 acc, double anAmount, int aCount) {
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
  private BankAccountV4 account;
  private double amount;
  private int count;
}


