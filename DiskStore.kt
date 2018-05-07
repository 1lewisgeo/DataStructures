import java.io.File
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.reflect.KClass
import kotlin.reflect.full.cast

fun write(data: Any, name: String) {

    File("src/data/$name.data").outputStream().let {
        ObjectOutputStream(it).use {
            it.writeObject(data)
        }
    }

}

fun <T: Any> read(name: String, type: KClass<T>): T? {

    if (Files.exists(Paths.get("src/data/$name.data"))) {

        return type.cast(File("src/data/$name.data").inputStream().let {
            ObjectInputStream(it).use {
                type.cast(it.readObject())
            }
        })

    }

    return null

}

fun cleanup() {
    File("src/data").deleteRecursively()
}

fun main(args: Array<String>) {

    write(6.4, "d")

    println(read("d", Double::class))

    write(7.8, "d")

    println(read("d", Double::class))

    cleanup()

}