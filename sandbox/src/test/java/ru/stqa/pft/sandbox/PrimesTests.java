package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.mustache.Value;

public class PrimesTests {

  @Test
  public void testPrimesFast(){
    Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
  }
  @Test (enabled = false)
  public void testLongPrimes(){
    long n = Integer.MAX_VALUE;
    Assert.assertTrue(Primes.isPrime(n));
  }
  @Test
  public void testNonPrimes(){
    Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE-2));
  }

}
