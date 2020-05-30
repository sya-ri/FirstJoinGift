package me.syari.fjg.util

import org.bukkit.ChatColor

object ColoredText {
    val String.toColor: String
        get() = ChatColor.translateAlternateColorCodes('&', this)
}