package me.syari.fjg.util

import me.syari.fjg.Main.Companion.plugin
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.io.IOException

class ConfigFile(fileName: String, directory: File = plugin.dataFolder, default: Map<String, Any> = emptyMap()) {
    private var file: File
    val config: YamlConfiguration

    init {
        if (!directory.exists()) {
            try {
                directory.mkdir()
            } catch (ex: IOException) {
                plugin.logger.severe("${directory.path.substring(1)} の作成に失敗しました")
            }
        }
        file = File(directory, fileName)
        val isCreate = if (!file.exists()) {
            try {
                file.createNewFile()
                true
            } catch (ex: IOException) {
                val filePath = file.path.substringAfter(plugin.dataFolder.path).substring(1)
                plugin.logger.severe("$filePath の作成に失敗しました")
                false
            }
        } else {
            false
        }
        config = YamlConfiguration.loadConfiguration(file)
        if (isCreate) {
            default.forEach { (key, value) ->
                config.set(key, value)
            }
            save()
        }
    }

    fun save() {
        config.save(file)
    }
}