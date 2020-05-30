package me.syari.fjg.util

import org.bukkit.inventory.ItemStack
import org.bukkit.util.io.BukkitObjectInputStream
import org.bukkit.util.io.BukkitObjectOutputStream
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

object Base64 {
    /**
     * アイテムを Base64 に変換します
     * @param items 対象アイテム
     * @return [String]
     */
    fun toBase64(items: Collection<ItemStack>): String {
        val outputStream = ByteArrayOutputStream()
        BukkitObjectOutputStream(outputStream).use { data ->
            data.writeInt(items.size)
            for (item in items) {
                data.writeObject(item)
            }
        }
        return Base64Coder.encodeLines(outputStream.toByteArray())
    }

    /**
     * Base64 をアイテムに変換します
     * @param base64 Base64 データ
     * @return [List]<[ItemStack]>
     */
    fun getItemStackFromBase64(base64: String): List<ItemStack> {
        return fromBase64(base64).filterNotNull()
    }

    private fun fromBase64(base64: String): Array<ItemStack?> {
        val inputStream = ByteArrayInputStream(Base64Coder.decodeLines(base64))
        return BukkitObjectInputStream(inputStream).use { data ->
            arrayOfNulls<ItemStack>(data.readInt()).also { items ->
                for (i in items.indices) {
                    items[i] = data.readObject() as? ItemStack
                }
            }
        }
    }
}