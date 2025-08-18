import java.util.*;

public class TestSongs {
    public static List<String> getSongs() {
        List<String> songs = new ArrayList<String>();
        songs.add("somersault");
        songs.add("cassidy");
        songs.add("$10");
        return Collections.unmodifiableList(songs);
    }

    public static void main(String[] args) {
        List<String> songs = TestSongs.getSongs();
        for (String song : songs) {
            System.out.println(song);
        }

        Runnable runnable = () -> System.out.println("No class");
        
        Function<String, Integer> f = s -> s.length();
    }
} 