package ru.stqa.pft.distance;

public class Distance {
  public static void main(String[] args){
    Point r1 = new Point(1,2);
    Point r2 = new Point(1,1);
    System.out.println("Расстояние между точками с координатами (" + r1.x + ", " + r1.y + ") и (" + r2.x + ", " + r2.y + ") = " + r1.distance(r2));
  }

}
