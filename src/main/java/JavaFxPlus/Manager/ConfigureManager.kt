package JavaFxPlus.Manager

import java.io.File

import com.google.gson.Gson

import JavaFxPlus.Tool.FileTool
import Main.Config

/**
 * configureManager
 * @author Administrator
 */
object ConfigureManager {


    private var configData = ConfigData()

    init {
        //init directory
        val file = File("data")
        if (!file.exists()) {
            file.mkdirs()
        }

        loadConfigure()
    }

    fun loadConfigure() {


        val file = File(Config.CONFIGUE)
        if (file.exists()) {
            val result = FileTool.readFile(file)
            val gson = Gson()
            this.configData = gson.fromJson<ConfigData>(result, configData.javaClass)
            

        }

    }


    fun saveConfigure() {

        val gson = Gson()
        val json = gson.toJson(this.configData)

        FileTool.writeFile(File(Config.CONFIGUE), json)

    }


    var lastChoosePath: File?
        get() = if (configData.lastChoosePath != null) {
             File(configData.lastChoosePath)
        } else {
             null
        }
        set(file) {
            this.configData.lastChoosePath = file?.getAbsolutePath()
            saveConfigure()

        }

    var lastSavePath: File?
        get() = if (configData.lastSavePath != null) {
             File(configData.lastSavePath)
        } else {
             null
        }
        set(lastSavePath) {
            this.configData.lastSavePath = lastSavePath?.getAbsolutePath()
            saveConfigure()
        }




}
