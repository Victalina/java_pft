package ru.stqa.pft.distance;

public class Point {
  public double x;
  public double y;

  public Point(double x,double y){
    this.x = x;
    this.y = y;
  }
  public double distance(Point r2){

    return Math.sqrt((r2.x-this.x)*(r2.x-this.x) + (r2.y-this.y)*(r2.y-this.y));
  }
}
