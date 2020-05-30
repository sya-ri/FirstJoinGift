package me.syari.fjg.util

import me.syari.fjg.Main.Companion.plugin
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.io.IOException

class Config(fileName: String, directory: File = plugin.dataFolder) {
    private var file = File(directory, fileName)
    val config: YamlConfiguration
    private val filePath: String

    init {
        config = YamlConfiguration.loadConfiguration(file)
        filePath = file.path.substringAfter(plugin.dataFolder.path).substring(1)
        if (!file.exists()) {
            try {
                file.createNewFile()
                plugin.logger.info("$filePath の作成に成功しました")
            } catch (ex: IOException) {
                plugin.logger.severe("$filePath の作成に失敗しました")
            }
        }
    }

    fun save() {
        config.save(file)
    }
}