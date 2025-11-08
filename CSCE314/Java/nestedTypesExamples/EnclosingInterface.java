
// CSCE 314 Programming Languages Java Example Code
// (Based on the textbook example) Modified by Hyunyoung Lee
// This example is for nesting in interfaces

import static java.lang.System.out;

interface Changeable { // Use 1.
  class Record {  // this class is used only to return multiple values 
                  // from the interface's method getLastChange()
    public Object changer;
    public String changeDesc;
  }
  Record getLastChange();
}

class RecordChange implements Changeable {
  private Changeable.Record rec = new Changeable.Record();
  public Record getLastChange() {
    return rec;
  } 
  public void change(String s1, String descript) {
    rec.changer = s1;
    rec.changeDesc = descript;    
  }
}

interface SharedData {
  class Data { // can be directly accessed as SharedData.Data
    private int x = 0;
    public int getX() { return x; }
    public void setX(int newX) { x = newX; }
  }
  Data data = new Data(); // anyone implements SharedData can access data
}

class SomeData implements SharedData {
  private int myData = 100;
  public void doSomething() {
    int myOldData = myData;
    data.setX(7);
    int sharedData = data.getX();
    myData *= sharedData;
    out.println("my old data = " + myOldData + 
                " shared data = " + sharedData +
                " my new data = " + myData);
  }
}

class Main {
  public static void main(String args[]) {
    RecordChange rc = new RecordChange();
    rc.change("title", "changed from Manager to CTO");
    out.println("What? = " + rc.getLastChange().changer +
                " How? = " + rc.getLastChange().changeDesc);

    SomeData d = new SomeData();
    d.doSomething();
    SharedData.Data dd = new SharedData.Data();
    dd.setX(34);
    out.println(dd.getX());
  }
} 
