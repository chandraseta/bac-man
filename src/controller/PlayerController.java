package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import model.Arena;
import model.character.Player;

/**
 * Kelas controller untuk Player.
 */
public class PlayerController extends Thread implements KeyListener {

  private Player player;
  private Thread pThread;
  private String threadName;
  public static boolean[] keyPressed = new boolean[256];

  public PlayerController() {
    player = new Player();
    Arrays.fill(keyPressed, false);
    this.threadName = "PlayerThread";
  }

  public PlayerController(int i, int j, String sprite, String threadName) {
    player = new Player(i, j, sprite);
    Arrays.fill(keyPressed, false);
    this.threadName = threadName;
  }

  @Override
  public void keyTyped(KeyEvent e) {
    keyPressed[e.getKeyCode()] = true;
    keyPressed[e.getKeyCode()] = false;
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
    if ((PlayerController.keyPressed[KeyEvent.VK_W])
        || (PlayerController.keyPressed[KeyEvent.VK_UP])) {
      return 1;
    } else if ((PlayerController.keyPressed[KeyEvent.VK_D])
        || (PlayerController.keyPressed[KeyEvent.VK_RIGHT])) {
      return 2;
    } else if ((PlayerController.keyPressed[KeyEvent.VK_S])
        || (PlayerController.keyPressed[KeyEvent.VK_DOWN])) {
      return 3;
    } else if ((PlayerController.keyPressed[KeyEvent.VK_A])
        || (PlayerController.keyPressed[KeyEvent.VK_LEFT])) {
      return 4;
    }
    else {
      return -1;
    }
  }

  public void run() {
    try {
      int direction = getDirection();
      if (direction == 1) {
        player.moveUp();
        if (Arena.getGrid(player.getI() - 1, player.getJ()).isAccessible()) {
          Player.setPlayerI(player.getI() - 1);
        }
        Player.setOrientation('n');
        player.setNewImage("\\assets\\bacbac_up.gif");
      } else if (direction == 2) {
        player.moveRight();
        if (Arena.getGrid(player.getI(), player.getJ() + 1).isAccessible()) {
          Player.setPlayerJ(player.getJ() + 1);
        }
        Player.setOrientation('e');
        player.setNewImage("\\assets\\bacbac_right.gif");
      } else if (direction == 3) {
        player.moveDown();
        if (Arena.getGrid(player.getI() + 1, player.getJ()).isAccessible()) {
          Player.setPlayerI(player.getI() + 1);
        }
        Player.setOrientation('s');
        player.setNewImage("\\assets\\bacbac_down.gif");
      } else if (direction == 4) {
        player.moveLeft();
        if (Arena.getGrid(player.getI(), player.getJ() - 1).isAccessible()) {
          Player.setPlayerJ(player.getJ() - 1);
        }
        Player.setOrientation('w');
        player.setNewImage("\\assets\\bacbac_left.gif");
      }
      Thread.sleep(500);
    }
    catch (InterruptedException e){
      e.printStackTrace();
    }
  }

  public void start() {
    if (pThread == null) {
      pThread = new Thread(this, threadName);
      pThread.start();
    }
  }

  public Player getPlayer() {
    return player;
  }
}
