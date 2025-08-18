import java.util.*;
import java.util.stream.*; // this imports a package ( collection of classes )
// import static java.util.stream.Collectors.*; // this imports all methods of a class. Can use the methods without the ClassName
import static java.util.stream.Collectors.toList;

class Songs {
    public List<Song> getSongs() {
        return List.of(
            new Song("$10", "Hitchhiker", "Electronic", 2016, 183),
            new Song("Havana", "Camila Cabello", "R&B", 2017, 324),
            new Song("Cassidy", "Grateful Dead", "Rock", 1972, 123),
            new Song("50 ways", "Paul Simon", "Soft Rock", 1975, 199),
            new Song("Hurt", "Nine Inch Nails", "Industrial Rock", 1995, 257),
            new Song("Silence", "Delerium", "Electronic", 1999, 134),
            new Song("Hurt", "Johnny Cash", "Soft Rock", 2002, 392),
            new Song("Watercolour", "Pendulum", "Electronic", 2010, 155),
            new Song("The Outsider", "A Perfect Circle", "Alternative Rock", 2004, 312),
            new Song("With a Little Help from My Friends", "The Beatles", "Rock", 1967, 168),
            new Song("Come Together", "The Beatles", "Blues rock", 1968, 173),
            new Song("Come Together", "Ike & Tina Turner", "Rock", 1970, 165),
            new Song("With a Little Help from My Friends", "Joe Cocker", "Rock", 1968, 46),
            new Song("Immigrant Song", "Karen O", "Industrial Rock", 2011, 12),
            new Song("Breathe", "The Prodigy", "Electronic", 1996, 337),
            new Song("What's Going On", "Gaye", "R&B", 1971, 420),
            new Song("Hallucinate", "Dua Lipa", "Pop", 2020, 75),
            new Song("Walk Me Home", "P!nk", "Pop", 2019, 459),
            new Song("I am not a woman, I'm a god", "Halsey", "Alternative Rock", 2021, 384),
            new Song("Pasos de cero", "Pablo Alborán", "Latin", 2014, 117),
            new Song("Smooth", "Santana", "Latin", 1999, 244),
            new Song("Immigrant song", "Led Zeppelin", "Rock", 1970, 484)
        );
    }
}

class Song {

    private final String title;

    private final String artist;

    private final String genre;

    private final int year;

    private final int timesPlayed;

    Song(String title, String a, String genre, int year, int timesPlayed) {
        // we use "this" here because the parameter name is the same as the instance variables
        this.title = title;
        this.artist = a;
        this.genre = genre;
        this.year = year;
        this.timesPlayed = timesPlayed;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public int getTimesPlayed() {
        return timesPlayed;
    }

    public String toString() {
        return title;
    }
}

class DeluxeJukeBox {
    public static void main(String[] args) {

        List<Song> songs = new Songs().getSongs();

        // songs.forEach( (s) -> System.out.println(s.getTitle() + " " + s.getTimesPlayed()));

        // get all rocks songs and put them in a new List

        List<Song> rocksSongs = songs.stream()
                                     // .filter( s -> s.getGenre().toLowerCase().equals("rock")) // matches only to rock
                                     .filter( s -> s.getGenre().toLowerCase().contains("rock")) // matches all genres that contain rock in the name. soft rock, industrial rock and so on
                                     .collect(Collectors.toList());
        
        rocksSongs.forEach( (s) -> System.out.println(s.getTitle() + " - " + s.getGenre()  + " - " + s.getTimesPlayed()));

        // get all songs by the beatles

        List<Song> beatlesSongs = songs.stream()
                                       .filter( s -> s.getArtist().toLowerCase().equals("the beatles"))
                                       .collect(Collectors.toList());

        beatlesSongs.forEach( (s) -> System.out.println(s.getArtist() + " - " + s.getTitle()  + " - " + s.getTimesPlayed()));

        // get all songs after 1995

        List<Song> ninetiesSongs = songs.stream()
                                        .filter( s -> s.getYear() > 1995)
                                        .sorted(Comparator.comparing(Song::getYear).reversed()) // this is another way by useing a comparator
                                        // .sorted( (s1, s2) -> s1.getYear() - s2.getYear()) // lambda uses "compare" which is the SAM, Returns: a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
                                        .collect(Collectors.toList());

        ninetiesSongs.forEach( (s) -> System.out.println(s.getYear() + " - " + s.getTitle()  + " - " + s.getTimesPlayed()));


        List<Song> hSongs = songs.stream()
                                 .filter(s -> s.getTitle().substring(0, 1).equals("H"))
                                 .collect(Collectors.toList());
        
        hSongs.forEach( (s) -> System.out.println(s.getYear() + " - " + s.getTitle()  + " - " + s.getTimesPlayed()));


        // get all the genres
        List<String> genres = songs.stream()
                                   .map( s -> s.getGenre() ) // after this we only have genres 
                                   .distinct()
                                   .filter( genre -> !genre.equals("Latin")) // genre is a string so has access to equals
                                   .collect(toList()); // we can use this without Collector. in front because of the static import at the top of the file
        
        System.out.println(genres);

    }
}
