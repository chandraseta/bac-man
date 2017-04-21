package controller;

import model.character.Ghost;

/**
 * Kelas controller untuk Ghost.
 */
public class GhostController {

  private Ghost ghost;

  /**
   * Fungsi menentukan pergerakan Ghost selanjutnya.
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

  public void move() {
    int movement = nextMovement();
    if (movement == 1) {
      ghost.moveUp();
    }
    else if (movement == 2) {
      ghost.moveRight();
    }
    else if (movement == 3) {
      ghost.moveDown();
    }
    else {
      ghost.moveLeft();
    }
  }
}
