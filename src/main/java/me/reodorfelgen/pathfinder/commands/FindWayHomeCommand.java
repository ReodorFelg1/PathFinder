package me.reodorfelgen.pathfinder.commands;

import me.reodorfelgen.pathfinder.PathFinder;
import me.reodorfelgen.pathfinder.pathfinding.AStarAlgorithm;
import me.reodorfelgen.pathfinder.pathfinding.Node;
import me.reodorfelgen.pathfinder.utils.LocationUtils;
import me.reodorfelgen.pathfinder.utils.VisualizationUtils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class FindWayHomeCommand implements CommandExecutor {

    private final SetHomeCommand setHomeCommand;

    public FindWayHomeCommand(SetHomeCommand setHomeCommand) {
        this.setHomeCommand = setHomeCommand;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        Player player = (Player) commandSender;
        Location homeLocation = setHomeCommand.getHomeLocation(player);

        if (homeLocation == null) {
            player.sendMessage(ChatColor.RED + "You haven't set a home location. Use /sethome to set one.");
            return true;
        }

        Location currentLocation = player.getLocation();
        Node startNode = LocationUtils.locationToNode(currentLocation);
        Node endNode = LocationUtils.locationToNode(homeLocation);

        // Run pathfinding asynchronously
        new BukkitRunnable() {
            @Override
            public void run() {
                AStarAlgorithm aStar = new AStarAlgorithm(player.getWorld(), player);
                List<Node> path = aStar.findPath(startNode, endNode);

                // Handle the result on the main server thread
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (path.isEmpty()) {
                            player.sendMessage(ChatColor.RED + "No path found to your home.");
                        } else {
                            player.sendMessage(ChatColor.GREEN + "Path found! Visualizing...");
                            VisualizationUtils.visualizePath(player, path);
                        }
                    }
                }.runTask(PathFinder.getPlugin());
            }
        }.runTaskAsynchronously(PathFinder.getPlugin());

        return true;
    }
}
