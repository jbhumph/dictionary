package logic;
// create a binary tree class
    // add node
    // delete node
    // return count
    // return lowest element
    // return highest element

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Stack;

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
    private int highestCount;
    private String highestWord;

    public Binary(ArrayList<String> dictionary) {
        this.root = null;
        this.size = 0;
        this.highestCount = 0;
        this.highestWord = "";
    }

    public Node<String> getRoot() {
        // return root
        return root;
    }

    public int getSize() {
        // return size
        return size;
    }

    public String getMostCommon() {
        // return most common word
        return highestWord;
    }

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

    public void delete(String data, ArrayList<String> dictionary) {
        // delete data from tree
        Node<String> current = root;
        Node<String> parent = root;
        boolean isLeftChild = false;
        while (current != null && !current.data.equals(data)) {
            parent = current;
            if (data.compareTo(current.data) < 0) {
                isLeftChild = true;
                current = current.left;
            } else {
                isLeftChild = false;
                current = current.right;
            }
        }
        if (current == null) {
            return;
        }
        if (current.count > 1) {
            current.count--;
            return;
        }
        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            }
            if (isLeftChild) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (current.right == null) {
            if (current == root) {
                root = current.left;
            } else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } else if (current.left == null) {
            if (current == root) {
                root = current.right;
            } else if (isLeftChild) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else {
            Node<String> successor = getSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = current.left;
        }
        size--;
    }

    public Node<String> getSuccessor(Node<String> deleteNode) {
        // get successor of node to be deleted
        Node<String> successor = null;
        Node<String> successorParent = null;
        Node<String> current = deleteNode.right;
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.left;
        }
        if (successor != deleteNode.right) {
            successorParent.left = successor.right;
            successor.right = deleteNode.right;
        }
        return successor;
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

    public void export(String filename) {
    try (Writer writer = new OutputStreamWriter(new FileOutputStream(filename), StandardCharsets.UTF_8)) {
        exportNode(root, writer);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

private void exportNode(Node<String> node, Writer writer) throws IOException {
    if (node == null) {
        return;
    }
    Stack<Node<String>> stack = new Stack<>();
    Node<String> current = node;
    while (current != null || !stack.isEmpty()) {
        while (current != null) {
            stack.push(current);
            current = current.left;
        }
        current = stack.pop();
        writer.write(current.data + "\n");
        current = current.right;
    }
}
}
