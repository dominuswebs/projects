package fileCreator.src;

import java.util.regex.Matcher;

// from the project root
// javac -d bin src/*.java 
// java -cp bin fileCreator.src.App

/*
 * 
 * 1. String manipulation class - create random string, toupper, tolower, reverse
 * 2. Write to file
 * 3. Read from file
 *
 */

public class App {
    
    public static void main(String[] args) {
        System.out.println("Starting!");

        System.out.println(StringHelper.uppercase(StringHelper.randomString(30)));

        String s = "abcdertdede";

        System.out.println(StringHelper.reverse(s));

        if(StringHelper.exists(s, "[efg]")) {
            System.out.println("Match found!");
        } else {
            System.out.println("Match not found!");
        }

        Matcher match = StringHelper.findS(s, "[efg]");

        // at this point no actual matching has been done. The match object has the input sequence and the pattern only
        // we need to trigger the search by using .find(). It updates the state of the Matcher to reflect the position of the match, if found.

        if(match.find()) {
            // first match. from index 0
            System.out.println(match.start());

            while(match.find()) { // let's see if there are more.
                System.out.println(match.start());
            }
            // we reached the end of the sequence, no more matches. If we want to start again we can use
            // matcher.reset(); Resets the matcher to the beginning of the input sequence

            System.out.println("No more matches found");

        } else {
            System.out.println("No matches found");
        }

        
    }
}
