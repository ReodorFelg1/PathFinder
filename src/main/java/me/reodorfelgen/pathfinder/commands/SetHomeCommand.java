package me.reodorfelgen.pathfinder.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class SetHomeCommand implements CommandExecutor {

    private final Map<Player, Location> homeLocations = new HashMap<>();

    public Location getHomeLocation(Player player) {
        return homeLocations.get(player);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        Player player = (Player) commandSender;
        homeLocations.put(player, player.getLocation());
        player.sendMessage(ChatColor.GREEN + "Home coordinates are set at: " + getHomeLocation(player).toString());

        return true;
    }
}
