package me.reodorfelgen.pathfinder.utils;

import me.reodorfelgen.pathfinder.pathfinding.Node;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.List;

public class VisualizationUtils {

    public static void visualizePath(Player player, List<Node> path) {
        World world = player.getWorld();
        System.out.println("trying to spawn");
        for (Node node : path) {
            Location loc = LocationUtils.nodeToLocation(node, world);
            world.getBlockAt(loc).setType(Material.GLOWSTONE); // You can change the material to your preferred block type
            System.out.println("Should have spawned");
        }
    }

}
