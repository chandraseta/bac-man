package controller;

import model.character.Ghost;
import model.character.GhostTypeA;
import model.character.GhostTypeB;

/**
 * Kelas controller untuk Ghost.
 */
public class GhostController {

  private Ghost ghost;

  public GhostController(int i, int j, char type) {
    // TODO: CHANGE IF NEW GHOST TYPE IS ADDED
    if (type == 'a') {
      ghost = new GhostTypeA(i, j);
    } else if (type == 'b') {
      ghost = new GhostTypeB(i, j);
    }
  }

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
      ghost.moveUp();
    } else if (movement == 2) {
      ghost.moveRight();
    } else if (movement == 3) {
      ghost.moveDown();
    } else {
      ghost.moveLeft();
    }
  }

  public Ghost getGhost() {
    return ghost;
  }
}
