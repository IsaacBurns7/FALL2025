
/* Course: CSCE 314 [Sections 200, 502] Programming Languages, Fall 2025
   Programming Homework Assignment 5 (Due: Monday, October 27, 2025)
   
   Skeleton for classes Vehicle, VehicleTest, PassengerVehicle, and
                PassengerVehicleTest
   DO NOT Change the frameword given in this skeleton code, including
   the class headers and method headers!
   
   Written by  Hyunyoung Lee for CSCE 314 Students

   Student Name:
   Student UIN:
   Acknowledgements:
*/

// DO NOT import any other libraries

import java.util.ArrayList;
import java.util.Collections;

// (60 points) classes Vehicle and VehicleTest
class Vehicle implements Comparable<Vehicle> {
  // other private fields go here
    static private int nextVehicleID = 0; // to keep track of how many
                     // Vehicles have been created so far; each time
                     // Vehicle object is created, increment this value and
                     // use it as the vehicle ID
    
  // public fields go here
    private int currentSpeed;
    private int currentDirection;
    private String ownerName;
    private int vehicleId;

    final static private String TURN_LEFT = "TURN_LEFT";
    final static private String TURN_RIGHT = "TURN RIGHT";

  // two constructors: no-arg constructor and one-arg constructor
  // The no-arg constructor sets the vehicle ID as the current value of
  // nextVehicleID and increment nextVehicleID by one
    Vehicle(){
        vehicleId = nextVehicleID;
        nextVehicleID++;
    }
  // The one-arg constructor accepts the owner name as the argument,
  // first invokes the no-arg constructor by invoking this() to set the
  // vehicle ID and set the owner name of this vehicle as the name passed as
  // the argument
    Vehicle(String name){
        this();
        ownerName = name;
    }
    
  // other public methods go here
    public int getCurrentSpeed(){
        return currentSpeed;
    }
    public int getCurrentDirection(){
        return currentDirection;
    }
    public int getVehicleId(){
        return vehicleId;
    }
    public String getOwnerName(){
        return ownerName;
    }
    public void changeSpeed(int speed){
        currentSpeed = speed;
    }
    public void setCurrentDirection(int currentDirection){
        this.currentDirection = (currentDirection % 360 + 360) % 360;
    }
    public void setOwnerName(String name){
        ownerName = name;
    }
    public void stop(){
        currentSpeed = 0;
    }
    public void turn(int degrees){
        int newDirection = currentDirection + degrees;
        currentDirection = (newDirection % 360 + 360) % 360;
    }
    public void turn(String value){
        int newDirection = currentDirection;
        if(value.equals(Vehicle.TURN_LEFT)){
            newDirection -= 90;
        }else if(value.equals(Vehicle.TURN_RIGHT)){
            newDirection += 90;
        }
        currentDirection = (newDirection % 360 + 360) % 360;
    }
  /* compare two vehicles based on their vehicle ID */
  public int compareTo(Vehicle v1) {
      if(this.vehicleId > v1.getVehicleId()) return 1;
      if(this.vehicleId < v1.getVehicleId()) return -1;
      return 0; // for now to pass compilation
  }
  public static int highestId(){
      return nextVehicleID - 1;
  }
  public String toString(){
      return String.format("""
             Vehicle ID: %d
             Owner name: %s
             Current speed: %d
             Current direction: %d
              """,
              this.getVehicleId(),
              this.getOwnerName(),
              this.getCurrentSpeed(),
              this.getCurrentDirection());
  }
  // private (helper) methods if you need
} // end of class Vehicle

 
class VehicleTest {
  public static void main(String[] args) {
    // create Vehicle instances
    // test the functionalities you implemented
      Vehicle v1 = new Vehicle();
      Vehicle v2 = new Vehicle();
      v1.setOwnerName("FORBIDDEN HERO");
      v2.setCurrentDirection(30);
      Vehicle v3 = new Vehicle("Alice");
      Vehicle v4 = new Vehicle("Bob");
      Vehicle v5 = new Vehicle("Charlie");

      System.out.println(v1.toString());
      System.out.println(v2.toString());
      System.out.println(v3.toString());
      System.out.println(v4.toString());
      System.out.println(v5.toString());
      v1.changeSpeed(15);
      System.out.println(v1.toString());
      v1.stop();
      System.out.println(v1.toString());
      v1.turn(-90);
      System.out.println(v1.toString());
      v1.turn(90);
      System.out.println(v1.toString());
      v1.turn("TURN LEFT");
      System.out.println(v1.toString());
      v1.turn("TURN RIGHT");
      System.out.println(v1.toString());

      System.out.println(v2.compareTo(v1));
      System.out.println(v2.compareTo(v2));
      System.out.println(v2.compareTo(v3));

      System.out.println("--- Testing highestId ---");
      System.out.println("Highest Vehicle ID created: " + Vehicle.highestId());
  }
}


// (50 points) class PassengerVehicle
class PassengerVehicle extends Vehicle {
  /* (3+3=6 points) Declare two private fields specific to PassengerVehicle:  
     total # of seats and occupied seats
  */
    private int seats;
    private int occupiedSeats;


  /* (2+3+5=10 points) Implement three constructors: 
     1. (2 points) no-arg constructor,
     2. (3 points) one arg constructor: only owner name as an argument,
     3. (5 points) two arg constructor: owner name and total # of seats

     You must already have the first two constructors in the Vehicle
     class. Invoke the Vehicle class constructor by using `super`,
     with no arg for the first constructor and 
     with appropriate argument (owner name) for the second constructor.
     For the third constructor, first, invoke the super constructor
     with the owner name and then, update this object's total # of seats with
     the argument total # of seats (by a simple assignment).
  */
    PassengerVehicle(){
        super();
    }
    PassengerVehicle(String ownerName){
        super(ownerName);
    }
    PassengerVehicle(String ownerName, int seats){
        super(ownerName);
        this.seats = seats;
    }


  /* (2+2=4 points) accessor (getter) methods for the private fields */
    public int getSeats(){
        return this.seats;
    }
    public int getOccupiedSeats(){
        return this.occupiedSeats;
    }

  /* (3+5=8 points) mutator (setter) methods for the private fields */
    public void setSeats(int seats){
        this.seats = seats;
    }
    public void setOccupiedSeats(int seats){
        this.occupiedSeats = seats;
    }

  /* (10 points) override the toString method */
  @Override
  public String toString() {
      return String.format("""
             Vehicle ID: %d
             Owner name: %s
             Current speed: %d
             Current direction: %d
             Seats: %d
             Occupied Seats: %d
              """,
              this.getVehicleId(),
              this.getOwnerName(),
              this.getCurrentSpeed(),
              this.getCurrentDirection(),
              this.seats,
              this.occupiedSeats);
  }
      

  /* (12 points) override the compareTo method
     signature: compareTo(Vehicle)
     First, make sure the Vehicle argument is an instance of PassengerVehicle.
       If not, then you can only compare the two vehicles using the 
       super's compareTo.
       If so, then typecast the argument object to PassengerVehicle, say y,
       and compare this object to y as below: 
       The comparison criteria is total # of seats. So, x.compareTo(y)
       returns 1 if x's total # of seats is greater than that of y,
       returns 0 if they are equal, and
       returns -1 if the former is smaller than the latter.
  */
  @Override
  public int compareTo(Vehicle ve) { // DO NOT change this method header
      if(ve instanceof PassengerVehicle){
            PassengerVehicle y = (PassengerVehicle) ve;
            return Integer.compare(this.seats, y.seats);
      }

      return 0;
  }

  // any helper methods if there need be go here
  
} // end of class PassengerVehicle


// (20 points) class PassengerVehicleTest with the main method
class PassengerVehicleTest {
  // main method to test PassengerVehicle implementation
  public static void main(String[] args) {
    /* Part I (6 points):
       You can use either the basic Java array [] (and use Arrays.sort)
       or ArrayList (or any Collections, whichever you feel the easiest)
       
       If you are using ArrayList, you would do something like,
    ArrayList<PassengerVehicle> pVs = new ArrayList<PassengerVehicle>(); 
       where pVs is object reference for ArrayList of PassengerVehicles

       Now, you can add PassengerVehicle objects (at least 5) to pVs
       e.g., pVs.add( new PassengerVehicle("H Lee", 7) ); 
       which addes a PassengerVehicle object with 
       owner name "H Lee" and total 7 seats

       Once you created enough PassengerVehicle objects adding them to
       pVs, first print out all PassengerVehicles stored in pVs using a
       for (or for-each) loop.
    */
      ArrayList<PassengerVehicle> pVs = new ArrayList<PassengerVehicle>();
      pVs.add(new PassengerVehicle("H Lee", 7));
      pVs.add(new PassengerVehicle("Isaac B", 8));
      pVs.add(new PassengerVehicle("Anna B", 5)); //anna's tessie ><
      System.out.println("--------------------------");
      System.out.println("-- Initial Passenger Vehicles");
      System.out.println("--------------------------");
      for(PassengerVehicle pv : pVs){
          System.out.println(pv.toString());
      }
      
    /* Part II (8 points):
       Now, you can sort pVs as
       
    Collections.sort(pVs); // Sort the PassengerVehicles 
                           // in an ascending order according to
                           // the compareTo method implementation 

       And then, find a way to output the ascending sorted result in
       descending order. Use a for loop to print out the sorted result 
       in a descending order.
    */
      System.out.println("--------------------------");
      System.out.println("-- Sorting based on number of seats");
      System.out.println("--------------------------");
      Collections.sort(pVs);
      for(PassengerVehicle pv : pVs){
          System.out.println(pv.toString());
      }
    /* Part III (6 points):
       Have some (or all) of the seats occupied in each passenger vehicle,
       and output the number of seats currently available in each vehicle.
       You can output all of the fields like you did in previous parts.
    */
      System.out.println("--------------------------");
      System.out.println("-- ADDING OCCUPIED SEATS");
      System.out.println("--------------------------");
      pVs.get(1).setOccupiedSeats(3);
      pVs.get(2).setOccupiedSeats(4);
      for(PassengerVehicle pv : pVs){
          System.out.println(pv.toString());
      }

  } // end of main

} // end of class PassengerVehicleTest

