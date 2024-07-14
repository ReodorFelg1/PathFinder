package me.reodorfelgen.pathfinder.commands;

import me.reodorfelgen.pathfinder.PathFinder;
import me.reodorfelgen.pathfinder.listeners.PlayerMoveListener;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class FindWayHomeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;

        if (commandSender instanceof Player) {

            getServer().getPluginManager().registerEvents(new PlayerMoveListener(), PathFinder.getPlugin());
            player.sendMessage(ChatColor.GREEN + "Finding your way back home...");
        }
        return true;
    }
}
