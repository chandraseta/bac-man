package controller;

import model.Player;

/**
 * Kelas controller untuk Player.
 */
public class PlayerController {
  private Player player;

  public int getMovement() {
    // TODO: Read from keyboard (WASD, UpLeftDownRight)
  }

  /**
   *
   */
  public void move() {
    int movement = getMovement();
    // TODO: Check if the targeted location is a wall, null movement if true
    if (movement == 1) {
      player.setPosition(player.getX(), player.getY()-1);
    }
    else if (movement == 2) {
      player.setPosition(player.getX()+1, player.getY());
    }
    else if (movement == 3) {
      player.setPosition(player.getX(), player.getY()+1);
    }
    else {
      player.setPosition(player.getX()-1, player.getY());
    }
  }
}
