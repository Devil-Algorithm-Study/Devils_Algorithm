package week01_recursion.gold;

import java.io.*;

public class BOJ5639_jiyun {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    static Node root;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line;
        while ((line = br.readLine())!=null) {
            root = insert(root, Integer.parseInt(line));
        }

        postorder(root);
        System.out.println(sb);
    }

    static Node insert(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }

        if (value < node.value) {
            node.left = insert(node.left, value);
        }
        else {
            node.right = insert(node.right, value);
        }

        return node;
    }

    static void postorder(Node node) {
        if (node == null) {
            return;
        }
        postorder(node.left);
        postorder(node.right);
        sb.append(node.value).append("\n");
    }
}
