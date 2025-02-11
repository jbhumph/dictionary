package logic;
// create a binary tree class
    // add node
    // delete node
    // return count
    // return lowest element
    // return highest element

import java.util.ArrayList;

public class Binary {
    private static class Node<T> {
        String data;
        Node<T> left;
        Node<T> right;
        int count;

        public Node (String data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.count = 1;
        }
    } 

    private Node<String> root;
    private int size;

    public Binary(ArrayList<String> dictionary) {
        this.root = null;
        this.size = 0;
    }

    public Node<String> getRoot() {
        // return root
        return root;
    }

    public void insert(String data, ArrayList<String> dictionary) {
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
                return;
            }
            parent = current;
            if (data.compareTo(current.data) < 0) {
                current = current.left;
                if (current == null) {
                    parent.left = newNode;
                    size++;
                    return;
                }
            } else if (data.compareTo(current.data) > 0) {
                current = current.right;
                if (current == null) {
                    parent.right = newNode;
                    size++;
                    return;
                }
            } else {
                current.count++;
                return;
            }
        }
    }

    public void printTree(Node<String> node) {
        // traverse tree in order and print
        if (node != null) {
            printTree(node.left);
            System.out.println(node.data);
            printTree(node.right);
        }
    }

    public boolean match(String word, ArrayList<String> dictionary, int start, int end) {
        // recursively checks if word is in dictionary using binary search
        if (start > end) {
            return false;
        }
        int mid = (start + end) / 2;
        if (word.compareTo(dictionary.get(mid)) < 0) {
            return match(word, dictionary, start, mid - 1);
        } else if (word.compareTo(dictionary.get(mid)) > 0) {
            return match(word, dictionary, mid + 1, end);
        } else {
            return true;
        }
    }
}
