// from filesAndDirectories
// javac test/Installer.java
// java test.Installer 


package test;

import java.nio.file.*;

public class Installer {
    public static void main(String[] args) {
        try {
            Path myPath = Paths.get("MyApp");
            Path myPath2 = Paths.get("MyApp", "media");
            Path myPath3 = Paths.get("MyApp", "source");
            Path mySource = Paths.get("instructions.txt");
            Path myMedia = Paths.get("image.png");


            newDirectory(myPath);
            newDirectory(myPath2);
            newDirectory(myPath3);

            // move
            // Files.move(mySource, myPath3.resolve(mySource.getFileName()));
            // Files.move(myMedia, myPath2.resolve(myMedia.getFileName()));
            // copy
            Files.copy(mySource, myPath3.resolve(mySource.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            Files.copy(myMedia, myPath2.resolve(myMedia.getFileName()), StandardCopyOption.REPLACE_EXISTING);
        } catch (FileAlreadyExistsException e) {
            System.out.println("Directory already exists: " + e.getMessage()); 
        } catch (Exception e) {
            System.out.println("Got an NIO Exception" + e.getMessage() + " " + e.getCause());
        }
    }

    private static void newDirectory(Path path) throws FileAlreadyExistsException, Exception {
        if (Files.exists(path) && Files.isDirectory(path)) {
            System.out.println(path.toString() + " already exists");
        } else {
            // if we use createDirectories instead it will not throw an error if it already exists
            Files.createDirectory(path);
        }
    }

}
