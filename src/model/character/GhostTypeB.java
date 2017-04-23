package model.character;

import model.VisibilityGraph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Kelas berisi GhostTypeB yang menggunakan algoritma Dijkstra dalam mengejar player.
 */
public class GhostTypeB extends Ghost {
  private class Node {

    private Deque<Integer> path;

    private int cost;

    public Node(int currNode, int initCost) {
      path = new LinkedList<>();
      path.add(currNode);
      cost = initCost;
    }

    public Node(Node parent, int currNode, int addCost) {
      path = new LinkedList<>(parent.path);
      cost = parent.cost + addCost;
      path.add(currNode);
    }

    public int compareTo(Node comparedNode) {
      if (this.cost < comparedNode.cost) {
        return -1;
      } else if (this.cost > comparedNode.cost) {
        return 1;
      } else {
        return 0;
      }
    }

  }
  public GhostTypeB(int i, int j) {
    super(i, j, "src/view/assets/ghost_b.png");
  }

  public int moveTowardsPlayer() {
    int movement = checkLineOfSight();
    if (movement != 0) {
      return movement;
    } else {
      return dijkstra();
    }
  }

  private int dijkstra() {
    PriorityQueue<Node> priorityQueue = new PriorityQueue<>(10, Node::compareTo);
    int n = VisibilityGraph.getNumOfLandmarks();
    int [][] adjacencyMatrix = VisibilityGraph.getAdjacencyMatrix();
    for (int i = 0; i < fromGhostDistance.length; ++i) {
      if (fromGhostDistance[i] != Integer.MAX_VALUE) {
        priorityQueue.add(new Node(i, fromGhostDistance[i]));
      }
    }
    Node currNode = priorityQueue.poll();
    while (currNode.path.getLast() != END) {
      int u = currNode.path.getLast();
      for (int v = 0; v < n; ++v) {
        if (!currNode.path.contains(v) && adjacencyMatrix[u][v] != Integer.MAX_VALUE) {
          priorityQueue.add(new Node(currNode, v, adjacencyMatrix[u][v]));
        }
      }
      if (toPlayerDistance[u] != Integer.MAX_VALUE) {
        priorityQueue.add(new Node(currNode, END, toPlayerDistance[u]));
      }
      currNode = priorityQueue.poll();
    }
    return fromGhostMovement[currNode.path.getFirst()];
  }
}