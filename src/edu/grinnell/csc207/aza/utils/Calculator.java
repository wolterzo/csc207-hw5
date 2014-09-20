package edu.grinnell.csc207.aza.utils;

public class Calculator
{
  private String[] storage;

  public Calculator()
  {
    this.storage = new String[8];
  }

  public Fraction evaluate(String expression)
  {
    String temp;
    if (expression.charAt(0) == 'r' && expression.charAt(3) == '=')
      {
        int stIndex = Character.getNumericValue(expression.charAt(1));
        if (stIndex <= 7)
          {
            temp = expression.substring(5);
            this.storage[stIndex] = this.evaluate(temp).toString();
            return evaluate(temp);
          }
        else
          {
            return new Fraction("0");
            //Report Error, storage element out of bounds
          }
      }
    else
      {
        Fraction result = new Fraction("0");
        char curr;
        int last = 0;
        char op = '+';
        Fraction currFrac = new Fraction("0");
        String operand;
        /*
         * Go through the string and calculate the result using the given fractions
         * and operators.
         */
        for (int i = 0; i < expression.length(); i++)
          {
            curr = expression.charAt(i);
            if ((curr == '*' || curr == '/' || curr == '+' || curr == '-' || curr == '^')
                && expression.charAt(i + 1) == ' ')
              {
                operand = expression.substring(last, i).trim();
                int stIndex = this.isStorage(operand);
                if (stIndex > -1)
                  {
                    currFrac = new Fraction(this.storage[stIndex]);
                  }
                else
                  {
                    currFrac =
                        new Fraction(expression.substring(last, i).trim());
                  }
                result = operate(result, op, currFrac);
                last = i + 1;
                op = curr;
              } // if
          } // for
        /*
         * Find and operate on the last fraction in the string.
         */
        currFrac = new Fraction(expression.substring(last).trim());
        result = operate(result, op, currFrac);
        return result;
      }

  }// evaluate(String)

  public int isStorage(String str)
  {
    int index = Character.getNumericValue(str.charAt(1));
    if (str.charAt(0) == 'r')
      {
        if (index >= 8)
          {
            return -1;
          }// If index out of bounds
        else if (this.storage[index] == null)
          {
            return -1;
          }// If storage value is null
      }// If r
    return index;
  }// isStorage(String)

  
  /**
   * Performs an operation between two fractions using the given operator. 
   * @param first
   * @param op
   * @param second
   * @return Fraction
   */
  public static Fraction operate(Fraction first, char op, Fraction second)
  {
    Fraction result = new Fraction("0");
    switch (op)
      {
        case '+':
          result = first.add(second);
          break;
        case '-':
          result = first.subtract(second);
          break;
        case '*':
          result = first.multiply(second);
          break;
        case '/':
          result = first.divide(second);
          break;
      /* Don't need exponentiation
      case '^':
      result = first.pow(Integer.valueOf(second.num.toString()));
      break;
      */
      } // switch
    return result;

  } // operate(Fraction, char, Fraction)
  
  public static void main(String[] args)
  {
    Calculator myCalc = new Calculator();
    
    myCalc.evaluate("r0 = 4/8 + 2/8").toString();
    String result = myCalc.evaluate("r0 * 4/8 + 2/8").toString();
    
    System.out.println(result);
  }

}// End Class Calculator
