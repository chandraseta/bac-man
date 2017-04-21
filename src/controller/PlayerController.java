package controller;

import model.character.Player;

/**
 * Kelas controller untuk Player.
 */
public class PlayerController {
  private Player player;

  public int getDirection() {
    // TODO: Read from keyboard (WASD, UpLeftDownRight)
  }

  public void move() {
    int direction = getDirection();
    // TODO: Check if the targeted location is a wall, null movement if true
    if (direction == 1) {
      player.moveUp();
    }
    else if (direction == 2) {
      player.moveRight();
    }
    else if (direction == 3) {
      player.moveDown();
    }
    else {
      player.moveLeft();
    }
  }
}
