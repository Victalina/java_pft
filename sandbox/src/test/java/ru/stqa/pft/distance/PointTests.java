package ru.stqa.pft.distance;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
  @Test
  public void testPoint1(){
    Point p = new Point(1, 2);
    Point l = new Point(1, 3);
    Assert.assertEquals(p.distance(l), 1.0);
  }
  @Test
  public void testPoint2(){
    Point p = new Point(1, 1);
    Point l = new Point(1, 1);
    Assert.assertEquals(p.distance(l), 0.0);
  }
  @Test
  public void testPoint3(){
    Point p = new Point(-1, -1);
    Point l = new Point(-1, -2);
    Assert.assertEquals(p.distance(l), 1.0);
  }
}
