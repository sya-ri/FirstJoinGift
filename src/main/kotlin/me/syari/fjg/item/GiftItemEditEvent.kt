package me.syari.fjg.item

import me.syari.fjg.item.GiftItem.giftItemList
import me.syari.fjg.item.GiftItem.isEditGiftItem
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryCloseEvent

object GiftItemEditEvent: Listener {
    @EventHandler
    fun on(e: InventoryCloseEvent) {
        val player = e.player as? Player ?: return
        if (player.isEditGiftItem) {
            val inventory = e.inventory
            giftItemList = inventory.contents.filterNotNull()
            player.isEditGiftItem = false
        }
    }
}