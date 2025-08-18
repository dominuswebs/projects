import java.util.*;
import java.util.stream.*;

// intermediate operations

// terminal operations

class StreamApi {
    public static void main(String[] args) {
        
        List<String> strings = List.of("I", "am", "a", "list", "of", "Strings");
        
        Stream<String> stream = strings.stream(); // create a stream from a list 

        Stream<String> limit = stream.limit(4); // limit the amount of elements we want to operate on. This is an INTERMIDIATE operation. nothing has been done to the elements themselves. 

        // long count = limit.count(); // returns a long not an int. Terminal operation.
        //System.out.println("count = " + count);

        // One of the most common things to do with Streams is put the results into another type of collection

        // this will thrown an error because the stream has been closed in line 17. Just comment that out.
        List<String> result = limit.collect(Collectors.toList()); // method toList outputs the results of a stream into a list.
    
        System.out.println("result = " + result);

        // we can chain stream methods and even pass lambdas to them

        List<String> chainResult = strings.stream()
                                          .sorted((s1, s2) -> s1.compareToIgnoreCase(s2))
                                          .skip(2)
                                          .limit(4)
                                          .collect(Collectors.toList());
        System.out.println(chainResult);

    }
}
