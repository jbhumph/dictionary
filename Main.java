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

import static logic.Validate.*;

import static logic.Function.*;
import static logic.ScanDoc.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import logic.Binary;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // create initial dictionary
        ArrayList<String> dictionary = new ArrayList<String>();
        File file = new File("./data/dictionary.txt");
        scanArray(file, dictionary);

        Binary tree = new Binary(dictionary);

        boolean running = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWelcome to Docu-Scan!\n");

        while (running) {
            running = menu(tree, dictionary, scanner);
        }

        scanner.close();
    }

    public static boolean menu(Binary tree, ArrayList<String> dictionary, Scanner scanner) throws FileNotFoundException {
        System.out.println("\n\n\nSCANNING MENU:\n");
        System.out.println("1.   Select an alternative dictionary\n");
        System.out.println("2.   Scan from document or folder\n");
        System.out.println("3.   Remove document from data\n");
        System.out.println("4.   Finish scanning and proceed to display options\n");
        System.out.println("5.   Quit scanning\n\n");

        System.out.println("Please enter an option below (1 - 5)");
        int menuA = scanner.nextInt();
        while (!numRange(menuA, 1, 5)) {
            System.out.println("\nInvalid option. Please enter a number between 1 and 5.");
            menuA = scanner.nextInt();
        }
        switch (menuA) {
            case 1:
                // select a file for new dictionary
                System.out.println("\nPlease enter a pathname for the new dictionary.");
                scanner.nextLine();
                String newPath = scanner.nextLine();
                File newFile = new File(newPath);
                if (newFile.exists()) {
                    scanArray(newFile, dictionary);
                } else {
                    System.out.println("\nFile does not exist.");
                }
                break;
            case 2:
                // single or bulk file scan
                System.out.println("\n1.   Single file scan\n");
                System.out.println("2.   Bulk file scan\n");
                System.out.println("Please enter an option below (1 or 2)");
                int menuB = scanner.nextInt();
                while (!numRange(menuB, 1, 2)) {
                    System.out.println("\nInvalid option. Please enter a number between 1 and 2.");
                    menuB = scanner.nextInt();
                }
                int count[] = {0, 0, 0, 0};
                switch (menuB) {
                    case 1:
                        // single file scan
                        System.out.println("\nPlease enter the pathname for the file you would like to scan.");
                        scanner.nextLine();
                        String filePath = scanner.nextLine();
                        File toScan = new File(filePath);
                        long duration = 0;
                        if (toScan.exists()) {
                            long startTime = System.currentTimeMillis();
                            scanFile(tree, toScan, dictionary, count);
                            long endTime = System.currentTimeMillis();
                            duration = endTime - startTime;
                        } else {
                            System.out.println("\nFile does not exist.");
                        }
                        System.out.println("\nFinished Scanning in " + duration + " milliseconds.");
                        System.out.println("Total words scanned: " + count[1] + "; Words in dictionary: " + count[2] + "; Words not in dictionary: " + count[3]);
                        break;
                    case 2:
                        // bulk file scan
                        System.out.println("\nPlease enter the pathname for the folder you would like to scan.");
                        scanner.nextLine();
                        String folderPath = scanner.nextLine();
                        File folder = new File(folderPath);
                        duration = 0;
                        if (folder.exists()) {
                            File[] files = folder.listFiles();
                            long startTime = System.currentTimeMillis();
                            for (File f : files) {
                                System.out.println(f);
                                scanFile(tree, f, dictionary, count);
                            }
                            long endTime = System.currentTimeMillis();
                            duration = endTime - startTime;
                        } else {
                            System.out.println("\nFolder does not exist.");
                            break;
                        }
                        System.out.println("\nFinished Scanning in " + duration + " milliseconds.");
                        System.out.println("Total files scanned: " + count[0] + "; Total words scanned: " + count[1] + "; Words in dictionary: " + count[2] + "; Words not in dictionary: " + count[3]);
                        break;
                }
                break;
            case 3:
                System.out.println("\nPlease enter the pathname for the file you would like to remove.");
                scanner.nextLine();
                String removePath = scanner.nextLine();
                File removeFile = new File(removePath);
                int deleted = 0;
                long duration = 0;
                if (removeFile.exists()) {
                    long startTime = System.currentTimeMillis();
                    deleted = scanDelete(tree, removeFile, dictionary);
                    long endTime = System.currentTimeMillis();
                    duration = endTime - startTime;
                } else {
                    System.out.println("\nFile does not exist.");
                }
                System.out.println("\nDocument has been removed. " + deleted + " words have been deleted in " + duration + " milliseconds.");
                System.out.println(tree.getSize() + " unique words remain in the dictionary.");
                break;
            case 4:
                System.out.println("\nYou have selected to finish scanning and proceed to display options.");
                break;
            case 5:
                System.out.println("\nYou have selected to quit scanning.");
                return false;
        }
        return true;
    }
        
}