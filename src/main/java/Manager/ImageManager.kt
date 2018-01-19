package Manager

import JavaFxPlus.KotlinEx.toFile
import Main.Config
import com.google.common.cache.CacheBuilder
import com.google.common.cache.CacheLoader
import com.google.common.cache.RemovalNotification
import javafx.scene.image.Image
import java.util.concurrent.TimeUnit


/**
 * the class is for cache icon
 * @author Administrator
 */
object ImageManager {


    val ICON_TEXTURE = "icon_image"
    val ICON_MOVIECLIP = "icon_movieclip"
    val ICON_MUSIC = "icon_music"


    /**
     * cache
     */
    private val map = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterAccess(10, TimeUnit.MINUTES)
            .removalListener<String, Image>(this::logReason)
            .build(createLoader({key->_loadImage(key)}))

    private fun<T,V> createLoader(func:(T)->V): CacheLoader<T,V> {

        return  object:CacheLoader<T, V>() {
            @Throws(Exception::class)
            override fun load(key: T): V {
                return func(key)
            }
        }
    }




    private fun logReason(it: RemovalNotification<String, Image>) {
        val str="cause:${it.cause} key:${it.key} value:${it.value}"
        println(str)
        //record to log file

    }


    /**
     * load image by file path
     */
    private fun _loadImage(name: String)= Image((Config.IMAGE_DIRECTORY + name + ".png").toFile().toURL().toString())



    fun getIcon(name: String): Image {
        return map[name]?:throw RuntimeException("$name do not exist!")
    }


}
