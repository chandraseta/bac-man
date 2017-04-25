package model.character;

import model.VisibilityGraph;

import java.awt.Point;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Kelas PathFinder
 */
public class PathFinder {
    /**
     * Kelas Node mendefinisikan struktur data yang terdiri dari Path yang dilalui, cost, dan heuristic.
     */
    private class Node {

        /**
         * Path dari titik yang dilalui dan dihitung untuk menghitung jarak menuju titik yang ingin dicapai.
         */
        private Deque<Integer> path;

        /**
         * Cost dari path yang dilalui.
         */
        private int cost;

        /**
         * Heuristic cost menuju titik yang ingin dicapai.
         */
        private int heuristic;

        /**
         * <p>
         * Constructor
         * <p>
         * Menciptakan Node
         * </p>
         *
         * @param currNode
         * @param initCost
         */
        Node(int currNode, int initCost) {
            path = new LinkedList<>();
            path.add(currNode);
            cost = initCost;
            heuristic = manhattanDistance(VisibilityGraph.getLandmarks().elementAt(currNode), Player.getPosition());
        }

        Node(Node parent, int currNode, int addCost) {
            path = new LinkedList<>(parent.path);
            cost = parent.cost + addCost;
            path.add(currNode);
            heuristic = manhattanDistance(VisibilityGraph.getLandmarks().elementAt(currNode), Player.getPosition());
        }

        int compareTo(Node n2) {
            if (this.cost + this.heuristic < n2.cost + n2.heuristic) {
                return -1;
            } else if (this.cost + this.heuristic > n2.cost + n2.heuristic) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    private final int END = -1;

    private Point origin;
    private Point destination;

    private int[] fromOriginDistance;
    private int[] fromOriginMovement;
    private int[] toDestinationDistance;
    private int[] toDestinationMovement;

    private int movement;

    public PathFinder(Point origin, Point destination) {
        this.origin = origin;
        this.destination = destination;

        movement = checkLineOfSight();
        if (checkLineOfSight() == 0) {
            addOriginToGraph();
            addDestinationToGraph();
            movement = findPath();
        }
    }

    private int checkLineOfSight() {
        if (origin.x == destination.x) {
            if (origin.y > destination.y) {
                return 4;
            } else {
                return 2;
            }
        } else if (origin.y == destination.y) {
            if (origin.x > destination.x) {
                return 1;
            } else {
                return 3;
            }
        } else {
            return 0;
        }
    }

    private void addOriginToGraph() {
        fromOriginDistance = new int[VisibilityGraph.getNumOfLandmarks()];
        fromOriginMovement = new int[VisibilityGraph.getNumOfLandmarks()];
        Arrays.fill(fromOriginDistance, Integer.MAX_VALUE);
        Arrays.fill(fromOriginMovement, 0);

        int n = VisibilityGraph.getLandmarks().indexOf(origin);
        if (n != -1) {
            fromOriginDistance[n] = 0;
            fromOriginMovement[n] = 0;
        } else {
            VisibilityGraph.findNeighbors(origin, fromOriginDistance, fromOriginMovement);
        }
    }

    private void addDestinationToGraph() {
        toDestinationDistance = new int[VisibilityGraph.getNumOfLandmarks()];
        toDestinationMovement = new int[VisibilityGraph.getNumOfLandmarks()];
        Arrays.fill(toDestinationDistance, Integer.MAX_VALUE);
        Arrays.fill(toDestinationMovement, 0);

        Point playerPos = new Point(Player.getPlayerI(), Player.getPlayerJ());
        int n = VisibilityGraph.getLandmarks().indexOf(playerPos);
        if (n != -1) {
            toDestinationDistance[n] = 0;
            toDestinationMovement[n] = 0;
        } else {
            VisibilityGraph.findNeighbors(playerPos, toDestinationDistance, toDestinationMovement);
            for (int movement : toDestinationMovement) {
                movement += 2;
                if (movement > 4)
                    movement -= 4;
            }
        }
    }

    private int findPath() {
        PriorityQueue<Node> aliveNodes = new PriorityQueue<>(10, Node::compareTo);
        int n = VisibilityGraph.getNumOfLandmarks();
        int[][] adjacencyMatrix = VisibilityGraph.getAdjacencyMatrix();

        for (int i = 0; i < fromOriginDistance.length; ++i) {
            if (fromOriginDistance[i] != Integer.MAX_VALUE) {
                aliveNodes.add(new Node(i, fromOriginDistance[i]));
            }
        }

        Node currNode = aliveNodes.poll();
        while (currNode.path.getLast() != END) {
            int u = currNode.path.getLast();
            for (int v = 0; v < n; ++v) {
                if (adjacencyMatrix[u][v] != Integer.MAX_VALUE && !currNode.path.contains(v)) {
                    aliveNodes.add(new Node(currNode, v, adjacencyMatrix[u][v]));
                }
            }
            if (toDestinationDistance[u] != Integer.MAX_VALUE) {
                aliveNodes.add(new Node(currNode, END, toDestinationDistance[u]));
            }
            currNode = aliveNodes.poll();
        }

        return fromOriginMovement[currNode.path.getFirst()];
    }

    public static int manhattanDistance(Point start, Point end) {
        int dx = start.x - end.x;
        if (dx < 0)
            dx *= -1;
        int dy = start.y - end.y;
        if (dy < 0)
            dy *= -1;

        return (dx + dy);
    }

    public int getMovement() {
        return movement;
    }
}
