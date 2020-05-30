package me.syari.fjg.command

import me.syari.fjg.item.GiftItem
import me.syari.fjg.util.ColoredText.sendWithPrefix
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

object FirstJoinGiftCommand: CommandExecutor, TabCompleter {
    override fun onCommand(
        sender: CommandSender, command: Command, label: String, args: Array<out String>
    ): Boolean {
        when (args.getOrNull(0)?.toLowerCase()) {
            "get" -> {
                if (sender !is Player) {
                    sender.sendWithPrefix("&cコンソールから実行できないコマンドです")
                    return true
                }
                GiftItem.give(sender)
                sender.sendWithPrefix("&fアイテムを取得しました")
            }
            "edit" -> {
                if (sender !is Player) {
                    sender.sendWithPrefix("&cコンソールから実行できないコマンドです")
                    return true
                }
                if (GiftItem.isNullEditPlayer) {
                    GiftItem.openEdit(sender)
                } else {
                    sender.sendWithPrefix("&c編集中のプレイヤーがいます")
                }
            }
            else -> {
                sender.sendWithPrefix(
                    """
                    &fコマンド一覧
                    &7- &6/fjg get &7テストでアイテムを取得します
                    &7- &6/fjg edit &7渡すアイテムを設定します
                """.trimIndent()
                )
            }
        }
        return true
    }

    override fun onTabComplete(
        sender: CommandSender, command: Command, alias: String, args: Array<out String>
    ): List<String> {
        fun complete(index: Int, vararg completeList: String): List<String> {
            val lower = args[index].toLowerCase()
            return completeList.filter { it.startsWith(lower) }
        }

        return when (args.size) {
            1 -> complete(0, "get", "edit")
            else -> emptyList()
        }
    }
}