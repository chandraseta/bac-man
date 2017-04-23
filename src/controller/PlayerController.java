package controller;

import model.character.Player;

/**
 * Kelas controller untuk Player.
 */
public class PlayerController {
  private Player player;

  public int getDirection() {
    // TODO: Read from keyboard (WASD, UpLeftDownRight)
    return 0;
  }

  public void move() {
    int direction = getDirection();
    // TODO: Check if the targeted location is a wall, null movement if true
    if (direction == 1) {
      player.moveUp();
      Player.setPlayerI(player.getI()-1);
    }
    else if (direction == 2) {
      player.moveRight();
      Player.setPlayerJ(player.getJ()+1);
    }
    else if (direction == 3) {
      player.moveDown();
      Player.setPlayerI(player.getI()+1);
    }
    else {
      player.moveLeft();
      Player.setPlayerJ(player.getJ()-1);
    }
  }
}
