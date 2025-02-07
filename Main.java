// main program to scan in multiple txt files
    // ui welcome screen
    // choose from built in dictionary or scan in your own
        // run function for either
    // choose between single or bulk file scan
        // if single determine file and run single scan
        // if bulk, determine folder and loop over individual docs
    // choose to remove a document
        // choose from list of previous files
        // select a new file
    // determine if scanning complete
        // if complete move on to DISPLAY section
        // if not, re-prompt for scanning options

    // Display options: 
        // print words that match dictionary
        // print words that did not match
        // print summary (Total words, match count, non match count)
        // export matched words to .txt file
        // export words that did not match to .txt file



import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            File file = new File("dictionary.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                System.out.println(data);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}