package JavaFxPlus.KotlinEx

import java.io.File

inline fun String.toFile()=File(this)
inline fun String.printToConsole()={    println(this) }
