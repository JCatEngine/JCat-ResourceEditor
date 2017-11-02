package JavaFxPlus.KotlinEx

import JavaFxPlus.Tool.FileTool
import java.io.File


inline fun File.ifExist(closuse:(File) -> Unit) {
    if(this.exists()){ closuse(this) }
}
inline fun File.ifNotExist(closuse:(File) -> Unit) {
    if(!this.exists()){ closuse(this) }
}

inline fun File.read()=FileTool.readFile(this)

inline fun File.toURLString()=this.toURL().toString()

infix inline fun File.write(s:String)=FileTool.writeFile(this,s)