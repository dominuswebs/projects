import java.util.*;

// custom comparator
class ArtistCompare implements Comparator<Song> {
    public int compare(Song one, Song two) { // this method is from Comparator, similar to compareTo from Comparable
        return one.getArtist().compareTo(two.getArtist());
    }
}

class Song implements Comparable<Song>{ // we need to implement Comparable to use sort. We specify the type that the implementing class can be compared against

    private String title;
    private String artist;
    private int bpm;

    Song(String title, String artist, int bpm) { // see constructor
        this.title = title;
        this.artist = artist;
        this.bpm = bpm;
    }
    // this works but only compares song title, not artist or bpm 
    // when using lambdas it overrides the body of the method? 
    public int compareTo(Song s) { // this method is from Comparable
        // System.out.println("Comparing " + title + " with " + s.getTitle()); // uncomment to see the recursion.
        return title.compareTo(s.getTitle()); // string1.compareTo(string2)
    }
    
    public boolean equals(Object aSong) { // we override equals. When we override a method we need to keep the same number of parameters, parameter type and return type
        Song other = (Song) aSong;
        return title.equals(other.getTitle());
    }

    public int hashCode() { // we also override hashcode
        return title.hashCode();
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getBpm() {
        return bpm;
    }

    public String toString() { // overriding the Object toString method
        return title;
    }
}

class MockSongs {
    public static List<Song> getSongs() {

        List<Song> songs = new ArrayList<>(); // type inference

        songs.add(new Song("somersault", "zero 7", 147));
        songs.add(new Song("cassidy", "grateful dead", 158));
        songs.add(new Song("$10", "hitchhiker", 140));
        songs.add(new Song("havana", "cabello", 105));
        songs.add(new Song("Cassidy", "grateful dead", 158));
        songs.add(new Song("50 ways", "simon", 102));
        return songs;
    }
}
// this class contains repeats
class MoreMockSongs {

    public static List<Song> getSongs() {

        List<Song> songs = new ArrayList<>(); // type inference

        songs.add(new Song("somersault", "zero 7", 147));
        songs.add(new Song("cassidy", "grateful dead", 158));
        songs.add(new Song("$10", "hitchhiker", 140));
        songs.add(new Song("havana", "cabello", 105));
        songs.add(new Song("Cassidy", "grateful dead", 158));
        songs.add(new Song("$10", "hitchhiker", 140));
        songs.add(new Song("50 ways", "simon", 102));
        songs.add(new Song("$10", "hitchhiker", 140));
        return songs;
    }
}


class Jukebox {
    public static void main(String[] args) {
        new Jukebox().go();
    }
        
    public void go() {
        List<Song> songList = MockSongs.getSongs();
        System.out.println(songList);
        /*
        *   Sorting using a specific class ArtistCompare or the override method compareTo in Song 
        *
        Collections.sort(songList); // sort method can only take lists of Comparable objects. This is why we implement Comparable in Song. It uses compareTo to sort. 
        System.out.println(songList);

        ArtistCompare artistCompare = new ArtistCompare();
        songList.sort(artistCompare); // sort will use compare method from ArtistCompare class.
        System.out.println(songList);
        */

        /*  
        Here we use lambdas. This means the class ArtistCompare is unnecessary. 
        We still require to implement the compareTo method but the body of it will be replaced by the right side of the -> 
        This is why it works for artist and bpm even though the method definition above only uses title
        */
        songList.sort((one, two) -> one.getTitle().compareTo(two.getTitle()));
        System.out.println(songList);

        songList.sort((one, two) -> one.getArtist().compareTo(two.getArtist()));
        System.out.println(songList);

        songList.sort((one, two) -> one.getBpm() - two.getBpm());
        System.out.println(songList);

        /*
         * Using HashSet to remove duplicates 
         */

        List<Song> moreSongs = MoreMockSongs.getSongs();
        System.out.println("Before removing doubles " + moreSongs);
        Set<Song> songSet = new HashSet<>(moreSongs);
        System.out.println("After removing doubles " + songSet);
        // if we want to sort it we use TreeSet
        Set<Song> sortedSet = new TreeSet<>(moreSongs); // this will use the compareTo method from Song.
        System.out.println("After removing doubles and sorted by title " + sortedSet);
        // We can pass a lambda or a comparator if we want to use a different sort
        Set<Song> anotherSortedSet = new TreeSet<>((o1, o2) -> o1.getBpm() - o2.getBpm()); // lambda with the comparison to be used with this TreeSet
        anotherSortedSet.addAll(moreSongs); // then we add all the objects to the set
        System.out.println("After removing doubles and sorted by bpm " + anotherSortedSet); // we lose Cassidy because it has the same bpm as cassidy
    }
}
