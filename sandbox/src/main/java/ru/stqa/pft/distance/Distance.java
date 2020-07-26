package ru.stqa.pft.distance;

public class Distance {
  public static void main(String[] args){
    Point r1 = new Point(4,2);
    Point r2 = new Point(5,1);
    System.out.println("Расстояние между точками с координатами (" + r1.x + ", " + r1.y + ") и (" + r2.x + ", " + r2.y + ") = " + distance(r1,r2));
  }

  public static double distance(Point r1, Point r2){

    return Math.sqrt((r2.x-r1.x)*(r2.x-r1.x) + (r2.y-r1.y)*(r2.y-r1.y));
  }
}
