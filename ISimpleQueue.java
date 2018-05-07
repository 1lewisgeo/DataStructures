public interface ISimpleQueue<E> {
    /**
     * Add an item to the queue.
     * @param item - The item being added to the queue
     */
    public void enqueue(E item);

    /**
     * Returns the next item in the queue, removing it from
     * the queue in the process.
     * @return the item being removed from the queue
     */
    public E dequeue();

    /**
     * Returns the item at the front of the queue without
     * changing the queue contents.
     * @return the item at the front of the queue.
     */
    public E peek();

    /**
     * Tests is the queue is empty.
     * @return true if and only if this queue contains no
     *    items; false otherwise.
     */
    public boolean isEmpty();

    /**
     * Returns the number of items in the queue.
     * @return the number of items currently stored in the
     *    queue
     */
    public int size();
}