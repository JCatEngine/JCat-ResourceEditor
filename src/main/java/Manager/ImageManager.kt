package Manager

import JavaFxPlus.KotlinEx.toFile
import Main.Config
import com.google.common.collect.ImmutableMap
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


    private var map:ImmutableMap<String,Image>;



    init {

        val map=HashMap<String,Image>()


        _loadImage(ICON_TEXTURE,map)
        _loadImage(ICON_MOVIECLIP,map)
        _loadImage(ICON_MUSIC,map)

        //make it immutablemap
        this.map= ImmutableMap.copyOf(map)


    }


    private fun _loadImage(name: String, map: HashMap<String, Image>) {

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
