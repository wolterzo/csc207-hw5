package edu.grinnell.csc207.aza.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utils
{

  /** 
   * Find the square root of a given BigDecimal d with the precision of epsilon.
   * @param d, a BigDecimal
   * @param epsilon, a BIgDecimal
   * @return the result of the approximation, an BigDecimal
   */
  public static BigDecimal sqrt(BigDecimal d, BigDecimal epsilon)
  {
    // approximation is d/2.00000000000 
    int scale = epsilon.scale();
    RoundingMode round = RoundingMode.HALF_UP;
    BigDecimal approximation = d.multiply(new BigDecimal(0.5));
    BigDecimal quotient = d.divide(approximation, scale, round);
    //Spencer helped us to change Boolean to the primitive boolean.
    boolean isClose = false;

    while (!isClose)
      {
        if (quotient.subtract(approximation).abs().compareTo(epsilon) < 0)
          {
            isClose = true;
          } // if 
        else
          {
            quotient = d.divide(approximation, scale, round);
            approximation =
                approximation.add(quotient).multiply(new BigDecimal(0.5));
          } // else
      } // while
    return approximation;
  } // sqrt(BigDecimal, BigDecimal)

  /**
   * returns the base, x, to the power of p, p.
   * @param base, an int
   * @param power, an int
   * @return int base^power
   */
  public static int expt(int base, int power)
  {
    int total = base; // start at base
    int exp = 1; // current exponent

    // base case, p = 0
    if (power == 0)
      {
        return 1;
      } //if
    while (exp < power)
      {
        if ((power - exp) % 2 == 0 && exp * 2 <= power)
          {
            total = total * total;
            exp = exp * 2;
          } // if even and not too large
        else
          {
            total = base * total;
            exp++;
          } // else odd or too large to double
      } // while
    return total;
  } // expt(int, int)
  
  public static void main(String[] args)
    throws Exception
  {
    //test sqrt
    System.out.println("Printing answer "
                       + Utils.sqrt(new BigDecimal(100),
                                    new BigDecimal(0.00001)));
    System.out.println("Printing answer "
                       + Utils.sqrt(new BigDecimal(4.0), new BigDecimal(0.1)));
    System.out.println("Printing answer "
                       + Utils.sqrt(new BigDecimal(5.0), new BigDecimal(0.001)));

  } // main(String[])

} // class Utils
