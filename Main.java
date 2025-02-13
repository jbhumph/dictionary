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

        // create BST
        Binary tree = new Binary(dictionary);

        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        while (running) {
            running = menu(tree, dictionary, scanner);
        }

        scanner.close();

        
        


    
    }

    public static boolean menu(Binary tree, ArrayList<String> dictionary, Scanner scanner) throws FileNotFoundException {
        System.out.println("\nWelcome to Docu-Scan!\n");
        System.out.println("SCANNING MENU:\n");
        System.out.println("1.   Select an alternative dictionary\n");
        System.out.println("2.   Scan from document or folder\n");
        System.out.println("3.   Remove document from data\n");
        System.out.println("4.   Finish scanning and proceed to display options\n");
        System.out.println("5.   Quit scanning\n\n");

        System.out.println("Please enter an option below (1 - 5)");
        int menuA = scanner.nextInt();
        while (!numRange(menuA, 1, 5)) {
            System.out.println("Invalid option. Please enter a number between 1 and 5.");
            menuA = scanner.nextInt();
        }
        switch (menuA) {
            case 1:
                // select a file for new dictionary
                System.out.println("Please enter a pathname for the new dictionary.");
                scanner.nextLine();
                String newPath = scanner.nextLine();
                File newFile = new File(newPath);
                if (newFile.exists()) {
                    scanArray(newFile, dictionary);
                } else {
                    System.out.println("File does not exist.");
                }
                break;
            case 2:
                // single or bulk file scan
                System.out.println("1.   Single file scan\n");
                System.out.println("2.   Bulk file scan\n");
                System.out.println("Please enter an option below (1 or 2)");
                int menuB = scanner.nextInt();
                while (!numRange(menuB, 1, 2)) {
                    System.out.println("Invalid option. Please enter a number between 1 and 2.");
                    menuB = scanner.nextInt();
                }
                switch (menuB) {
                    case 1:
                        // single file scan
                        System.out.println("Please enter the pathname for the file you would like to scan.");
                        scanner.nextLine();
                        String filePath = scanner.nextLine();
                        File toScan = new File(filePath);
                        if (toScan.exists()) {
                            scanFile(tree, toScan, dictionary);
                        } else {
                            System.out.println("File does not exist.");
                        }
                        tree.printTree(tree.getRoot());
                        break;
                    case 2:
                        // bulk file scan
                        System.out.println("Please enter the pathname for the folder you would like to scan.");
                        scanner.nextLine();
                        String folderPath = scanner.nextLine();
                        File folder = new File(folderPath);
                        if (folder.exists()) {
                            File[] files = folder.listFiles();
                            for (File f : files) {
                                scanFile(tree, f, dictionary);
                            }
                        } else {
                            System.out.println("Folder does not exist.");
                        }
                        break;
                }
                break;
            case 3:
                System.out.println("Please enter the pathname for the file you would like to remove.");
                scanner.nextLine();
                String removePath = scanner.nextLine();
                File removeFile = new File(removePath);
                if (removeFile.exists()) {
                    scanDelete(tree, removeFile, dictionary);
                } else {
                    System.out.println("File does not exist.");
                }
                break;
            case 4:
                System.out.println("You have selected to finish scanning and proceed to display options.");
                break;
            case 5:
                System.out.println("You have selected to quit scanning.");
                return false;
        }
        return true;
    }
        
}