package week01.gold;
import java.util.*;

public class BOJ5639_joshcho
{
    static class TreeNode {
        int value;
        TreeNode left, right;
        TreeNode(int value) { this.value = value; }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        TreeNode root = null;

        while (input.hasNextInt()) {
            int key = input.nextInt();
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
