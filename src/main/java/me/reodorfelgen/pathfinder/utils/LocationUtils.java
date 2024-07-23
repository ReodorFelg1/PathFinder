package me.reodorfelgen.pathfinder.utils;

import me.reodorfelgen.pathfinder.pathfinding.Node;
import org.bukkit.Location;

public class LocationUtils {

    public static Node locationToNode(Location location) {
        return new Node(location.getBlockX(), location.getBlockZ());
    }

    public static Location nodeToLocation(Node node, org.bukkit.World world) {
        return new Location(world, node.getX(), 117, node.getY());
    }

}
