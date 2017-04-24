package controller;

import model.character.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

/**
 * Kelas controller untuk Player.
 */
public class PlayerController extends KeyAdapter {

  private Player player;
  public static boolean[] keyPressed = new boolean[256];

  public PlayerController() {
    player = new Player();
    Arrays.fill(keyPressed, false);
  }

  public PlayerController(int i, int j, String sprite) {
    player = new Player(i, j, sprite);
    Arrays.fill(keyPressed, false);
  }

  @Override
  public void keyPressed(KeyEvent e) {
    keyPressed[e.getKeyCode()] = true;
  }

  @Override
  public void keyReleased(KeyEvent e) {
    keyPressed[e.getKeyCode()] = false;
  }

  public int getDirection() {
    // TODO: Read from keyboard (WASD, UpLeftDownRight)
    if ((PlayerController.keyPressed[KeyEvent.VK_W])
        || (PlayerController.keyPressed[KeyEvent.VK_UP])) {
      return 1;
    }
    else if ((PlayerController.keyPressed[KeyEvent.VK_D])
        || (PlayerController.keyPressed[KeyEvent.VK_RIGHT])) {
      return 2;
    }
    else if ((PlayerController.keyPressed[KeyEvent.VK_S])
        || (PlayerController.keyPressed[KeyEvent.VK_DOWN])) {
      return 3;
    }
    else if ((PlayerController.keyPressed[KeyEvent.VK_A])
        || (PlayerController.keyPressed[KeyEvent.VK_LEFT])) {
      return 4;
    }
    return 0;
  }

  public void move(int direction) {
    // TODO: Check if the targeted location is a wall, null movement if true
    if (direction == 1) {
      player.moveUp();
      Player.setPlayerI(player.getI() - 1);
      Player.setOrientation('n');
      player.setNewImage("\\assets\\bacbac_up.gif");
    } else if (direction == 2) {
      player.moveRight();
      Player.setPlayerJ(player.getJ() + 1);
      Player.setOrientation('e');
      player.setNewImage("\\assets\\bacbac_right.gif");
    } else if (direction == 3) {
      player.moveDown();
      Player.setPlayerI(player.getI() + 1);
      Player.setOrientation('s');
      player.setNewImage("\\assets\\bacbac_down.gif");
    } else if (direction == 4) {
      player.moveLeft();
      Player.setPlayerJ(player.getJ() - 1);
      Player.setOrientation('w');
      player.setNewImage("\\assets\\bacbac_left.gif");
    }
  }
}
