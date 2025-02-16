package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ScanDoc {
    public static void scanArray(File file, ArrayList<String> dictionary) {
        dictionary.clear();
        try {
            Scanner scanner = new Scanner(file);
            int count = 0;
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                dictionary.add(data);
                count++;
            }
            System.out.println("Dictionary has been updated.");
            System.out.println(count + " words have been added.");
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void scanFile(Binary tree, File file, ArrayList<String> dictionary, int[] count) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter("[\\s,;:.!?-]+");
        while (scanner.hasNext()) {
            String word = scanner.next();
            tree.insert(word, dictionary, count);
        }
        scanner.close();
        count[0]++;
    }

    public static int scanDelete(Binary tree, File file, ArrayList<String> dictionary) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter("[\\s,;:.!?-]+");
        int deleted = 0;
        while (scanner.hasNext()) {
            String word = scanner.next();
            tree.delete(word, dictionary);
            deleted++;
        }
        scanner.close();
        return deleted;
    }
}
