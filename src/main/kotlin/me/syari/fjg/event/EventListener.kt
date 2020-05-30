package me.syari.fjg.event

import me.syari.fjg.Main.Companion.plugin
import me.syari.fjg.item.GiftItem
import me.syari.fjg.message.MessageChanger.firstJoinMessage
import me.syari.fjg.message.MessageChanger.isOverrideJoinQuitMessage
import me.syari.fjg.message.MessageChanger.overrideFirstJoinMessage
import me.syari.fjg.message.MessageChanger.overrideJoinMessage
import me.syari.fjg.message.MessageChanger.overrideQuitMessage
import me.syari.fjg.util.ColoredText.sendColoredMessage
import me.syari.fjg.util.ColoredText.toColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

object EventListener: Listener {
    @EventHandler
    fun on(e: PlayerJoinEvent) {
        val player = e.player
        val isFirstJoin = !player.hasPlayedBefore()
        if (isFirstJoin) {
            plugin.server.scheduler.runTaskLater(plugin, {
                GiftItem.give(player)
                firstJoinMessage?.let {
                    player.sendColoredMessage(it)
                }
            }, 3)
        }
        if (isOverrideJoinQuitMessage) {
            e.joinMessage = if (isFirstJoin) {
                overrideFirstJoinMessage
            } else {
                overrideJoinMessage
            }?.replace("%player%", player.displayName)?.toColor
        }
    }

    @EventHandler
    fun on(e: PlayerQuitEvent) {
        if (isOverrideJoinQuitMessage) {
            val player = e.player
            e.quitMessage = overrideQuitMessage?.replace("%player%", player.displayName)?.toColor
        }
    }
}