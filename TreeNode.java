public class TreeNode<E> {
    private E data;
    private TreeNode<E> leftChild;
    private TreeNode<E> rightChild;

    /**
     * constructs a new treenode with some initial data
     * @param data the initial data value
     */
    public TreeNode(E data) {
        this.data = data;
    }

    /**
     * Get the left treenode
     * @return the left treenode
     */
    public TreeNode<E> getLeft() {
        return leftChild;
    }

    /**
     * set the left treenode
     * @param leftChild the new left node
     */
    public void setLeft(TreeNode<E> leftChild) {
        this.leftChild = leftChild;
    }

    /**
     * get the right treenode
     * @return the right treenode
     */
    public TreeNode<E> getRight() {
        return rightChild;
    }

    /**
     * set the right treenode
     * @param rightChild the new right node
     */
    public void setRight(TreeNode<E> rightChild) {
        this.rightChild = rightChild;
    }

    /**
     * gets the data associated with this node
     * @return the node's data
     */
    public E getData() {
        return data;
    }
}