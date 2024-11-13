
class TreeNode {
    int data;
    TreeNode left, right;

    public TreeNode(int data) {
        this.data = data;
        this.left = this.right = null;
    }
}

class BinaryTree {
    private TreeNode root;

    public BinaryTree() {
        this.root = new TreeNode(40);  // Root node with value 40
        root.left = new TreeNode(20);  // Left child of root
        root.right = new TreeNode(60); // Right child of root
        root.left.left = new TreeNode(10);  // Left child of node 20
        root.left.right = new TreeNode(30); // Right child of node 20
        root.right.left = new TreeNode(50); // Left child of node 60
        root.right.right = new TreeNode(70); // Right child of node 60
    }

    // In-order traversal (Left, Root, Right)
    public void inorder() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(TreeNode root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.data + " ");
            inorderRec(root.right);
        }
    }

    // Search method
    public boolean search(int key) {
        return searchRec(root, key);
    }

    private boolean searchRec(TreeNode root, int key) {
        if (root == null) {
            return false;
        }
        if (root.data == key) {
            return true;
        }
        if (key < root.data) {
            return searchRec(root.left, key);
        }
        return searchRec(root.right, key);
    }

    // Pre-order traversal (Root, Left, Right)
    public void preorder() {
        preorderRec(root);
        System.out.println();
    }

    private void preorderRec(TreeNode root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }

    // Post-order traversal (Left, Right, Root)
    public void postorder() {
        postorderRec(root);
        System.out.println();
    }

    private void postorderRec(TreeNode root) {
        if (root != null) {
            postorderRec(root.left);
            postorderRec(root.right);
            System.out.print(root.data + " ");
        }
    }
}

public class TreeStructure {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        // Print the tree using different traversals
        System.out.print("In-order traversal: ");
        tree.inorder();

        System.out.print("Pre-order traversal: ");
        tree.preorder();

        System.out.print("Post-order traversal: ");
        tree.postorder();

        // Search for a value in the tree
        int valueToSearch = 50;
        if (tree.search(valueToSearch)) {
            System.out.println(valueToSearch + " found in the tree.");
        } else {
            System.out.println(valueToSearch + " not found in the tree.");
        }
    }
}
