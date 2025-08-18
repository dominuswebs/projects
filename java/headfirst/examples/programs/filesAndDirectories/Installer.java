// from filesAndDirectories
// javac test/Installer.java
// java test.Installer 


import java.nio.file.*;

public class Installer {
    public static void main(String[] args) {
        try {
            Path myPath = Paths.get("MyApp");
            Path myPath2 = Paths.get("MyApp", "media");
            Path myPath3 = Paths.get("MyApp", "source");
            Path mySource = Paths.get("instructions.txt");
            Path myMedia = Paths.get("image.png");

            Files.createDirectory(myPath);
            Files.createDirectory(myPath2);
            Files.createDirectory(myPath3);
            // move
            // Files.move(mySource, myPath3.resolve(mySource.getFileName()));
            // Files.move(myMedia, myPath2.resolve(myMedia.getFileName()));
            // copy
            Files.copy(mySource, myPath3.resolve(mySource.getFileName()));
            Files.copy(myMedia, myPath2.resolve(myMedia.getFileName()));
        } catch (Exception e) {
            System.out.println("Got an NIO Exception" + e.getMessage() + " " + e.getCause());
        }
    }
}
