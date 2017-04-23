package controller;

import model.Ghost;

/**
 * Kelas controller untuk Ghost.
 */
public class GhostController {

  private Ghost ghost;

  /**
   * Fungsi menentukan pergerakan Ghost selanjutnya.
   *
   * @return Nilai integer yang menentukan arah gerak Ghost berdasarkan kondisi Ghost.
   */
  public int nextMovement() {
    if (ghost.isNormal()) {
      return ghost.moveTowardsPlayer();
    } else if (ghost.isVulnerable()) {
      return ghost.moveAwayFromPlayer();
    } else {
      return ghost.returnToBase();
    }
  }

  /**
   * Method untuk menggerakkan Ghost berdasarkan kondisi Ghost.
   */
  public void move() {
    int movement = nextMovement();
    if (movement == 1) {
      ghost.setPosition(ghost.getX(), ghost.getY() - 1);
    } else if (movement == 2) {
      ghost.setPosition(ghost.getX() + 1, ghost.getY());
    } else if (movement == 3) {
      ghost.setPosition(ghost.getX(), ghost.getY() + 1);
    } else {
      ghost.setPosition(ghost.getX() - 1, ghost.getY());
    }
  }
}
