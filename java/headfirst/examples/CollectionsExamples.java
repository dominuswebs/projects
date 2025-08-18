// Collection Framework
// Sets

// Lists

// Maps (do not implement the Collection interface)
import java.util.*;

class Songs {
    public static List<String> getSongs() {
        List<String> songs = new ArrayList<String>();
        songs.add("somersault");
        songs.add("cassidy");
        songs.add("$10");
        return Collections.unmodifiableList(songs); // this kept breaking because initially I named my main class Collections. It was a conflict.
    }
}

class CollectionsExamples {
    public static void main(String[] args) {

        Integer[] numbers = {2, 3, 6, 1, 4, 2, 10};

        String[] words = {"one", "two", "three", "four"};
 

        /*
         * LISTS
         */

        // create a List from an array
        List<Integer> numbersList = new ArrayList<>(Arrays.asList(numbers));
        List<String> wordsList = new ArrayList<>(Arrays.asList(words));
        
        System.out.println("List numbers:\n");
        
        for(int a : numbersList) {
            System.out.println(a);
        }

        System.out.println("\nList words:\n");

        for(String w : wordsList) {
            System.out.println(w);
        }

        /*
         * SETS
         */
        
        Set<Integer> numbersHash = new HashSet<>(Arrays.asList(numbers));
        Set<String> wordsHash = new HashSet<>(Arrays.asList(words));
        
        System.out.println("\nHashSet numbers\n");
        // this actually comes out sorted because for int and double, these are auto-boxed into the Integer and Double classes. 
        // When you make a HashSet of ints, it uses Integer's hashCode() method, which just returns the int. 
        // So if you add ints, they get stored sorted. But for double, Double's hashCode() method is much more complicated, because of the way doubles are represented in memory.
        for(int b : numbersHash) {
            
            System.out.println(b);
        }

        System.out.println("\nHashSet words\n");
        // The element order is not assured. This is normal behaviour.
        for(String w  : wordsHash) {
            System.out.println(w);
        }

        // to ensure sorting we use TreeSet
        Set<String> wordsTree = new TreeSet<>(Arrays.asList(words));
        System.out.println("\nTreeSet words\n");
        // this is now ordered 
        for(String w  : wordsTree) {
            System.out.println(w);
        }
     
        System.out.println("\nTreeSet words reversed using lambda\n");
        wordsTree = new TreeSet<>((w1, w2) -> w2.compareTo(w1));
        wordsTree.addAll(Arrays.asList(words));
        // this is now ordered in reverse using a lambda.
        for(String w  : wordsTree) {
            System.out.println(w);
        }
        // we can also reverse it using a method
        // If we this we lose Polymorphism advantage, since the variable type can no longer be Set but TreeSet
        System.out.println("\nTreeSet words reversed using method\n");
        TreeSet<String> newWordsTree = new TreeSet<>(Arrays.asList(words));
    
        for(String w  : newWordsTree.descendingSet()) {
            System.out.println(w);
        }

        // this also works
        // newWordsTree.forEach((word) -> System.out.println(word));

        /*
         * MAPS (similar to associative arrays)
         */
        
        Map<String, Integer> scores = new HashMap<>(); // key, value

        scores.put("Kathy", 42);
        scores.put("John", 412);
        scores.put("Skylar", 142);

        System.out.println(scores);
        System.out.println(scores.get("Skylar"));

        scores.forEach((key, value) -> System.out.println("Key: " + key + " value: " + value));

    

        /*
        * Creating an unmodifiable list
        */

        List<String> songs = Songs.getSongs();

        System.out.println(songs);

        try {
            songs.add("Booga Booga");
            System.out.println(songs);

        } catch (Exception e) {
            System.out.println("Cannot modify list " + e);

        }
    }
}
