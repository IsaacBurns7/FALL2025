
/* Skeleton provided by Hyunyoung Lee
   For CSCE 314 [Sections 200, 502] Fall 2025, Assignment 6
   Due: Friday, November 7, 2025, 11:59 p.m.

   Classes contained: Shape, Point, RightTriangle, Square, Circle,
                      TotalAreaCalculator, ShapeTest
 
   Student Name:
   UIN:
   Acknowledgements:
*/


// below are imports for class ShapeTest
import java.io.File;
        import java.io.IOException;
        import java.util.Scanner;
import static java.lang.System.*;


abstract class Shape implements Comparable<Shape> {
  public Point position;
  public double area;
    
  // constructor that sets position as the Point passed as an argument
  // signature: Shape (Point)
  // implement the constructor
    Shape(Point position){
        this.position = position;
    }

  // implement equals()
  @Override
  public boolean equals(Object o) {  // implement this method and explain your implementation
      //by default, equality in java means two references point to the same object in memory
      if (this == o) return true;
      //has to be a shape if not pointing to same in memory
      if (!(o instanceof Shape)) {
          return false;
      }
      //shape equality defined by default as position equality
      Shape s = (Shape) o;
      if(this.position.equals(s.position)){
          return true;
      }
      return false;
  }

  // area() must be abstract
  public abstract double area();

  // implement compareTo()
  @Override
  public int compareTo(Shape s)
  {  // implement this method and explain your implementation
      //natural ordering of shapes defined as as comparison of area -
      return Double.compare(this.area, s.area);
  }
} // end of class Shape

// class Point
final class Point {
  public double x;
  public double y;

  // constructor that sets the values of x and y
  public Point(double x, double y)
  {  // implement the constructor
    this.x = x;
    this.y = y;
  }

  // implement equals, hashCode(), toString()
  @Override
  public boolean equals(Object s){
      //logic: s must be a point and be at the same position for it to be equal
      //turn s into point to allow access of x, use double.compare for more consistency
      if(this == s) return true;
      if(!(s instanceof Point)) {
          return false;
      }
      Point other = (Point) s;
      return Double.compare(x, other.x) == 0 && Double.compare(y, other.y) == 0;
  }

  @Override
  public int hashCode()
  {
      //multiply by 31 b/c primes reduce collisions.
      //use Double.hashCode to turn double into consistent hash, also better than
      //(int) x as that would result in many collisions
      int result = 17; //start with nonzero
      result = 31 * result + Double.hashCode(x);
      result = 31 * result + Double.hashCode(y);
      return result;
  }

  @Override
  public String toString()
  {
      //short basic point notation, easy to read when other classes call multiple
     String d = String.format("(%f, %f)", x, y);
     return d;
  }

  // implement main method for Point

    
} // end of class Point


class RightTriangle extends Shape {
  private Point p1, p2;
  private double width, height; // you may need those

  // constructor that accepts three Points, and sets 
  // the position stored in Shape with the first point, 
  // and p1 and p2 with the other two Points
  public RightTriangle(Point p0, Point p1, Point p2)
  {  // implement the constructor and explain your implementation 
     // check the invariant of a "right" triangle and 
     // set the height and width of this triangle appropriately
     // as explained in the pdf pages 2-3.

      // set the first point to the location of the right triangle
      super(p0);

      //check that at least two points share the same y coord

      if (p1.y == p2.y) {
          width = Math.abs(p1.x - p2.x);
      } else if (p1.y == p0.y) {
          width = Math.abs(p1.x - p0.x);
      } else if (p2.y == p0.y) {
          width = Math.abs(p2.x - p0.x);
      } else {
          throw new IllegalArgumentException("No two points share the same y-coordinate");
      }

      //check that at least two points share the same x coord

      if (p1.x == p2.x) {
          height = Math.abs(p1.y - p2.y);
      } else if (p1.x == p0.x) {
          height = Math.abs(p1.y - p0.y);
      } else if (p2.x == p0.x) {
          height = Math.abs(p2.y - p0.y);
      } else {
          throw new IllegalArgumentException("No two points share the same x-coordinate");
      }

      this.p1 = p1;
      this.p2 = p2;

  }

  // implement equals(), hashCode(), area(), and toString()
  @Override
  public boolean equals(Object s)
  {
      if(this == s) return true;
      if(!(s instanceof RightTriangle)) return false;
      if(!super.equals(s)) return false;
      RightTriangle rt = (RightTriangle) s;
      return this.p1.equals(rt.p1) && this.p2.equals(rt.p2);
  }

  @Override
  public int hashCode()
  {
      //add prime each time to
      int result = 17; // start with a non-zero prime
      //get p0 from the shape
      Point p0 = ((Shape) this).position;
      result = 31 * result + p0.hashCode();
      result = 31 * result + p1.hashCode();
      result = 31 * result + p2.hashCode();
      return result;
  }

  @Override
  public double area()
  {
      return (width * height) / 2;
  }

  @Override
  public String toString()
  {  // implement this method and explain your implementation
      //print out defining three points
      String s = String.format("RightTriangle with points \n %s %s %s\n",
              ((Shape) this).position.toString(), p1.toString(), p2.toString());
      return s;
  }

  // implement main method for RightTriangle
    
} // end of class RightTriangle


class Square extends Shape {
  private double side; // side is the side length

  // constructor that accepts a Point (for position) and a double
  // (for the side length).
  public Square(Point p0, double side)
  {
      //initialize position for Shape parent class
      super(p0);
      this.side = side;
  }

  // implement equals(), hashCode(), area(), and toString()
  @Override
  public boolean equals(Object o)
  {
      if(this == o) return true;
      if(!(o instanceof Square)) return false;
      if(!(super.equals(o))) return false;
      Square s = (Square) o;
      return Double.compare(this.side, s.side) == 0;
  }

  @Override
  public int hashCode()
  {  // implement this method and explain your implementation
      //two primes -> less likely to collide
      int result = 17;
      result = result * 31 + Double.hashCode(side);
      result = result * 31 + ((Shape) this).position.hashCode();
      return result;
  }

  @Override
  public double area()
  {
      return side * side;
  }

  @Override
  public String toString()
  {
      //Print all attributes
      String s = String.format("Square with position %s and side length %f\n",
              ((Shape) this).position.toString(), side);
      return s;
  }

  // implement main method for Square
    
} // end of class Square


class Circle extends Shape {
  private double radius;

  // constructor that accepts a Point (for position) and a double
  // (for the radius).
  public Circle(Point p0, double r)
  {  // implement the constructor
      super(p0);
      this.radius = radius;
  }

  // implement equals(), hashCode(), area(), and toString()
  @Override
  public boolean equals(Object o)
  {  // implement this method and explain your implementation
      //equality based on same ref, same kind of object, same local vars
      //and same area
      if(this == o) return true;
      if(!(o instanceof Circle)) return false;
      if(!super.equals(o)) return false;
      Circle c = (Circle) o;
      return Double.compare(radius, c.radius) == 0;
  }

  @Override
  public int hashCode()
  {  // implement this method and explain your implementation
      //two primes -> less likely to collide
      int result = 17;
      result = result * 31 + Double.hashCode(radius);
      result = result * 31 + ((Shape) this).position.hashCode();
      return result;
  }

  @Override
  public double area()
  {
      return Math.PI * (radius * radius);
      //pi r^2, formula for area of a radius

  }

  @Override
  public String toString()
  {  // implement this method and explain your implementation
      //Print all attributes
      String s = String.format("Circle with position %s and radius %f\n",
              ((Shape) this).position.toString(), radius);
      return s;
  }

  // implement main method for Circle
    
} // end of class Circle


class TotalAreaCalculator {
  public static double calculate(Shape[] shapes) {
  // for each shape in the shapes array,   
  // invoke the object's area() method,
  // summing up the areas
  // and finally returns the total area
      double ans = 0;
      for(Shape shape : shapes){
          ans += shape.area();
      }
      return ans;
  }
}


// class ShapeTest
// The main method and the helper methods for file input are
// written by Hyunyoung Lee
//
// The student must add the parts that outputs the sorted shapes and 
// the total area of all shapes toward the end of main, where it is 
// specified in the comment as "student's code goes here ...".

class ShapeTest {
  public static Shape[] shapes; // using an array is a requirement

  public static Point readPoint(Scanner scan)
  { String point = scan.next();
    //out.println(point);
    Scanner aPoint = new Scanner(point).useDelimiter("\\s*,\\s*");
    double x = aPoint.nextDouble();
    double y = aPoint.nextDouble();
    //  out.println("double " + x);
    //  out.println("double " + y);
    return new Point(x, y);
  }

  public static RightTriangle readRightTriangle(Scanner scan)
  {
    Point[] points = new Point[3];
    int i = 0;
    while (scan.hasNext()) {
      String point = scan.next();
      //out.println(point);
      Scanner aPoint = new Scanner(point).useDelimiter("\\s*,\\s*");
      if (aPoint.hasNext() ) {
        double x = aPoint.nextDouble();
        double y = aPoint.nextDouble();
        //out.println("double " + x);
        //out.println("double " + y);
        points[i++] = new Point(x, y);
      }
    }
    return new RightTriangle(points[0], points[1], points[2]);
  }

  public static Square readSquare(Scanner scan)
  {
    Point p1 = readPoint(scan);
    String dbls = scan.next();
    Scanner dblScan = new Scanner(dbls);
    Double len = dblScan.nextDouble();
    return new Square(p1, len);
  }

  public static Circle readCircle(Scanner scan)
  {
    Point p1 = readPoint(scan);
    String dbls = scan.next();
    Scanner dblScan = new Scanner(dbls);  

    Double r = dblScan.nextDouble();
    //out.println("double " + r);
    return new Circle(p1, r);
  }

  public static Shape[] fileInputShapes(String inFileName) 
    throws IOException {
  // read in the shape specifications from infile,
  // construct the shapes and store them in a Shape array
  // This method is essentially "parsing" the input shapes,
  // and involves quite some thought.
    out.println(inFileName);
    File infile = new File(inFileName);
    String delim = "\\s*;[\\s*\\n]*";
    int arrSize = 0;
    Scanner fs = new Scanner(infile).useDelimiter(delim);
 
    String s = null;
    while (fs.hasNext()) {
      ++arrSize;
      s = fs.next();
      out.println(s); 
    }
    out.println("The input file contains " + arrSize + " shapes.");  
    shapes = new Shape[arrSize]; // we now know how many shapes are in input

    fs = new Scanner(infile).useDelimiter(delim);
    int i = 0;
    String aShape = null;
    while (fs.hasNext()) {
      aShape = fs.next();
      aShape.trim();
      //out.println("aShape = "+aShape);
      Scanner inAShape = new Scanner(aShape).useDelimiter("\\s*[()]\\s*");
      String kind = inAShape.next();
      //out.println( kind );
      switch( kind )
      {
        case "t": shapes[i] = readRightTriangle(inAShape);
                  //out.println(shapes[i]);
	          break;
        case "s": shapes[i] = readSquare(inAShape);
                  //out.println(shapes[i]);
                  break;
        case "c": shapes[i] = readCircle(inAShape);
                  //out.println(shapes[i]);
                  break;
      }
      shapes[i].area = shapes[i].area();
      ++i;
    }
    out.println(shapes.length +" shapes have been created");
    
    //for (Shape e : shapes) out.println(e);

    fs.close(); // important to close the handle 
    return shapes;
  }

  public static void main (String args[]) {
    try {
    if (args.length >= 1) {
       //out.println(args[0]);
       shapes = fileInputShapes( args[0] );
       for (Shape e : shapes) { out.println(e); }
    } else System.err.println("Usage: "+"java ShapeTest input_file_name\n");
    } catch (IOException e) {}

  // Test code for the equals method by invoking equals on each shape itself 
    out.println("\n== Testing the equals method ...");
    for (Shape s : shapes)
        if (s.equals(s)) out.println(s + " equals " + s);


  // Implement Task 8.
  // Sort the shapes according to their area and output them nicely in 
  // an ascending order of area 
  // student's code goes here ...
      //selection sort
      for(int i = 0;i< shapes.length - 1;i ++){
          int minIndex = 1;
      }



  // output the total area of all the shapes
  // student's code goes here ...
      TotalAreaCalculator tac = new TotalAreaCalculator();
      Double totalArea = tac.calculate(shapes);
      System.out.printf("Total Area of Shapes: %f\n", totalArea);



  } // end of main()
} // end of class ShapeTest



