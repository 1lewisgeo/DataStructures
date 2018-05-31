import kotlin.math.floor

/**
 * Coverts a decimal number into base2
 * @param n a base10 number
 * @return the base2 representation of n
 */
fun toBinary(n: Int): String {

    val q = KQueue<Int>()

    var proc: Int = n

    while (true) {

        val digit = proc % 2

        q.enqueue(digit)

        println(proc)

        if (proc <= 1) {
            break
        }

        proc = floor(proc / 2.0).toInt() + digit.let { if (it == 0) 1 else -1 }

    }

    return q.toString()

}

/**
 * Calculates the specified element of the fibonacci sequence using @see KQueue
 * @param n which element of the sequence is requested
 * @return the element n of the fibonacci sequence
 */
fun qfib(n: Int): String {

    if (n == 1) {
        return "[1]"
    }

    val q = KQueue<Int>()

    val qf = KQueue<Int>()

    q.enqueue(1)

    q.enqueue(1)

    qf.enqueue(1)

    qf.enqueue(1)

    var count = 2

    while (count < n) {
        val a = q.dequeue()!!
        val b = q.dequeue()!!
        val c = a + b
        qf.enqueue(c)
        q.enqueue(b)
        q.enqueue(c)
        count++
    }

    return qf.toString()

}

/**
 * Determines whether or not the brackets in a string are balanced
 * @param input the string to test for balance
 * @return a boolean value; true if brackets are balanced, false otherwise
 */
fun isBalanced(input: String): Boolean {

    input.run { count { it == '(' } == count { it == ')' } &&
                    count { it == '[' } == count { it == ']' } &&
                        count { it == '{' } == count { it == '}' } }.let { if (!it) return@isBalanced false }

    val m = mapOf(')' to '(', ']' to '[', '}' to '{')

    val stack = KStack<Char>()

    for (i in input.indices) {

        val brac = input[i]

        if (brac in m.values) {

            stack.push(brac)

        } else {

            // Close bracket

            if (m[brac] != stack.pop()) {
                return@isBalanced false
            }

        }

    }

    return true

}

/**
 * Converts infix notation to postfix notation
 * @param input a space separated math statement with infix notation
 * @return the postfix representation of the input math statement
 */
fun infixToPostfix(input: String): String {

    val operators = arrayOf("+", "-", "*", "/")

    val opstack = KStack<String>()

    var output = ""

    val tokens = input.split(" ")

    for (token in tokens) {

        if (token in operators) {

            if (token in arrayOf("+", "-")) {

                while (!opstack.isEmpty) {
                    if (opstack.peek()!! in arrayOf("(", ")")) {
                        //opstack.pop()
                        break // break
                    }
                    output += "${opstack.pop()} "
                }

            } else {

                // * or /

                while (!opstack.isEmpty) {

                    if (opstack.peek() in arrayOf("*", "/")) {
                        output += "${opstack.pop()} "
                    } else { // + - ( )
                        break
                    }

                }

            }

            opstack.push(token)

        } else if (token == "(") {

            opstack.push(token)

        } else if (token == ")") {

            while (true) {

                if (opstack.isEmpty) {
                    break
                }

                val pop = opstack.pop()

                if (pop == "(") {
                    break
                } else {
                    output += "$pop "
                }

            }

        } else {
            output += "$token "
        }

    }

    while (!opstack.isEmpty) {
        output += "${opstack.pop()} "
    }

    return output.trim()

}

/**
 * Calculates the result of a postfix math statement
 * @param input the postfix math statement to calculate
 * @return the numberic result of the math statement
 */
fun postfixCalc(input: String): Double {

    val proc = input.split(" ").toList().toMutableList()

    while (proc.size > 1) {

        val operator = proc.indexOfFirst { it in arrayOf("*", "/", "+", "-") }

        val op1 = operator - 2

        val op2 = operator - 1

        println("op: $operator, op1: $op1. op2: $op2")

        val result: Double = when(proc[operator]) {
            "*" -> proc[op1].toDouble() * proc[op2].toDouble()
            "/" -> proc[op1].toDouble() / proc[op2].toDouble()
            "+" -> proc[op1].toDouble() + proc[op2].toDouble()
            "-" -> proc[op1].toDouble() - proc[op2].toDouble()
            else -> Double.MIN_VALUE // Will never happen
        }

        proc.removeAt(operator)

        proc.removeAt(op2)

        proc.removeAt(op1)

        proc.add(op1, result.toString())

        println(proc)

    }

    return proc.first().toDouble()

}

fun main(args: Array<String>) {

    println(toBinary(5))

    println(toBinary(11))

    println(toBinary(13))

    println(qfib(5))

    println(isBalanced("({["))

    println(isBalanced("({[]})"))

    println(isBalanced("[(])"))

    println(infixToPostfix("1 + 4 * ( 2 + ( 1 + 1 + 1 ) )"))

    println(infixToPostfix("1 + 7 * 4"))

    println(infixToPostfix("1 + 2 + 3"))

    println(infixToPostfix("2 * 3 * 4 + 7"))

    println(postfixCalc("2 5 2 * +"))

    println(postfixCalc(infixToPostfix("1 + 4 * ( 2 + ( 1 + 1 + 1 ) )")))

}