package controller;

import model.character.Player;

/**
 * Kelas controller untuk Player.
 */
public class PlayerController {
  private Player player;

  public PlayerController() {
    player = new Player();
  }

  public PlayerController(int i, int j, String sprite) {
    player = new Player(i, j, sprite);
  }

  public int getDirection() {
    // TODO: Read from keyboard (WASD, UpLeftDownRight)
    return 0;
  }

  public void move(int direction) {
    // TODO: Check if the targeted location is a wall, null movement if true
    if (direction == 1) {
      player.moveUp();
      Player.setPlayerI(player.getI()-1);
      Player.setOrientation('n');
    }
    else if (direction == 2) {
      player.moveRight();
      Player.setPlayerJ(player.getJ()+1);
      Player.setOrientation('e');
    }
    else if (direction == 3) {
      player.moveDown();
      Player.setPlayerI(player.getI()+1);
      Player.setOrientation('s');
    }
    else {
      player.moveLeft();
      Player.setPlayerJ(player.getJ()-1);
      Player.setOrientation('w');
    }
  }
}
