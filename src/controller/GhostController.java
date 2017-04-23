package controller;

import model.character.Blinky;
import model.character.Clyde;
import model.character.Ghost;
import model.character.Inky;
import model.character.Pinky;

/**
 * Kelas controller untuk Ghost.
 */
public class GhostController {

  private Ghost ghost;

  public GhostController(int i, int j, char type) {
    if (type == 'a') {
      ghost = new Blinky(i, j);
    } else if (type == 'b') {
      ghost = new Pinky(i, j);
    } else if (type == 'c') {
      ghost = new Inky(i, j);
    } else {
      ghost = new Clyde(i, j);
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
      if (ghost instanceof Blinky) {

      }
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
