package me.syari.fjg.command

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

object FirstJoinGiftCommand: CommandExecutor, TabCompleter {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        when(args.getOrNull(0)?.toLowerCase()){
            "get" -> {

            }
            "edit" -> {

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