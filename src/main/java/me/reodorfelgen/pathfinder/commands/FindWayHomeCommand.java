package me.reodorfelgen.pathfinder.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FindWayHomeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;

        if (commandSender instanceof Player) {
            //TODO needs logic to be implimented
            p.sendMessage(ChatColor.GREEN + "Finding your way back home...");
        }
        return true;
    }
}
