package week01_recursion.gold;

import java.util.*;

public class BOJ5639_dwoong {
    public static void main(String[] args) {
        new week01_2().run();
    }
}

class week01_2 {
    class Node {
        int key;
        Node left, right;

        Node(int k) {
            key = k;
        }
    }

    Node root = null;

    void run() {
        Scanner sc = new Scanner(System.in);

        //읽고 트리 넣기
        while (sc.hasNextInt()) {
            int v = sc.nextInt();
            root = insert(root, v);
        }

        // 출력 ㄱ
        search(root);
    }

    Node insert(Node n, int k) {

        if (n == null){
            return new Node(k);
        }
        if (k < n.key){
            n.left = insert(n.left, k);
        }
        else
            n.right = insert(n.right, k);


        return n;
    }

    void search(Node n) {

        if (n == null){
            return;
        }

        search(n.left);
        search(n.right);

        System.out.println(n.key);
    }
}
