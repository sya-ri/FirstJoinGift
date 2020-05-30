package me.syari.fjg

import me.syari.fjg.command.FirstJoinGiftCommand
import me.syari.fjg.item.GiftItem
import me.syari.fjg.item.GiftItemEditEvent
import org.bukkit.event.Listener
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
        registerEvents(GiftItemEditEvent)
        GiftItem.Config.load()
    }

    private fun registerEvents(vararg listener: Listener) {
        listener.forEach {
            server.pluginManager.registerEvents(it, this)
        }
    }
}