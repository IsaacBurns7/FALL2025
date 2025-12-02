
/*  Written by Hyunyoung Lee for CSCE 314 students 
 *  This BankAccountV1 does not use any synchronization mechanism, 
 *  thus, can easily lead to a race hazard. I.e., when run, the output reveals
 *  the interleavings between the executions of multiple threads. 
 *  Such interleavings can manifest itself as a harmful interference, called
 *  race hazard, which can lead to overdraft situations (balance of -100.0)
 *  in the output of this code.
 * 
 *  The BankAccountV2 code prevents the race hazard
 *  by using a lock object as a synchronization mechanism.
 */

public class BankAccountV1 {
  public BankAccountV1() { balance = 0; }
  public void deposit(double amount) {
    System.out.println("Depositing " + amount);
    balance = balance + amount;
    System.out.println("New balance is " + balance);
  }
  public void withdraw(double amount) {
    System.out.println("Withdrawing " + amount);
    balance = balance - amount;
    System.out.println("New balance is " + balance);
  }
  public double getBalance() { return balance; }
  private double balance;
  //private volatile double balance;

  public static void main(String args[]) { 
    BankAccountV1 account = new BankAccountV1();
    final double AMOUNT = 100;
    final int REPETITIONS = 200000;

    DepositRunnable d = new DepositRunnable(account, AMOUNT, REPETITIONS);
    WithdrawRunnable w = new WithdrawRunnable(account, AMOUNT, REPETITIONS);
    Thread t1 = new Thread(d);
    Thread t2 = new Thread(w);
    t1.start();
    t2.start();
  }
}

class DepositRunnable implements Runnable {
  public DepositRunnable(BankAccountV1 acc, double anAmount, int aCount) {
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
  private BankAccountV1 account;
  private double amount;
  private int count;
}

class WithdrawRunnable implements Runnable {
    public WithdrawRunnable(BankAccountV1 acc, double anAmount, int aCount) {
        account = acc;
        amount = anAmount;
        count = aCount;
    }

    public void run() {
  try {
        for (int i = 0; i < count; i++) {
            account.withdraw(amount);
        Thread.sleep(DELAY);
        }
    } catch(
    InterruptedException exception)

    {
    }
}
  private static final int DELAY = 0;
  private BankAccountV1 account;
  private double amount;
  private int count;
}

