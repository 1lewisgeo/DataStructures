public interface ISimpleList<E> {
    /**
     * Appends an element e to the end of the list. Returns
     * success status.
     * @param e - the element to be added to the end
     * @return boolean - true if and only if the add was
     *        successful.
     */
    boolean add(E e);

    /**
     * Inserts an element, e, into the list without losing
     * or reordering existing data.
     * @param i - the index to insert the element.
     * @param e - the element being inserted.
     * @return boolean - true if and only if the add was
     *        successful.
     */
    boolean insert(int i, E e);

    /**
     * Replaces the element at index i with new element e.
     * Returns the old element.
     * @param i - the index of the element being replaced.
     * @param e - the element being placed into the list.
     * @return E - The previous element from the index
     */
    E replace(int i, E e);

    /**
     * Removes the element at index i and returns it.
     * @param i - the index of the element being removed.
     * @return E - the item that was removed.
     */
    E remove(int i);

    /**
     * Returns the element at index i
     * @param i - the index of the element
     * @return E - the item at the specified index.
     */
    E get(int index);

    /**
     * Removes all elements from the list
     */
    void clear();

    /**
     * Returns the number of elements currently in the list.
     * @return int - the number of elements in the list
     */
    int size();

    /**
     * Returns true if and only if the list has more than
     * zero elements.
     * @return boolean - true if and only if there are no
     *         elements in the list.
     */
    boolean isEmpty();

    /**
     * Returns a new SimpleList which contains the elements
     * starting at startIndex inclusive and ending at
     * endIndex exclusive.
     * @param startIndex - the beginning index of the
     *        new sublist, inclusive.
     * @param endIndex - the index at which to stop. The
     *        element at this index is not included in the
     *        returned sublist.
     * @return ISimpleList<E> - A new list object containing
     *        the specified elements.
     */
    ISimpleList<E> subList(int startIndex, int endIndex);
}
