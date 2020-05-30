package me.syari.fjg.message

import me.syari.fjg.util.ConfigFile

object MessageChanger {
    var firstJoinMessage: String? = null
        private set

    var isOverrideJoinQuitMessage = false
        private set

    var overrideJoinMessage: String? = null
        private set

    var overrideFirstJoinMessage: String? = null
        private set

    var overrideQuitMessage: String? = null
        private set

    internal object Config {
        private val configFile = ConfigFile(
            "message.yml", default = mapOf(
                "message.firstjoin" to "&b初ログイン報酬を受け取りました",
                "override.enable" to true,
                "override.join" to "&e%player% joined the game",
                "override.firstjoin" to "&e%player% joined the game",
                "override.quit" to "&e%player% left the game"
            )
        )

        fun load() {
            configFile.config.run {
                firstJoinMessage = getString("message.firstjoin")
                isOverrideJoinQuitMessage = getBoolean("override.enable")
                overrideJoinMessage = getString("override.join")
                overrideFirstJoinMessage = getString("override.firstjoin")
                overrideQuitMessage = getString("override.quit")
            }
        }
    }
}