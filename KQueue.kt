import java.util.*

class KQueue<E>() : ISimpleQueue<E> {

    private var list = LinkedList<E>()

    override fun enqueue(item: E) = list.add(item).let { Unit }

    override fun dequeue(): E? = list.pop() as E?

    override fun peek(): E = list.peek()

    override fun isEmpty(): Boolean = list.isEmpty()

    override fun size(): Int = list.size

}