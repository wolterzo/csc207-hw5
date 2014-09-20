package edu.grinnell.csc207.aza.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class UtilsTest
{

  @Test
  public void testSqrt()
  {
    fail("Not yet implemented");
  }

  @Test
  public void testExpt()
  {
    assertEquals("zero base", 0, Utils.expt(0, 6));
    assertEquals("zero power", 1, Utils.expt(5, 0));
    assertEquals("even power", 1024, Utils.expt(2, 10));
    assertEquals("odd power", 512, Utils.expt(2, 9));
  }
}
