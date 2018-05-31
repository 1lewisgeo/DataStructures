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

        List<TreeNode<E>> list = elements();

        boolean success = false;

        for (TreeNode<E> tn : list) {

            if (tn.getLeft() != null && tn.getLeft().getData().equals(element)) {
                tn.setLeft(null);
            } else if (tn.getRight() != null && tn.getRight().getData().equals(element)) {
                tn.setRight(null);
            }

            if (tn.getData().equals(element)) {
                list.remove(element);
                tn = null;
                success = true;
            }

        }

        return success;

    }

    @Override
    public int size() {

        return elements().size();

    }

    /**
     * An internal method to get all the elements as a list
     * @return a list of all the elements in the tree
     *
     */
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

    /**
     * performs a depth first search on the tree
     * @param element the element to search for
     * @return true if the tree contains the element and false otherwise
     */
    public boolean depthFirstSearch(E element) {

        ArrayList<TreeNode<E>> proc = new ArrayList<>();

        List<TreeNode<E>> elements = elements();

        TreeNode<E> node = root;

        if (root.getLeft() != null && root.getRight() != null) {
            if (root.getData().equals(element)) {
                return true;
            } else {
                return false;
            }
        }

        while (true) {

            if (node.getLeft() != null) {

                node = node.getLeft();

            } else if (node.getRight() != null) {

                node = node.getRight();

            } else /*(node.getLeft() == null && node.getRight() == null)*/ {

                if (node.getData().equals(element)) {
                    return true;
                } else {
                    proc.add(node);
                }

            }

            elements.sort(Comparator.comparing(TreeNode::getData));

            proc.sort(Comparator.comparing(TreeNode::getData));

            if (elements.equals(proc)) {
                break;
            }

        }

        return false;

    }

    /**
     * Performs a breadth first search on the tree
     * @param element the element to search for
     * @return true if the tree contains the element, false otherwise
     */
    public boolean breadthFirstSearch(E element) {

        ArrayList<TreeNode<E>> list = new ArrayList<>();

        ArrayList<TreeNode<E>> currentNodes = new ArrayList<TreeNode<E>>();

        currentNodes.add(root);

        /*if (root.getLeft() != null && root.getRight() != null) {
            if (root.getData().equals(element)) {
                return true;
            } else {
                return false;
            }
        }*/

        while (true) {

            for (TreeNode<E> tn : currentNodes) {

                if (tn.getData().equals(element)) {
                    return true;
                }

                if (tn.getLeft() != null) {

                    list.add(tn.getLeft());

                }

                if (tn.getRight() != null) {

                    list.add(tn.getRight());

                }

            }

            currentNodes.clear();

            currentNodes.addAll(list);

            list.clear();

            if (currentNodes.stream().allMatch(tn -> tn.getLeft() == null && tn.getRight() == null)) {
                break;
            }

        }

        return false;

    }

    public static void main(String[] args) {

        SimpleTree<Integer> tree = new SimpleTree<>();

        System.out.println(tree.size());

        tree.insert(5);

        tree.insert(2);

        System.out.println(tree.size());

        System.out.println(tree.delete(2));

        System.out.println(tree.size());

        System.out.println(tree.depthFirstSearch(5));

        System.out.println(tree.depthFirstSearch(3));

        System.out.println(tree.breadthFirstSearch(5));

        System.out.println(tree.breadthFirstSearch(3));

    }

}