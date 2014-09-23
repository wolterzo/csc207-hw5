package edu.grinnell.csc207.aza.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Utils
{

  /** 
   * Find the square root of a given BigDecimal d with the precision of epsilon.
   * @param d
   * @param epsilon
   * @return the result of the approximation
   */

  public static BigDecimal sqrt(BigDecimal d, BigDecimal epsilon)
  {
    // approximation is d/2.00000000000 
    int scale = epsilon.scale();
    RoundingMode round = RoundingMode.HALF_UP;
    BigDecimal approximation = d.divide(new BigDecimal(2.0), scale, round);
    BigDecimal quotient = d.divide(approximation, scale, round);
    Boolean good = false;

    while (!good)
      {
        if (quotient.subtract(approximation).abs().compareTo(epsilon) < 0)
          {
            good = true;
          } // if 
        else
          {
            quotient = d.divide(approximation, scale, round);
            approximation =
                approximation.add(quotient).divide(new BigDecimal(2.0), scale,
                                                   round);
          } // else
      } // while

    return approximation;
  } // sqrt(BigDecimal, BigDecimal)

  /**
   * 
   * @param x
   * @param p
   * @return
   */
  public static int expt(int x, int p)
  {
    int total = x;
    int exp = 1;

    if (p == 0)
      {
        return 1;
      } //if
    while (exp < p)
      {
        if ((p - exp) % 2 == 0 && exp * 2 <= p)
          {
            total = total * total;
            exp = exp * 2;
          } // if even and not too large
        else
          {
            total = x * total;
            exp++;
          } // else
      } // while
    return total;
  } // expt(int, int)

  public static void main(String[] args)
    throws Exception
  {

    BigDecimal test = new BigDecimal(100);
    int n = 5;
    int p = 100;
    System.out.println(n + "^" + p + " = " + Utils.expt(n, p));

    System.out.println("Printing answer "
                       + Utils.sqrt(test, new BigDecimal(0.00001)));

  }

}
