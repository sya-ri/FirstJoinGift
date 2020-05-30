package me.syari.fjg.item

import me.syari.fjg.Main.Companion.plugin
import me.syari.fjg.util.ColoredText.toColor
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.UUID

object GiftItem {
    internal var giftItemList = listOf<ItemStack>()

    private var editPlayer: UUID? = null

    var Player.isEditGiftItem
        get() = uniqueId == editPlayer
        set(value) {
            if (value) {
                editPlayer = uniqueId
            }
        }

    val isNullEditPlayer
        get() = editPlayer == null

    fun openEdit(player: Player) {
        val inventory = plugin.server.createInventory(null, 36, "".toColor).apply {
            contents = giftItemList.toTypedArray()
        }
        player.openInventory(inventory)
        player.isEditGiftItem = true
    }

    fun give(player: Player) {
        player.inventory.addItem(*giftItemList.toTypedArray())
    }
}