package me.reodorfelgen.pathfinder.pathfinding;

import java.util.*;

public class AStarAlgorithm {

    public List<Node> findPath(Node start, Node end) {
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Set<Node> closedSet = new HashSet<>();
        openSet.add(start);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            if (current.equals(end)) {
                System.out.println("Found path!");
                return reconstructPath(current);
            }

            closedSet.add(current);

            for (Node neighbor : getNeighbors(current)) {
                if (closedSet.contains(neighbor)) continue;

                double tentativeGCost = current.getGCost() + distance(current, neighbor);

                if (tentativeGCost < neighbor.getGCost() || !openSet.contains(neighbor)) {
                    neighbor.setParent(current);
                    neighbor.setGCost(tentativeGCost);
                    neighbor.sethCost(distance(neighbor, end));

                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }

        return Collections.emptyList(); //return empty list if no path found
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

        // Add neighboring nodes (assuming a grid with 4 or 8 possible directions)
        // Example for 4 directions (up, down, left, right)
        neighbors.add(new Node(node.getX() + 1, node.getY()));
        neighbors.add(new Node(node.getX() - 1, node.getY()));
        neighbors.add(new Node(node.getX(), node.getY() + 1));
        neighbors.add(new Node(node.getX(), node.getY() - 1));

        // Uncomment below lines for 8 directions (including diagonals)
        neighbors.add(new Node(node.getX() + 1, node.getY() + 1));
        neighbors.add(new Node(node.getX() + 1, node.getY() - 1));
        neighbors.add(new Node(node.getX() - 1, node.getY() + 1));
        neighbors.add(new Node(node.getX() - 1, node.getY() - 1));

        return neighbors;
    }

    private double distance(Node a, Node b) {
        return Math.hypot(a.getX() - b.getX(), a.getY() - b.getY());
    }

}
 