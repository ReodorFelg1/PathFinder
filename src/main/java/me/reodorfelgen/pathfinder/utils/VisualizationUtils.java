package me.reodorfelgen.pathfinder.utils;

import me.reodorfelgen.pathfinder.PathFinder;
import me.reodorfelgen.pathfinder.pathfinding.Node;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class VisualizationUtils {

    public static void visualizePath(Player player, List<Node> path) {
        World world = player.getWorld();
        for (Node node : path) {
            Location loc = LocationUtils.nodeToLocation(node, world);
            Material originalMaterial = world.getBlockAt(loc).getType();
            world.getBlockAt(loc).setType(Material.GLOWSTONE);

            new BukkitRunnable() {
                @Override
                public void run() {
                    world.getBlockAt(loc).setType(originalMaterial);
                }
            }.runTaskLater(PathFinder.getPlugin(), 500); //25 seconds
        }
    }

}
