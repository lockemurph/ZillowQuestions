package stringtolong;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  Contains a function that converts a String to a long and functions to test
 * it
 * @author Brian Murphy
 */
public class StringToLongUtil
{

    /**
     * Runs various tests on the convertStringToLong method
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
       testInput("0");
       testInput("-0");
       testInput("1");     
       testInput("-1");
       testInput("987654321");
       testInput("-987654321");
       testInput(String.valueOf(Long.MAX_VALUE));
       testInput(String.valueOf(Long.MIN_VALUE));
       testInput(String.valueOf(Long.MAX_VALUE-1));
       testInput(String.valueOf(Long.MIN_VALUE+1));
       
       expectNull(null);
       expectNull("one hundred");
       expectNull("1-");
       expectNull("--1");
       expectNull("1apple");
       expectNull("1 1 1");
       expectNull("9223372036854775808") ; //Long.MAX_VALUE + 1
       expectNull("-9223372036854775809") ; //Long.MIN_VALUE -1
       expectNull("9999999999999999999"); //19 digit number
       expectNull("-9999999999999999999");
       expectNull("9999999999999999999999999999999999999999999999999999999999");
       
    }
    
    /**
     * Used for testing convertStringToLong.  Prints the long and success if 
     * convertStringToLong returns the same value as Long.parseLong.  Prints 
     * failure otherwise
     * @param input the String to convert to a long
     */
    public static void testInput(String input)
    {
        if(Long.compare(Long.parseLong(input),(convertStringToLong(input)))==0)
        {
            System.out.println(convertStringToLong(input) + " Success");
        }
        else
        {
            System.out.println(input + " Failure");
        }
    }
    /**
     * Used for testing failure cases of convertStringtoLong.  Prints the input
     * and "returned null, Success" if convertStringtoLong returns null, prints
     * the input and "returned something else, Failure" otherwise
     * @param input the input to be tested
     */
    private static void expectNull(String input)
    {
        if(convertStringToLong(input) == null)
        {
            System.out.println(input + " returned null, Success");
        }
        else
        {
            System.out.println(input + " returned somethine else, Failure");
        }
    }
    
    /**
     * Takes a String and converts it into a Long.  Limitations - Must be a 
     * valid Long format.  -0 is returned as 0.  Does not handle leading 0s
     * Runs in O(n) where n is the number of characters in the String.  However,
     * n will never be greater than 20, as the longest Long is a - followed by 
     * 19 digits. 
     * @param input the String to be converted into a Long
     * @return the Long represented by the String.  If input is not in valid
     * Long format e.g. not an integer, contains letters, is greater than 
     * Long.MAX_VALUE or less than Long.MIN_VALUE, returns null
     */
    private static Long convertStringToLong(String input)
    {
        long multiplier = 1;
        Long result = 0L;
        //Check if the String is in a valid format, otherwise return null
        if (!isValidLongFormat(input))
        {
            result = null;
        }
        else
        {
            //From least significant digit to most, add the values in the String
            //to get the absolute value of the input.
            for (int cnt = input.length() - 1; cnt >= 0; cnt--)
            {
                //currently we are getting the abs of the integer, so skip the sign
                //for now.  Add the value at its order of magnitude
                if (input.charAt(cnt) != '-')
                {
                    result +=
                       (convertChatToLong(input.charAt(cnt)) * multiplier);
                    multiplier *= 10;
                }
            }
            //make the result negative if we have a negative number
            if (input.charAt(0) == '-')
            {
                result *= -1;
            }
            //If there was an overflow, a negative number will be positive and vice
            //versa
            if ((input.charAt(0) == '-' && result > 0L)
                || (input.charAt(0) != '-' && result < 0L))
            {
                result = null;
            }
        }
        return result;
    }

    /**
     * Converts a char to a long
     * @param number a char between 0 and 9
     * @return the long represented by the char, 9 if not between 0 and 9
     */
    private static long convertChatToLong(char number)
    {
        switch (number)
        {
            case '0':
                return 0;
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            default:
                return 9;
        }
    }

    /**
     * Determines if a String is in the format that can be converted to a long
     *
     * @param input the String to be determined if it can converted successfully
     * to a long
     * @return true if the String is a integer between Long.MAX_LONG and
     * Long.MIN_LONG. Returns false if the String is null, not an integer, or
     * outside of the range
     */
    private static boolean isValidLongFormat(String input)
    {
        if (input == null)
        {
            return false;
        }
        //Regex for a long, 1 or 0 '-' followed by a 1-9, followed by 0 to 18
        //digits, or a 0 or -0
        String longRegex = "^-?[1-9]\\d{0,18}$|^-?0$";
        Pattern longPattern = Pattern.compile(longRegex);
        Matcher longMatch = longPattern.matcher(input);
        return longMatch.matches();
    }

}
