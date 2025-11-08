
// CSCE 314 Programming Languages Java Example Code 
// (Based on the textbook example) Modified by Hyunyoung Lee
// This example is for
// (1) a static field (class member)
// (2) a static nested class
// (3) a non-static nested class (called "inner class")
// (4) String equals() method

import static java.lang.System.out;

public class BankAccount { // enclosing class
  private double balance;
  private Action lastAct;
  public static int totalAccounts = 0;
  public BankAccount() {
    balance = 0;
    totalAccounts++;
  }
  public void deposit(double amount) {  
    balance += amount; 
    lastAct = new Action("deposit", amount);
    out.println("new balance = " + balance);
  }
  public Action getAction() {
    return lastAct;
  }
  public Permissions permissionsFor(String who) {
    Permissions perm = new Permissions();
    perm.canWithdraw = canWithdraw(who);
    perm.canClose = canClose(who);
    return perm;
  }
  protected boolean canWithdraw(String who) {
    if (who.equals("owner")) return true;
    else return false;
  }
  protected boolean canClose(String who) {
    if (who.equals("owner")) return true;
    else return false;
  }  
						
  // static nested class
  public static class Permissions {
    public boolean canDeposit, canWithdraw, canClose;
    public void staticNestedClassMethod() { 
      out.println("static nested class method!"); 
      BankAccount a = new BankAccount();
      a.balance = 50;
      out.println("Account a's private balance = " + a.balance);
    }
    public void printPermissions() {
      out.print("permissions: ");
      if (canDeposit) out.print("deposit ");
      if (canWithdraw) out.print("withdraw ");
      if (canClose) out.print("close ");
      out.println(); 
    }
  }

  // non-static nested class (inner class)
  public class Action {
    private String act;
    private double amount;
    Action(String act, double amount) { 
      this.act = act;
      this.amount = amount;
      printAction();
    }
    public void printAction() {
      out.println("Action to perform = " + act + " " + amount); 
    }
  }
}

class Driver {
  public static void main( String[] args ) {
    BankAccount a = new BankAccount();
    BankAccount.Permissions ap = a.permissionsFor("owner");
    ap.printPermissions();
    out.println();

    BankAccount.Permissions nestedObject = new BankAccount.Permissions();
    nestedObject.staticNestedClassMethod();
    out.println();

    // The following line is a compile error since Action is a 
    // non-static inner class
    // BankAccount.Action act = new BankAccount.Action();
    // Correct syntax: 
    // OuterClass.InnerClass innerObject = outerObject.new InnerClass();
    BankAccount.Action act = a.new Action("deposit", 43); // this works
    out.println();

    BankAccount b = new BankAccount();
    b.deposit( 100 );
    b.deposit( 10 );
  }
}

