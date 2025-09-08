package week01.gold;
import java.util.*;

public class BOJ5639_joshcho
{

    Scanner input  = new Scanner(System.in);

    static class TreeNode {
        int value;
        TreeNode left, right;
        TreeNode(int value) { this.value = value; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TreeNode root = null;

        while (sc.hasNextInt()) {
            int key = sc.nextInt();
            root = insert(root, key);
        }

        postOrder(root);

    }

    static TreeNode insert(TreeNode node, int value) {
        if (node == null) return new TreeNode(value);
        if (value < node.value) node.left = insert(node.left, value);
        else node.right = insert(node.right, value);
        return node;
    }

    static void postOrder(TreeNode node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.value);
    }

}