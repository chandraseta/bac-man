package model.character;

import model.VisibilityGraph;

import java.awt.Point;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Kelas berisi GhostTypeA yang menggunakan algoritma A* dalam mengejar player.
 */
public class GhostTypeA extends Ghost {
  private class Node {
    private Deque<Integer> path;
    private int cost;
    private int heuristic;

    public Node(int currNode, int initCost) {
      path = new LinkedList<>();
      path.add(currNode);
      cost = initCost;
      heuristic = manhattanDistance(VisibilityGraph.getLandmarks().elementAt(currNode),Player.getPosition());
    }

    public Node(Node parent, int currNode, int addCost) {
      path = new LinkedList<>(parent.path);
      cost = parent.cost + addCost;
      path.add(currNode);
      heuristic = manhattanDistance(VisibilityGraph.getLandmarks().elementAt(currNode),Player.getPosition());
    }
    
    public int compareTo(Node n2) {
      if (this.cost + this.heuristic < n2.cost + n2.heuristic) {
        return -1;
      } else if (this.cost + this.heuristic > n2.cost + n2.heuristic) {
        return 1;
      } else {
        return 0;
      }
    }
  }

  public GhostTypeA(int i, int j) {
    super(i, j, "src/view/assets/ghost_a.png");
  }

  public int moveTowardsPlayer() {
    // Check line of sight
    int movement = checkLineOfSight();
    if (movement != 0) {
      return movement;
    } else {
      return aStar();
    }
  }

  private int aStar() {
    PriorityQueue<Node> aliveNodes = new PriorityQueue<>(10, Node::compareTo);
    int n = VisibilityGraph.getNumOfLandmarks();
    int [][] adjacencyMatrix = VisibilityGraph.getAdjacencyMatrix();

    for (int i = 0; i < fromGhostDistance.length; ++i) {
      if (fromGhostDistance[i] != Integer.MAX_VALUE) {
        aliveNodes.add(new Node(i, fromGhostDistance[i]));
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
      if (toPlayerDistance[u] != Integer.MAX_VALUE) {
        aliveNodes.add(new Node(currNode, END, toPlayerDistance[u]));
      }
      currNode = aliveNodes.poll();
    }

    return fromGhostMovement[currNode.path.getFirst()];
  }

  private int manhattanDistance(Point start, Point end) {
    int dx = start.x - end.x;
    if (dx < 0)
        dx *= -1;
    int dy = start.y - end.y;
    if (dy < 0)
        dy *= -1;

    return (dx + dy);
  }
}
