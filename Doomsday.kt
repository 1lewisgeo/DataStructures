import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.math.absoluteValue
import kotlin.math.floor

enum class Day {
    Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday
}

val anchors = mapOf(1800..1899 to Day.Friday,
                        1900..1999 to Day.Wednesday,
                        2000..2099 to Day.Tuesday,
                        2100..2199 to Day.Sunday)


// month to day
val doomsdays = listOf(9 to 5, 4 to 4, 6 to 6, 8 to 8, 10 to 10, 12 to 12).map { LocalDate.of(0, it.first, it.second) }

fun main(args: Array<String>) {

    while (true) {

        // Parse input

        val input = println("Please enter a date (DD-MM-YYYY)").run { readLine() }?.let { it } ?: continue

        val (day, month, year) = input.split("-").map { it.toInt() }

        val initial_date = LocalDate.of(year, month, day)

        // Get century anchor

        val anchor = anchors.filter { year in it.key }.map { it.value }.getOrNull(0)?.let { it } ?: continue

        val digits = year.toString().substring(2).toInt()

        val y = digits / 12.0

        val a = floor(y)

        val b = digits % 12.0

        val c = floor(b / 4.0)

        val d = a + b + c

        val offset = ((y / 12.0) + y % 7.0 + (y % 12.0)/4.0) % 7.0

        //val doomsday = Day.values().run { this[indexOf(anchor) + offset.toInt()] }

        val doomsday = Day.values()[(anchor.ordinal + d.toInt()) % 7]

        //val closest = doomsdays.map { it.withYear(year) }.map { ChronoUnit.DAYS.between(initial_date, it).absoluteValue to it }.sortedBy { it.first }[0]

        val closest = ChronoUnit.DAYS.between(initial_date, LocalDate.of(year, 4, 4)).absoluteValue

        println(closest)

        println("Doomsday is $doomsday and the delta is $closest, index of ${Day.values().indexOf(doomsday)}, digits: $digits, a: $a b: $b c: $c d: $d, anchor: $anchor, offset: $offset")

        val result = Day.values().run { this[(indexOf(doomsday) + closest.toInt()) % 7] }

        println(result)

    }

}