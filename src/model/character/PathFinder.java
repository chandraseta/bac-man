package model.character;

import model.VisibilityGraph;

import java.awt.*;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Kelas PathFinder mendefinisikan kelas untu mencari jalur Ghost ke suatu titik.
 */
public class PathFinder {

  /**
   * Kelas Node mendefinisikan struktur data yang terdiri dari Path yang dilalui, cost, dan
   * heuristic.
   */
  private class Node {

    /**
     * Path dari titik yang dilalui dan dihitung untuk menghitung jarak menuju titik yang ingin
     * dicapai.
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
     * Constructor Node dengan parameter currNode dan initCost.
     *
     * @param currNode Node yang akan di-expand.
     * @param initCost Cost mula-mula Node dari Ghost mencapai titik pada Node.
     */
    Node(int currNode, int initCost) {
      path = new LinkedList<>();
      path.add(currNode);
      cost = initCost;
      if (currNode != END) {
        heuristic = manhattanDistance(VisibilityGraph.getLandmarks().elementAt(currNode),
            destination);
      } else {
        heuristic = 0;
      }
    }

    /**
     * Constructor Node dengan parameter parent, currNode, dan addCost.
     *
     * @param parent Parent Node pada Node yang akan di-expand.
     * @param currNode Node yang aan di-expand.
     * @param addCost Cost yang ditambahkan pada Node.
     */
    Node(Node parent, int currNode, int addCost) {
      path = new LinkedList<>(parent.path);
      cost = parent.cost + addCost;
      path.add(currNode);
      if (currNode != END) {
        heuristic = manhattanDistance(VisibilityGraph.getLandmarks().elementAt(currNode),
            destination);
      } else {
        heuristic = 0;
      }
    }

    /**
     * Fungsi mengembalikan :
     * -1 jika total cost objek node lebih kecil dari node yang dibandingkan.
     * 0 jika total cost objek node sama dengan node yang dibandingkan.
     * 1 jika total cost objek node lebih besar dari node yang dibandingkan.
     *
     * @param n2 Node yang dibandingkan.
     * @return Integer
     */
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
  /**
   * Konstanta banyaknya landmark pada Arena.
   */
  private final int END = VisibilityGraph.getNumOfLandmarks();

  /**
   * Titik mula-mula.
   */
  private Point origin;

  /**
   * Titik yang ingin dituju.
   */
  private Point destination;

  /**
   * Jarak dari titik origin ke setiap landmark yang segaris dengan origin.
   * Bila tidak segaris, jarak bernilai 0.
   */
  private int[] fromOriginDistance;

  /**
   * Pergerakan dari titik origin ke setiap landmark yang segaris dengan origin.
   * Bila tidak segaris, movement bernilai 0.
   */
  private int[] fromOriginMovement;

  /**
   * Jarak dari setiap landmark ke titik tujuan yang segaris dengan landmark itu sendiri.
   * Bila tidak segaris, jarak bernilai 0.
   */
  private int[] toDestinationDistance;

  /**
   * Pergerakan dari setiap landmark ke titik tujuan yang segaris dengan landmark itu sendiri.
   * Bila tidak segaris, movement bernilai 0.
   */
  private int[] toDestinationMovement;

  /**
   * Matriks aksesibilitas Arena.
   */
  private boolean[][] accessibilityMatrix;

  /**
   * Arah pergerakan Ghost.
   */
  private int movement;

  /**
   * <p>
   * Constructor
   *
   * Menciptakan PathFinder yang menghasilkan VisibilityGraph, jarak dan pergerakan titik awal ke
   * setiap landmark dan setiap landmark ke titik tujuan.
   * </p>
   */
  public PathFinder() {
    fromOriginDistance = new int[VisibilityGraph.getNumOfLandmarks()];
    fromOriginMovement = new int[VisibilityGraph.getNumOfLandmarks()];
    toDestinationDistance = new int[VisibilityGraph.getNumOfLandmarks()];
    toDestinationMovement = new int[VisibilityGraph.getNumOfLandmarks()];
    accessibilityMatrix = VisibilityGraph.getAccessibilityMatrix();
  }

  /**
   * Melakukan kalkulasi pergerakan berdasarkan dua titik yang berbeda pada map.
   *
   * @param origin Titik mula-mula.
   * @param destination Titik yang ingin dituju.
   */
  public void calculateMovement(Point origin, Point destination) {
    this.origin = origin;
    this.destination = destination;

    if (origin.equals(destination)) {
      movement = 0;
    } else {
      movement = checkLineOfSight();
      if (movement == 0) {
        addOriginToGraph();
        addDestinationToGraph();
        movement = findPath();
      }
    }
    if (movement == 0) {
      movement = ThreadLocalRandom.current().nextInt(1, 5);
    }
  }

  /**
   * Fungsi mengembalikan movement Ghost.
   * Movement Ghost = 0 apabila Ghost tidak segaris dengan Player.
   * Apabila segaris, Ghost akan bergerak menuju Player.
   *
   * @return Pergerakan Ghost.
   */
  private int checkLineOfSight() {
    boolean isLineOfSight = true;

    if (origin.x == destination.x) {
      if (destination.y > origin.y) {
        for (int i = origin.y; i < destination.y && isLineOfSight; ++i) {
          isLineOfSight = accessibilityMatrix[origin.x][i];
        }
        if (isLineOfSight) {
          return 2;
        }
      } else {
        for (int i = origin.y; i > destination.y && isLineOfSight; --i) {
          isLineOfSight = accessibilityMatrix[origin.x][i];
        }
        if (isLineOfSight) {
          return 4;
        }
      }
    } else if (origin.y == destination.y) {
      if (destination.x > origin.x) {
        for (int i = origin.x; i < destination.x && isLineOfSight; ++i) {
          isLineOfSight = accessibilityMatrix[i][origin.y];
        }
        if (isLineOfSight) {
          return 3;
        }
      } else {
        for (int i = origin.x; i > destination.x && isLineOfSight; --i) {
          isLineOfSight = accessibilityMatrix[i][origin.y];
        }
        if (isLineOfSight) {
          return 1;
        }
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
      for (int movement : toDestinationMovement) {
        if (movement != 0) {
          movement += 2;
          if (movement > 4) {
            movement -= 4;
          }
        }
      }
    }
  }

  /**
   * Fungsi mengembalikan pergerakan Ghost dengan menentukan sebuah path untuk mencapai titik yang
   * dituju Ghost.
   *
   * @return pergerakan Ghost.
   */
  private int findPath() {
    try {
      PriorityBlockingQueue<Node> aliveNodes = new PriorityBlockingQueue<>(10, Node::compareTo);
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
      if (movement == 0) {
        movement = VisibilityGraph.getMovementMatrix()[destNode][currNode.path.getFirst()];
      }
      return movement;
    } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
      return 0;
    }
  }

  /**
   * Fungsi mengembalikan Jarak Manhattan dari dua titik yang berbeda.
   *
   * @param start Titik mula-mula.
   * @param end Titik yang dituju.
   * @return Jarak Manhattan dari kedua titik.
   */
  public static int manhattanDistance(Point start, Point end) {
    if (start.equals(end)) {
      return 0;
    } else {
      int dx = Math.abs(start.x - end.x);
      int dy = Math.abs(start.y - end.y);
      if (dy < 0) {
        dy *= -1;
      }

      return (dx + dy);
    }
  }

  /**
   * Fungsi mengembalikan pergerakan Ghost.
   *
   * @return Arah pergerakan Ghost.
   */
  public int getMovement() {
    return movement;
  }
}
