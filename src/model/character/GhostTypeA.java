package model.character;

import java.util.Deque;
import java.util.PriorityQueue;

/**
 * Kelas berisi GhostTypeA yang menggunakan algoritma A* dalam mengejar player.
 */
public class GhostTypeA extends Ghost {
  private class Node {
    public Deque<Integer> path;
    public int cost;
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

  public int aStar() {
    PriorityQueue<Node> frontier;

    return 0;
  }
}
