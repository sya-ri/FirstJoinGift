package me.syari.fjg

import me.syari.fjg.command.FirstJoinGiftCommand
import org.bukkit.plugin.java.JavaPlugin

class Main: JavaPlugin() {
    companion object {
        internal lateinit var plugin: JavaPlugin
    }

    override fun onEnable() {
        plugin = this
        getCommand("firstjoingift")?.run {
            executor = FirstJoinGiftCommand
            tabCompleter = FirstJoinGiftCommand
        }
    }
}