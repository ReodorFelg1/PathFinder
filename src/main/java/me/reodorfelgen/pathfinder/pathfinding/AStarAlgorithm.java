package me.reodorfelgen.pathfinder.pathfinding;

import me.reodorfelgen.pathfinder.utils.LocationUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.*;

public class AStarAlgorithm {

    private final World world;
    private final Player player;

    public AStarAlgorithm(World world, Player player) {
        this.world = world;
        this.player = player;
    }

    public List<Node> findPath(Node start, Node end) {
        long startTime = System.nanoTime();  // Start timing

        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Set<Node> closedSet = new HashSet<>();
        openSet.add(start);
        start.setGCost(0);
        start.setHCost(distance(start, end));

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            Location currentLocation = LocationUtils.nodeToLocation(current, world);

            // Visualize current node processing
            world.spawnParticle(Particle.HEART, currentLocation.add(0.5, 0.5, 0.5), 1);
            player.sendBlockChange(currentLocation, world.getBlockAt(currentLocation).getBlockData());

            if (current.equals(end)) {
                List<Node> path = reconstructPath(current);
                long endTime = System.nanoTime();  // End timing
                double duration = (endTime - startTime) / 1_000_000.0;  // Convert to milliseconds
                System.out.println("Pathfinding took " + duration + " milliseconds.");
                return path;
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

        long endTime = System.nanoTime();  // End timing if no path found
        double duration = (endTime - startTime) / 1_000_000.0;  // Convert to milliseconds
        System.out.println("Pathfinding completed without finding a path in " + duration + " milliseconds.");

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
        int dx = a.getX() - b.getX();
        int dy = a.getY() - b.getY();
        int dz = a.getZ() - b.getZ();
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

}
