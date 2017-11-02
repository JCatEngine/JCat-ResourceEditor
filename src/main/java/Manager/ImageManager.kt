package Manager

import JavaFxPlus.KotlinEx.toFile
import Main.Config
import javafx.scene.image.Image
import java.net.MalformedURLException

/**
 * the class is for cache icon
 * @author Administrator
 */
object ImageManager {


    val ICON_TEXTURE = "icon_image"
    val ICON_MOVIECLIP = "icon_movieclip"
    val ICON_MUSIC = "icon_music"


    private val map = HashMap<String, Image>()

    init {
        loadImage()

    }


    private fun loadImage() {
        _loadImage(ICON_TEXTURE)
        _loadImage(ICON_MOVIECLIP)
        _loadImage(ICON_MUSIC)
    }

    private fun _loadImage(name: String) {

        try {

            val image=Image((Config.IMAGE_DIRECTORY + name + ".png").toFile().toURL().toString())
            //System.out.println(new File(Config.IMAGE_DIRECTORY+name+".png").toURL().toString());
            map.put(name,image)
        } catch (e: MalformedURLException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

    }


    fun getIcon(name: String): Image {
        return map[name]?:throw RuntimeException("$name do not exist!")
    }


}
