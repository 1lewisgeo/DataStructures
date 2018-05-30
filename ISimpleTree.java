public interface ISimpleTree<E extends Comparable<? super E>> {
    /**
     * Inserts an element into the tree.
     * @param element - the element to be inserted
     */
    void insert(E element);

    /**
     * Clears all elements from the tree
     */
    void clear();

    /**
     * Deletes an element in the tree, if it exists.
     * NOT CURRENTLY IMPLEMENTED.
     * @param element - the element to be deleted
     * @return boolean true if and only if the elmenent was
     *         removed from the tree.
     */
    boolean delete(E element);

    /**
     * Returns a count of the number of elements in the tree
     * @return int - the number of elements in the tree.
     */
    int size();
}