import java.util.*
import kotlin.test.assertFalse
import kotlin.test.assertSame

class KStack<E>() : ISimpleStack<E> {

    private var arr = Array<Any?>(10, { null })

    var ptr = 0

    internal fun resize() {

        if (ptr == arr.size) {

            val narr = Array<Any?>(arr.size + 10, { null })

            for (i in arr.indices) {
                narr[i] = arr[i]
            }

            arr = narr

        }

    }

    override fun push(item: E) {

        resize()

        arr[ptr] = item

        ptr++

    }

    override fun pop(): E? = arr[--ptr]?.let { arr[ptr] = null; it as E }

    override fun peek(): E? = arr[ptr-1]?.let { it as E }

    override fun isEmpty(): Boolean = arr.filter { it != null }.isEmpty()

    override fun size(): Int {

        var count = 0

        for (item in arr) {
            item?.let { count++ }
        }

        return count

    }

    override fun toString(): String = arr.filter { it != null }.toString()

}

fun main(args: Array<String>) {

    val stack = KStack<Int>()

    assert(stack.isEmpty)

    stack.push(5)

    assert(stack.toString() == "[5]")

    stack.push(8)

    assert(stack.toString() == "[5, 8]")

    assert(stack.peek() == 8)

    assert(stack.toString() == "[5, 8]")

    assert(stack.pop() == 8)

    assert(stack.toString() == "[5]")

    assertFalse { stack.isEmpty }

    assert(stack.pop() == 5)

    assert(stack.isEmpty)

    assert(stack.toString() == "[]")

}