fun toBinary(n: Int): String {

    val q = KQueue<Int>()

    var proc = n

    while (proc > 2) {

        q.enqueue(proc % 2)

        proc /= 2

    }

    return q.toString()

}

fun main(args: Array<String>) {

    println(toBinary(5))

}