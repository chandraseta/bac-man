package model.character;

import java.awt.Point;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.concurrent.ThreadLocalRandom;
import model.VisibilityGraph;

/**
 * Created by ASUS on 23/04/17.
 */
public class PathFinder {
  private class Node {
    private Deque<Integer> path;
    private int cost;
    private int heuristic;

    Node(int currNode, int initCost) {
      path = new LinkedList<>();
      path.add(currNode);
      cost = initCost;
      if (currNode != END)
        heuristic = manhattanDistance(VisibilityGraph.getLandmarks().elementAt(currNode),destination);
      else
        heuristic = 0;
    }

    Node(Node parent, int currNode, int addCost) {
      path = new LinkedList<>(parent.path);
      cost = parent.cost + addCost;
      path.add(currNode);
      if (currNode != END)
        heuristic = manhattanDistance(VisibilityGraph.getLandmarks().elementAt(currNode),destination);
      else
        heuristic = 0;
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

  private final int END = VisibilityGraph.getNumOfLandmarks();
  
  private Point origin;
  private Point destination;

  private int [] fromOriginDistance;
  private int [] fromOriginMovement;
  private int [] toDestinationDistance;
  private int [] toDestinationMovement;

  private boolean [][] accessibilityMatrix;

  private int movement;
  
  public PathFinder() {
    fromOriginDistance = new int[VisibilityGraph.getNumOfLandmarks()];
    fromOriginMovement = new int[VisibilityGraph.getNumOfLandmarks()];
    toDestinationDistance = new int[VisibilityGraph.getNumOfLandmarks()];
    toDestinationMovement = new int[VisibilityGraph.getNumOfLandmarks()];
    accessibilityMatrix = VisibilityGraph.getAccessibilityMatrix();
  }

  public void calculateMovement(Point origin, Point destination) {
    this.origin = origin;
    this.destination = destination;
    System.out.print(origin);
    System.out.println(destination);

    if (origin.equals(destination))
      movement = 0;
    else {
      movement = checkLineOfSight();
      if (movement == 0) {
        addOriginToGraph();
        addDestinationToGraph();
        movement = findPath();
      }
    }
    if (movement == 0)
      movement = ThreadLocalRandom.current().nextInt(1,5);
  }

  private int checkLineOfSight() {
    boolean isLineOfSight = true;

    if (origin.x == destination.x) {
      if (destination.y > origin.y) {
        for (int i = origin.y; i < destination.y && isLineOfSight; ++i) {
          isLineOfSight = accessibilityMatrix[origin.x][i];
        }
        if (isLineOfSight)
          return 2;
      } else {
        for (int i = origin.y; i > destination.y && isLineOfSight; --i) {
          isLineOfSight = accessibilityMatrix[origin.x][i];
        }
        if (isLineOfSight)
          return 4;
      }
    } else if (origin.y == destination.y) {
      if (destination.x > origin.x) {
        for (int i = origin.x; i < destination.x && isLineOfSight; ++i) {
          isLineOfSight = accessibilityMatrix[i][origin.y];
        }
        if (isLineOfSight)
          return 3;
      } else {
        for (int i = origin.x; i > destination.x && isLineOfSight; --i) {
          isLineOfSight = accessibilityMatrix[i][origin.y];
        }
        if (isLineOfSight)
          return 1;
      }
    }
    return 0;
  }

  private void addOriginToGraph() {
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
    Arrays.fill(toDestinationDistance, Integer.MAX_VALUE);
    Arrays.fill(toDestinationMovement, 0);

    int n = VisibilityGraph.getLandmarks().indexOf(destination);
    if (n != -1) {
      toDestinationDistance[n] = 0;
      toDestinationMovement[n] = 0;
    } else {
      VisibilityGraph.findNeighbors(destination, toDestinationDistance, toDestinationMovement);
      for (int movement: toDestinationMovement) {
        movement += 2;
        if (movement > 4)
          movement -= 4;
      }
    }
  }

  private int findPath() {
    try {
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

      int destNode = currNode.path.poll();
      int movement = fromOriginMovement[destNode];
      if (movement == 0)
        movement = VisibilityGraph.getMovementMatrix()[destNode][currNode.path.getFirst()];
      return movement;
    } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
      return 0;
    }
  }

  public static int manhattanDistance(Point start, Point end) {
    if (start.equals(end)) {
      return 0;
    } else {
      int dx = start.x - end.x;
      if (dx < 0)
        dx *= -1;
      int dy = start.y - end.y;
      if (dy < 0)
        dy *= -1;

      return (dx + dy);
    }
  }

  public int getMovement() {
    return movement;
  }
}
