import java.util.*

class SortedList<T : Comparable<T>>() : LinkedList<T>() {

    override fun add(element: T): Boolean {

        if (this.isEmpty()) {
            return super.add(element)
        }

        for (i in this.indices) {
            if (element <= this[i]) {
                this.add(i, element)
                return true
            }
        }

        return false

    }

}

fun main(args: Array<String>) {

    val l = SortedList<Int>()

    println(l.add(5))

    println(l)

    println(l.add(1))

    println(l)

    println(l.add(3))

    println(l)

    println(l.add(0))

    println(l)

}