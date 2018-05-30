import java.util.*;

public class SimpleTree<E extends Comparable<? super E>> implements ISimpleTree<E> {

    private TreeNode<E> root = null;

    @Override
    public void insert(E element) {
        // Box the element in a node
        TreeNode<E> newNode = new TreeNode<E>(element);

        // Determine the position of the element by comparing tree elements.
        if (root == null){
            // Tree is empty. New node is root.
            root = newNode;
            return;
        }

        TreeNode<E> n = root;
        while (true){ // Keep looking until we find an empty node.
            if (element.compareTo(n.getData()) < 0){
                // If the new item is "less than" the item in the tree,
                // our new object belongs on the left
                if (n.getLeft() == null) {
                    // our node is empty on the left, add it to the node
                    n.setLeft(newNode);
                    return;
                }
                // Keep going!
                n = n.getLeft();
            } else {
                // If the new item is "greater than or equal to" the item in the tree,
                // our new object belongs on the right
                if (n.getRight() == null) {
                    // our node is empty on the right, add it to the node
                    n.setRight(newNode);
                    return;
                }
                // Keep going!
                n = n.getRight();
            }
        }
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean delete(E element) {
        // TODO NOT IMPLEMENTED YET
        return false;
    }

    @Override
    public int size() {

        return elements().size();

    }

    private List<TreeNode<E>> elements() {

        if (root == null) {
            return Collections.emptyList();
        }

        Stack<TreeNode<E>> proc = new Stack<>();

        Stack<TreeNode<E>> fin = new Stack<>();

        proc.push(root);

        while (true) {

            TreeNode<E> node = proc.pop();

            if (node.getLeft() != null) {
                proc.push(node.getLeft());
            } else if (node.getRight() != null) {
                proc.push(node.getRight());
            }

            fin.push(node);

            if (proc.isEmpty()) {
                break;
            }

        }

        return Collections.list(fin.elements());

    }

    public static void main(String[] args) {

        SimpleTree<Integer> tree = new SimpleTree<>();

        System.out.println(tree.size());

        tree.insert(5);

        tree.insert(2);

        System.out.println(tree.size());

    }

}