package model.character;

import model.GameCharacter;
import model.VisibilityGraph;

import java.awt.*;
import java.util.Arrays;

/**
 * Kelas abstract Ghost yang menjadi dasar berbagai tipe Ghost.
 */
public abstract class Ghost extends GameCharacter {

  /**
   * Berisi informasi status Ghost
   * 0 = Normal
   * 1 = Vulnerable
   * 2 = Dead
   */
  protected int state;

  protected int [] fromGhostDistance;
  protected int [] fromGhostMovement;
  protected int [] toPlayerDistance;
  protected int [] toPlayerMovement;

  public Ghost(int i, int j, String sprite) {
    super(i, j, sprite);
    state = 0;
  }

  /**
   * Setter nilai state.
   * PREKONDISI: _state [0..2]
   *
   * @param _state Nilai state baru.
   */
  public void setState(int _state) {
    state = _state;
  }

  public boolean isNormal() {
    return state == 0;
  }

  public boolean isVulnerable() {
    return state == 1;
  }

  public boolean isDead() {
    return state == 2;
  }

  /**
   * Menggerakkan Ghost menuju Player saat kondisi normal.
   *
   * @return Nilai integer yang menentukan arah gerak Ghost pada status normal.
   */
  public abstract int moveTowardsPlayer();

  /**
   * Menggerakkan Ghost menjauhi Player saat Player dalam mode Super.
   *
   * @return Nilai integer yang menentukan arah gerak Ghost pada status vulnerable.
   */
  public int moveAwayFromPlayer() {
    // TODO: IMPLEMENT
    return 0;
  }

  /**
   * Menggerakkan Ghost menuju Base setelah dimakan oleh Player.
   *
   * @return Nilai integer yang menentukan arah gerak Ghost pada status dead.
   */
  public int returnToBase() {
    // TODO: IMPLEMENT
    return 0;
  }

  protected int checkLineOfSight() {
    if (this.position.x == Player.getPlayerI()) {
      if (this.position.y > Player.getPlayerJ()) {
        return 4;
      } else {
        return 2;
      }
    } else if (this.position.y == Player.getPlayerJ()) {
      if (this.position.x > Player.getPlayerI()) {
        return 1;
      } else {
        return 3;
      }
    } else {
      return 0;
    }
  }

  protected void addGhostToGraph() {
    fromGhostDistance = new int[VisibilityGraph.getNumOfLandmarks()];
    fromGhostMovement = new int[VisibilityGraph.getNumOfLandmarks()];
    Arrays.fill(fromGhostDistance, Integer.MAX_VALUE);
    Arrays.fill(fromGhostMovement, 0);

    int n = VisibilityGraph.getLandmarks().indexOf(this.position);
    if (n != -1) {
      fromGhostDistance[n] = 0;
      fromGhostMovement[n] = 0;
    } else {
      VisibilityGraph.findNeighbors(this.position, fromGhostDistance, fromGhostMovement);
    }
  }

  protected void addPlayerToGraph() {
    toPlayerDistance = new int[VisibilityGraph.getNumOfLandmarks()];
    toPlayerMovement = new int[VisibilityGraph.getNumOfLandmarks()];
    Arrays.fill(toPlayerDistance, Integer.MAX_VALUE);
    Arrays.fill(toPlayerMovement, 0);

    Point playerPos = new Point(Player.getPlayerI(), Player.getPlayerJ());
    int n = VisibilityGraph.getLandmarks().indexOf(playerPos);
    if (n != -1) {
      toPlayerDistance[n] = 0;
      toPlayerMovement[n] = 0;
    } else {
      VisibilityGraph.findNeighbors(playerPos, toPlayerDistance, toPlayerMovement);
      for (int movement: toPlayerMovement) {
        movement += 2;
        if (movement > 4)
          movement -= 4;
      }
    }
  
  }

}
