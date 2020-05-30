package me.syari.fjg.util

import org.bukkit.ChatColor
import org.bukkit.command.CommandSender

object ColoredText {
    val String.toColor: String
        get() = ChatColor.translateAlternateColorCodes('&', this)

    fun CommandSender.sendColoredMessage(message: String) {
        sendMessage(message.toColor)
    }

    fun CommandSender.sendWithPrefix(message: String) {
        sendColoredMessage("&6[FirstJoinGift] $message")
    }
}