// create a binary tree class
    // add node
    // delete node
    // return count
    // return lowest element
    // return highest element

public class Binary {
    private static class Node<T> {
        String data;
        Node<T> left;
        Node<T> right;

        public Node (String data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    } 

    private Node<String> root;
    private int size;

    public Binary() {
        this.root = null;
        this.size = 0;
    }

    public void insert(String data) {
        Node<String> newNode = new Node<String>(data);
        if (root == null) {
            root = newNode;
            size++;
            return;
        }
        Node<String> current = root;
        Node<String> parent = null;
        while (true) {
            parent = current;
            if (data.compareTo(current.data) < 0) {
                current = current.left;
                if (current == null) {
                    parent.left = newNode;
                    size++;
                    return;
                }
            } else {
                current = current.right;
                if (current == null) {
                    parent.right = newNode;
                    size++;
                    return;
                }
            }
        }
    }
}
