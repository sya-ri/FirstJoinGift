package me.syari.fjg.event

import me.syari.fjg.Main.Companion.plugin
import me.syari.fjg.item.GiftItem
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

object PlayerJoinEventListener: Listener {
    @EventHandler
    fun on(e: PlayerJoinEvent) {
        val player = e.player
        if (!player.hasPlayedBefore()) {
            plugin.server.scheduler.runTaskLater(plugin, {
                GiftItem.give(player)
            }, 3)
        }
    }
}