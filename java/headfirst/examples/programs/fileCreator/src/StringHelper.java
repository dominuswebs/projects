package fileCreator.src;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper {

    private StringHelper() { // private constructor so no objects can be instantiated
        throw new UnsupportedOperationException("Utility class");
    }
    
    public static String randomString(int n) {
        
        char[] array = new char[n];
        Random random = new Random();
        
        // Fill the char array with random characters
        for (int i = 0; i < array.length; i++) {
            array[i] = (char) (random.nextInt(26) + 'a'); // Generates a random lowercase letter
        }

        return new String(array); // Convert the char array to a String
    }

    public static String uppercase(String s) {
        return s.toUpperCase();
    }

    public static String reverse(String s) {
        
        /* StringBuilder objects are like String objects, except that they can be modified. 
         * Internally, these objects are treated like variable-length arrays that contain a sequence of characters. 
         * At any point, the length and content of the sequence can be changed through method invocations.
         */
        StringBuilder sb = new StringBuilder();
        sb.append(s);
        return sb.reverse().toString(); 

        /*
         * Using charArray
         */

        // char[] chars = s.toCharArray();
        // String reversedString = "";
        // for(int i = 0; i < chars.length; i++) {
        //    reversedString += chars[chars.length - i - 1];
        // }
        // return reversedString;

        /*
         * Using charAt
         */
        
        // String reversedString = "";
        // for(int i = 0; i < s.length(); i++) {
        //    reversedString += s.charAt(s.length() - i - 1);
        // }
        // return reversedString;
    }

    public static Boolean exists(String s, String p) {
        Pattern pattern = Pattern.compile(p, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(s);
        return matcher.find();
    }

    public static Matcher findS(String s, String p) {
        Pattern pattern = Pattern.compile(p, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(s);
    }
}
