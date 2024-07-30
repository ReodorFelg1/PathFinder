package me.reodorfelgen.pathfinder.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.World;
import me.reodorfelgen.pathfinder.pathfinding.Node;

public class LocationUtils {

    public static Node locationToNode(Location location) {
        return new Node(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    public static Location nodeToLocation(Node node, World world) {
        return new Location(world, node.getX(), node.getY(), node.getZ());
    }

    public static boolean isWalkable(Location location) {
        Block block = location.getWorld().getBlockAt(location);
        return block.getType().isAir() || block.getType().equals(Material.WATER) && block.getType() != Material.LAVA;
    }
}
