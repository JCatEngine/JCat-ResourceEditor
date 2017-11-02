package JavaFxPlus.Manager

import JavaFxPlus.KotlinEx.*
import JavaFxPlus.Tool.FileTool
import Main.Config
import com.google.gson.Gson
import java.io.File

/**
 * configureManager
 * @author Administrator
 */
object ConfigureManager {


    private var configData = ConfigData()

    init {
        //init directory

        "data".toFile().ifNotExist { it.mkdirs() }
        loadConfigure()
    }

    fun loadConfigure() = Config.CONFIGUE.toFile().ifExist {
        val result = it.read()
        this.configData = Gson().fromJson(result, configData.javaClass)
    }


    fun saveConfigure() = Config.CONFIGUE.toFile() write  Gson().toJson(this.configData)


    var lastChoosePath: File?
        get() = configData.lastChoosePath?.toFile()

        set(file) {
            this.configData.lastChoosePath = file?.getAbsolutePath()
            saveConfigure()

        }

    var lastSavePath: File?
        get() = configData.lastSavePath?.toFile()

        set(lastSavePath) {
            this.configData.lastSavePath = lastSavePath?.getAbsolutePath()
            saveConfigure()
        }




}
