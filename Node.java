public class Node<E> {

    private E data;
    private Node<E> next;
    private Node<E> parent;

    /**
     * Construsts a new Node with the initial data value *data*
     * @param data The initial data value of this node
     */
    public Node(E data) {
        this.data = data;
    }

    /**
     * @return the next Node in the chain
     */
    public Node<E> getNext() {
        return next;
    }

    /**
     * Sets the next Node in the chain
     * @param next the Node to be next in the chain
     */
    public void setNext(Node<E> next) {
        this.next = next;
    }

    /**
     * @return the data associated with this Node
     */
    public E getData() {
        return data;
    }

    /**
     * Assigns new data to this Node
     * @param data the new data
     */
    public void setData(E data) {
        this.data = data;
    }

    /**
     * Get the Node that links to this one
     * @return the Node to which this one is a child, or null if it is the root node
     */
    public Node<E> getParent() {
        return parent;
    }

    /**
     * Sets the parent for this node
     * @param parent the new parent Node
     */
    public void setParent(Node<E> parent) {
        this.parent = parent;
    }

}