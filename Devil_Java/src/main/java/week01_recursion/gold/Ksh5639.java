package Devil_Java.src.main.java.week01_recursion.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Ksh5639 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Node rootNode = new Node(Integer.parseInt(br.readLine()));
        while (true) {
            String read = br.readLine();
            if (read == null || read.isEmpty()) break;

            int input = Integer.parseInt(read);
            rootNode.insert(input);
        }
        // 50 30 24 5 28 45 98 52 60


        postOrder(rootNode);
    }


    private static void postOrder(Node n) {
        if (n == null) {
            return;
        }
        postOrder(n.left);
        postOrder(n.right);
        System.out.println(n.data);
    }
}

class Node {
    int data;
    Node left = null;
    Node right = null;

    Node(int data) {
        this.data = data;
    }

    public void insert(int input) {
        if (this.data > input) {
            if (this.left == null) {
                this.left = new Node(input);
            } else {
                this.left.insert(input);
            }
        }

        if (this.data < input) {
            if (this.right == null) {
                this.right = new Node(input);
            } else {
                this.right.insert(input);
            }
        }
    }
}
