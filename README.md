# Docu Scan
Docu-Scan is a file scanning and dictionary application written in Java for CS 240 at WCC.

```
 ______   ______   ______   __  __             ______   ______   ________   ___   __      
/_____/\ /_____/\ /_____/\ /_/\/_/\           /_____/\ /_____/\ /_______/\ /__/\ /__/\    
\:::_ \ \\:::_ \ \\:::__\/ \:\ \:\ \   _______\::::_\/_\:::__\/ \::: _  \ \\::\_\\  \ \   
 \:\ \ \ \\:\ \ \ \\:\ \  __\:\ \:\ \ /______/\\:\/___/\\:\ \  __\::(_)  \ \\:. `-\  \ \  
  \:\ \ \ \\:\ \ \ \\:\ \/_/\\:\ \:\ \\__::::\/ \_::._\:\\:\ \/_/\\:: __  \ \\:. _    \ \ 
   \:\/.:| |\:\_\ \ \\:\_\ \ \\:\_\:\ \           /____\:\\:\_\ \ \\:.\ \  \ \\. \`-\  \ \
    \____/_/ \_____\/ \_____\/ \_____\/           \_____\/ \_____\/ \__\/\__\/ \__\/ \__\/
                                                                                          
```                                                                                          


## Authors:

John Humphrey

With contributors: Adam Sabet and Jing Lin Lai


## Contents:

- [Authors](#authors)
- [Contents](#contents)
- [Overview](#overview)
- [Instructions](#instructions)
- [About](#about)
- [Attributions](#attributions)
- [Screenshots](#screenshots)


## Overview:

Docu-Scan is a file-scanning dictionary written in Java. The program allows users to scan in single or bulk documents to a single data-structure, compared against a dictionary. 
There is an included dictionary available, but users may supply their own. Users may also delete whole documents from the structure as well. 
Once the structure is populated, users may run any number of actions against it. Some of these actions include: Printing a general data overview (docs scanned, number of words, etc), printing the structure, exporting unique words to a .txt file etc.


## Instructions:

At the moment, Docu-Scan is meant to be run from the terminal from Main.java.

Several parts of the application require the user to input the location of documents to scan. You are welcome to scan from anywhere on your system. 
The application provides a number of example documents, and their input may look like this:

```
./data/dictionary.txt
```

or

```
./sample_texts/frankenstein.txt
```

At other points the application may only ask for a directory, such as with batch scanning or writing to a .txt file. 
This may be entered as the following:

```
./data
```

The application should continue to return to the main menu until the user exits the application.


## About:

This application uses a Binary Search tree as it's primary data structure. I wrote my own custom version here as I needed specific applications and integrated traversals for the program while shaving off functions that were not needed. 

The dictionary section of the application scans a .txt file and parses it for words set apart by a new line. It then adds each word into an array. The array is accessed and searched via a binary search function in Binary.java.

The scanning is run by a scan function in ScanDoc.java which the calls an insert function in Binary.java for each word input while checking against a dictionary. Batch processing merely runs the same process for each .txt file in scanned file.

ScanDoc.java
```
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
```

Binary.java
```
public void insert(String data, ArrayList<String> dictionary, int[] count) {
        // insert data into tree
        Node<String> newNode = new Node<String>(data);
        if (root == null) {
            root = newNode;
            size++;
            return;
        }
        Node<String> current = root;
        Node<String> parent = null;
        while (true) {
            if (!match(data, dictionary, 0, (int) dictionary.size() - 1)) {
                count[3]++;
                count[1]++;
                return;
            }
            parent = current;
            if (data.compareTo(current.data) < 0) {
                current = current.left;
                if (current == null) {
                    parent.left = newNode;
                    size++;
                    count[1]++;
                    count[2]++;
                    return;
                }
            } else if (data.compareTo(current.data) > 0) {
                current = current.right;
                if (current == null) {
                    parent.right = newNode;
                    size++;
                    count[1]++;
                    count[2]++;
                    return;
                }
            } else {
                current.count++;
                count[1]++;
                if (current.count > highestCount) {
                    highestCount = current.count;
                    highestWord = current.data;
                }
                return;
            }
        }
    }
```


## Attributions:

Docu-Scan ASCII art is Swamp Land from [Patorjk](https://patorjk.com/software/taag/#p=display&f=Swamp%20Land&t=Docu-Scan)

10000 word example dictionary is from [MIT Wordlist](https://www.mit.edu/~ecprice/wordlist.10000)

Public domain sample texts are from [Project Gutenberg](https://www.gutenberg.org/browse/scores/top)

## Screenshots:

Screenshot of programs Title and Main Menu:

documentation/Screenshot 2025-02-17 at 11.01.27 AM.png

Scrrenshot of successful batch scan with scan data output

documentation/Screenshot 2025-02-17 at 11.02.23 AM.png

