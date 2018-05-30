import com.sun.org.apache.xpath.internal.operations.Bool
import kotlin.math.round
import kotlin.test.assertSame
import kotlin.test.assertTrue

val b32 = "0 1 2 3 4 5 6 7 8 9 b c d e f g h j k m n p q r s t u v w x y z".toUpperCase().split(" ").mapIndexed { i, e -> e to i }.toMap()

fun geohash(hash: String): Pair<Double, Double> {

    val binary = hash.toUpperCase().toCharArray().map { Integer.toBinaryString(b32.get(it.toString())!!).padStart(5, '0') }.reduce { acc, s -> "$acc$s" }

    val lonstr = binary.toCharArray().filterIndexed { i, e -> i % 2 == 0 }.joinToString("")

    val latstr = binary.toCharArray().filterIndexed { i, e -> i % 2 != 0 }.joinToString("")

    var lon  = -180.0..180.0

    var lat = -90.0..90.0

    for (x in lonstr) {

        println("${lon.start} ${(lon.start+lon.endInclusive)/2} ${lon.endInclusive}")

        val mid = (lon.start + lon.endInclusive) / 2.0

        if (x == '1') {

            lon = mid..lon.endInclusive

        } else {

            lon = lon.start..mid

        }

    }

    println("${lon.start} ${(lon.start+lon.endInclusive)/2} ${lon.endInclusive}")

    for (x in latstr) {

        val mid = (lat.start + lat.endInclusive) / 2.0

        println("${lat.start} $mid ${lat.endInclusive}")

        if (x == '1') {

            lat = mid..lat.endInclusive

        } else {

            lat = lat.start..mid

        }

    }

    println("${lat.start} ${(lat.start+lat.endInclusive)/2} ${lat.endInclusive}")

    return lat.run { (this.start + this.endInclusive) / 2.0 } to lon.run { (this.start + this.endInclusive) / 2.0 }

}

fun test(hash: String, lat: Double, lon: Double, not: Boolean = false) {
    val v = geohash(hash).run { round(first) to round(second) } == round(lat) to round(lon)
    assertSame(!not, v)
}

fun main(args: Array<String>) {

    // Regular high precision input
    test("t4r7byumm43n", 13.353423, 55.234324)

    // Regular input
    test("u4pruydqqvj", 57.64911,10.40744)

    // Edge case test (null island)
    test("s000", 0.0, 0.0)

    // Edge case test (extremities)
    test("bpbpbpbpb", 90.0, -180.0)

    // Regular test
    test("s000d", 0.1, 0.1)

    // Regular test
    test("s09cvet", Math.PI, Math.E)

    // Bad input
    test("f8sdf768", 3.42343, 5.23432, not = true)

    // Bad input
    test("0998dsf34832", 43.8, 2.86, not = true)

    geohash("tmyg")

}