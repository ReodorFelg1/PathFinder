package me.reodorfelgen.pathfinder.utils;

import org.bukkit.Location;
import me.reodorfelgen.pathfinder.pathfinding.Node;

public class LocationUtils {

    public static Node locationToNode(Location location) {
        return new Node(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    public static Location nodeToLocation(Node node, org.bukkit.World world) {
        return new Location(world, node.getX(), node.getY(), node.getZ());
    }
}
