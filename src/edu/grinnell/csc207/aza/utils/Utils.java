package edu.grinnell.csc207.aza.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Utils
{

  /** 
   * 
   * @param d
   * @param epsilon
   * @return the result of the approximation
   */

  public static BigDecimal sqrt(BigDecimal d, BigDecimal epsilon)
  {
    // approximation is d/2.00000000000 
    MathContext precision = new MathContext(5, RoundingMode.HALF_UP);
    BigDecimal approximation = d.divide(new BigDecimal(2.0, precision));
    BigDecimal quotient = d.divide(approximation, precision);
    Boolean good = false;

    while (!good)
      {
        if (quotient.subtract(approximation).abs().compareTo(epsilon) < 0)
          {
            good = true;
          }
        else
          {
            quotient = d.divide(approximation, precision);
            approximation =
                approximation.add(quotient).divide(new BigDecimal(2.0,
                                                                  precision));
          }
      }

    return approximation;
  }

  public static int exptR(int x, int p)
  {
    // Base case: When p = 0, result is 1
    if (p == 0)
      {
        return 1;
      } // if (p == 0)
    // Base case: When p = 1, result is x
    else if (p == 1)
      {
        return x;
      } // if (p == 1)
    // Recursive case: When p is 2k, x^(2k) = (x^k) * (x^k)
    else if (p % 2 == 0)
      {
        int tmp = exptR(x, p / 2);
        return tmp * tmp;
      } // if (p is even)
    // Recursive case: When p is odd, result is x*(x^(p-1))
    else
      {
        return exptR(x * x, (p - 1) / 2);
      } // if p is odd.
  } // expt(int,int)

  public static int expt(int x, int p)
  {
    int soFar = x;
    while (p > 0) 
      {
        if (p == 1)
          {
            
          }
      }
    return 1;
  } // expt(int, int)
  public static void main(String[] args)
    throws Exception
  {

    BigDecimal test = new BigDecimal(100);

    System.out.println("Printing answer "
                       + Utils.sqrt(test, new BigDecimal(0.00001)));

  }

}
