public class TreeNode<E> {
    private E data;
    private TreeNode<E> leftChild;
    private TreeNode<E> rightChild;

    public TreeNode(E data) {
        this.data = data;
    }

    public TreeNode<E> getLeft() {
        return leftChild;
    }

    public void setLeft(TreeNode<E> leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNode<E> getRight() {
        return rightChild;
    }

    public void setRight(TreeNode<E> rightChild) {
        this.rightChild = rightChild;
    }

    public E getData() {
        return data;
    }
}