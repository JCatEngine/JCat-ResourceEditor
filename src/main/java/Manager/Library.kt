package Manager

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.util.ArrayList
import java.util.stream.Collectors

import com.google.gson.JsonIOException
import com.google.gson.JsonSyntaxException

import Bean.AnimeClip
import Bean.ResourceData
import JavaFxPlus.KotlinEx.toURLString
import JavaFxPlus.Tool.ImageTool
import JavaFxPlus.Util.FilterAbleList
import Parser.GsonOutput
import Parser.GsonParser
import Parser.SerializaOutput
import Parser.SerializaParser
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.image.Image

import java.util.stream.Collectors.*

object Library {

    private val list: FilterAbleList<ResourceData>

    private var id = 0

    init {
        val list = FXCollections.observableArrayList<ResourceData>()
        this.list = FilterAbleList(list)
    }


    fun importImage(file: File) {

        try {

            val image = Image(file.toURLString())
            var name = file.name
            name = name.substring(0, name.lastIndexOf("."))
            addToLibrary(name, ResourceType.TEXTURE, image)

        } catch (e: IOException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

    }

    /**
     * add a image to library
     * @param name
     * @param type
     * @param data
     */
    fun addToLibrary(name: String, type: ResourceType, data: Any) {
        val resourceData = ResourceData()
        resourceData.name = name
        resourceData.type = type
        resourceData.data = data

        list.add(resourceData)

    }

    val resources: ObservableList<ResourceData>
        get() = list.list

    /**
     * remove all selected items
     * @param selectedItems
     */
    fun removeAll(selectedItems: ObservableList<ResourceData>) =
            //shallow clone and remove
            selectedItems
                    .stream()
                    .collect(toList())
                    .stream()
                    .forEach { remove(it) }


    private fun remove(resourceData: ResourceData) = list.remove(resourceData)

    /**
     * check whether a image with name has exist
     * @param name
     * @return
     */
    fun hasSameName(name: String): Boolean {

        return list.filter { it.name == name }.size > 0
    }

    /**
     * slice a image and add to library
     * @param image
     * @param xAmount
     * @param yAmount
     * @param name
     * @param jgzs
     * @param anime
     * @param zscdh  是否只生成动画不生成切片
     */
    fun sliceAndAdd(image: Image, xAmount: Int, yAmount: Int, name: String, anime: Boolean, jgzs: Int, zscdh: Boolean) {


        val partWidth = (image.width / xAmount).toInt()
        val partHeight = (image.height / yAmount).toInt()



        for (y in 0..yAmount - 1) {
            //if need to generate a new anime
            var animeClip: AnimeClip? = null
            if (anime) {
                val maxFrame = xAmount * jgzs
                animeClip = AnimeClip(name + "anime" + y, maxFrame)
            }

            for (x in 0..xAmount - 1) {
                val xPos = x * partWidth
                val yPos = y * partHeight
                val autoNewName = name + x + y


                val sub = slice(image, xPos, yPos, partWidth, partHeight, autoNewName, zscdh)


                if (anime) {
                    animeClip!!.insertFrame(x * jgzs + 1, sub)
                }

            }

            if (anime) {
                addToLibrary(animeClip!!.name, ResourceType.MOVIECLIP, animeClip)
            }
        }


    }

    /**
     * slice a image and add to library
     * @param image
     * @param xPos
     * @param yPos
     * @param partWidth
     * @param partHeight
     * @param autoNewName
     * @param zscdh
     * @return
     */
    fun slice(image: Image, xPos: Int, yPos: Int, partWidth: Int, partHeight: Int, autoNewName: String, zscdh: Boolean): Image {

        val newImage = ImageTool.subImage(image, xPos, yPos, partWidth, partHeight)
        //if this do not exist,then add it
        if (hasSameName(autoNewName) == false && !zscdh) {
            addToLibrary(autoNewName, ResourceType.TEXTURE, newImage)
        }


        return newImage

    }


    fun fliterResource(text: String) {
        list.filterList { d -> d.name.startsWith(text) }
    }


    fun createEmptyAnime() {

        val name = "anime" + id++
        addToLibrary(name, ResourceType.MOVIECLIP, AnimeClip(name, 1))


    }


    fun outputToFileXLH(file: File) {

        //arraylist 才可以序列化
        val resourceDatas = ArrayList(resources)
        SerializaOutput(resourceDatas, file)


    }


    fun inputFromFileXLH(file: File) {
        val resourceDatas = SerializaParser(file).toList()
        this.list.addAll(resourceDatas)

    }


    fun inputFromFileGson(file: File) {

        var resourceDatas: ArrayList<ResourceData>? = null
        try {
            resourceDatas = GsonParser(file).toList()
        } catch (e: JsonIOException) {
            e.printStackTrace()
        } catch (e: JsonSyntaxException) {
            e.printStackTrace()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

        this.list.addAll(resourceDatas)

    }


    fun outputToFileGson(file: File) {

        val resourceDatas = ArrayList(resources)
        try {
            GsonOutput(resourceDatas, file)
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }


}
