package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ScanDoc {
    public static void scanArray(File file, ArrayList<String> dictionary) {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                dictionary.add(data);
            }
            System.out.println("Dictionary has been updated.");
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void scanFile(Binary tree, File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter("[\\s,;:.!?-]+");
        while (scanner.hasNext()) {
            String word = scanner.next();
            tree.insert(word);
        }

    }
}
