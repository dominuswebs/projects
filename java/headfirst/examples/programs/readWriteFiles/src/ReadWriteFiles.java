package readWriteFiles.src;
/*
 * In terminal navigate to the root folder of the project (readWriteFiles) then run:
 * javac -d bin -cp ../../../lib/* src/*.java ( since we need to include external libraries)
 * java -cp bin readWriteFiles.src.ReadWriteFiles 
 */

// since we are using a library we need to include all the dependacies
// javac -d bin -cp ../../../lib/opencsv-5.7.1.jar:../../../lib/commons-text-1.12.0.jar:../../../lib/commons-beanutils-1.9.4.jar:../../../lib/commons-lang3-3.16.0.jar:../../../lib/commons-collections4-4.5.0-M2.jar src/*.java 
// java -cp bin:../../../lib/opencsv-5.7.1.jar:../../../lib/commons-text-1.12.0.jar:../../../lib/commons-beanutils-1.9.4.jar:../../../lib/commons-lang3-3.16.0.jar:../../../lib/commons-collections4-4.5.0-M2.jar readWriteFiles.src.ReadWriteFiles 


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class ReadWriteFiles {
    
    public static void main(String[] args) {

        // new WriteToFile().writeString("Testing again 2\n");

        new ReadFromFile().readString();

        new ReadFromFile().readCsv();
    }
}

class WriteToFile {
    
    public void writeString(String text) {
        try {
            // if the file does not exist, create it. Second parameter determines if existing text is replaced or appended to.
            FileWriter writer = new FileWriter("StringExample.txt", true); 
            writer.write(text);
            writer.close();
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
// https://www.digitalocean.com/community/tutorials/java-read-file-line-by-line
class ReadFromFile {
    public void readString() {
        try {

            /*
             * Using streams API
             */

            Files.lines(Path.of("MyText.txt"))
                        .forEach(line -> System.out.println(line));
            
            
            /*
             * Using buffers
             */
            /*
            BufferedReader reader = new BufferedReader(new FileReader("StringExample.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			reader.close(); 
            */
            
            /* this will only read one character at a time or a set amount 
            FileReader reader = new FileReader("StringExample.txt");
            reader.read(); 
            reader.close();
            */


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void readCsv() {

        try {

            CSVReader reader = new CSVReader(new FileReader("csvExample.csv"));
    
            // read line by line
            String[] record = null;
    
            while ((record = reader.readNext()) != null) { 
                System.out.println(record[0] + " : " + record[1]);
            }        
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException c) {
            c.printStackTrace();
        }
    }
}
