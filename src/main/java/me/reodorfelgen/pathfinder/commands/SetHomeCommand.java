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

    private Map<Player, Location> homeLocations = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            homeLocations.put(p, p.getLocation());
            p.sendMessage(ChatColor.GREEN + "Home coordinates are set at :" + homeLocations.put(p, p.getLocation()));
        }

        return true;

    }
}
