import sun.security.util.BigInt;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Hashtable;
import java.util.Scanner;

public class Tree {

    public static BigInteger total = BigInteger.valueOf(0);

    public static class Node {
        public int index;
        public int parent;
        public int left;
        public int right;
        public long subtrees;

        public Node(int index, int left, int right) {
            subtrees = 0;
            parent = -1;
            this.index = index;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String args[]) throws FileNotFoundException {
        Hashtable<Integer, Node> tree = new Hashtable<Integer, Node>();
        Scanner scanner = new Scanner(new File("tree.txt"));
        int numNodes = scanner.nextInt();
        int top = 0;
        for (int i = 0; i < numNodes; i++) {
            int index = scanner.nextInt();
            if (i == 0) {
                top = index;
            }
            int left = scanner.nextInt();
            int right = scanner.nextInt();
            tree.put(index, new Node(index, left, right));
        }

        // Assign parents
        for (Integer key : tree.keySet()) {
            int index = tree.get(key).index;
            for (Integer parentKey : tree.keySet()) {
                if (tree.get(parentKey).left == index || tree.get(parentKey).right == index) {
                    tree.get(key).parent = parentKey;
                }
            }
        }
        computeSubtrees(tree, top);
        total = total.subtract(BigInteger.valueOf(1));
        System.out.println(total.mod(BigInteger.valueOf(1000000007)));
    }

    public static BigInteger computeSubtrees(Hashtable<Integer, Node> tree, int index) {

        /*long subtrees = 1; // Subtree for initial node
        Node node = tree.get(index);
        if(node.left != 0) {
            BigInteger subs = computeSubtrees(tree, node.left) + 1;
            subtrees *= subs;
        }
        if(node.right != 0) {
            BigInteger subs = (computeSubtrees(tree, node.right) + 1);
            subtrees *= subs;
        }
        System.out.println(subtrees);
        total = total.add(BigInteger.valueOf(subtrees));
        return subtrees;*/


/*        long subtrees = 0; // Subtree for initial node
        Node node = tree.get(index);
        if(node.left != 0 && node.right == 0) {
            subtrees = (computeSubtrees(tree, node.left) + 1);
        } else if(node.left == 0 && node.right != 0) {
            subtrees = (computeSubtrees(tree, node.right) + 1);
        } else if(node.left != 0 && node.right != 0) {
            subtrees = (computeSubtrees(tree, node.left) + 1) * (computeSubtrees(tree, node.right) + 1);
        }
        return subtrees; */
        return BigInteger.valueOf(0);
    }
}