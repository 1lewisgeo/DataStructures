public interface ISimpleStack<E> {
    /**
     * Places the object, item, on the top of the stack.
     * @param item - the item to be pushed onto this stack.
     */
    public void push(E item);

    /**
     * Removes the item on the top of the stack and returns
     * it.
     * @return the item from the top of the stack.
     */
    public E pop();

    /**
     * Returns the item at the top of the stack without
     * changing the stack contents.
     * @return the item from the top of the stack.
     */
    public E peek();


    /**
     * Tests if this stack is empty.
     * @return true if and only if this stack contains no
     *    items; false otherwise
     */
    public boolean isEmpty();

    /**
     * Returns the number of items in the stack.
     * @return an integer representing the number of items
     *    currently in the stack.
     */
    public int size();
}