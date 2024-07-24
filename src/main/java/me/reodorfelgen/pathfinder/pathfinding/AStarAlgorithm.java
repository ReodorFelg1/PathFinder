package me.reodorfelgen.pathfinder.pathfinding;

import me.reodorfelgen.pathfinder.utils.LocationUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import java.util.*;

public class AStarAlgorithm {

    private final World world;
    private final Player player;  // Need to pass Player object to visualize to the right player

    public AStarAlgorithm(World world, Player player) {
        this.world = world;
        this.player = player;
    }

    public List<Node> findPath(Node start, Node end) {
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Set<Node> closedSet = new HashSet<>();
        openSet.add(start);
        start.setGCost(0);
        start.setHCost(distance(start, end));

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            Location currentLocation = LocationUtils.nodeToLocation(current, world);

            // Visualize current node processing
            world.spawnParticle(Particle.VIBRATION, currentLocation.add(0.5, 0.5, 0.5), 5, 0.1, 0.1, 0.1, 0.02);
            player.sendBlockChange(currentLocation, world.getBlockAt(currentLocation).getBlockData()); // Send block change to only the player

            if (current.equals(end)) {
                return reconstructPath(current);
            }

            closedSet.add(current);

            for (Node neighbor : getNeighbors(current)) {
                if (closedSet.contains(neighbor) || !LocationUtils.isWalkable(LocationUtils.nodeToLocation(neighbor, world))) continue;

                double tentativeGCost = current.getGCost() + distance(current, neighbor);
                if (!openSet.contains(neighbor) || tentativeGCost < neighbor.getGCost()) {
                    neighbor.setParent(current);
                    neighbor.setGCost(tentativeGCost);
                    neighbor.setHCost(distance(neighbor, end));
                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }

        return Collections.emptyList(); // Return an empty list if no path is found.
    }

    private List<Node> reconstructPath(Node end) {
        List<Node> path = new ArrayList<>();
        Node current = end;
        while (current != null) {
            path.add(current);
            current = current.getParent();
        }
        Collections.reverse(path);
        return path;
    }

    private List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        int[] deltas = {-1, 0, 1};
        for (int dx : deltas) {
            for (int dy : deltas) {
                for (int dz : deltas) {
                    if (dx != 0 || dy != 0 || dz != 0) {
                        Node potentialNeighbor = new Node(node.getX() + dx, node.getY() + dy, node.getZ() + dz);
                        if (LocationUtils.isWalkable(LocationUtils.nodeToLocation(potentialNeighbor, world))) {
                            neighbors.add(potentialNeighbor);
                        }
                    }
                }
            }
        }
        return neighbors;
    }

    private double distance(Node a, Node b) {
        return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2) + Math.pow(a.getZ() - b.getZ(), 2));
    }
}
