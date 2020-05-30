package me.syari.fjg.item

import me.syari.fjg.Main.Companion.plugin
import me.syari.fjg.util.Base64
import me.syari.fjg.util.ColoredText.toColor
import me.syari.fjg.util.ConfigFile
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.UUID

object GiftItem {
    internal var giftItemList = listOf<ItemStack>()

    internal var editPlayer: UUID? = null

    val Player.isEditGiftItem
        get() = uniqueId == editPlayer

    val isNullEditPlayer
        get() = editPlayer == null

    fun openEdit(player: Player) {
        val inventory = plugin.server.createInventory(null, 36, "&0&lギフトアイテム編集".toColor).apply {
            contents = giftItemList.toTypedArray()
        }
        player.openInventory(inventory)
        editPlayer = player.uniqueId
    }

    fun give(player: Player) {
        player.inventory.addItem(*giftItemList.toTypedArray())
    }

    internal object Config {
        private val configFile = ConfigFile("item.yml")

        fun load() {
            configFile.config.run {
                val base64 = getString("base64")
                val itemList = base64?.let { Base64.getItemStackFromBase64(base64) } ?: emptyList()
                giftItemList = itemList
            }
        }

        fun update() {
            configFile.config.set("base64", Base64.toBase64(giftItemList))
            configFile.save()
        }
    }
}