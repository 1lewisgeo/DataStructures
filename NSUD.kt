import java.io.BufferedReader
import java.io.File


// Create map of definitions and load from file
val m = File("src/NSUD.data").bufferedReader().use(BufferedReader::readLines).map { val spl =  it.split(" - ") ; spl[0] to spl[1] }.toMap().toMutableMap()

// The main function
fun main(args: Array<String>) {

    // Main loop
    while (true) {


        // Prompt the user
        println("Please select a word to define by number of name, or type \"define [word] [definition]\"...")

        // Print all entries
        m.entries.forEachIndexed { index, (word, _) ->
            println("${index+1}. $word")
        }

        // Parse user input
        readLine().let { if (it.isNullOrBlank()) null else it }?.let {
            if (it.startsWith("define") && it.split(" ").size >= 3) {
                // Add a new definition
                m.put(it.split(" ")[1], it.split(" ").subList(1, it.split(" ").size).joinToString ( " "))
            } else {
                try {
                    // Get definition by number
                    it.toInt().takeIf { it in 0..(m.size - 1) }?.let { m.toList()[it] }?.let { (a, b) -> println("$a: $b") }
                } catch (e: NumberFormatException) {
                    // Get definition by word
                    m.filter { (k, _) -> k.startsWith(it.toLowerCase()) }.let { if (it.size > 1) println("Multiple matches found"); it.forEach { (a, b) -> println("$a: $b") } }
                }
            }

            // Pause before next loop
            println("Press return to continue...")

            readLine()

        }

    }

}