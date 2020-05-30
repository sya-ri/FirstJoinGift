package me.syari.fjg.command

import me.syari.fjg.item.GiftItem
import me.syari.fjg.util.ColoredText.toColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

object FirstJoinGiftCommand: CommandExecutor, TabCompleter {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        when(args.getOrNull(0)?.toLowerCase()){
            "get" -> {
                if(sender !is Player) {
                    sender.sendMessage("コンソールから実行できないコマンドです".toColor)
                    return true
                }
                GiftItem.give(sender)
            }
            "edit" -> {
                if(sender !is Player) {
                    sender.sendMessage("コンソールから実行できないコマンドです".toColor)
                    return true
                }
                GiftItem.openEdit(sender)
            }
        }
        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): List<String> {
        fun complete(index: Int, vararg completeList: String): List<String> {
            val lower = args[index].toLowerCase()
            return completeList.filter { it.startsWith(lower) }
        }

        return when(args.size){
            1 -> complete(0, "get", "edit")
            else -> emptyList()
        }
    }
}