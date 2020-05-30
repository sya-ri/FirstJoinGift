package me.syari.fjg

import me.syari.fjg.command.FirstJoinGiftCommand
import me.syari.fjg.event.EventListener
import me.syari.fjg.item.GiftItem
import me.syari.fjg.item.GiftItemEditEvent
import me.syari.fjg.message.MessageChanger
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
        registerEvents(GiftItemEditEvent, EventListener)
        GiftItem.Config.load()
        MessageChanger.Config.load()
    }

    private fun registerEvents(vararg listener: Listener) {
        listener.forEach {
            server.pluginManager.registerEvents(it, this)
        }
    }
}