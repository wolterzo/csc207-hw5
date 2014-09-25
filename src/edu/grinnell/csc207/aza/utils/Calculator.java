package edu.grinnell.csc207.aza.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Calculator
{
  //array of storage values
  private String[] storage;

  public Calculator()
  {
    this.storage = new String[8];
  }

  /**
   * Prints prompt and reads user input
   * @param pw, a PrintWriter
   * @param br, a BufferedReader
   * @param prompt, a String
   * @return a String
   */
  public static String readInput(PrintWriter pw, BufferedReader br,
                                 String prompt)
  {
    if (!prompt.equals(""))
      {
        pw.print(prompt);
        pw.flush();
      } // if there is a prompt
    try
      {
        return br.readLine();
      }
    catch (IOException e)
      {
        return "operator error";
      }
  } // readInput(PrintWriter, BufferedReader, String)

  /**
   * Reads user input, evaluates input on a given calculator, 
   * prints the result, and loops.(REPL)
   * @param calc, a Calculator
   */
  public static void useCalc(Calculator calc)
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    BufferedReader eyes = new BufferedReader(new InputStreamReader(System.in));
    String response;
    Fraction result;
    while (true)
      {
        response = readInput(pen, eyes, "Please enter an expression: ");
        if (response.compareTo("exit") == 0)
          {
            return;
          } // if
        try
          {
            result = calc.evaluate(response);
            pen.println(result);
          } // try
        catch (Exception e)
          {
            String eMessage = e.getMessage();
            if (eMessage.compareTo("Storage value out of bounds") == 0)
              {
                pen.println(eMessage);
              }// if
            else if (eMessage.compareTo("Storage value is empty") == 0)
              {
                pen.println(eMessage);
              }// else if
            else if (eMessage.compareTo("division by zero") == 0)
              {
                pen.println(eMessage);
              }// else if
            else
              {
                pen.println("Invalid input, please try again");
              }// else if
          }// catch
      } // while
  } // useCalc(Calculator)

  public Fraction evaluate(String expression)
    throws Exception
  {
    String tempS;
    if (expression.charAt(0) == 'r' && expression.length() > 3
        && expression.charAt(3) == '=')
      {
        int stIndex = Character.getNumericValue(expression.charAt(1));
        if (stIndex <= 7)
          {
            tempS = expression.substring(5);
            this.storage[stIndex] = this.evaluate(tempS).toString();
            return evaluate(tempS);
          } // if 
        else
          {
            throw new Exception("Storage value out of bounds");
          } // else
      } // if
    else
      {
        /* following code taken from Ameer Shujjah, Camila Mateo 
         * Volkart, and Tommy Pitcher's homework 4. 
         */
        // Initialize an empty array of strings for the fractions,
        // an array of characters for the operators, opIterator and
        // fracIterator to keep track of the aforementioned arrays.
        // Also initialize a String buffer and a temp char and a
        // boolean to differentiate the fraction from the operand /.
        String[] fractions = new String[expression.length()];
        char[] operators = new char[expression.length()];
        int opIterator = 0;
        int fracIterator = 0;
        String buffer = "";
        char temp;
        boolean fracSwitch = false;
        // Iterate over all the characters in the string.
        for (int i = 0; i < expression.length(); i++)
          {
            temp = expression.charAt(i);
            if (temp == ' ')
              {
                fracSwitch = false;
                fractions[fracIterator] = buffer;
                buffer = "";
                fracIterator++;
              }// If temp is an empty space, add the buffer to the String array
               // and empty the buffer
            else if (temp == '/' && fracSwitch)
              {
                buffer += temp;
              }// If temp is a /, and fracSwitch is true, add it to the buffer
            else if (temp == '+' || temp == '-' || temp == '*' || temp == '/'
                     || temp == '^' && !fracSwitch)
              {
                operators[opIterator] = temp;
                opIterator++;
                i++;
              }// If temp is an operand, add it to the operators array and
               // increment i to skip the white space
            else
              {
                buffer += temp;
                fracSwitch = true;
              }// Else add the temp to the buffer
          }// End for loop
           // Add the last buffer to the fractions array
        fractions[fracIterator] = buffer;
        // Convert the first fraction in the fractions strings into a
        // Fraction object.
        Fraction result;
        Fraction operand;
        if (isStorage(fractions[0]) > -1)
          {
            result = new Fraction(this.storage[isStorage(fractions[0])]);
          } // if
        else
          {
            result = new Fraction(fractions[0]);
          } // else
        // Loop through all the operands
        for (int i = 1; i <= opIterator; i++)
          {
            if (isStorage(fractions[i]) > -1)
              {
                operand = new Fraction(this.storage[isStorage(fractions[i])]);
              } // if
            else
              {
                operand = new Fraction(fractions[i]);
              } // else
            //Fraction operand = new Fraction(fractions[i]);
            // Switch statement to do the operations
            switch (operators[i - 1])
              {
                case '+':
                  result = result.add(operand);
                  break;
                case '-':
                  result = result.subtract(operand);
                  break;
                case '*':
                  result = result.multiply(operand);
                  break;
                case '/':
                  result = result.divide(operand);
                  break;
                case '^':
                  result = result.pow(Integer.parseInt((fractions[i])));
                  break;
              } // End switch statement
          } // End calculation for-loop
        return result;
      } // else
  }// evaluate(String)

  public int isStorage(String str)
    throws Exception
  {
    int index = -1;
    if (str.length() > 1 && str.charAt(0) == 'r')
      {
        index = Character.getNumericValue(str.charAt(1));
        if (index >= 8)
          {
            throw new Exception("Storage value out of bounds");
          }// If index out of bounds
        else if (this.storage[index] == null)
          {
            throw new Exception("Storage value is empty");
          }// If storage value is null
      }// If r
    return index;
  }// isStorage(String)

  public static void main(String[] args)
    throws Exception
  {
    Calculator myCalc = new Calculator();
    /*
        myCalc.evaluate("r1 = 2");
        System.out.println(myCalc.evaluate("r1"));
    */
    useCalc(myCalc);
  } // main(String[])

}// End Class Calculator
